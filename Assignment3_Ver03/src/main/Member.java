
// THIS CODE IS INCOMPLETE

import java.util.ArrayList;
import java.util.SortedSet;

public class Member extends Person {
    protected float height;
    protected float startWeight;
    protected String chosenPackage;


    public Member(String email, String name, String address,
                  String gender, float height, float startWeight, String chosenPackage) {
        super(email, name, address, gender);
        this.height = height;
        this.startWeight = startWeight;
        this.chosenPackage = chosenPackage;

    }




    public static Member findByEmail(String email)
    {
        return findByEmail(email);
    }



    //-------
    //getters
    //-------
    public float getHeight() {
        return height;
    }

    public void setHeight(float height) {
        if((height >= 1.0f) && (height <= 3.0f)) {
            this.height = height ;
        }
        else {
            this.height = 1.5f;
        }
    }

    public float getStartWeight() {
        return startWeight;
    }

    /*while(!goodInput) {
        try {
            System.out.print("Enter height in meters: ");
            height = input.nextFloat();
            input.nextLine();
            goodInput = true;
        } catch (Exception e) {
            input.nextLine(); // swallows Scanner Bug
            System.out.println("Number expected - you entered text");
        }

    }*/

    public String getChosenPackage() {
        return chosenPackage;
    }

    //-------
    //setters
    //-------
    public void setStartWeight(float startWeight) {
        if ((startWeight >= 35.0) && (startWeight <= 250.0))
        {
            this.startWeight = startWeight;
        } else {
            this.startWeight = 100.0f;
        }
    }

    public void setChosenPackage(String chosenPackage) {
        this.chosenPackage = chosenPackage;
    }




    /**
     * Builds a String representing a user friendly representation of the member state
     * Member inheriting all common fields from Person; email, name, email, address & gender
     * @return Details of the specific member
     */
    @Override
    public String toString() {
        return "Member details: " + "\t" + " "
                + super.toString() + ", " //sending a call to the supeclass method
                + ", Height: " + height
                + ", Starting weight: " + startWeight
                + ", Chosen Package: "  + chosenPackage;
    }
}

