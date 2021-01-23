
// THIS CODE IS INCOMPLETE

public class Member extends Person {
    private float height;
    private float startWeight;
    private String chosenPackage;

    public Member(String email, String name, String address,
                  String gender, float height, float startWeight, String chosenPackage) {
        super(email, name, address, gender);
        setHeight(height);
        setStartWeight(startWeight);
        setChosenPackage(chosenPackage);
    }

    public static Member findByEmail(String email)
    {
        return findByEmail(email);
    }

    @Override
    public String toString() {
        return "Member details: " + "\t" + " "
                + super.toString() + ", " //sending a call to the supeclass method
                + ", Height: " + height
                + ", Starting weight: " + startWeight
                + ", Chosen Package: "  + chosenPackage;
    }

    public float getHeight() {
        return height;
    }

    public void setHeight(float height) {
        this.height = height ;
    }

    public float getStartWeight() {
        return startWeight;
    }

    public void setStartWeight(float startWeight) {
        this.startWeight = startWeight ;
    }

    public String getChosenPackage() {
        return chosenPackage;
    }

    public void setChosenPackage(String chosenPackage) {
        this.chosenPackage = chosenPackage;
    }

}

