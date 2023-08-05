#!/bin/bash
# gpt https://chat.openai.com/share/b8020629-a81b-4470-91b9-35a25b8a5be5

# Function to print the tree structure
print_tree() {
    local dir=$1
    local prefix=$2

    # Print the current directory or file
    if [ -d "$dir" ]; then
        echo "${prefix}- $(basename "$dir")/"
    else
        echo "${prefix}- $(basename "$dir")"
    fi

    # Increase the prefix for subdirectories
    local new_prefix="${prefix}  |"

    # Recursively list subdirectories and files
    while IFS= read -r file; do
        print_tree "$file" "$new_prefix"
    done < <(find "$dir" -mindepth 1 -maxdepth 1 -not -name '.*' -print)
}

# Main script
if [ -n "$1" ]; then
    # If a directory is provided as an argument, use it
    target_dir="$1"
else
    # If no directory is provided, use the current directory
    target_dir="."
fi

# Check if the target directory exists
if [ ! -d "$target_dir" ]; then
    echo "Error: Directory '$target_dir' not found."
    exit 1
fi

# Start printing the tree structure
print_tree "$target_dir" ""

