# Compilation:
# javac for java 8 DOES NOT create the directory, however it is required by subject
mkdir lib target

# this command copies jar files into lib directory
cp ./src/resources/*.jar ./lib

# this command compiles .java files into .class files, put them into target directory
javac -cp ".:./lib/JColor-5.3.1.jar:./lib/jcommander-1.82.jar" -d ./target/ src/java/edu/school21/printer/*/*.java

# this command unpacks lib jar files to target folder, keeping packages and subdir
cd target ; jar xf ../lib/JColor-5.3.1.jar com ; jar xf ../lib/jcommander-1.82.jar com ; cd ..

# required by subject
cp -r src/resources target/.

# Build JAR-file:
#if not deleted, there will be a Invalid or corrupt jarfile error
rm -f ./target/images-to-chars-printer.jar

# this command packs .class files, resources and a manifest file into a single images-to-chars-printer.jar file
jar -cvfm ./target/images-to-chars-printer.jar src/manifest.txt -C target .

# OPTIONAL: this command will display contents of the manifest file
# unzip -q -c ./target/images-to-chars-printer.jar META-INF/MANIFEST.MF

# Execute the JAR file:
#path to file is not needed since it's already packaged
java -jar target/images-to-chars-printer.jar --white=BRIGHT_RED --black=BRIGHT_GREEN