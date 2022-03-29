import javax.swing.*;
import java.io.File;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Scanner;

public class ReadCSV {

    private ArrayList<LocalEleStat> stats = new ArrayList<>();          //stats arraylist decleration
    private String [] headings;                                         //array for headings

    public ReadCSV(File f)          //readcsv method
    {
        try{                        //trycatch block

            Scanner sc = new Scanner(f);                    //scanner

            sc.nextLine();                                    //skip first heading
            headings = sc.nextLine().split(",");        //add second row to headings

            while (sc.hasNextLine())                         //while scanner has next line
            {
                try {
                    stats.add(new LocalEleStat(sc.nextLine()));     //try to add to stats with new LocalEleStat obj, scanner next line
                }
                catch (IllegalArgumentException ex)
                {
                                                                     //if exception, do nothing
                }
            }

        }
        catch (Exception e)
        {
            e.printStackTrace();;               //exception e, printstacktrrace
        }
    }

    public ArrayList<LocalEleStat> getStats()
    {
        return stats;
    }           //return arraylist stats
    public String [] getHeadings()
    {
        return headings;
    }                   //return array getheadings

    public void addStat(LocalEleStat s)
    {
        stats.add(s);
    }               //add to stats

    public void removeStat(String s)
    {
        int i = -1;
        for(LocalEleStat stat : stats)
        {
            if(stat.getNo().equals(s))                                  //remove from stat,
            {
                i = stats.indexOf(stat);
                break;                                                  //stats = index of stat arraylist
            }
        }

        if(i != -1)                                                     //if i is not -1, remove i from stats
            stats.remove(i);

    }

    public void writeFile()                                             //write to file
    {
        try {

            System.out.println("here");
            File f = new File("output"+ (int)((Math.random()*1000)) + ".csv");      //random number for printed out csv file, add.csv to end

            System.out.println(f.toString());
            f.createNewFile();
            PrintWriter pw = new PrintWriter(f);            //printwriter for printing to csv file

            for(LocalEleStat stat : stats)
            {
                pw.println(stat.toCSV());                       //printwriter from stat.tocsv
            }

            pw.close();                                     //close printerwriter
        }
        catch(Exception e)
        {                                                   //exception, printstacktrace
            e.printStackTrace();
        }
    }


}