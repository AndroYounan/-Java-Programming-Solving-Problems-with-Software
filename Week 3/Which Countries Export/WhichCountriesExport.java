
/**
 * Write a description of WhichCountriesExport here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import org.apache.commons.csv.*;
public class WhichCountriesExport {
   public void listExporters(CSVParser parser, String exportOfInterest) {
        //for each row in the CSV File
        for (CSVRecord record : parser) {
            //Look at the "Exports" column
            String export = record.get("Exports");
            //Check if it contains exportOfInterest
            if (export.contains(exportOfInterest)) {
                //If so, write down the "Country" from that row
                String country = record.get("Country");
                System.out.println(country);
            }
        }
   }
   public void whoExportsCoffee() {
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        listExporters(parser, "coffee");
    }
    public void tester(){
         FileResource fr = new FileResource();
         CSVParser parser = fr.getCSVParser();
         //method
         countryInfo(parser,"Nauru");
         //reset the parser before calling a new metod
         parser = fr.getCSVParser();
         listExportersTwoProducts(parser, "cotton", "flowers");
         parser = fr.getCSVParser();
         numberOfExporters(parser, "cocoa");
         parser = fr.getCSVParser();
         bigExporters(parser, "$999,999,999,999");
   }
   public void countryInfo (CSVParser parser, String country){
        boolean found = false;
        for (CSVRecord record : parser) {
            String count = record.get("Country");
            if (count.contains(country)) {
                String exports = record.get("Exports");
                String value = record.get("Value (dollars)");
                System.out.println(country+": "+exports+": "+value);
                found = true;
             }
        }
        if (found==false) System.out.println(country+" IS NOT FOUND");
    }
   public void listExportersTwoProducts(CSVParser parser, String exportItem1, String exportItem2){
       System.out.println("country that exports "+exportItem1+" and "+exportItem2+" is ");
       for (CSVRecord record : parser) {
            String Ex = record.get("Exports");
            if (Ex.contains(exportItem1) && Ex.contains(exportItem2)) {
                String country = record.get("Country");
                System.out.println(country);
            }
        }
    }
   public void numberOfExporters (CSVParser parser,String exportItem){
       int counter = 0;
       for (CSVRecord record : parser) {
            String Ex = record.get("Exports");
            if (Ex.contains(exportItem)) {
                counter++;
            }
        }
       System.out.println("number of contries that export "+exportItem+" = "+counter);
    }
   public void bigExporters (CSVParser parser, String amount){
       for (CSVRecord record : parser) {
            String country = record.get("Country");
            String value = record.get("Value (dollars)");
            if (value.length() > amount.length()) {
                System.out.println(country+" "+value);
            }
        }
    }
}