
// THIS CODE IS INCOMPLETE

public class Person {
    private String name;



    private String email;
    private String address;
    private String gender;

    /**
     * Default, empty constructor
     * Can instantiate a subclass with a no-arg constructor
     */
    public Person() {    }

    // constructor that takes person's name, email, address and gender
    public Person(String email, String name, String address, String gender) {
        setName(name);
        //this.(refers to field) = referring to the parameter on RHS
        this.email = email;
        this.address = address;
        setGender(gender);
    }

    public String toString() {
        return " Email: " + email + ", " + "Name: " + name + ", "
                + "Address: " + address + ", " + "Gender: " + gender ;
    }


    public void setEmail(String email) { this.email = email; }

    public void setName(String name) {
        this.name = name;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getAddress() {
        return address;
    }

    public String getGender() {
        return gender;
    }


}