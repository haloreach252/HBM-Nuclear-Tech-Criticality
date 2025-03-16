import re

# Mapping from creative tab names to item categories
tab_to_category = {
    "partsTab": "PARTS",
    "controlTab": "CONTROL",
    "templateTab": "TEMPLATE",
    "blockTab": "BLOCK",
    "machineTab": "MACHINE",
    "nukeTab": "NUKE",
    "missileTab": "MISSILE",
    "weaponTab": "WEAPON",
    "consumableTab": "CONSUMABLE"
}

# Read the original Java file
with open("OriginalModItems.java", "r") as file:
    content = file.read()

# Regex to capture item assignments.
# This expects lines like:
#   nuclear_waste = new ItemNuclearWaste(...).setUnlocalizedName("nuclear_waste")
#       .setCreativeTab(MainRegistry.partsTab)...;
pattern = re.compile(r'(\w+)\s*=\s*new\s+(\w+)\(([^)]*)\)([^;]*);')

registration_lines = []

for match in pattern.finditer(content):
    var_name = match.group(1)              # e.g., nuclear_waste
    item_class = match.group(2)              # e.g., ItemNuclearWaste
    constructor_args = match.group(3).strip()  # whatever is inside the parentheses
    method_chain = match.group(4)            # all the chained method calls

    # Initialize values for the properties we'll extract.
    unlocalized_name = None
    creative_tab = None
    max_stack = None
    extra_methods = []

    # Define methods we want to remove (their functionality is replaced)
    remove_methods = {"setUnlocalizedName", "setCreativeTab", "setTextureName", "setMaxStackSize"}
    
    # Find all method calls (with or without arguments)
    method_calls = re.findall(r'\.(\w+)(\([^)]*\))?', method_chain)
    for method, args in method_calls:
        if method == "setUnlocalizedName":
            # Capture the string literal inside the parentheses
            unlocalized_name = args.strip("()\"")
        elif method == "setCreativeTab":
            # Expecting something like (MainRegistry.partsTab)
            m_tab = re.search(r'MainRegistry\.(\w+)', args)
            if m_tab:
                creative_tab = m_tab.group(1)
        elif method == "setMaxStackSize":
            m_stack = re.search(r'\d+', args)
            if m_stack:
                max_stack = m_stack.group(0)
        else:
            # If it is not one of the removed methods, we keep it as an extra chain
            if method not in remove_methods:
                extra_methods.append("." + method + (args if args else ""))

    # Use the unlocalized name if found; otherwise, default to the variable name (lowercase)
    if not unlocalized_name:
        unlocalized_name = var_name.lower()
    
    # Convert British spelling "aluminium" to American "aluminum"
    unlocalized_name_converted = unlocalized_name.replace("aluminium", "aluminum")
    # Use the converted unlocalized name (in uppercase) as the variable name
    var_name_converted = unlocalized_name_converted.upper()

    # Determine the item category based on the creative tab.
    category = "TEMPLATE"  # default if no creative tab is found
    if creative_tab:
        if creative_tab in tab_to_category:
            category = tab_to_category[creative_tab]
        else:
            # Fallback: remove "Tab" suffix and uppercase
            if creative_tab.endswith("Tab"):
                category = creative_tab[:-3].upper()
            else:
                category = creative_tab.upper()

    # Prepare the Item.Properties() call (include .stacksTo if setMaxStackSize was provided)
    if max_stack:
        properties_str = f"new Item.Properties().stacksTo({max_stack})"
    else:
        properties_str = "new Item.Properties()"

    # Build the new constructor call.
    # If there were original constructor arguments, append a comma and the properties.
    new_constructor = f"new {item_class}("
    if constructor_args:
        new_constructor += constructor_args + ", " + properties_str
    else:
        new_constructor += properties_str
    new_constructor += ")"
    
    # Append any extra method calls (for example, .makeCan()) that are not removed
    for extra in extra_methods:
        new_constructor += extra

    # Convert any float literals from e.g. 1.15F to 1.15f
    new_constructor = re.sub(r'(\d+\.\d+)F\b', lambda m: m.group(1) + "f", new_constructor)
    
    # Build the full registration line
    registration_line = (
        f'public static final RegistryObject<Item> {var_name_converted} = '
        f'registerWithCategory("{unlocalized_name_converted}", () -> {new_constructor}, '
        f'ItemCategory.{category});'
    )
    
    registration_lines.append(registration_line)

# Write the converted registration lines to a new Java file.
with open("ConvertedModItems.java", "w") as out_file:
    out_file.write("// Converted registration lines\n")
    for line in registration_lines:
        out_file.write(line + "\n")

print("Conversion complete. Check ConvertedModItems.java for the output.")
