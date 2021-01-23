
import java.util.*;

/**
 * This class has the main() method runs the application and handles the Person's I/O
 * A global Scanner object was created that could be used by all methods in the class.
 * Reads the persons details from the user via the console
 * Creates a new member and Trainer object with inherited parameters from Person object
 * Prints the persons whether it be a member or trainer to the console.
 *
 * @version 5.0
 */
public class MenuController {
   /**
    * declare and initialize a new Scanner object 'input'
    * that is reading from the standard nput stream and reads a single line from it.
    */
   private Scanner input = new Scanner(System.in);
   private GymAPI gymAPI;
   private Map<String, String> packages;  //creating a HashMap called packages, this maps keys to values.

   /*
    * Constructor for objects of the MenuController class
    */
   public MenuController() {
      gymAPI = new GymAPI();
      packages = new HashMap<>(); // initialise the HashMap
      fillPackageMap();       // fill packages to hashMap method
      try {
         gymAPI.loadMember();
         gymAPI.loadTrainer();
      } catch (Exception e) {
         System.err.println("Error loading from file: " + e);
      }
   }

   public static void main(String[] args) {
      MenuController app = new MenuController(); //create a new instance of MenuController object app
      app.startSystem();  // calling the startSystem menu at startup
   }

   /**
    * Print a welcome message to the screen.
    * Prompt the user to login or register
    *
    * @return returnsthe users menu choice, type String
    */
   private String welcomeMenu() { // welcomeMenu has a return type String.
      System.out.println("***************************");
      System.out.println("+++  My Prog Gym 2020   +++");
      System.out.println("***************************");
      System.out.println("Please type 'quit', to exit system.");
      System.out.println();
      //find out from the user if they want to continue as a member or trainer
      System.out.println("Are you  a member(m) or a trainer(t)");
      System.out.print(">> ");
      // Will accept both lower and upper case characters
      // The input.nextLine().toLowerCase() method takes our input variable to be changed, then
      // it is assigned to this new object back into the variable, discarding the original one.
      String userChoice = input.nextLine();
      //find out from the user if they want to login or register
      System.out.println("Do you want to to login(l) or register(r): ");
      System.out.print(">> ");
      String loginOrRegisterChoice = input.nextLine();
      // concatenating two strings 'm' and 'l' choice from user to return member login
      if (userChoice.equalsIgnoreCase("m") && loginOrRegisterChoice.equalsIgnoreCase("l")) {
         return userChoice + loginOrRegisterChoice;
         // concatenating two strings 't' and 'l' choice from user to return trainer login
      } else if (userChoice.equalsIgnoreCase("t") && loginOrRegisterChoice.equalsIgnoreCase("l")) {
         return userChoice + loginOrRegisterChoice;
         // concatenating two strings 'm' and 'r' choice from user to return member registration
      } else if (userChoice.equalsIgnoreCase("m") && loginOrRegisterChoice.equalsIgnoreCase("r")) {
         return userChoice + loginOrRegisterChoice;
         // concatenating two strings 't' and 'r' choice from user to return trainer registration
      } else if (userChoice.equalsIgnoreCase("t") && loginOrRegisterChoice.equalsIgnoreCase("r")) {
         return userChoice + loginOrRegisterChoice;
      } else if (loginOrRegisterChoice.equalsIgnoreCase("quit")) {
         return loginOrRegisterChoice;
      }  else if (userChoice.equalsIgnoreCase("quit")) {
         return loginOrRegisterChoice;
      } else {
         return "Invalid Entry";
      }
   }

   /**
    * switch statement compares the String object in its expression
    * with the expressions associated with each case label
    */
   public void startSystem() {
      boolean quit = false; // using quit as a flag that becomes true when the user wants to end the loop
      do { // will restart from this point if user input is not equal to string 'quit'
         // using string to control switch statement
         String choice = welcomeMenu(); //handling menu input of type string
         switch (choice) {   // Switch checks the value of 'choice' and directs it to output matching case statement
            case "mr":
               registerMember(); //display the register menu for member
               break; // after registerMember is performed, it will stop the choice and jump to the end and continue to rest of program
            case "MR":
               registerMember(); //display the register member
               break; // after registerMember is performed, it will stop the choice and jump to the end and continue to rest of program
            case "tr":
               registerTrainer();
               break;
            case "ml":
               login(choice); //user chose login as  m
               break;
            case "tl":
               login(choice); //user chose login as t
               break;
            case "quit":
               quit = true;
               /**
                * before save member and trainer details
                * catch block receives the invalid entry from user from the saveMember & saveTrainer method and then
                * retrieves the message that details what the exception was and it is printed to the console
                */
               try {
                  gymAPI.saveMember();
                  gymAPI.saveTrainer();

               } catch (Exception e) {
                  System.out.println("Couldn't save data to xml" + e);
               }
               System.exit(0); // Reports the successful termination of the program.
               break;
            default:
               /**
                *  default is performed if entry by user is not any of the cases above,
                *  a message is output to console + the users choice
                */
               System.err.println("Invalid entry: " + choice);
         }
      } while (!quit);
      //pause the program so that the user can read what we just printed to the terminal window
      System.out.println("\nPress any key to continue...");
      input.nextLine();
      /*this second read is required - bug in Scanner class;
       *a String read is ignored straight after reading an int.*/
      input.nextLine();
      printGoodbye();
   }

   /**
    * Print a good-bye message to the screen.
    */
   private void printGoodbye() {
      System.out.println("Thanks for visiting our site. Bye...");
   }

   /**
    * @param gymLogin receives data of type String and carries it from user entry making it available inside the method
    *                 gymLogin will persist for the lifetime of the object and enable transfer to relevant menus for each member and trainer.
    */
   private void login(String gymLogin) {
      //input.nextLine();
      System.out.println("Login: ");
      //prompt user to enter their email address
      System.out.print(" Enter your email address: ");
      String userEmail = input.nextLine();
      // check if the user trying to login is a member
      if (gymLogin.equalsIgnoreCase("ml")) {
         // if yes, search in members list for the email
         /**
          * Returns the member object if email matches the user email entered.
          * if user input doesn't match, return null.
          */
         if (gymAPI.searchMembersByEmail(userEmail) != null) {
            // if found, show them member's menu
            runMember(userEmail);
         } else {
            System.out.println("Access Denied");
         }
         // check if the user trying to login is a trainer
      } else if (gymLogin.equalsIgnoreCase("tl")) {
         // if yes, search in trainers list for the email
         // comparing the reference object email is not null and passing in email entered
         if (gymAPI.searchTrainersByEmail(userEmail) != null) {
            runTrainer();
         } else {
            System.out.println("Access Denied");
         }

      } else {
         System.out.println("You need to register as a member or trainer to gain access");
      }
   }

   //gather the member data from the user and create a new member.
   private void registerMember() {
      boolean goodInput = false; // LCV - using goodInput as a flag that becomes true when the user enters invalid entry
      //dummy read of String to clear the buffer - bug in Scanner class.
      input.nextLine();
      float startWeight = 0;
      float height = 0;

      System.out.print("Enter Email: ");
      String email = input.nextLine();
      if (gymAPI.searchMembersByEmail(email) != null) {
         System.out.println("Member already exists!!");
      } else {
         System.out.print("Enter Name: ");
         String name = input.nextLine();
         System.out.print("Enter Address: ");
         String address = input.nextLine();
         System.out.print("Specify Gender (M or F): ");
         String gender = input.nextLine();
         while (!goodInput) {
            try {
               System.out.print("Enter height in meters: ");
               height = input.nextFloat();
               input.nextLine();
               goodInput = true;
            } catch (Exception e) {
               goodInput = false;
               input.nextLine(); // swallows Scanner Bug
               System.out.println("Number expected - you entered text");
            }
         }
         do {
            try {
               System.out.print("Enter Starting Weight, in KG's: ");
               startWeight = input.nextFloat();
               goodInput = true;
            } catch (Exception e) {
               goodInput = false;
               input.nextLine(); // swallows Scanner Bug
               System.out.println("Number expected - you entered text");
            }
         } while (!goodInput);

         /**
          * Printing all packages to console to let user  view before choosing one
          * The get(Object key) method is used to return the value to which the specified key is mapped,
          * or null if this map contains no mapping for the key.
          */
         System.out.println("Number of choices to choose from: " + packages.size());
         // iterating over set of all key-value pairs and printing them to the console
         for (String packageChoice : packages.keySet()) {
            System.out.println(packageChoice + " " + packages.get(packageChoice));
         }
         input.nextLine();
         System.out.println("Choose Gym Package:");
         String chosenPackage = input.nextLine();
         do {
            /**
             * ContainsKey() method is used to check whether a particular key is exists in the map or not
             */
            if (packages.containsKey(chosenPackage)) { // checking user input contains/ matches package choice entered
               /* calls the Member constructor to build a member object in
                * memory with the details entered by the user.
                */
               if (chosenPackage.equals("WIT")) {
                  System.out.println("Are you a student (y/n) ?");
                  String isStudent = input.nextLine();
                  if (isStudent.equalsIgnoreCase("y")) {
                     System.out.println("College Name");
                     String collegeName = input.nextLine();
                     System.out.println("Student ID");
                     String studentID = input.nextLine();
                     // calls the addMember method in gymAPI class to add member object in the members arraylist
                     gymAPI.addMember(new StudentMember(email, name, address, gender, height, startWeight, chosenPackage, studentID, collegeName));
                     goodInput = true;
                  } else {
                     System.out.println("Student package only available to people with valid ID.");
                     break;
                  }
               } else if (chosenPackage.equals("Package 1")) {
                  gymAPI.addMember(new PremiumMember(email, name, address, gender, height, startWeight, chosenPackage)); // checking if valid package user will be added
                  goodInput = true;
               } else {
                  gymAPI.addMember(new Member(email, name, address, gender, height, startWeight, chosenPackage)); // checking if valid package user will be added
                  goodInput = true;
               }
            } else {
               //goodInput = false;
               System.out.println("Invalid - Package description entered does not match.");
               break;
            }
         } while (!goodInput);
      }
   }

   /**
    * Map string keys to string values.
    * populate the packages hash map
    */
   private void fillPackageMap() {
      packages.put("Package 1",
              "Allowed access anytime to gym. Free access to all classes. Access to all changing areas including deluxe changing rooms");
      packages.put("Package 2",
              "Allowed access anytime to gym. €3 fee for all classes.Access to all changing areas including deluxe changing rooms.");
      packages.put("Package 3",
              "Allowed access to gym at off-peak times. €5 fee for all classes. No access to deluxe changing rooms.");
      packages.put("WIT",
              "Allowed access to gym during term time. €4 fee for all classes. No access to deluxe changing rooms.");

   }

   /**
    * memberMenu() - This method displays the menu the menu for the specific to member,
    * it reads the menu option that the user entered and returns it.
    *
    * @return the users menu choice
    */
   private int memberMenu() {
      System.out.println("Member Display");
      System.out.println("===============");
      System.out.println("  1) View member profile.");
      System.out.println("  2) Update profile.");
      System.out.println("===============");
      System.out.println("  0) Exit");
      System.out.print("==>> ");

      int option = input.nextInt();
      input.nextLine();
      return option;
   }

   private void runMember(String email) {
      {
         int option = memberMenu(); //handling menu input of type int
         while (option != 0) {
            switch (option) {
               case 1:
                  //save member after login and stored as currentMember
                  Member currentMember = gymAPI.searchMembersByEmail(email);
                  //check if currentMember is not null, display only when its non null, else display a message to the user
                  if (currentMember != null) {
                     System.out.println(currentMember); // it calls the toString() method
                  } else {
                     System.out.println("Error in rendering member profile");
                  }
                  break;
               case 2:
                  editMember(email);
                  break;
               default:
                  System.out.println("Invalid option entered: " + option);
                  break;
            }
            option = memberMenu();
         }
      }
   }

   /**
    * trainerMenu() - This method displays the menu for the specific to trainer,
    * reads the menu option that the user entered and returns it.
    *
    * @return the users menu choice
    */
   private int trainerMenu() {
      System.out.println("Trainer Menu");
      System.out.println("===============");
      System.out.println("  1) Add a new member");
      System.out.println("  2) List all members");
      System.out.println("  3) Search member by email");
      System.out.println("  4) Add a member Assessment");
      System.out.println("  5) Delete a member");
      System.out.println("  6) Number of members");
      System.out.println("===============");
      System.out.println("  0) Exit");
      System.out.print("==>> ");

      int option = input.nextInt();
      input.nextLine();
      return option;
   }

   private void runTrainer() {
      {
         int option = trainerMenu(); // trainerMenu assigned an integer type called option
         while (option != 0) {
            switch (option) {
               case 1:
                  registerMember(); //
                  break;
               case 2:
                  //print all the members in the array list (the toString method is automatically called).
                  System.out.println(gymAPI.listMembers());
                  break;
               case 3:
                  System.out.println("Enter member email : "); //prompt user to enter email of member they are looking for
                  String email = input.nextLine();  //reading in the input from the user
                  Member member = gymAPI.searchMembersByEmail(email); // calling search by eamail method in gymAPI
                  if (member != null) { // null check - if member found display member
                     System.out.println(member); // printing member details to console
                  } else { // else if member not found
                     System.out.println("member with email = " + email + " not found!!");
                  }
                  break;
               case 4:
                  addAssessment();
                  break;
               case 5:
                  deleteMember();  //trainer has ability to remove trainers from the gym.
                  break;
               case 6:
                  System.out.println("Current number of members on system: " + gymAPI.numberOfMembers());
                  break;
               default:
                  System.out.println("Invalid option entered: " + option);
                  break;
            }
            //display the main menu again
            option = trainerMenu();
         }
      }
   }


   //gather the trainer data from the user and create a new trainer.
   private void registerTrainer() {
      //dummy read of String to clear the buffer - bug in Scanner class.
      input.nextLine();
      //create a trainer object
      System.out.print("Please enter the trainer's Email: ");
      String email = input.nextLine();
      if (gymAPI.searchTrainersByEmail(email) != null) {
         System.out.println("Member already exists!!");
      } else {
         System.out.print("Enter your Name: ");
         String name = input.nextLine();
         System.out.print("Enter Address: ");
         String address = input.nextLine();
         System.out.print("Specify Gender (M or F): ");
         String gender = input.nextLine();
         System.out.print("Enter trainers speciality: ");
         String speciality = input.nextLine();
         // calls the add method in gymAPI class to add Trainer object in the trainers arraylist
         gymAPI.addTrainer(new Trainer(email, name, address, gender, speciality));
         try {
            gymAPI.saveTrainer();
            System.out.print("Saving Trainer " + name + " to database");
         } catch (Exception e) {
            System.out.println("Error writing to trainer.xml file: " + e);
         }
      }
   }
   // method's job - to edit member by first using the value stored in email attribute
   public void editMember(String email) {
      boolean goodInput = false; // LCV
      /* Take in all the fields from adding a member and run the same code again,
       * (below). We store those values in the variables, and call setter methods to change
       * values currently in that member that you have just read in.
       */
      //get member by email
      Member updatedMember = gymAPI.searchMembersByEmail(email);
      if (updatedMember != null) {
         //gather new details for each field from the user.
         System.out.print("Enter Address: ");
         String address = input.nextLine();
         System.out.print("Specify Gender (M or F): ");
         String gender = input.nextLine();
         System.out.print("Enter height in meters: ");
         float height = input.nextFloat();
         input.nextLine();
         System.out.print("Enter Starting Weight, in KG's: ");
         float startWeight = input.nextFloat();
         input.nextLine();
         /**
          * retrieve the selected member from the Arraylist and update the new details
          * entered b the member.
          */
         updatedMember.setAddress(address);
         updatedMember.setGender(gender);
         updatedMember.setHeight(height);
         updatedMember.setStartWeight(startWeight);
      }
      gymAPI.updateMember(email, updatedMember); // removing current member details and saving the new ones.
   }

   public void deleteMember() {  // method has no return type
      //list the members
      System.out.println(gymAPI.listMembers());
      if (gymAPI.getMembers().size() > 0) {  //validation
         //only ask the user to choose the member to delete if member exists
         System.out.print("Enter the index of the member to delete ==> ");
         int index = input.nextInt(); //take in value from user representing index of member to delete
         /**
          * Checking that input is less >0 and less than the size of the member arraylist.
          * Ensures user input to access a valid member between zero and size of array
          */
         if ((index >= 0) && (index < gymAPI.getMembers().size())) {  //validation
            //if the index is valid, delete the product at the given index
            gymAPI.getMembers().remove(index);
            System.out.println("Member Removed.");
         } else {

            System.out.println("There is no Member for this index number");
         }
      }
   }
   // Add assessment started but not finished.
   private void addAssessment() {
      boolean goodInput = false; //LCV
      System.out.println("List of current members :");
      /* looping through every member in member arraylist and
       * build up a string called list of members
       */
      System.out.println(gymAPI.listMembers());
      if (gymAPI.getMembers().size() > 0) {
         System.out.print("Enter the index of the member to update ==> ");
         int index = 0;

         if ((index >= 0) && (index < gymAPI.getMembers().size())) {
            while (!goodInput) {
               try {
                  System.out.println("Please enter weight (in Kg's:");
                  double weight = input.nextDouble();
                  input.nextLine();
                  goodInput = true;
               } catch (Exception e) {
                  input.nextLine(); // swallows Scanner Bug
                  System.out.println("Number expected - you entered text");
               }
               try {
                  System.out.println("Please enter thigh dimensions(in cm: ");
                  double thigh = input.nextDouble();
                  input.nextLine();
                  goodInput = true;
               } catch (Exception e) {
                  input.nextLine(); // swallows Scanner Bug
                  goodInput = false;
                  System.out.println("Number expected - you entered text");
               }
               try {
                  System.out.println("Please enter waist dimensions(in cm: ");
                  double waist = input.nextDouble();
                  input.nextLine();
                  goodInput = true;
               } catch (Exception e) {
                  input.nextLine(); // swallows Scanner Bug
                  goodInput = false;
                  System.out.println("Number expected - you entered text");
               }
               try {
                  System.out.println("Please enter comment: ");
                  String comment = input.nextLine();
                  input.nextLine();
                  goodInput = true;
               } catch (Exception e) {
                  input.nextLine(); // swallows Scanner Bug
                  goodInput = false;
                  System.out.println("Number expected - you entered text");
               }
            }
         } else {
            System.out.println("There are no members for this index number");
         }
      }
      //gymAPI.addAssessment(new Assessment(weight, thigh, waist, comment));
   }


}
