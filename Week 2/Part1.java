
/**
 * Write a description of Part1 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
public class Part1 {
   public int findStopCodon(String dna, int startIndex, String stopCodon) {
        int currIndex = dna.indexOf(stopCodon, startIndex + 3);
        while(currIndex != -1) {
            int diff = currIndex - startIndex;
            if(diff % 3 == 0) {
                return currIndex;
            } else {
                currIndex = dna.indexOf(stopCodon, currIndex + 1);
            }
        }

        return dna.length();
    }
   public void testFindStopCodon() {
        String dna = "xxxyyyzzzTAAxxxyyyzzzTAAxx";
        
        int dex = findStopCodon(dna, 0,"TAA");
        System.out.println(dex);

        dex = findStopCodon(dna, 9,"TAA");
        System.out.println(dex);

        dex = findStopCodon(dna, 1,"TAA");
        System.out.println(dex);

        dex = findStopCodon(dna, 0,"TAG");
        System.out.println(dex);

    }
   public String findGene(String dna, int index) {
    int startIndex = dna.indexOf("ATG", index);
    if(startIndex == -1) {
            return "";
    }

    int taaIndex = findStopCodon(dna, startIndex, "TAA");
    int tagIndex = findStopCodon(dna, startIndex, "TAG");
    int tgaIndex = findStopCodon(dna, startIndex, "TGA");

    int minIndex = 0;
    if(taaIndex == -1 || (tagIndex != -1 && tagIndex < taaIndex)) {
            minIndex = tagIndex;
    } else {
        minIndex = taaIndex;
    }

    if(minIndex == -1 || (tgaIndex != -1 && tgaIndex < minIndex)) {
            minIndex = tgaIndex;
    }

    if(minIndex == -1) {
            return "";
    }

    return dna.substring(startIndex, minIndex + 3);
        }
   public void testFindGene() {
    String one = "ATFxxxyyyzzzTAAxxxTAGxxx";
    String two = "xxxATGxxxyyyxxTAGxTAAxxx";
    String three = "xyyATGxxxyyyuuuTGAxxxTAGxxx";
    String four = "xyyATGxxxyyxxxyuuuTGAxxxTAGxxx";

    //System.out.println("Gene is: " + one + " " + findGene(one,));
    //System.out.println("Gene is: " + two + " " + findGene(two));
    //System.out.println("Gene is: " + three + " " + findGene(three));
    //System.out.println("Gene is: " + four + " " + findGene(four));
   }
   public void CountGenes() {
        int startIndex = 0;
        int count = 0;
    
        FileResource fr = new FileResource("GRch38dnapart.fa");
        String dna = fr.asString().toUpperCase();

        while (true) {
            String gene = findGene(dna, startIndex);
            
            if (gene == "") {
                break;
            }

            startIndex = dna.indexOf(gene, startIndex) + gene.length();
            

            //if(gene.length() > 60) {
                count++;
            //}
            if (startIndex >= dna.length()){
            break;}
        }

        System.out.println("Number of genes is: " + count);
    }
   public StorageResource GetAllGenes(String dna) {
       StorageResource genelist = new StorageResource();       
       int genecounter=0; int startIndex = 0; 
       while (true) {
           String gene = findGene(dna, startIndex );
           if (gene.isEmpty()) {
            break;
        } else {
             genelist.add(gene); 
             startIndex = dna.indexOf(gene, startIndex) + gene.length();
             genecounter++;
             
            }
        if (startIndex >= dna.length()){
            break;}
       }
       System.out.println("Number of genes is: " + genecounter);
       return genelist;
   } 
   public double cgRatio (String dna){
   
   int StrLength = dna.length();
   double CharCounter=0;
       for (int i=0; i<StrLength; i++){
           if (dna.charAt(i)=='C'||dna.charAt(i)=='G')
           { CharCounter++;} 
   }   
   double ratio = CharCounter/StrLength;
   return ratio;
   }
   public int countCTG(String dna){
      int startindex=0; int count=0; 
      int index = dna.indexOf("CTG",startindex);
   while (true){
       if (index==-1){break;}
       count ++; index=dna.indexOf("CTG",index+3);
   }
   return count;
   }
   public void processGenes (StorageResource sr){
       int stringlongerthan60counter = 0;       
       for (String str : sr.data()){
           int StrLength = str.length();
           if (StrLength > 60) {
               System.out.println(str); 
               stringlongerthan60counter++;}
       }
       System.out.println("number of strings longer than 60 is " + stringlongerthan60counter);
       int stringwithhighcgratiocounter=0;
       for (String str : sr.data()){
           double ratio = cgRatio(str);
           if (ratio>0.35){System.out.println(str);
           stringwithhighcgratiocounter++;}
       }
       System.out.println("number of strings with high cg ratio is " + stringwithhighcgratiocounter);
            int maxLength =0;
       for (String str : sr.data()){           
           if (str.length()> maxLength){
               maxLength = str.length();
            }                    
        }System.out.println("MaxLength is " + maxLength);
    }
   public void testProcessGenes (){       
       // CountGenes();
        StorageResource Genes = new StorageResource();
        FileResource fr = new FileResource("GRch38dnapart.fa");
    String dna = fr.asString().toUpperCase();
        Genes = GetAllGenes(dna);
        int count = countCTG(dna);
        System.out.println("CTGcount is " + count);
        processGenes (Genes);
        
        
   }
}