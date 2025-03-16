import re

def convert_java_items(input_text):
    output_lines = []
    
    # Extract the content of the initializeItem method.
    # This regex assumes the method signature is "initializeItem(...)" and uses a DOTALL search.
    init_match = re.search(r'initializeItem\s*\([^)]*\)\s*{(.*?)}', input_text, re.DOTALL)
    if not init_match:
        print("initializeItem method not found.")
        return ""
    
    init_content = init_match.group(1)
    
    # Regex to match item initialization assignments.
    # It captures:
    #   group(1): the variable name
    #   group(2): the item class (e.g. Item or ItemCatalyst)
    #   group(3): the constructor arguments (if any)
    #   group(4): the rest of the chained method calls (from which we extract creative tab and max stack size)
    pattern = re.compile(r'\s*(\w+)\s*=\s*new\s+(\w+)\s*\(([^)]*)\)(.*?);', re.DOTALL)
    
    for match in pattern.finditer(init_content):
        var_name = match.group(1)
        class_name = match.group(2)
        constructor_args = match.group(3).strip()
        chain_calls = match.group(4)
        
        # Extract the creative tab call e.g. ".setCreativeTab(MainRegistry.partsTab)"
        creative_tab_match = re.search(r'\.setCreativeTab\s*\(\s*MainRegistry\.(\w+)\s*\)', chain_calls)
        if creative_tab_match:
            tab = creative_tab_match.group(1)  # e.g., "partsTab" or "controlTab"
            # Remove a trailing "Tab" if present, then convert to uppercase to match enum naming
            category = tab[:-3].upper() if tab.endswith("Tab") else tab.upper()
        else:
            # Default category if none is found
            category = "PARTS"
        
        # Extract max stack size if provided (e.g. ".setMaxStackSize(1)")
        max_stack_match = re.search(r'\.setMaxStackSize\s*\(\s*(\d+)\s*\)', chain_calls)
        if max_stack_match:
            max_stack = max_stack_match.group(1)
            properties_str = f'new Item.Properties().maxStackSize({max_stack})'
        else:
            properties_str = 'new Item.Properties()'
        
        # Build the new constructor call.
        # If there are constructor arguments, append a comma before adding the Item.Properties argument.
        if constructor_args:
            new_constructor_args = constructor_args + ", " + properties_str
        else:
            new_constructor_args = properties_str
        
        new_item_call = f'new {class_name}({new_constructor_args})'
        
        # Field name: uppercase version of the variable name
        field_name = var_name.upper()
        # Registry name: lower-case version of the variable name (as used in the registration string)
        registry_name = var_name.lower()
        
        # Build the final registration line using registerWithCategory
        line = (f'public static final RegistryObject<Item> {field_name} = registerWithCategory('
                f'"{registry_name}", () -> {new_item_call}, ItemCategory.{category});')
        output_lines.append(line)
    
    return "\n\n".join(output_lines)

# File names (adjust as needed)
input_file = "ModItems.java"
output_file = "ConvertedItems.java"

try:
    with open(input_file, "r", encoding="utf-8") as file:
        input_code = file.read()
        
    converted_code = convert_java_items(input_code)
    
    with open(output_file, "w", encoding="utf-8") as file:
        file.write(converted_code)
        
    print(f"Conversion complete! Check '{output_file}' for the result.")

except FileNotFoundError:
    print(f"Error: The file '{input_file}' was not found.")
except Exception as e:
    print(f"An error occurred: {e}")
