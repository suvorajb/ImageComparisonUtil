# ImageComparisonUtil
A image utility program to compare images to find visual similarity and output the data into a csv file

## How to run the program in any machine (Windows, MacOS, Linux)
The program is a Java application and it requires Java/JRE to be installed in your Operating System. Please download Java if this is not there already in your PC from https://www.java.com/en/download/
Once Java is installed follow the following steps to run the program
 
### Steps:

1) Go to **target** folder and download the program (ImageComparisonUtil-1.0.0-SNAPSHOT.jar) and keep in a folder in your PC. Always download the latest file if many version of the jar exists.
2) Open command prompt or shell in this location and type the following command to run it
```
java -jar target/ImageComparisonUtil-1.0.0-SNAPSHOT.jar C:/temp/imgcmpare/images.csv C:/temp/imgcmpare/output.csv
```
Where `C:/temp/imgcmpare/images.csv` is the location of the input CSV and `C:/temp/imgcmpare/output.csv` is the location of output file. Change the path or filename according to your location and filename. Sample input csv file format is given below:
```
**image1** **image2**
C:/temp/imgcmpare/aa.png C:/temp/imgcmpare/ba.png
C:/temp/imgcmpare/ac.jpg C:/temp/imgcmpare/ac.gif
```
3) After successful run of the progranm the output csv file will be created in the location and will have the following format
```
**image1** **image2** similarity elapsed
C:/temp/imgcmpare/aa.png C:/temp/imgcmpare/ba.png 0 104ms
C:/temp/imgcmpare/ac.jpg C:/temp/imgcmpare/ac.gif 0.017 22ms
```
pls note elapsed time will be shown in millisecond

## Working with Image Comparison Util [Developer's Only]
1) The application uses Maven build tool to compile and package the jar file. Use `mvn package` to package the program into a jar file. 
2) Before importing the program into your Eclipse use `mvn eclipse:eclipse` to make the project Eclipse compatible.

## File Structure and logic flow [Developer's Only]
1) **LoBlawSREImageCompareUtilApp** is the main Java class which does all the processing. It reads the input filename and outputfilename from the program arguments supplied as command line arguments.
2) It first reads the input CSV file and based on the delimiter (' ') blank it separates the image file location of a pair.
3) It uses the **ImgVisualCmprUtil.compareVisualSimilarity()** method to find the similarity value and also the processing time and return in a String array.
4) The method **compareVisualSimilarity()** internally uses **java.awt.image.PixelGrabber** libary to read pixel of each image file and then compare them to see the match.
5) Once a pair's similarity and elapsed values are calculated it is placed in bean (**ImageDataBean**) to store the record.
6) When all pairs of image records are processed this is written in the output CSV file using **CsvUtil** class.
7) In **test** folder sample 4 images and one corresponding input csv file is present for local testing. Please update the path of the image in the input csv file before running.

## Flow Diagram
![Flow Diagram](https://github.com/suvorajb/ImageComparisonUtil/blob/master/Flow%20Diagram.png)
