import java.util.ArrayList;
import java.util.Scanner;

public class MenuController {
    private Scanner input = new Scanner(System.in);
    private ArrayList<Member> members;
    private GymAPI gymAPI;


    public static void main(String[] args) {
        MenuController app = new MenuController();
        app.run();
    }

    /*
     * Constructor for objects of class MenuController
     */
    public  MenuController() {
        members = new ArrayList<Member>();
        input = new Scanner(System.in);
        gymAPI = new GymAPI();
    }


    /**
     * trainerMenu() - This method displays the menu for the application,
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
        System.out.println("  3) Update a members details");
        System.out.println("  4) Delete a member");
        System.out.println("  5) Save member to people.xml");
        System.out.println("  6) Load member from people.xml");
        System.out.println("===============");;
        System.out.println("   0) Exit");
        System.out.print("==>> ");

        int option = input.nextInt();
        return option;
    }

    private void run() {
        {
            int option = trainerMenu();

            while (option != 0) {
                switch (option) {
                    case 1:
                        addMember();
                        break;
                    case 2:
                        System.out.println(gymAPI.listMembers());
                        break;
                    case 3:     editMember();
                        break;
                    case 4:     deleteMember();
                        break;
                    case 5:
                        try {
                            gymAPI.save();
                        }
                        catch (Exception e) {
                            System.err.println("Error writing to file: " + e);
                        }
                        break;
                    case 6:
                        try {
                            gymAPI.load();
                        }
                        catch (Exception e) {
                            System.err.println("Error loading from file: " + e);
                        }
                        break;
                    default:
                        System.out.println("Invalid option entered: " + option);
                        break;
                }
                //pause the program so that the user can read what we just printed to the terminal window
                System.out.println("\nPress any key to continue...");
                input.nextLine();
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

    private void addMember() {
        //dummy read of String to clear the buffer - bug in Scanner class.
        input.nextLine();
        //create a member object
        System.out.print("Enter Email: ");
        String email = input.nextLine();
        System.out.print("Enter Name: ");
        String name = input.nextLine();
        System.out.print("Enter Address: ");
        String address = input.nextLine();
        System.out.print("Specify Gender (M or F): ");
        String gender = input.nextLine();
        System.out.println(gender);
        if((gender.equals("M")) || (gender.equals("m"))) {
            gender = "Male";
        } else if ((gender.equals("F") || gender.equals("f"))) {
            gender = "female";
        } else {
            gender = "Unspecified";
        }
        /**
         *  while loop will continually prompt user until a valid height is entered
         */
        System.out.print("Enter height in meters: ");
        float height = input.nextFloat();
        input.nextLine();
        // -1 used as an number that is impossible for our member
        //float height = -1;
        /*while ((height <=1) && (height >=3)) {
            String UserInput = input.nextLine();
            try {
                //converting String 'UserInput' to a float
                height = Float.valueOf(UserInput);
            } catch (NumberFormatException e) { //if exception occurs, the user has input an invalid value
                System.err.println("Error: " + e);
                // invalid value will trigger '-1' which will make the while loop repeat
                height = -1;
            }
        }*/
        System.out.print("Enter Starting Weight, in KG's: ");
        float startWeight = input.nextFloat();

        input.nextLine();
        System.out.print("Chose Gym Package (1, 2 or 3): ");
        String chosenPackage = input.nextLine();

        /*creates a Member object with the details entered by the user.
         * calls the add method in the gymAPI class to add the Member
         * object to the members ArrayList.*/
        gymAPI.addMember(new Member(email, name, address, gender, height, startWeight, chosenPackage));
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
                System.out.print("Enter Email: ");
                String email = input.nextLine();
                System.out.print("Enter Name: ");
                String name = input.nextLine();
                System.out.print("Enter Address: ");
                String address = input.nextLine();
                System.out.print("Specify Gender (M or F): ");
                String gender = input.nextLine();
                System.out.println(gender);
                if((gender.equals("M")) || (gender.equals("m"))) {
                    gender = "Male";
                } else if ((gender.equals("F") || gender.equals("f"))) {
                    gender = "female";
                } else {
                    gender = "Unspecified";
                }
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
                member.setEmail(email);
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


}
