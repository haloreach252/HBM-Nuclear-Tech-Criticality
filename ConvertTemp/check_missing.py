import re

def extract_full_names(full_text):
    """
    Extracts item variable names from the full file.
    This looks for patterns where a variable is assigned a new instance,
    e.g. "ingot_u233 = new Item()..."
    """
    # Regex to capture the variable name before "= new"
    pattern = re.compile(r'\s*(\w+)\s*=\s*new')
    names = set()
    for match in pattern.finditer(full_text):
        # Convert to uppercase to match the conversion output
        names.add(match.group(1).strip().upper())
    return names

def extract_converted_names(converted_text):
    """
    Extracts item names from the converted file.
    It assumes each converted registration is of the form:
    public static final RegistryObject<Item> <NAME> = registerWithCategory(...
    """
    pattern = re.compile(r'public static final RegistryObject<Item>\s+(\w+)\s*=')
    names = set()
    for match in pattern.finditer(converted_text):
        names.add(match.group(1).strip().upper())
    return names

def main():
    # Adjust these file names as needed
    full_file = "ModItems.java"          # Original file with all item initializations
    converted_file = "ConvertedItems.java"  # File generated by your conversion script
    missing_file = "missing_items.txt"   # File to output missing names
    
    try:
        with open(full_file, "r", encoding="utf-8") as f:
            full_text = f.read()
        with open(converted_file, "r", encoding="utf-8") as f:
            converted_text = f.read()
            
        full_names = extract_full_names(full_text)
        converted_names = extract_converted_names(converted_text)
        
        # Identify names that are in the full file but missing from the conversion
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
