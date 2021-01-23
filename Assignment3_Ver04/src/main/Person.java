/**
 * @author Ian Hutchinson
 * @version 4.0
 */

/**
 * The Person class stores details about the person
 * name, email, address and gender,
 */
public class Person {
    private String name;    // the person's full name
    private String email;   // the person's email address
    private String address; // the person's home address
    private String gender;  // the person's gender (optional)
    /**
     * Constructor for objects of class Person
     * @param email Email of the person
     * @param name Name of the person
     * @param address address of the person
     * @param gender gender cost of the person
     */
    public Person(String email, String name, String address, String gender) {
        this.name =name;
        this.email = email; //this.(refers to object property field) = email on it's own is passed into the method
        this.address = address;
        this.gender = gender;
    }


    //-------
    //setters
    //return information about the state of an object, i.e. the values stored in the fields.
    //-------
    /**
     * Updates the email address to the value, 'String' passed as a parameter
     * @param email The new email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Updates the Name to the value passed as a parameter
     * @param name The new name of person is set
     */
    public void setName(String name) {
        if (name.length() < 30) // if name is less than 30 chars, name is okay
        {
            this.name = name;
        } else {
            this.name = name.substring(0, 30); // else trim back to 30 characters
        }
    }

    /**
     * @param gender the String passed will set the gender of the person
     *               if f or m is not chosen, gender is assigned 'unspecified.
     */
    public void setGender(String gender) {
        if((gender.equals("M")) || (gender.equals("m"))) {
            this.gender = "M";
        } else if ((gender.equals("F") || gender.equals("f"))) {
            this.gender = "F";
        } else {
            this.gender = "Unspecified";
        }
    }

    /**
     * Updates the person's address to the value passed as a parameter
     * @param address The new Person's address
     */
    public void setAddress(String address) {
        this.address = address;
    }

    //-------
    //getters
    //contains a return statement and does not change the object state
    //-------

    /**
     * @return returns the person's name
     */
    public String getName() {
        return name;
    }
    /**
     * @return returns the person's email
     */
    public String getEmail() {
        return email;
    }
    /**
     * @return returns the person's address
     */
    public String getAddress() {
        return address;
    }
    /**
     * @return returns the person's gender
     */
    public String getGender() {
        return gender;
    }

    /**
     * Builds a String representing a user friendly representation of the object state
     * @return Formats the printing of the person object state and returns it
     */
    public String toString() {
        return " Email: " + email + ", " + "Name: " + name + ", "
                + "Address: " + address + ", " + "Gender: " + gender ;
    }


}