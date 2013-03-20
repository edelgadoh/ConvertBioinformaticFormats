========================
BUILD OUTPUT DESCRIPTION
========================

When you build an Java application project that has a main class, the IDE
automatically copies all of the JAR
files on the projects classpath to your projects dist/lib folder. The IDE
also adds each of the JAR files to the Class-Path element in the application
JAR files manifest file (MANIFEST.MF).

To run the project from the command line, go to the dist folder and
type the following:

java -jar "convertBioinformaticFormats.jar" 

To distribute this project, zip up the dist folder (including the lib folder)
and distribute the ZIP file.


Usage: java -jar <option> <params[]>
<option>
1 - convert from MAF to MAF_tab format
2 - convert from MAF_tab to Gbrowse_Syn format
3 - convert from Blast to Gbrowse_Syn format
4 - convert from MAF to Clustalw format
5 - convert from MAF to GBrowse_Syn format

Option 1:
Usage: java -jar 1 <input_file_maf_format> > output_file_tabs
Example: java -jar 1 file.maf > file.maf_tab

Option 2:
Usage: java -jar 2 <query_1> <query_2> <input_file_maf_tabs> > output_file
Example: java -jar 2 cane sorghum file.maf_tab > file.gbrowse_syn

Option 3:
Usage: java -jar 3 <query_database> <subject_database> <input file> <output file>
Example: java -jar 3 sorghum cane file.blast_tabs file.gbrowse_syn

Option 4:
Usage: java -jar 4 <input_file_maf> <output_file>
Usage: java -jar 4 file.maf file.clustalw

Option 5:
Usage: java -jar 5 <query_1> <query_2> <input_file_maf> <output_file>
Example: java -jar 5 cane sorghum file.maf file.gbrowse_syn


If you need more details about this script, please contact me at edychrist@gmail.com
