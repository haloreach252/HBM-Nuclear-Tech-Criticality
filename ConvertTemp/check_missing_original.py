import re

def extract_full_names(full_text):
    """
    Extracts item variable names from the full ModItems.java file using the static declarations.
    This pattern matches lines like:
       public static Item ingot_u233;
       public static ItemCatalyst ams_catalyst_aluminium;
    """
    # Regex: matches "public static" followed by a type and then the variable name, ending with a semicolon.
    pattern = re.compile(r'public static\s+\w+\s+(\w+)\s*;')
    names = set()
    for match in pattern.finditer(full_text):
        names.add(match.group(1).upper())
    return names

def extract_converted_names(converted_text):
    """
    Extracts item names from the converted file which should have lines like:
       public static final RegistryObject<Item> INGOT_U233 = registerWithCategory(...);
    """
    pattern = re.compile(r'public static final RegistryObject<Item>\s+(\w+)\s*=')
    names = set()
    for match in pattern.finditer(converted_text):
        names.add(match.group(1).upper())
    return names

def main():
    # Adjust file names as necessary.
    full_file = "OriginalModItems.java"           # Original file with all item declarations (~1800 items)
    converted_file = "ConvertedItems.java"  # Converted file generated by your script (~704 items)
    missing_file = "missing_items.txt"      # File where missing names will be written

    try:
        with open(full_file, "r", encoding="utf-8") as f:
            full_text = f.read()
        with open(converted_file, "r", encoding="utf-8") as f:
            converted_text = f.read()
        
        full_names = extract_full_names(full_text)
        converted_names = extract_converted_names(converted_text)
        
        # Determine the names present in the original but missing in the converted file
        missing_names = sorted(list(full_names - converted_names))
        
        if missing_names:
            print("Missing item names:")
            for name in missing_names:
                print(name)
                
            with open(missing_file, "w", encoding="utf-8") as f:
                for name in missing_names:
                    f.write(name + "\n")
                    
            print(f"\nMissing names have been written to '{missing_file}'.")
        else:
            print("No missing item names found.")
            
    except FileNotFoundError as e:
        print(f"Error: {e.filename} not found.")
    except Exception as e:
        print(f"An error occurred: {e}")

if __name__ == "__main__":
    main()
