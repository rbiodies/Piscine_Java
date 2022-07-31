# Compilation:
# javac for java 8 DOES NOT create the directory, however it is required by subject
mkdir target

# this command compiles .java files into .class files, put them into target directory
javac -d ./target src/java/edu/school21/printer/*/*.java

cp -r src/resources target/.

# Build JAR-file:
# this command packs .class files, resources and a manifest file into a single images-to-chars-printer.jar file
jar cfm ./target/images-to-chars-printer.jar src/manifest.txt -C ./target .

# OPTIONAL: this command will display contents of the manifest file
# unzip -q -c ./target/images-to-chars-printer.jar META-INF/MANIFEST.MF

# Execute the JAR file:
#path to file is not needed since it's already packaged
java -jar target/images-to-chars-printer.jar . o

# Remove target directory:
rm -rf target