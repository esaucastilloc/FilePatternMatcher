FilePatternMatcher 1.0 release
==========================================

The FilePatternMatcher is a java program that allows to find patterns in files using regular expressions. 

Download
---------

To download the source code please visit the following link: https://github.com/esaucastilloc/FilePatternMatcher


Requirements
------------

FilePatternMatcher requires Java version 1.7 and upper. You can download the Java latest version from:

https://java.com/en/download/manual.jsp

and install it in a suitable location.

Installation
-------------

1. Download the FilePatternMatcher-1.0.jar

2. Assuming that you have downloaded and configured Java 

3.-Create a folder e.g: FilePatternMatcher and save the jar downloaded in the step 1

4.-Place a txt or html file in the same folder where the FilePatternMatcher-1.0.jar was placed. The content of the file
should be:

[id]':'[content]

where id is the unique line identifier int64 and  the 'content' is a string in UTF-8 format with embedded newlines escaped as '\n'.

5.- Create the "input" txt file in the folder created in the step number 3, you can give the name you prefere to this file, the content of the file must be the following:
file=[here indicate the path of the file of the step number 4]
regex=[here indicate a regular expression e.g: (.)+]

It is important to notice that "file=" can be the path of a file or a folder containing many files

Execution
-----------------
Once you finilized the installation steps, open a command line terminal, go to the folder were you placed the FilePatternMatcher-1.0.jar and "input.txt" file

type the following command: java -jar FilePatternMatcher-1.0.jar input.txt 

If the file/folders indicated in the input.txt are found and there are lines matching the regex configured you will be able to see the following content in your terminal:


File: 9.html Number of lines: 2 Lines: [8689782839, 8689816109]

Problems and Suggestions
------------------------

You are welcome to contact me at: esaucastilloc@hotmail.com