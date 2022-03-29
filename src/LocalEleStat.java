import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Scanner;

public class LocalEleStat {

    private String no, surname,firstName,party,localElectoralArea;      //declare strings and address
    private Address address;


    public LocalEleStat(String s)
    {
        try {                                           //try catch block
            Scanner sc = new Scanner(s);                //scanner

            sc.useDelimiter("\"");                      //delimiter is "\"

            String[] part1 = sc.next().split(",");
            address = new Address(sc.next());                   //Set string part1,2, 3 with scanner.next
            String[] part3 = sc.next().split(",");

            no = part1[0];
            surname = encode(part1[1]);
            firstName = encode(part1[2]);                       //encode no,surname,firstname, etc as part1[1], [2] etc
            party = encode(part3[1]);
            localElectoralArea = part3[2];
        }
        catch (Exception e)
        {
            //ignore exceptions
            throw new IllegalArgumentException("Does not fit!");        //exception
        }
    }

    //constructor for new object, passing through all the entries in the add person pane in the gui
    public LocalEleStat(String number, String firstName, String surname, String address, String party, String localElectoralArea) {
        this.no = number;
        this.firstName = firstName;
        this.surname = surname;
        this.address = new Address(address);        //using this. to refer to the declared variables
        this.party = party;
        this.localElectoralArea = localElectoralArea;
    }

    private String encode(String s)
    {
        ByteBuffer buffer = StandardCharsets.UTF_8.encode(s);           //encode method, set standard charset to utf.8
        return StandardCharsets.UTF_8.decode(buffer).toString();
    }

    private String pad(String s, int padding)
    {
        String temp = "";
        for(int i = 0; i < padding; i++)                //padding, for temp
        {
            temp = temp + " ";
        }

        return (s+temp).substring(0, padding);

    }

    //print to new csv method, format with %s, and add no, surname, firstname etc
    public String toCSV()
    {
        return String.format("%s,%s,%s,%s,%s",no, surname, firstName, party, localElectoralArea);
    }
    @Override
    public String toString() {
        return String.format("<tr><td>%s</td><td>%s</td><td>%s</td></tr>", (surname+"," +firstName), party, localElectoralArea);
        //return surname + ", \t" + firstName + "\t(" + party +") \t\t\t" + localElectoralArea;
    }

    //getters for all the variables that will be used for each new object added
    public String getNo() {
        return no;
    }

    public String getSurname() {
        return surname;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getParty() {
        return party;
    }

    public String getLocalElectoralArea() {
        return localElectoralArea;
    }

    public Address getAddress() {
        return address;
    }


    //class for address, split lines with s.split
    class Address
    {
        String [] lines;

        public Address(String s)
        {
            lines = s.split(",");
        }


        //tostring method, return arrays.tostring lines which is the array declared above.
        @Override
        public String toString() {
            return  Arrays.toString(lines);

        }
    }
}