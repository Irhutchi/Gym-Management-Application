import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Set;

/**
 * This class runs the application and handles the Person's I/O
 * @version 2.0
 */
public class MenuController {
    /**
     * initialize a new Scanner that is reading from the standard
     * input stream  and reads a single line from it.
     */
    private Scanner input = new Scanner(System.in);
    private ArrayList<Member> members;
    private GymAPI gymAPI;
    private HashMap<String, String> packages; //create hash map
    boolean exit; // initialize exit to false automatically
    /*
     * Constructor for objects of class MenuController
     */
    public  MenuController() {
        gymAPI = new GymAPI();
        packages = new HashMap<String, String>(); // initialise hash map
        fillPackageMap();       // fill packages to hashMap
        try {
            gymAPI.loadMember();
            gymAPI.loadTrainer();
        }
        catch (Exception e) {
            System.err.println("Error loading from file: " + e);
        }
    }

    public static void main(String[] args) {
        MenuController app = new MenuController();
        app.startSystem();
        //app.runTrainer();
        //app.runMember();
    }

    /**
     * Print a welcome message to the screen.
     * Prompt the user to login or register
     * @return the users menu choice
     */
    private String welcomeMenu() {
        System.out.println("***************************");
        System.out.println("+++  My Prog Gym 2020   +++");
        System.out.println("***************************");
        System.out.println("Please type 'quit', to exit system.");
        System.out.println();
        System.out.println("Are you  a member(m) or a trainer(t)");
        System.out.print("==>> ");
        String choice1 = input.nextLine().toLowerCase();
        System.out.println("Do you want to to login(l) or register (r): ");
        System.out.print("==>> ");
        String choice = input.nextLine().toLowerCase();
        /**
         * The equalsIgnoreCase() method compares two strings,
         * ignoring lower case and upper case differences.
         * returning l or r for login and registration for both member and trainer
         */
        if(choice1.equals("m") && choice.equals("l")) {
            return choice1;
        } else if (choice1.equals("t") && choice.equals("l")) {
            return choice1;
            // concatenating two strings 'm' and 'r' choice from user to return member registration
        } else if (choice1.equals("m") && choice.equals("r")) {
            return choice1 + choice;
            // concatenating two strings 't' and 'r' choice from user to return trainer registration
        } else if (choice1.equals("t") && choice.equals("r")) {
            return choice1 + choice;
        } else if(choice.equals("quit")) {
            return choice;
        }else{
            return "Invalid";
        }
    }


    /**
     * switch statement compares the String object in its expression
     * with the expressions associated with each case label
     */
    public void startSystem() {
        boolean quit = false;
        do {
            String choice = welcomeMenu();
            switch (choice) {   // Switch construct
                case "mr":     registerMember();
                    break;
                case "tr":     registerTrainer();
                    break;
                case "m":      login(choice); //decide if login as t or m
                    break;
                case "t":      login(choice); //user chose login as t
                    break;
                case "quit":  quit = true;
                    try {
                        gymAPI.saveMember();
                        gymAPI.saveTrainer();

                    } catch (Exception e) {
                        System.out.println("Couldn't save data to xml");
                    }
                        System.exit(0);
                    break;
                default:
                    throw new IllegalArgumentException("Invalid option selected. " + choice);
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
    private void printGoodbye()
    {
        System.out.println("Thanks for visiting. Bye...");
    }

    private void login(String gymLogin)  {
        input.nextLine();
        System.out.println("Login: ");

            System.out.print(" Enter your email address: ");
            String userEmail = input.nextLine().toLowerCase();
            // check if the user trying to login is a member
            if(gymLogin.equals("m")) {
                // if yes, search in members list for the email
                if(gymAPI.searchMembersByEmail(userEmail) != null){
                    // if found, show them member's menu
                    memberMenu();
                } else{
                    System.out.println("Access Denied");
                }
              // check if the user trying to login is a trainer
            }else if(gymLogin.equals("t")) {
                // if yes, search in trainers list for the email
                if(gymAPI.searchTrainersByEmail(userEmail) != null){
                    trainerMenu();
                } else {
                    System.out.println("Access Denied");
                }

            } else {
                System.out.println("Not a member or trainer");
            }
    }

    private void registerMember() { //create a member object
        boolean goodInput = false; // LCV
        //Set keySet = packages.keySet(); // get keyset value from packages map
        //dummy read of String to clear the buffer - bug in Scanner class.
        input.nextLine();
        float startWeight= 0;
        float height =0;

        System.out.print("Enter Email: ");
        String email = input.nextLine();
        if(gymAPI.searchMembersByEmail(email) != null) {
            System.out.println("Member already exists!!");
        } else {
            System.out.print("Enter Name: ");
            String name = input.nextLine();
            System.out.print("Enter Address: ");
            String address = input.nextLine();
            System.out.print("Specify Gender (M or F): ");
            String gender = input.nextLine();
            while(!goodInput) {
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
            System.out.println("Package 1: " + packages.get("Package 1") + "\n"
                    +"Package 2: " + packages.get("Package 2") + "\n"
                    + "Package 3: " + packages.get("Package 3") + "\n"
                    +" Package 4 (WIT): " + packages.get("WIT")+"\n");
            input.nextLine();  // swallows Scanner Bug
            System.out.print("Choose Gym Package: ");
            String chosenPackage = input.nextLine();
            do {
                 //ContainsKey() method is used to check whether a particular key is exists in the map or not
                if(packages.containsKey(chosenPackage) ) { // checking user input contains/ matches package choice entered
                    /*creates a Member object with the details entered by the user.
                     * calls the add method in the gymAPI class to add the Member
                     * object to the members ArrayList.*/
                    //gymAPI.addMember(new Member(email, name, address, gender, height, startWeight, chosenPackage));
                    gymAPI.addMember(new Member(email, name, address, gender, height, startWeight, chosenPackage)); // checking if valid package user will be added
                    goodInput = true;
                }  else {
                    goodInput = false;
                    System.out.println("Invalid - Package description entered does not match.");
                }
            } while(!goodInput);
        }

    }
    private void fillPackageMap()
    {
        packages.put("Package 1",
                "Allowed access anytime to gym. Free access to all classes. Access to all changing areas including deluxe changing rooms");
        packages.put("Package 2",
                "Allowed access anytime to gym. €3 fee for all classes.Access to all changing areas including deluxe changing rooms.");
        packages.put("Package 3",
                "Allowed access to gym at off-peak times. €5 fee for all classes. No access to deluxe changing rooms.");
        packages.put("WIT",
                "Allowed access to gym during term time. €4 fee for all classes. No access to deluxe changing rooms.");

    }



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

    private void runMember() {
        {
            int option = memberMenu();
            while (option != 0) {
                switch (option) {
                    case 1:     System.out.println("Need to add method");;
                        break;
                    case 2:     editMember();
                        break;
                    default:
                        System.out.println("Invalid option entered: " + option);
                        break;
                }
                input.nextLine();
                //pause the program so that the user can read what we just printed to the terminal window
                System.out.println("\nPress any key to continue...");

                input.nextLine(); /*this second read is required - bug in Scanner class;
                 *a String read is ignored straight after reading an int.*/

                //display the main menu again
                option = memberMenu();
            }
            try {
                gymAPI.saveMember();
            }
            catch (Exception e) {
                System.err.println("Error writing to file: " + e);
            }
            //the user chose the option 0, so exit the program
            System.out.println("End of program... bye");
            System.exit(0);  // terminate program
        }
    }



    /**
     * trainerMenu() - This method displays the menu for the specific to trainer,
     * reads the menu option that the user entered and returns it.
     *
     * @return      the users menu choice
     */
    private int trainerMenu()
    {
        System.out.println("Trainer Menu");
        System.out.println("===============");
        System.out.println("  1) Add a new member");
        System.out.println("  2) Add a new trainer");
        System.out.println("  3) List all members");
        System.out.println("  4) Update a member's details");
        System.out.println("  5) Add a member Assessment");
        System.out.println("  6) Delete a member");
        System.out.println("  7) Save member to member.xml");
        System.out.println("  8) Save member to trainers.xml");
        System.out.println("  9) Number of members");
        System.out.println("===============");
        System.out.println("  0) Exit");
        System.out.print("==>> ");

        int option = input.nextInt();
        input.nextLine();
        return option;
    }

    private void runTrainer() {
        {
            int option = trainerMenu();
            while (option != 0) {
                switch (option) {
                    case 1:     registerMember();
                        break;
                    case 2:     registerTrainer();
                        break;
                    case 3:     System.out.println(gymAPI.listMembers());
                        break;
                    case 4:     editMember();
                        break;
                    case 5:     addAssessment();
                        break;
                    case 6:     deleteMember();
                        break;
                    case 7:
                        try {
                            gymAPI.saveMember();
                        }
                        catch (Exception e) {
                            System.err.println("Error writing to file: " + e);
                        }
                        break;
                    case 8:
                        try {
                            gymAPI.saveTrainer();
                        }
                        catch (Exception e) {
                            System.err.println("Error writing to file: " + e);
                        }
                        break;
                    case 9:
                        System.out.println("Current number of members on system: " + gymAPI.numberOfMembers());
                        break;
                    default:
                        System.out.println("Invalid option entered: " + option);
                        break;
                }
                input.nextLine();
                //pause the program so that the user can read what we just printed to the terminal window
                System.out.println("\nPress any key to continue...");

                input.nextLine(); /*this second read is required - bug in Scanner class;
                 *a String read is ignored straight after reading an int.*/

                //display the main menu again
                option = trainerMenu();
            }
            //the user chose the option 0, so exit the program
            System.out.println("End of program... bye");
            System.exit(0);  // terminate program
        }
    }



    private void registerTrainer() {
        //dummy read of String to clear the buffer - bug in Scanner class.
        input.nextLine();
        //create a trainer object
        System.out.print("Please enter the trainer's Email: ");
        String email = input.nextLine();
        System.out.print("Enter your Name: ");
        String name = input.nextLine();
        System.out.print("Enter Address: ");
        String address = input.nextLine();
        System.out.print("Specify Gender (M or F): ");
        String gender = input.nextLine();
        System.out.print("Enter trainers speciality: ");
        String speciality = input.nextLine();

        gymAPI.addTrainer(new Trainer(email, name, address, gender, speciality));
        try{
            gymAPI.saveTrainer();
            System.out.print("Saving Trianer " + name + " to database");
        }
        catch (Exception e) {
            System.out.println("Error writing to trainer.xml file: " + e);
        }
    }

    public void editMember() {
        boolean goodInput = false; // LCV
        //list the members and ask the user to choose the member profile to edit
        /* Take in all the fields from adding a member and run the same code again,
         * (below). We store those values in the variables, and call setter methods to change
         * values currently in that member that you have just read in.
         */
        System.out.println(gymAPI.listMembers());

        if (gymAPI.getMembers().size() != 0){  //validation
            //only process the update if members exist in the ArrayList
            int index = 0;
            System.out.print("Enter the index of the member to update ==> ");
            input.nextLine(); //dummy read of String to clear the buffer

            if ((index >= 0) && (index < gymAPI.getMembers().size()))  // validation index being passed
            {
                //gather new details for each field from the user
                input.nextLine();
                System.out.print("Enter Address: ");
                String address = input.nextLine();
                System.out.print("Specify Gender (M or F): ");
                String gender = input.nextLine();
                System.out.print("Enter height in meters: ");
                float height = input.nextFloat();
                input.nextLine();
                System.out.print("Enter Starting Weight, in KG's: ");
                float startWeight = input.nextFloat();
                input.nextLine();  //input.nextLine();  // swallows Scanner Bug
                for(String packageChoice: packages.keySet()) { // get keyset value from map
                    System.out.println(packageChoice + " " + packages.get(packageChoice)); // displaying package and package value
                }
                System.out.print("Choose Gym Package: ");
                String chosenPackage = input.nextLine();
                do {
                    if(packages.containsKey(chosenPackage) ) { // checking user input contains/ matches package choice entered
                        //retrieve the selected member from the Arraylist and update the details
                        Member member = gymAPI.getMembers().get(index);
                        member.setAddress(address);
                        member.setGender(gender);
                        member.setHeight(height);
                        member.setStartWeight(startWeight);
                        member.setChosenPackage(chosenPackage);
                        if(goodInput = true){
                            try {
                                gymAPI.saveMember();
                            } catch (Exception e) {
                                System.err.println ("Error loading from file: " +e);
                            }
                        }
                    }  else {
                        System.out.println("Invalid - Package description entered does not match.");
                    }
                } while(!goodInput);
            }
            else
            {
                System.out.println("There are no member for index number entered");
            }
        }
    }

    private void addAssessment() {

        boolean goodInput = false;
        while(!goodInput) {
            try {
                System.out.println("Please enter weight (in Kg's:");
                double weight = input.nextDouble();
                input.nextLine();
                goodInput = true;
            } catch (Exception e) {
                input.nextLine(); // swallows Scanner Bug
                System.out.println("Number expected - you entered text");
            }
        }
        while(!goodInput) {
            try {
                System.out.println("Please enter chest dimensions(in cm: ");
                double chest = input.nextDouble();
                input.nextLine();
                goodInput = true;
            } catch (Exception e) {
                input.nextLine(); // swallows Scanner Bug
                System.out.println("Number expected - you entered text");
            }
        }
        while(!goodInput) {
            try {
                System.out.println("Please enter thigh dimensions(in cm: ");
                double thigh = input.nextDouble();
                input.nextLine();
                goodInput = true;
            } catch (Exception e) {
                input.nextLine(); // swallows Scanner Bug
                System.out.println("Number expected - you entered text");
            }
        }
        while(!goodInput) {
            try {
                System.out.println("Please enter upper arm dimensions(in cm: ");
                double upperArm = input.nextDouble();
                input.nextLine();
                goodInput = true;
            } catch (Exception e) {
                input.nextLine(); // swallows Scanner Bug
                System.out.println("Number expected - you entered text");
            }
        }
        while(!goodInput) {
            try {
                System.out.println("Please enter waist dimensions(in cm: ");
                double waist = input.nextDouble();
                input.nextLine();
                goodInput = true;
            } catch (Exception e) {
                input.nextLine(); // swallows Scanner Bug
                System.out.println("Number expected - you entered text");
            }
        }
        while(!goodInput) {
            try {
                System.out.println("Please enter hip dimensions(in cm: ");
                double hips = input.nextDouble();
                input.nextLine();
                goodInput = true;
            } catch (Exception e) {
                input.nextLine(); // swallows Scanner Bug
                System.out.println("Number expected - you entered text");
            }
        }
        //gymAPI.addAssessment(new Assessment(member.weight, members.chest, members.thigh, upperArm, waist, members.hips));
    }


    public void deleteMember() {
        //list the members and ask the user to choose the member to delete
        System.out.println(gymAPI.listMembers());
        if(gymAPI.getMembers().size() >0) {  //validation
            System.out.print("Enter the index of the member to delete ==> ");
            int index = input.nextInt(); //take in value from user representing index of member to delete
            /**
             * Checking that input is less >0 and less than the size of the member arraylist.
             * Ensures user input to access a valid member between zero and size of array
             */
            if((index >= 0) && (index < gymAPI.getMembers().size())){  //validation
                //if the index is valid, delete the product at the given index
                gymAPI.getMembers().remove(index);
                System.out.println("Member Removed.");
            }
            else {

                System.out.println("There is no Member for this index number");
            }
        }

    }


}
