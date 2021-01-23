/**
 * A premium member has no special fields and inherits all properties from parent class
 * Premium member cannot hold a student membership package
 */

public class PremiumMember extends Member {


    public PremiumMember(String email, String name, String address,
                  String gender, float height, float startWeight, String chosenPackage) {
        super(email, name, address, gender, height, startWeight, chosenPackage);

    }


    /*public void chosenPackage(String packageChoice) {
        return chosenPackage;
    }*/

    public static Member findByEmail(String email)
    {
        return findByEmail(email);
    }


    @Override
    public String toString() {
        return "Member details: " + "\t" + " "
                + super.toString();
    }





}