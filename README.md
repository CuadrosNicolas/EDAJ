# External Dependency Analyzer for Java A.K.A EDAJ

This program simply allow to analyse a maven java project to identify how
much a project contain external dependencies.

For now the program only identify when types used in a program come from the project
himself or an external library declared in the pom file(and which one).

## HOW TO USE

```
chmod -x run.sh
./run.sh {project path}
```

The project path must contain a 'pom.xml' file and 'src' directory. You have to indicate the path without the end '/'.

The program will output a 'output.csv' file in your current working directory.
This output is formated with space as separator and with rows as follow :

FILE_PATH METHOD_NAME NUMBER_OF_ANALYSED_FILE SKIPPED_IDENTIFICATION LIBRARIES...

Skipped identification is the number of identifications that have failed (unknown type,
java.* type,primitive type).
Librairies are a list of the different librairies extracted from the pom.xml file.