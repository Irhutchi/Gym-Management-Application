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
    private Scanner input = new Scanner(System.in);
    private ArrayList<Member> members;
    private GymAPI gymAPI;
    private HashMap<String, String> packages; //create hash map
    /*
     * Constructor for objects of class MenuController
     */
    public  MenuController() {
        members = new ArrayList<Member>();
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
        //app.startSystem();
        app.run();
    }

    /**
     * Print a welcome message to the screen.
     * Prompt the user to login or register
     * @return the users menu choice
     */
    private String welcomeMenu() {
        System.out.println("Welcome to our new gym.");
        System.out.println();
        System.out.println("Do you want to continue as a trainer or member? ");
        System.out.println("login as a trainer or member: ");
        System.out.println("Enter (l) to login or (r) to register: ");
        System.out.println("Please type 'exit', to exit our system.");
        System.out.println(">");

        String choice = input.nextLine();
        return choice;
    }

    /**
     * switch statement compares the String object in its expression
     * with the expressions associated with each case label
     */
    public void startSystem() {
        boolean exit = false; // initialize exit to false
        do {
            String choice = welcomeMenu();
            switch (choice) {
                case "r":     registration();
                    break;
                case "l":     login();
                    break;
                case "exit":  exit = true;
                    System.out.println("Invalid option entered: " + choice);
                    break;
                default:
                    throw new IllegalArgumentException("Invalid option selected. " + choice);
            }

        } while (!exit);
        //pause the program so that the user can read what we just printed to the terminal window
        System.out.println("\nPress any key to continue...");
        input.nextLine();
        /*this second read is required - bug in Scanner class;
         *a String read is ignored straight after reading an int.*/
        input.nextLine();
        printGoodbye();
    }

    private void registration() {

    }
    private void login()  {

    }

    /**
     * Print a good-bye message to the screen.
     */
    private void printGoodbye()
    {
        System.out.println("Thanks for visiting. Bye...");
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
        System.out.println("  2) List all members");
        System.out.println("  3) Update a member's details");
        System.out.println("  4) Delete a member");
        System.out.println("  5) Save member to people.xml");
        //System.out.println("  6) Load member from people.xml");
        System.out.println("  6) Number of members");
        System.out.println("===============");
        System.out.println("  0) Exit");
        System.out.print("==>> ");

        int option = input.nextInt();
        return option;
    }

    private void run() {
        {
            int option = trainerMenu();
            while (option != 0) {
                switch (option) {
                    case 1:     addMember();
                        break;
                    case 2:     System.out.println(gymAPI.listMembers());
                        break;
                    case 3:     editMember();
                        break;
                    case 4:     deleteMember();
                        break;
                    case 5:
                        try {
                            gymAPI.saveMember();
                        }
                        catch (Exception e) {
                            System.err.println("Error writing to file: " + e);
                        }
                        break;
                    case 6:
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
            System.out.println("Exiting... bye");
            System.exit(0);
        }
    }

    private void addMember() { //create a member object
        boolean goodInput = false; // LCV
        boolean vaildEmail = false;
        Set keySet = packages.keySet(); // get keyset value from packages map
        //dummy read of String to clear the buffer - bug in Scanner class.
        input.nextLine();
        float startWeight= 0;
        float height =0;
        String email = "";
        System.out.print("Enter Email: ");
        email = input.nextLine();
        /*while(!vaildEmail) {
            try {
                System.out.print("Enter Email: ");
                email = input.nextLine();
                for (Member member: members) {  // iterate through every member
                    if (!member.getEmail().equals(email)) { // if email is not '=' to another email in member array, return true.
                        vaildEmail = true;
                    }

                }
            }catch (Exception e) {
                input.nextLine(); // swallows Scanner Bug
                System.out.println("Email not valid, enter another: ");
                }
        }*/
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
                input.nextLine(); // swallows Scanner Bug
                System.out.println("Number expected - you entered text");
            }
        } while (!goodInput);
        //System.out.println("Choose Gym Package : ");

        for(String packageChoice: packages.keySet()) { // get keyset value from map
            System.out.println(packageChoice + " " + packages.get(packageChoice)); // displaying package and package value
        }
        input.nextLine();  // swallows Scanner Bug
        System.out.print("Choose Gym Package : ");
        String chosenPackage = input.nextLine();
        do {
            if(packages.containsKey(chosenPackage) ) { // checking user input contains/ matches package choice entered
                gymAPI.addMember(new Member(email, name, address, gender, height, startWeight, chosenPackage)); // checking if valid package user will be added
                goodInput = true;
            }  else {
                System.out.println("Invalid - Package description entered does not match.");
            }
        } while(!goodInput);

        /*creates a Member object with the details entered by the user.
         * calls the add method in the gymAPI class to add the Member
         * object to the members ArrayList.*/
        //gymAPI.addMember(new Member(email, name, address, gender, height, startWeight, chosenPackage));
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

    public void editMember() {
        //list the members and ask the user to choose the DVD to edit
        /* Take in all the fields from adding a member and run the same code again,
         * (below). We store those values in the variables, and call setter methods to change
         * values currently in that member that you have just read in.
         */
        System.out.println(gymAPI.listMembers());

        if (gymAPI.getMembers().size() != 0){
            //only process the update if members exist in the ArrayList
            System.out.print("Enter the index of the member to update ==> ");
            int index = 0;

            if ((index >= 0) && (index < gymAPI.getMembers().size()))
            {
                //gather new details for each field from the user
                input.nextLine(); //dummy read of String to clear the buffer
                input.nextLine();
                System.out.print("Enter Name: ");
                String name = input.nextLine();
                System.out.print("Enter Address: ");
                String address = input.nextLine();
                System.out.print("Specify Gender (M or F): ");
                String gender = input.nextLine();
                System.out.println(gender);
                System.out.print("Enter height in meters: ");
                float height = input.nextFloat();
                input.nextLine();
                System.out.print("Enter Starting Weight, in KG's: ");
                float startWeight = input.nextFloat();
                input.nextLine();
                System.out.print("Chose Gym Package (1, 2 or 3): ");
                String chosenPackage = input.nextLine();

                //retrieve the selected member from the Arraylist and update the details
                Member member = gymAPI.getMembers().get(index);
                member.setName(name);
                member.setAddress(address);
                member.setGender(gender);
                member.setHeight(height);
                member.setStartWeight(startWeight);
                member.setChosenPackage(chosenPackage);
            }
            else
            {
                System.out.println("There is no member for index number entered");
            }
        }
    }



    public void deleteMember() {
        //list the members and ask the user to choose the product to delete
        System.out.println(gymAPI.listMembers());
        System.out.print("Enter the index of the member to delete ==> ");
        int index = input.nextInt();

        if((index >= 0) && (index < gymAPI.getMembers().size())){
            //if the index is valid, delete the product at the given index
            gymAPI.getMembers().remove(index);
            System.out.println("Member Removed.");
        }
        else {

            System.out.println("There is no Member for this index number");
        }
    }

    private void addTrainer() {
        //dummy read of String to clear the buffer - bug in Scanner class.
        input.nextLine();
        //create a trainer object
        System.out.print("Please enter the trainer's Email: ");
        input.nextLine();
        String email = input.nextLine();
        System.out.print("Enter your Name: ");
        String name = input.nextLine();
        System.out.print("Enter Address: ");
        String address = input.nextLine();
        System.out.print("Specify Gender (M or F): ");
        String gender = input.nextLine();
        System.out.println(gender);
        System.out.print("Enter trainers speciality: ");
        String speciality = input.nextLine();

        gymAPI.addTrainer(new Trainer(email, name, address, gender, speciality));
    }
}
