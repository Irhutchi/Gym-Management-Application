import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

// THIS CODE IS INCOMPLETE

public class GymAPI {
    private ArrayList<Member> members;
    private ArrayList<Trainer> trainers; // Declaring trainers as a private array list of Trainer

    // Initialization required for the GymAPI
    public GymAPI() {
        this.members = new ArrayList<Member>(); //Initialise members object variable
        this.trainers = new ArrayList<Trainer>(); //Initialise trainers object variable
    }

    public ArrayList<Member> getMembers() {
        return members;
    }
                       //(Class, Object)
    public void addMember(Member member) {
        //adding a member object to an array list of members
        members.add(member);
    }

    /**
     * List all of the members in the gym
     */
    public String listMembers() {
        if(members.size() == 0) {
            return "No members";
        } else {
            String listOfMembers = "";
            /*looping through every member in member arraylist and
             * build up a string called list of members
             */
            for(int i = 0; i < members.size(); i++) {
                listOfMembers += + i + ": " + members.get(i) + "\n";
            }
            return listOfMembers;
        }
    }



    public void addTrainer(Trainer trainer) {
        //adding a trainer object to an array list of trainers
        trainers.add(trainer);
    }

    public int numberOfMembers() {
        if(members.size()!= 0) {
            int totalMembers = 0;
            int index = 0;
            for(Member member: members) {
                // this will print the size of this list
                totalMembers = members.size();
                System.out.println("Number of Members: " + totalMembers);
            }
        }
        return 0;
    }

    public int numberOfTrainers() {
        if(trainers.size()!= 0) {
            return trainers.size();
        } else {
            return 0;
        }
    }


    public boolean isValidMemberIndex(int index) {
        return false;
    }

    public boolean isValidTrainerIndex(int index) {
        return false;
    }

    //prevents warning messages popping up in the compiler
    @SuppressWarnings("unchecked")
    public void save() throws Exception {
        // boiler plate code - 'Xstream' obj & 'xstream' var
        XStream xstream = new XStream(new DomDriver());

        // ------------------ PREVENT SECURITY WARNINGS-----------------------------
        // The Product class is what we are reading in.
        // Modify to include others if needed by modifying the next line,
        // add additional classes inside the braces, comma separated

        Class<?>[] classes = new Class[] { Member.class, Trainer.class };

        XStream.setupDefaultSecurity(xstream);
        xstream.allowTypes(classes);
        // -------------------------------------------------------------------------

        //use xstream obj var to initialize an ObjectOutputStream to a specified file
        ObjectOutputStream out = xstream.createObjectOutputStream(new FileWriter("people.xml"));
        //write out the object you want saved "members & trainers" object
        out.writeObject(members);
        //out.writeObject(trainers);
        //close the stream / file
        out.close();
    }

    //prevents warning messages popping up in the compiler
    @SuppressWarnings("unchecked")
    public void load() throws Exception {
        XStream xstream = new XStream(new DomDriver());

        // ------------------ PREVENT SECURITY WARNINGS-----------------------------
        // The Product class is what we are reading in.
        // Modify to include others if needed by modifying the next line,
        // add additional classes inside the braces, comma separated

        Class<?>[] classes = new Class[] { Member.class, Trainer.class };

        XStream.setupDefaultSecurity(xstream);
        xstream.allowTypes(classes);
        // -------------------------------------------------------------------------

        // object input stream called 'is' which reads in the "products.xml" file
        ObjectInputStream is = xstream.createObjectInputStream(new FileReader("people.xml"));
        /*products is assigned a value from array list of Product.
         * using 'is' input stream to read in the object and assign values to
         * e.g. products  */
        members = (ArrayList<Member>) is.readObject();
        //trainers = (ArrayList<Trainer>) is.readObject();

        is.close();
    }


}
