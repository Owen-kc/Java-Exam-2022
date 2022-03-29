import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Scanner;

public class LocalEleStat {

    private String no, surname,firstName,party,localElectoralArea;
    private Address address;


    public LocalEleStat(String s)
    {
        try {
            Scanner sc = new Scanner(s);

            sc.useDelimiter("\"");

            String[] part1 = sc.next().split(",");
            address = new Address(sc.next());
            String[] part3 = sc.next().split(",");

            no = part1[0];
            surname = encode(part1[1]);
            firstName = encode(part1[2]);
            party = encode(part3[1]);
            localElectoralArea = part3[2];
        }
        catch (Exception e)
        {
            //ignore exceptions
            throw new IllegalArgumentException("doesnt fit");
        }
    }

    public LocalEleStat(String number, String firstName, String surname, String address, String party, String localElectoralArea) {
        this.no = number;
        this.firstName = firstName;
        this.surname = surname;
        this.address = new Address(address);
        this.party = party;
        this.localElectoralArea = localElectoralArea;
    }

    private String encode(String s)
    {
        ByteBuffer buffer = StandardCharsets.UTF_8.encode(s);
        return StandardCharsets.UTF_8.decode(buffer).toString();
    }

    private String pad(String s, int padding)
    {
        String temp = "";
        for(int i = 0; i < padding; i++)
        {
            temp = temp + " ";
        }

        return (s+temp).substring(0, padding);

    }

    public String toCSV()
    {
        return String.format("%s,%s,%s,%s,%s",no, surname, firstName, party, localElectoralArea);
    }
    @Override
    public String toString() {


        return String.format("<tr><td>%s</td><td>%s</td><td>%s</td></tr>", (surname+"," +firstName), party, localElectoralArea);
        //return surname + ", \t" + firstName + "\t(" + party +") \t\t\t" + localElectoralArea;
    }

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

    class Address
    {
        String [] lines;

        public Address(String s)
        {
            lines = s.split(",");
        }


        @Override
        public String toString() {
            return  Arrays.toString(lines);

        }
    }
}