
/**
 * Write a description of CSVMin here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import org.apache.commons.csv.*;
import java.io.*;
public class CSVMin {
    public CSVRecord coldestHourInFile(CSVParser parser) {
        //start with largestSoFar as nothing
        CSVRecord lowestSoFar = null;
        //For each row (currentRow) in the CSV File
        for (CSVRecord currentRow : parser) {
            // use method to compare two records
            lowestSoFar = getLowestOfTwoTemps (currentRow, lowestSoFar);
        }
        //The largestSoFar is the answer
        return lowestSoFar;
    }

    public void testColdestHourInFile () {
        FileResource fr = new FileResource();
        CSVRecord lowest = coldestHourInFile(fr.getCSVParser());
        System.out.println("coldest temperature was " + lowest.get("TemperatureF") +
                   " at " + lowest.get("DateUTC"));
    }

    

    public CSVRecord getLowestOfTwoTemps (CSVRecord currentRow, CSVRecord lowestSoFar) {
        //If largestSoFar is nothing
        if (lowestSoFar == null) {
            lowestSoFar = currentRow;
        }
        //Otherwise
        else {
            double currentTemp = Double.parseDouble(currentRow.get("TemperatureF"));
            double lowestTemp = Double.parseDouble(lowestSoFar.get("TemperatureF"));
            //Check if currentRow’s temperature > largestSoFar’s
            if (currentTemp < lowestTemp) {
                //If so update largestSoFar to currentRow
                lowestSoFar = currentRow;
            }
        }
        return lowestSoFar;
    }
    public CSVRecord getLowestOfTwoH (CSVRecord currentRow, CSVRecord lowestSoFar) {
        //If largestSoFar is nothing
        if (lowestSoFar == null) {
            lowestSoFar = currentRow;
        }
        //Otherwise
        else {if (!currentRow.get("Humidity").equals("N/A")){
            double currentTemp = Double.parseDouble(currentRow.get("Humidity"));
            double lowestTemp = Double.parseDouble(lowestSoFar.get("Humidity"));
            //Check if currentRow’s temperature > largestSoFar’s
            if (currentTemp < lowestTemp) {
                //If so update largestSoFar to currentRow
                lowestSoFar = currentRow;
               }
            }
        }
        return lowestSoFar;
    }

    
    public String fileWithColdestTemperature(){
        File f1=null;
        String coldestTempOnThatDay="";
        CSVRecord lowestSoFar = null;
        String coldestFile="";
        DirectoryResource dr = new DirectoryResource();
        // iterate over files
        for (File f : dr.selectedFiles()) {
            FileResource fr = new FileResource(f);
            // use method to get largest in file.
            CSVRecord currentRow = coldestHourInFile(fr.getCSVParser());
            // use method to compare two records
            if (lowestSoFar == null) {
            lowestSoFar = currentRow;
            coldestFile = f.getName();
            }
            //Otherwise
            else {if (!currentRow.get("TemperatureF").equals("-9999")){
            double currentTemp = Double.parseDouble(currentRow.get("TemperatureF"));
            double lowestTemp = Double.parseDouble(lowestSoFar.get("TemperatureF"));
            //Check if currentRow’s temperature > largestSoFar’s
            if (currentTemp < lowestTemp) {
                //If so update largestSoFar to currentRow
                lowestSoFar = currentRow;
                coldestFile = f.getName();
                f1=f;
                FileResource fr1 = new FileResource(f.getPath());
                CSVParser parser = fr1.getCSVParser();
                CSVRecord coldestRECTempOnThatDay = coldestHourInFile(parser);
                coldestTempOnThatDay = coldestRECTempOnThatDay.get("TemperatureF");
                }
            }
        }
		}
	
	System.out.println("Coldest day was in file "+coldestFile);
	System.out.println("Coldest temperature on that day was "+ coldestTempOnThatDay);
	System.out.println("All the Temperatures on the coldest day were: ");
	FileResource fr1 = new FileResource(f1.getPath());
	for (CSVRecord current : fr1.getCSVParser()) {
            System.out.println(current.get("DateUTC") + ": " + current.get("TemperatureF"));
        }
        return coldestFile;
	}
    public void testFileWithColdestTemperature(){
        fileWithColdestTemperature();
        
	}
	 
    public CSVRecord lowestHumidityInFile(CSVParser parser){
        CSVRecord lowestSoFar = null;
        //For each row (currentRow) in the CSV File
        for (CSVRecord currentRow : parser) {
            // use method to compare two records
            lowestSoFar = getLowestOfTwoH(currentRow, lowestSoFar);
        }
        //The largestSoFar is the answer
        return lowestSoFar;
    }
    public void testLowestHumidityInFile(){
        FileResource fr = new FileResource();
        CSVRecord lowest = lowestHumidityInFile(fr.getCSVParser());
        System.out.println("Lowest Humidity was " + lowest.get("Humidity") +
                   " at " + lowest.get("DateUTC"));
    }
    
    public CSVRecord lowestHumidityInManyFiles(){
	CSVRecord lowestSoFar = null;
	DirectoryResource dr = new DirectoryResource();
	// iterate over files
	for (File f : dr.selectedFiles()) {
			FileResource fr = new FileResource(f);
			// use method to get largest in file.
			CSVRecord currentRow = lowestHumidityInFile(fr.getCSVParser());
			// use method to compare two records
			lowestSoFar = getLowestOfTwoH(currentRow, lowestSoFar);
	}
	//The largestSoFar is the answer
	return lowestSoFar;
    }
    public void testLowestHumidityInManyFiles(){
    CSVRecord lowest = lowestHumidityInManyFiles();
    System.out.println("Lowest Humidity was " + lowest.get("Humidity") +
                   " at " + lowest.get("DateUTC"));
    }
    
    public double averageTemperatureInFile(CSVParser parser){
    int counter = 0; double temp=0; double averageTemp=0;
    for (CSVRecord record : parser){
    temp = temp + Double.parseDouble(record.get("TemperatureF"));
    counter ++;
    }
    return averageTemp = temp / counter;
    }
    public void testAverageTemperatureInFile(){
    FileResource fr = new FileResource();
    double averageTemperatureInFile = averageTemperatureInFile(fr.getCSVParser());
    System.out.println("Average temperature in file is "+averageTemperatureInFile);
    }
    
    public double averageTemperatureWithHighHumidityInFile(CSVParser parser, int value){
    int counter = 0; double temp=0; double averageTemp=0; double humidity=0;
    for (CSVRecord record : parser){
        humidity = Double.parseDouble(record.get("Humidity"));
        if (humidity>= value){
          temp = temp + Double.parseDouble(record.get("TemperatureF"));
          counter ++;
        }
    }
    if (temp==0) {System.out.println("No temperatures with that humidity");}
    return averageTemp = temp / counter;
    }
    public void testAverageTemperatureWithHighHumidityInFile() {
    FileResource fr = new FileResource();
    double averageTemperatureInFileWH = averageTemperatureWithHighHumidityInFile(fr.getCSVParser(),80);
    System.out.println("Average temperatureWH in file is "+averageTemperatureInFileWH);
    }
    }