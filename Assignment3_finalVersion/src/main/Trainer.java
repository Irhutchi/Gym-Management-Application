import java.util.ArrayList;
import java.util.HashMap;
import java.util.SortedSet;

/**
 * The Trainer class stores specific details about the trainer
 * the trainers speciality
 * name, email, address and gender are all inherited from the parent class - Person
 */
public class Trainer extends Person {
   // Encapsulation - declare all fields private
   private String speciality;   // the trainers speciality
   private ArrayList<Assessment> assessments = new ArrayList<Assessment>();

   /**
    * Construct  a Trainer instance with name, address, email and gender inerited from Person
    *
    * @param speciality unique property to trainer and returns their speciality of String type
    */
   public Trainer(String email, String name, String address, String gender, String speciality) {
      super(email, name, address, gender);
      this.speciality = speciality;
   }

   /**
    * @return returns the speciality of trainer
    */
   public String getSpeciality() {
      return speciality;
   }

   /**
    * @param specialty assigns speciality to trainer assigned by user
    *                  takes in parameter and assigns string value to trainer
    */
   public void setSpeciality(String specialty) {
      this.speciality = speciality;
   }

   /**
    * @param assessment adds assessment object of type Assessment
    */
   public void addAssessment(Assessment assessment) {
      assessments.add(assessment);
   }

   /**
    * accessor method for the Arraylist of Assessments
    *
    * @return assessments from Arraylist
    */
   public ArrayList<Assessment> getAssessments() {
      return assessments;
   }

   public Assessment latestAssessment() {
      return latestAssessment();
   }

   //Returns the latest assessment based on last entry (by calendar date)
   public SortedSet<String> sortedAssessmentDates() {
      return sortedAssessmentDates();
   }

   /**
    * @return details of specific trainer.
    * Trianer is a subclass of person, thus overrides Person toString and also includes
    * trainers specific field speciality.
    */
   @Override
   public String toString() {
      return "Trainer Details: " + super.toString() + ", " //sending a call to the supeclass method
              + "speciality" + speciality;
   }
}