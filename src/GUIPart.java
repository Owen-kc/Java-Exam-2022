import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;

public class GUIPart extends JFrame implements ActionListener, ChangeListener, WindowListener {


    //add tabbedpane, table, scrollpane, comboboxes, textpanes and panels to gui
    private JTabbedPane tabbedPane = new JTabbedPane();
    private JTable table = new JTable();

    private JScrollPane scrollPane;
    private JComboBox<String> choices = new JComboBox<>();

    private JPanel p1 = new JPanel();
    private JPanel p2 = new JPanel();
    private JPanel p3 = new JPanel();


    private JTextPane textArea = new JTextPane();


    ///ADD NEW PERSON


    //labels for add entry
    private JLabel buttonLabel = new JLabel("Click to Add");
    private JLabel addNoLabel = new JLabel("Number");
    private JLabel addSurnameLabel = new JLabel("Surname");
    private JLabel addFirstNameLabel = new JLabel("First Name");
    private JLabel addPartyddLabel = new JLabel("Party");
    private JLabel addLocalAreaLabel = new JLabel("Local Area");
    private JLabel addAddressLabel= new JLabel("Address");


    //textpanes for add entry incl combo box for adding party for new entry
    private JTextPane addNo = new JTextPane();
    private JTextPane addSurname = new JTextPane();
    private JTextPane addFirstname = new JTextPane();
    private JComboBox<String> partyDropdown = new JComboBox<>();
    private JTextPane addLocalarea = new JTextPane();
    private JTextPane addAddress = new JTextPane();

    //add and remove button
    private JButton addButton = new JButton("Add");
    private JButton removeButton = new JButton("Remove");

    private ReadCSV csv;        //declare ReadCSV file as csv to reference

    public GUIPart()
    {
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setTitle("GUI Part");
        this.setSize(750,750);              //set title, size, layout etc
        this.setLayout(new BorderLayout());
        this.addWindowListener(this);
    }

    public void init()
    {
        File selectedFile = null;                                           //get selected csv file
        JFileChooser fileChooser = new JFileChooser();                      //new file chooser
        fileChooser.setCurrentDirectory(new File("."));            //set file chooser to the selected file
        int result = fileChooser.showOpenDialog(this.getContentPane());     //display result in content pane

        if (result == JFileChooser.APPROVE_OPTION)
        {
            selectedFile = fileChooser.getSelectedFile();   //file passed through filechooser.getselectedfile

        }

        csv = new ReadCSV(selectedFile);            //read selected file through class csv
        choices.addActionListener(this);

        //___________________________________________
        // Panel 1
        fillAreaCombo();                                    //populate the area dropdown
        p1.setLayout(new GridBagLayout());                  //set gbc for pane1
        GridBagConstraints c = new GridBagConstraints();    //declare gridbagconstraints as c

        c.insets = new Insets(0,5,0,5); //set insets for gbc

        c.gridx = 0;                                         //declare gridx/y
        c.gridy = 0;

        c.fill = GridBagConstraints.HORIZONTAL;              //fill horizontally

        p1.add(choices, c);                                  //add choices to pane1, with gridbagconstraints applied (c is gridbagconstraints)

        c.gridy = 1;                                         //add to gridy =1, fill both, weight definition
        c.fill = GridBagConstraints.BOTH;
        c.weightx= 1.0;
        c.weighty= 1.0;

        p1.add(textArea, c);                                 //add text area with gridbagconstraints

        String area = (String)choices.getSelectedItem();     //get selected area from dropdown
        setArea(area);

        //___________________________________________
        // Panel 2

        p2.setLayout(new GridBagLayout());                   //set layout of pane2, gridbagconstraints
        c = new GridBagConstraints();                        //once again, declare c to be gridbagconstraints
        c.insets = new Insets(0,5,0,5); //insets again
        c.gridx = 0;
        c.gridy = 0;                                         //declare gridx/y
        c.fill = GridBagConstraints.HORIZONTAL;              //fill gridbagconstraints horizontally

        updateTable();                                      //call updatetable method

        table.setAutoCreateRowSorter(true);                 //create row sorter

        scrollPane = new JScrollPane(table);                //add scrollpane to table

        c.fill = GridBagConstraints.BOTH;
        c.gridx = 0;                                        //add gridbagconstraints gridx/y, weight
        c.gridy = 0;
        c.weightx= 1.0;
        c.weighty= 0.9;

        p2.add(scrollPane, c);                              //add scrollpane to pane2 with gridbagconstraints

        c.fill = GridBagConstraints.NONE;
        c.gridy = 1;                                        //set grid
        c.weighty= .1;

        p2.add(removeButton,c);                             //add remove button to pane with gridbagconstraints

        removeButton.addActionListener(this);             //add action listener to removebutton

        //___________________________________________
        // Panel 3
        fillPartyCombo();                                   //populate dropdown menu for party dropdown
        p3.setLayout(new GridBagLayout());                  //add gridbagconstraints to pane3, with an action listener for addbutton
        c = new GridBagConstraints();
        addButton.addActionListener(this);


        //add person
        c.insets = new Insets(10, 10, 10, 10);
        c.weightx = 0.5;
        c.fill = GridBagConstraints.HORIZONTAL;                     //create insets, weight, gridx/y for pane3
        c.gridx = 0;
        c.gridy = 0;

        this.p3.add(buttonLabel, c);
        c.gridy=1;
        this.p3.add(addButton, c);
        c.gridy = 2;

        this.p3.add(addNoLabel, c);
        c.gridy=3;
        this.p3.add(addNo, c);
        c.gridy = 4;

        this.p3.add(addSurnameLabel, c);
        c.gridy=5;
        this.p3.add(addSurname, c);
        c.gridy= 6;

        this.p3.add(addFirstNameLabel, c);
        c.gridy=7;
        this.p3.add(addFirstname, c);               //here, add the labels and the textfields for each respective part (number, name, surname etc)
        c.gridy = 8;                                //use gridbagconstraints for this to layout the add new person page

        this.p3.add(addPartyddLabel, c);
        c.gridy=9;
        this.p3.add(partyDropdown, c);
        c.gridy = 10;

        this.p3.add(addLocalAreaLabel, c);
        c.gridy=11;
        this.p3.add(addLocalarea, c);
        c.gridy = 12;

        this.p3.add(addAddressLabel, c);
        c.gridy = 13;
        this.p3.add(addAddress, c);
        c.gridy = 14;


        tabbedPane.add("Select Area",p1);
        tabbedPane.add("View All",p2);      //add tabbedpane to all three pages
        tabbedPane.add("Add New",p3);

        tabbedPane.addChangeListener(this);         //change listenener for tabbedpane
        this.add(tabbedPane, BorderLayout.CENTER);    //add tabbedpane to pane, center it
        c.gridy = 1;                                  //gridy=1 for this
        this.setVisible(true);                        //set visible
    }

    //fill area combo method, cycle through the area and automatically fill the areas from the csv file into the dropdown menu
    public void fillAreaCombo()
    {
        DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>();      //default comboboxmodel

        for(LocalEleStat stat : this.csv.getStats())                            //for localelestat, getStats for csv
        {
            if(model.getIndexOf(stat.getLocalElectoralArea()) == -1)            //if index = -1, add element to area dropdown
            {
                model.addElement(stat.getLocalElectoralArea());
            }
        }
        this.choices.setModel(model);                                           //set model choices to be the choices strings declared above
    }

    //fill party combo method, cycle through the party and automatically fill the possible parties from the csv file into the dropdown menu
    public void fillPartyCombo()
    {
        DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>();      //defaultcombobox

        for(LocalEleStat stat : this.csv.getStats())
        {
            if(model.getIndexOf(stat.getParty()) == -1)                         //same as above, if index = -1, add element to party dropdown
            {
                model.addElement(stat.getParty());
            }
        }
        this.partyDropdown.setModel(model);                                     //set model choices to be the choices strings declared above
    }


    //update table method, called when new object added, for example
    private void updateTable()
    {
        String [] cols = csv.getHeadings();     //array for headings

        DefaultTableModel model = new DefaultTableModel(cols, 0);
        for(LocalEleStat stat : csv.getStats())
        {
            model.addRow(new Object[]{
                    stat.getNo(),
                    stat.getSurname(),
                    stat.getFirstName(),            //add new row, this is used when a new object is added in the add pane
                    stat.getAddress(),
                    stat.getParty(),
                    stat.getLocalElectoralArea()
            });
        }

        table.setModel(model);
    }

    public void setArea(String area)
    {
        textArea.setText(" ");                                                  //set text area to empty string
        StringBuilder display = new StringBuilder("<html><table>");             //stringbuilder definition
        for(LocalEleStat stat: csv.getStats())                                  //for csv stats
        {
            if(stat.getLocalElectoralArea().equals(area))
            {
                display.append(stat.toString());                                //display appended stat.tostring
            }

        }
        display.append("</table></html>");
        textArea.setContentType("text/html");                                   //textarea content type set to text/html
        textArea.setText(display.toString());
    }

    public void addCandidate(String number, String firstName, String surname, String address, String party, String localElectoralArea) throws Exception
    {
        if(number.isEmpty()){
            throw new Exception("Number cannot be empty!");
        }
        if(surname.isEmpty()){
            throw new Exception("Surname name cannot be empty!");
        }
        if(firstName.isEmpty()){
            throw new Exception("First name cannot be empty!");         //error checking, if any of the entry panes are empty, throw exception and don't add person
        }                                                               //one for each entry, number, name etc
        if(localElectoralArea.isEmpty()){
            throw new Exception("Local Electoral Area cannot be empty!");
        }
        if(address.isEmpty()){
            throw new Exception("Address Area cannot be empty!");
        }
        this.csv.addStat(new LocalEleStat(number, firstName, surname, address, party, localElectoralArea));
        JOptionPane.showMessageDialog(this, firstName+ " " + surname + " has been added");      //show the name of the person added via
    }                                                                                                                 //via Joption (prompt) concat name, empty string, surname and has been added


    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == choices){
            String area = (String)choices.getSelectedItem();        //set area from dropdown
            setArea(area);
        }
        if (e.getSource() == addButton)     //if add buttonn is clicked
        {
            try{
                addCandidate(addNo.getText().trim(), addFirstname.getText(), addSurname.getText(), addAddress.getText(),
                        (String) partyDropdown.getSelectedItem(), addLocalarea.getText() );                                     //add the new object from values from textpanes
            }catch (Exception exception){
                JOptionPane.showMessageDialog(this, exception.getMessage());            //exception, show dialog from .getmessage
            }
        }
        if (e.getSource() == removeButton)
        {
            String value = table.getModel().getValueAt(table.getSelectedRow(), 0).toString();
            csv.removeStat(value);                                                                          //remove button, if clicked, remove the selected row
            updateTable();
        }

    }
    @Override
    public void stateChanged(ChangeEvent changeEvent) {

        JTabbedPane temp = (JTabbedPane)changeEvent.getSource();

        if(temp.getSelectedIndex() == 0)
        {
            fillAreaCombo();
            String area = (String)choices.getSelectedItem();                //statechanged, for area dropdown
            setArea(area);
        }
        else if (temp.getSelectedIndex() == 1)
        {
            updateTable();                                                  //update table index if selected
        }

    }


    @Override
    public void windowOpened(WindowEvent windowEvent) {

    }

    @Override
    public void windowClosing(WindowEvent windowEvent) {
        csv.writeFile();            //write to csv
    }

    @Override
    public void windowClosed(WindowEvent windowEvent) {

    }

    @Override
    public void windowIconified(WindowEvent windowEvent) {

    }

    @Override
    public void windowDeiconified(WindowEvent windowEvent) {

    }

    @Override
    public void windowActivated(WindowEvent windowEvent) {

    }

    @Override
    public void windowDeactivated(WindowEvent windowEvent) {

    }
    public static void main(String[] args) {
        new GUIPart().init();
    }


}