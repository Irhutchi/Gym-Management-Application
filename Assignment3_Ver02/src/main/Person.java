/**
 * @author Ian Hutchinson
 * @version 2.0
 */

import java.util.HashMap;

public class Person {
    private String name;
    private String email;
    private String address;
    private String gender;
    public String password;
    //HashMap<String, String> email = new HashMap<String, String>();
    /**
     * Default, empty constructor
     * Can instantiate a subclass with a no-arg constructor
     */
    public Person(String password) {
        this.password = password;
    }


    /**
     * Constructor for objects of class Person
     * @param email Email of the person
     * @param name Name of the person
     * @param address address of the person
     * @param gender gender cost of the person
     */
    public Person(String email, String name, String address, String gender) {
        setName(name);
        this.email = email; //this.(refers to field) = referring to the parameter on RHS
        this.address = address;
        setGender(gender);
    }

    //-------
    //setters
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
     *
     * @param password the String passed is set for the person
     */
    public void setPassword(String password) {
        this.password = password;
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
     * @return returns the person's password
     */
    public String getPassword() {return password; }

    /**
     * Builds a String representing a user friendly representation of the object state
     * @return Details of the specific person
     */
    public String toString() {
        return " Email: " + email + ", " + "Name: " + name + ", "
                + "Address: " + address + ", " + "Gender: " + gender ;
    }


}