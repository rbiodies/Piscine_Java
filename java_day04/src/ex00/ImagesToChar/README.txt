# Compilation:
# javac for java 8 DOES NOT create the directory, however it is required by subject
mkdir target

# this command compiles .java files into .class files, put them into target directory
javac -d ./target src/java/edu/school21/printer/*/*.java

# Launch app:
# Usage: app <char for white pixels> <char for black pixels> <full path to black & white bmp image>
# Example:
java -cp ./target edu.school21.printer.app.Program . 0 $PWD/../../../resources/it.bmp

# Remove target directory:
rm -rf target