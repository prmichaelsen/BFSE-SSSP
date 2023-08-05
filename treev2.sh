#!/bin/bash

# Function to print the tree structure
print_tree() {
    local dir=$1
    local prefix=$2

    # Check if the directory is hidden (starts with a dot) or should be ignored
    local base=$(basename "$dir")
    if [[ "$base" =~ ^\..* ]] || [[ "${ignored_dirs[*]}" =~ "$base" ]]; then
        return
    fi

    # Print the current directory or file
    if [ -d "$dir" ]; then
        echo "${prefix}- $base/"
    else
        echo "${prefix}- $base"
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

# Array to store ignored directory names
ignored_dirs=()

# Process command-line arguments
while [[ $# -gt 0 ]]; do
    case "$1" in
        -x)
            shift
            while [[ $# -gt 0 ]] && [[ ! "$1" =~ ^- ]]; do
                ignored_dirs+=("$1")
                shift
            done
            ;;
        *)
            target_dir="$1"
            shift
            ;;
    esac
done

# Check if the target directory exists
if [ ! -d "$target_dir" ]; then
    echo "Error: Directory '$target_dir' not found."
    exit 1
fi

# Start printing the tree structure
print_tree "$target_dir" ""
