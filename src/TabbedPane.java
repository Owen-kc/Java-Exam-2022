import javax.swing.*;
import java.awt.*;

public class TabbedPane extends JFrame {

    private JTabbedPane tabbed = new JTabbedPane();
    private JPanel p1 = new JPanel(), p2 = new JPanel(), p3 = new JPanel();     //tabbed and jpanel declerations

    public TabbedPane()
    {
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(400,400);              //set size, title
        this.setTitle("Tabbed");
    }

    public void init()
    {
        p1.setBackground(Color.BLACK);
        p2.setBackground(Color.BLUE);
        p3.setBackground(Color.GREEN);          //set colour of each respective pane

        tabbed.add("Black", p1);
        tabbed.add("Blue", p2);            //set colour of each successful tabbed pane
        tabbed.add("Green", p3);

        this.add(tabbed);
        this.setVisible(true);                  //add and set visible.
    }

    public static void main(String[] args) {
        new TabbedPane().init();
    }
}