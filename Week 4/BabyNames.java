
/**
 * Write a description of BabyNames here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import org.apache.commons.csv.*;
import java.io.*;
public class BabyNames {
    public void totalBirths (FileResource fr) {
        int totalBirths = 0;
        int totalBoys = 0;
        int totalGirls = 0;
        int girlsnames=0;
        int boysnames=0;
        for (CSVRecord rec : fr.getCSVParser(false)) {
            int numBorn = Integer.parseInt(rec.get(2));
            totalBirths += numBorn;
            if (rec.get(1).equals("M")) {
                totalBoys += numBorn;boysnames++;
            }
            else {
                totalGirls += numBorn;girlsnames++;
            }
        }
        System.out.println("total births = " + totalBirths);
        System.out.println("females = " + totalGirls);
        System.out.println("males = " + totalBoys);
        System.out.println("girls names = " + girlsnames);
        System.out.println("boys names = " + boysnames);
    }
    public void testTotalBirths () {
        FileResource fr = new FileResource();//"data/yob1900.csv"//"data/yob1905.csv"
        totalBirths(fr);
    }
   
    public int getRank (int year, String name, String gender){
       String fname = "data/yob"+year+".csv";
       FileResource fr = new FileResource (fname);
       CSVParser parser = fr.getCSVParser (false);
       int girlrank =0; int boyrank=0; int rank=0;        
       for (CSVRecord record : parser){
            String getname = record.get(0);
            String getgender = record.get(1);
            if (getgender.equals("F")){girlrank++;}
            if (getgender.equals("M")){boyrank++;}
            if(getname.equals(name) && gender.equals("F")){rank = girlrank;break;}
            if(getname.equals(name) && gender.equals("M")){rank = boyrank;}
           }
       return rank;
    }
    public void testgetRank(){
        int rank = getRank (1972,"Susan","F");
        System.out.println(" The name rank is "+rank);
    }
    
    public String getName(int year,int rank, String gender){
       String fname = "data/yob"+year+".csv";
       FileResource fr = new FileResource (fname);
       CSVParser parser = fr.getCSVParser (false);
       int girlrank =0; int boyrank=0; String name="";  
       for (CSVRecord record : parser){
            String getname = record.get(0);
            String getgender = record.get(1);
            if (getgender.equals("F")){girlrank++;}
            if (getgender.equals("M")){boyrank++;}
            if(girlrank==rank && gender.equals("F")){name = getname;break;}
            if(boyrank==rank && gender.equals("M")){name = getname;}
           }
       return name;
    }
    public void testgetName(){
        String name = getName (2014,24 ,"F");
        System.out.println(" The name is "+name);
        }
        
    public void whatIsNameInYear(String name, int year, int newyear, String gender){
    int rank = getRank(year,name,gender);//(1972,"Susan","F");
    String newname = getName(newyear,rank,gender);//(2014,rank,"F");
    System.out.println(name+" born in "+year+" would be "+newname+" in "+newyear);
    }
    public void testwhatIsNameInYear(){
    whatIsNameInYear("Walter", 1915, 1914, "M");
    }
    
    public void yearOfHighestRank(String name,String gender){
    DirectoryResource dr = new DirectoryResource();
    int highestrank = 0; String filewithhighestrank="";
    for (File f : dr.selectedFiles()) {
        FileResource fr = new FileResource(f);
        CSVParser parser = fr.getCSVParser (false);
        int girlrank =0; int boyrank=0; int rank=0;    
       for (CSVRecord record : parser){
            String getname = record.get(0);
            String getgender = record.get(1);
            if (getgender.equals("F")){girlrank++;}
            if (getgender.equals("M")){boyrank++;}
            if(getname.equals(name) && gender.equals("F")){rank = girlrank;break;}
            if(getname.equals(name) && gender.equals("M")){rank = boyrank;}
           }
           if(rank==0){continue;}
       if (highestrank==0){highestrank=rank;filewithhighestrank=f.getName().substring(3,7);System.out.println(highestrank+" "+filewithhighestrank);}
       if(rank<=highestrank){highestrank=rank;filewithhighestrank=f.getName().substring(3,7);System.out.println(highestrank+" "+filewithhighestrank);}
    }
    System.out.println("The ((LATEST)) year with the highest rank is "+ filewithhighestrank);
    }
    public void testyearOfHighestRank(){
    yearOfHighestRank("Genevieve","F");
    }
    
    public double getAverageRank(String name, String gender){
         DirectoryResource dr = new DirectoryResource();  double counter=0; double averagerank; int rank=0;
         for (File f : dr.selectedFiles()) {
            FileResource fr = new FileResource(f);
            CSVParser parser = fr.getCSVParser (false);
            int girlrank =0; int boyrank=0; 
               for (CSVRecord record : parser){
                   String getname = record.get(0);
                   String getgender = record.get(1);
                   if (getgender.equals("F")){girlrank++;}
                   if (getgender.equals("M")){boyrank++;}
                   if(getname.equals(name) && gender.equals("F")){rank += girlrank;break;}
                   if(getname.equals(name) && gender.equals("M")){rank += boyrank;}
           }
           counter++;
       }
       averagerank = rank / counter;return averagerank;
    }
    public void testgetAverageRank(){
    double averagerank = getAverageRank("Robert","M");
    System.out.println("The average rank is "+averagerank);
    }
    
    public int getTotalBirthsRankedHigher(int year,String name,String gender){
        String fname = "data/yob"+year+".csv";
        FileResource fr = new FileResource (fname);
        CSVParser parser = fr.getCSVParser (false);
        int girlrank =0; int boyrank=0; int rank=0; int girlsbirths=0; int boysbirths=0; int totalbirths=0;
        for (CSVRecord record : parser){
            String getname = record.get(0);
            String getgender = record.get(1);
            int birthsnum = Integer.parseInt(record.get(2));
            if(getname.equals(name) && gender.equals("F")){totalbirths=girlsbirths;break;}
            if(getname.equals(name) && gender.equals("M")){totalbirths=boysbirths;}
            if (getgender.equals("F")){girlsbirths+=birthsnum;}
            if (getgender.equals("M")){boysbirths+=birthsnum;}
            
           }
           return totalbirths;
    }
    public void testgetTotalBirthsRankedHigher(){
    int totalbirths = getTotalBirthsRankedHigher(1990,"Emily","F");
    System.out.println("The total births ranked higher is "+totalbirths);
    }
}