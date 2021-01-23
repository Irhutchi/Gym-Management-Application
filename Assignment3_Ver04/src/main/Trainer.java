import java.util.ArrayList;
import java.util.SortedSet;

/**
 * The Trainer class stores specific details about the trainer
 * the trainers speciality
 * name, email, address and gender are all inherited from the parent class - Person
 */
public class Trainer extends Person {

    private String speciality;
    //private int numAssessments;     //number of assessments given to members
    private ArrayList<Assessment> assessments = new ArrayList<Assessment>();

    // Construct  a Trainer instance with a name, address, email and gender
    public Trainer(String email, String name, String address, String gender, String speciality) {
        super(email, name, address, gender);
        this.speciality = speciality;
        //numAssessments = 0;

    }

    public String getSpeciality() {
        return speciality;
    }

    public void setSpeciality(String specialty) {
        this.speciality = speciality;
    }


    public void addAssessment(Assessment assessment) {
        assessments.add(assessment);
    }
    public ArrayList<Assessment> getAssessments() {return assessments; }

    public Assessment latestAssessment() {
        return latestAssessment();
    }

    //Returns the latest assessment based on last entry (by calendar date)
    public SortedSet<String> sortedAssessmentDates() {
        return sortedAssessmentDates();
    }

    @Override
    public String toString() {
        return "Trainer Details: " + super.toString() + ", " //sending a call to the supeclass method
                + "speciality" + speciality;
    }


    /** Adds a course. Returns false if the course has already existed */
    /*public boolean addAssessment(String assessment) {
        // Check if the course already in the course list
        for (int i = 0; i < numAssessments; i++) {
            if (assessments.get(i).equals(assessment)) return false;
        }
        assessments.set(numAssessments, Assessment);
        numAssessments++;
        return true;
    }

    /** Removes a course. Returns false if the course cannot be found in the course list */
    /*public boolean removeCourse(String course) {
        boolean found = false;
        // Look for the course index
        int courseIndex = -1;  // need to initialize
        for (int i = 0; i < numAssessments; i++) {
            if (assessments.get(i).equals(assessment)) {
                assessmentIndex = i;
                found = true;
                break;
            }
        }
        if (found) {
            // Remove the course and re-arrange for courses array
            for (int i = courseIndex; i < numAssessments-1; i++) {
                assessments.get(i) = assessments.get(i+1);
            }
            numAssessments--;
            return true;
        } else {
            return false;
        }
    }
  }*/
}