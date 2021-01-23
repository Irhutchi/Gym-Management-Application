
/**
 * The Member class stores specific details about the member
 * height, starting weight and chosen package
 * name, email, address and gender are all inherited from the parent class - Person
 */
public class Member extends Person {
   protected float height;
   protected float startWeight;
   protected String chosenPackage;



   public Member(String email, String name, String address,
                 String gender, float height, float startWeight, String chosenPackage) {
      super(email, name, address, gender);
      setHeight(height);
      setStartWeight(startWeight);
      this.chosenPackage = chosenPackage;

   }

   //-------
   //getters
   //contains a return statement and does not change the object state
   //-------
   /**
    * @return returns the member's height
    */
   public float getHeight() {
      return height;
   }
   /**
    *
    * @return returns the member's starting weight
    */
   public float getStartWeight() {
      return startWeight;
   }

   public String getChosenPackage() {
      return chosenPackage;
   }

   //-------
   //setters
   //takes in information about the state of an object, i.e. the values stored in the fields.
   // changes the object state
   //-------
   /**
    * @param startWeight assigns member start weight as type float
    *                    validating Starting Weight measured in kgs and is
    *                    between 35 and 250. else default to 100.
    */
   public void setStartWeight(float startWeight) {
      if ((startWeight >= 35.0f) && (startWeight <= 250.0f)) {
         this.startWeight = startWeight;
      } else {
         this.startWeight = 100.0f;
      }
   }
   /**
    * @param height assigns height as type float
    *               validating user height entered is between 1-3m
    *               otherwise return default height of 1.5m
    */
   public void setHeight(float height) {
      if ((height >= 1.0f) && (height <= 3.0f)) {
         this.height = height;
      } else {
         this.height = 1.5f;
      }
   }
   /**
    * @param chosenPackage assigns the chosenPackage by the user
    */
   public void setChosenPackage(String chosenPackage) {
      this.chosenPackage = chosenPackage;
   }

   /**
    * Builds a String representing a user friendly representation of the member state
    * Member inheriting all common fields from Person; email, name, email, address & gender
    *
    * @return Details of the specific member
    */
   @Override
   public String toString() {
      return "Member details:" + "\t"
              + super.toString()  //sending a call to the supeclass method
              + ", Height: " + height
              + ", Starting weight: " + startWeight
              + ", Chosen Package: " + chosenPackage;
   }
}

