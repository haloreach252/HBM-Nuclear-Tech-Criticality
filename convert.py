import re

def convert_java_items(input_text):
    output_lines = []
    items_register = "ITEMS.register"
    
    # Regex to match item declarations
    pattern = re.compile(r'public static (\w+)\s+(\w+);')
    
    for match in pattern.finditer(input_text):
        item_type = match.group(1) # e.g. ItemRBMKPellet or Item
        item_name = match.group(2) # e.g. rbmk_pellet_zfb_am_mix
        
        # Convert variable name to uppercase format with underscores
        registry_name = item_name.upper()
        
        # Determine if it's a special item or a generic item
        line = f'public static final RegistryObject<Item> {registry_name} = \n'
        
        if item_type == "Item":
            line += f'  {items_register}("{item_name}", () -> new Item(new Item.Properties()));'
        else:
            line += f'  {items_register}("{item_name}", () -> new {item_type}(new Item.Properties()));'
            
        output_lines.append(line)
        
    return "\n\n".join(output_lines)
    
# Read input from file
input_file = "input_names.txt"
output_file = "output_names.txt"

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