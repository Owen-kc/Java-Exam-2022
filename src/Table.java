import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import java.awt.*;

public class Table extends JFrame {
    private JTable table;               //table, pane declarations
    private JScrollPane pane;

    public Table()
    {
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(400,400);
        this.setTitle("Tables");
    }

    //init method
    public void init()
    {
        //data for columns, data
        String [] cols = new String[]{"name", "age", "location"};

        Object [][] data ={
                {"Sam", "22", "Dublin"},
                {"john", "50", "Galway"},
                {"Jane", "30", "Athlone"},
                {"Sam", "22", "Dublin"},
                {"john", "50", "Galway"},
                {"Jane", "30", "Athlone"},
                {"Sam", "22", "Dublin"},
                {"john", "50", "Galway"},
                {"Jane", "30", "Athlone"},
                {"Sam", "22", "Dublin"},
                {"john", "50", "Galway"},
                {"Jane", "30", "Athlone"},
                {"Sam", "22", "Dublin"},
                {"john", "50", "Galway"},
                {"Jane", "30", "Athlone"},{"Sam", "22", "Dublin"},
                {"john", "50", "Galway"},
                {"Jane", "30", "Athlone"},
                {"Sam", "22", "Dublin"},
                {"john", "50", "Galway"},
                {"Jane", "30", "Athlone"},
                {"Sam", "22", "Dublin"},
                {"john", "50", "Galway"},
                {"Jane", "30", "Athlone"},
                {"Sam", "22", "Dublin"},
                {"john", "50", "Galway"},
                {"Jane", "30", "Athlone"},
                {"Sam", "22", "Dublin"},
                {"john", "50", "Galway"},
                {"Jane", "30", "Athlone"},{"Sam", "22", "Dublin"},
                {"john", "50", "Galway"},
                {"Jane", "30", "Athlone"},
                {"Sam", "22", "Dublin"},
                {"john", "50", "Galway"},
                {"Jane", "30", "Athlone"},
                {"Sam", "22", "Dublin"},
                {"john", "50", "Galway"},
                {"Jane", "30", "Athlone"},
                {"Sam", "22", "Dublin"},
                {"john", "50", "Galway"},
                {"Jane", "30", "Athlone"},
                {"Sam", "22", "Dublin"},
                {"john", "50", "Galway"},
                {"Jane", "30", "Athlone"},
        };

        //that is printed out in the pane



        table = new JTable(data, cols);     //declare table attributes

        pane = new JScrollPane(table);      //add scrollpane to table

        table.setAutoCreateRowSorter(true);     //autosorter for table

        this.add(pane);
                                            //add and set visible
        this.setVisible(true);
    }

    public static void main(String[] args) {
        new Table().init();
    }


}