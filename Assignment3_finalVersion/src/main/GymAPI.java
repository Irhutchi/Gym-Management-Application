import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

/**
 * GymAPI class maintains a collection of Members and Trainers
 * i.e. an array of Members and Trainers; gymApi.Trainers, gymAPi.Members
 * The The default constructor instantiated both ArrayLists
 *
 * The MenuController allows the user to decide how many personal details they want to store.
 */

public class GymAPI {
   private ArrayList<Member> members;   // Declares members as an ArrayList of Members
   private ArrayList<Trainer> trainers; // Declaring trainers as a private array list of Trainer
   private ArrayList<Assessment> assessments;
   private int total = 0;

   // Initialization required for the GymAPI
   public GymAPI() {
      this.members = new ArrayList<Member>(); //Initialise members object variable
      this.trainers = new ArrayList<Trainer>(); //Initialise trainers object variable

   }

   // accessor method for the Arraylist of Members
   public ArrayList<Member> getMembers() {
      return members;
   }

   // Member type Class, member Object)
   public void addMember(Member member) {
      //adding a member object to an array list of members
      members.add(member);
   }

   /**
    * @return returns a String comprising the index and the names of all the members in the Arraylist
    * if no members are stored in the Arraylist, return "No members"
    */
   public String listMembers() {
      if (isEmpty()) {
         return "Currently no members on the system ";
      } else {
         String listOfMembers = "";
         /*looping through every member in member arraylist and
          * build up a string called list of members
          */
         for (int i = 0; i < members.size(); i++) {
            // Concatenation of all the fields with a new line character after each member
            listOfMembers +=  i + ": " + members.get(i) + "\n";
         }
         return listOfMembers;
      }
   }

   private boolean isEmpty() {
      return (total == 0);
   }


   public void addTrainer(Trainer trainer) {
      //adding a trainer object to an array list of trainers
      trainers.add(trainer);
   }

   public void addAssessment(Assessment assessment) {
      //adding am assessment object to an array list of assessment
      assessments.add(assessment);
   }

   public void updateMember(String email, Member updatedMember) {
      //search the member by email
      Member member = searchMembersByEmail(email);
      //remove the existing member that is about to be updated
      members.remove(member);
      //add the updated member with updated fields from user
      members.add(updatedMember);
   }

   /*public int numberOfMembers() {
       if (members.size() != 0) {
           return members.size();
       }else {
           return 0;
       }
   }*/
   /*public int numberOfMembers() {
      if (members.size() == 0) {
         return 0;
      } else {
         int totalMembers = 0;
         for (int i = 0; i < members.size(); i++) {
            // this will print the size of this list
            totalMembers = members.size();
            System.out.println("Number of Members: " + totalMembers + "\n");
         }
         return totalMembers;
      }
   }*/
   public int numberOfMembers() {
      if (members.size() == 0) {
         return 0;
      } else {
         return members.size();
      }
   }

   /**
    * @return the total number of trainers stored in the trainer array list
    */
   public int numberOfTrainers() {
      if (trainers.size() != 0) {
         return trainers.size();
      } else {
         return 0;
      }
   }

   /**
    * @param index Checks if user input matches a valid member index of member array list
    * @return boolean indicating if user input is a valid index of member's array list.
    */
   public boolean isValidMemberIndex(int index) {
      if ((index >= 0) && (index < members.size())) { // is a valid trainer index
         return true;
      } else if (members.size() == 0) { // This is not a valid trainer index
         return false;
      } else {  // This is not a valid trainer index
         return false;
      }
   }

   /**
    * @param index Checks if user input matches a valid trainer index of trainer array list
    * @return boolean parameter for the trainer's array list.
    */
   public boolean isValidTrainerIndex(int index) {
      if ((index >= 0) && (index < trainers.size())) { // is a valid trainer index
         return true;
      } else if (trainers.size() == 0) { // This is not a valid trainer index
         return false;
      } else {  // This is not a valid trainer index
         return false;
      }
   }

   /**
    * @param nameOfTrainer parameter passed
    * @return member name if user input matches name stored in the member array list
    * if user input doesn't match, return null
    */
   public ArrayList<String> searchTrainersByName(String nameOfTrainer) {
      ArrayList<String> trainerName = new ArrayList<>();
      //for(ElementType element: collection)
      for (Trainer trainer : trainers) {
         if (trainer.getName().contains(nameOfTrainer)) {
            trainerName.add((trainer.getName())); // returns specific element from trainers collection
            return trainerName;
         }
      }
      return trainerName;
   }

   /**
    * @param nameEntered is name entered by user
    * @return member name if user input matches name stored in the member array list
    * if user input doesn't match, return null
    */
   //public ArrayList<String> memberNames = new ArrayList<>();
   public ArrayList<String> searchMembersByName(String nameEntered) {
      ArrayList<String> memberNames = new ArrayList<>();
      for (Member member : members) {
         // contains will help with partial matches
         if (member.getName().contains(nameEntered)) {
            memberNames.add((member.getName()));
            return memberNames;
         }
      }
      return memberNames;
   }

   /**
    * @param email
    * @return the trainer object if email matches the user email entered.
    * if user input doesn't match return null;
    */
   public Trainer searchTrainersByEmail(String email) {
      for (Trainer trainer : trainers) {  // iterate through every member using for-each loop
         if (trainer.getEmail().equals(email)) { // if email is not '=' to another email in member array, return true.
            return trainer;
         }

      }
      return null;
   }


   /**
    * @param email
    * @return the member object if email matches the user email entered.
    * if user input doesn't match, return null
    */
   public Member searchMembersByEmail(String email) {
      for (Member member : members) {  // iterate through every member
         if (member.getEmail().equals(email)) { // if email is '=' to email entered by user is in member array, return true.
            return member;
         }
      }
      return null;
   }


   /**
    *
    * Xstream is a library to serialize objects to XML and back again.
    */
   @SuppressWarnings("unchecked") //prevents warning messages popping up in the compiler
   public void saveMember() throws Exception {
      // boiler plate code - 'Xstream' obj & 'xstream' var
      XStream xstream = new XStream(new DomDriver());

      // ------------------ PREVENT SECURITY WARNINGS-----------------------------
      // The Member class is what we are reading in.
      // Modify to include others if needed by modifying the next line,
      // add additional classes inside the braces, comma separated

      Class<?>[] classes = new Class[]{Member.class, Assessment.class};

      XStream.setupDefaultSecurity(xstream);
      xstream.allowTypes(classes);
      // -------------------------------------------------------------------------

      //use xstream obj var to initialize an ObjectOutputStream to a specified file
      ObjectOutputStream out = xstream.createObjectOutputStream(new FileWriter("members.xml"));
      //write out the object you want saved "members & trainers" object
      out.writeObject(members);
      //out.writeObject(assessments);
      //close the stream / file
      out.close();
   }


   @SuppressWarnings("unchecked") //prevents warning messages popping up in the compiler
   public void loadMember() throws Exception {
      XStream xstream = new XStream(new DomDriver());

      // ------------------ PREVENT SECURITY WARNINGS-----------------------------
      // The Member class is what we are reading in.
      // Modify to include others if needed by modifying the next line,
      // add additional classes inside the braces, comma separated

      Class<?>[] classes = new Class[]{Member.class, StudentMember.class, PremiumMember.class};

      XStream.setupDefaultSecurity(xstream);
      xstream.allowTypes(classes);
      // -------------------------------------------------------------------------

      // object input stream called 'is' which reads in the "products.xml" file
      ObjectInputStream is = xstream.createObjectInputStream(new FileReader("members.xml"));
      /*products is assigned a value from array list of Product.
       * using 'is' input stream to read in the object and assign values to
       * e.g. products  */
      members = (ArrayList<Member>) is.readObject();


      is.close();
   }


   @SuppressWarnings("unchecked")  //prevents warning messages popping up in the compiler
   public void saveTrainer() throws Exception {
      // boiler plate code - 'Xstream' obj & 'xstream' var
      XStream xstream = new XStream(new DomDriver());

      // ------------------ PREVENT SECURITY WARNINGS-----------------------------
      // The Trainer class is what we are reading in.
      // Modify to include others if needed by modifying the next line,
      // add additional classes inside the braces, comma separated

      Class<?>[] classes = new Class[]{Trainer.class};

      XStream.setupDefaultSecurity(xstream);
      xstream.allowTypes(classes);
      // -------------------------------------------------------------------------

      //use xstream obj var to initialize an ObjectOutputStream to a specified file
      ObjectOutputStream out = xstream.createObjectOutputStream(new FileWriter("trainers.xml"));
      //write out the object you want saved "members & trainers" object
      out.writeObject(trainers);
      //close the stream / file
      out.close();
   }


   @SuppressWarnings("unchecked") //prevents warning messages popping up in the compiler
   public void loadTrainer() throws Exception {
      XStream xstream = new XStream(new DomDriver());

      // ------------------ PREVENT SECURITY WARNINGS-----------------------------
      // The Trainer class is what we are reading in.
      // Modify to include others if needed by modifying the next line,
      // add additional classes inside the braces, comma separated

      Class<?>[] classes = new Class[]{Trainer.class};

      XStream.setupDefaultSecurity(xstream);
      xstream.allowTypes(classes);
      // -------------------------------------------------------------------------

      // object input stream called 'is' which reads in the "products.xml" file
      ObjectInputStream is = xstream.createObjectInputStream(new FileReader("trainers.xml"));
      /*products is assigned a value from array list of Product.
       * using 'is' input stream to read in the object and assign values to
       * e.g. products  */
      trainers = (ArrayList<Trainer>) is.readObject();

      is.close();
   }

}

