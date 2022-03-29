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

    private JTabbedPane tabbedPane = new JTabbedPane();

    private JTable table = new JTable();

    private JScrollPane scrollPane;
    private JComboBox<String> choices = new JComboBox<>();

    private JPanel p1 = new JPanel();
    private JPanel p2 = new JPanel();
    private JPanel p3 = new JPanel();


    private JTextPane textArea = new JTextPane();


    ///ADD NEW PERSON

    //String[] partyOptions = {"The Workers Party", "The Labour Party", "Sinn Fein", "People Before Profit", "Non Party", "Green Party", "Fine Gael", "Fiann Fail", "Comhar Crastana"};
    //labels
    private JLabel buttonLabel = new JLabel("Click to Add");
    private JLabel addNoLabel = new JLabel("Number");
    private JLabel addSurnameLabel = new JLabel("Surname");
    private JLabel addFirstNameLabel = new JLabel("First Name");
    private JLabel addPartyddLabel = new JLabel("Party");
    private JLabel addLocalAreaLabel = new JLabel("Local Area");
    private JLabel addAddressLabel= new JLabel("Address");


    //textpanes
    private JTextPane addNo = new JTextPane();
    private JTextPane addSurname = new JTextPane();
    private JTextPane addFirstname = new JTextPane();
    private JComboBox<String> partyDropdown = new JComboBox<>();
    private JTextPane addLocalarea = new JTextPane();
    private JTextPane addAddress = new JTextPane();
    ///END ADD NEW PERSON

    private JButton addButton = new JButton("Add");
    private JButton removeButton = new JButton("Remove");

    private ReadCSV csv;

    public GUIPart()
    {
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setTitle("GUI Part");
        this.setSize(750,750);
        this.setLayout(new BorderLayout());
        this.addWindowListener(this);
    }

    public void init()
    {
        File selectedFile = null;
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setCurrentDirectory(new File("."));
        int result = fileChooser.showOpenDialog(this.getContentPane());

        if (result == JFileChooser.APPROVE_OPTION)
        {
            selectedFile = fileChooser.getSelectedFile();

        }

        csv = new ReadCSV(selectedFile);



        choices.addActionListener(this);

        //___________________________________________
        // Panel 1
        fillAreaCombo();
        p1.setLayout(new GridBagLayout());


        GridBagConstraints c = new GridBagConstraints();

        c.insets = new Insets(0,5,0,5);

        c.gridx = 0;
        c.gridy = 0;

        c.fill = GridBagConstraints.HORIZONTAL;

        p1.add(choices, c);

        c.gridy = 1;
        c.fill = GridBagConstraints.BOTH;
        c.weightx= 1.0;
        c.weighty= 1.0;

        p1.add(textArea, c);

        String area = (String)choices.getSelectedItem();
        setArea(area);

        //___________________________________________
        // Panel 2

        p2.setLayout(new GridBagLayout());
        c = new GridBagConstraints();
        c.insets = new Insets(0,5,0,5);
        c.gridx = 0;
        c.gridy = 0;
        c.fill = GridBagConstraints.HORIZONTAL;

        updateTable();

        table.setAutoCreateRowSorter(true);

        scrollPane = new JScrollPane(table);

        c.fill = GridBagConstraints.BOTH;
        c.gridx = 0;
        c.gridy = 0;
        c.weightx= 1.0;
        c.weighty= 0.9;

        p2.add(scrollPane, c);

        c.fill = GridBagConstraints.NONE;
        c.gridy = 1;
        c.weighty= .1;

        p2.add(removeButton,c);

        removeButton.addActionListener(this);

        //___________________________________________
        // Panel 3
        fillPartyCombo();
        p3.setLayout(new GridBagLayout());
        c = new GridBagConstraints();
        addButton.addActionListener(this);


        //add person
        c.insets = new Insets(10, 10, 10, 10);
        c.weightx = 0.5;
        c.fill = GridBagConstraints.HORIZONTAL;
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
        this.p3.add(addFirstname, c);
        c.gridy = 8;

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

        //p3.add(addNo, c);
        //p3.add(addSurname, c);
        //p3.add(addFirstname, c);
        //end addperson


        tabbedPane.add("Select Area",p1);
        tabbedPane.add("View All",p2);
        tabbedPane.add("Add New",p3);

        tabbedPane.addChangeListener(this);
        this.add(tabbedPane, BorderLayout.CENTER);
        c.gridy = 1;
        this.setVisible(true);
    }

    public void fillAreaCombo()
    {
        DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>();

        for(LocalEleStat stat : this.csv.getStats())
        {
            if(model.getIndexOf(stat.getLocalElectoralArea()) == -1)
            {
                model.addElement(stat.getLocalElectoralArea());
            }
        }
        this.choices.setModel(model);
    }

    public void fillPartyCombo()
    {
        DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>();

        for(LocalEleStat stat : this.csv.getStats())
        {
            if(model.getIndexOf(stat.getParty()) == -1)
            {
                model.addElement(stat.getParty());
            }
        }
        this.partyDropdown.setModel(model);
    }


    private void updateTable()
    {
        String [] cols = csv.getHeadings();

        DefaultTableModel model = new DefaultTableModel(cols, 0);
        for(LocalEleStat stat : csv.getStats())
        {
            model.addRow(new Object[]{
                    stat.getNo(),
                    stat.getSurname(),
                    stat.getFirstName(),
                    stat.getAddress(),
                    stat.getParty(),
                    stat.getLocalElectoralArea()
            });
        }

        table.setModel(model);
    }

    public void setArea(String area)
    {
        textArea.setText(" ");
        StringBuilder display = new StringBuilder("<html><table>");
        for(LocalEleStat stat: csv.getStats())
        {
            if(stat.getLocalElectoralArea().equals(area))
            {
                display.append(stat.toString());

            }

        }
        display.append("</table></html>");

        textArea.setContentType("text/html");
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
            throw new Exception("First name cannot be empty!");
        }
        if(localElectoralArea.isEmpty()){
            throw new Exception("Local Electoral Area cannot be empty!");
        }
        if(address.isEmpty()){
            throw new Exception("Address Area cannot be empty!");
        }
        this.csv.addStat(new LocalEleStat(number, firstName, surname, address, party, localElectoralArea));
        JOptionPane.showMessageDialog(this, firstName+ " " + surname + " has been added");
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        String number =  addNo.getText();
        if (e.getSource() == choices){
            String area = (String)choices.getSelectedItem();
            setArea(area);
        }
        if (e.getSource() == addButton)
        {
            try{
                addCandidate(addNo.getText().trim(), addFirstname.getText(), addSurname.getText(), addAddress.getText(),
                        (String) partyDropdown.getSelectedItem(), addLocalarea.getText() );
            }catch (Exception exception){
                JOptionPane.showMessageDialog(this, exception.getMessage());
            }
        }
        if (e.getSource() == removeButton)
        {
            String value = table.getModel().getValueAt(table.getSelectedRow(), 0).toString();
            csv.removeStat(value);
            updateTable();
        }

    }
    @Override
    public void stateChanged(ChangeEvent changeEvent) {

        JTabbedPane temp = (JTabbedPane)changeEvent.getSource();

        if(temp.getSelectedIndex() == 0)
        {
            fillAreaCombo();
            String area = (String)choices.getSelectedItem();
            setArea(area);
        }
        else if (temp.getSelectedIndex() == 1)
        {
            updateTable();
        }

    }


    @Override
    public void windowOpened(WindowEvent windowEvent) {

    }

    @Override
    public void windowClosing(WindowEvent windowEvent) {
        csv.writeFile();
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