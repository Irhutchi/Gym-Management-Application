import java.util.ArrayList;
import java.util.List;

public class GymUtility {

    public float bmi;

    public List<Assessment> assessments = new ArrayList<Assessment>();

    /*public float memberBMI() {
        if(assessments.size()!=0) {
            Assessment assessment = assessments.get(assessments.size()-1);
            bmi = (float) (assessment.weight / (member.height * height));
            return toTwoDecimalPlaces(bmi);
        }
        else {
            System.out.println("Calculating member bmi " + bmi);
            return toTwoDecimalPlaces(startingWeight / (height * height));
        }
    }*/

    public String bmiResult() {
        String result = "";

        if (bmi < 16.0f) {
            result = "SEVERELY UNDERWEIGHT";
        } else if ((bmi >= 16.0f) && (bmi < 18.5f)) {
            result = "UNDERWEIGHT";
        } else if ((bmi >= 18.5f) && (bmi < 25.0f)) {
            result = "NORMAL";
        } else if ((bmi >= 25.0f) && (bmi < 30.0f)) {
            result = "OVERWEIGHT";
        } else if ((bmi >= 30.0f) && (bmi < 35.0f)) {
            result = "OVERWEIGHT";
        } else if (bmi >= 35.0f) {
            result = "SEVERELY OBESE";
        }
        System.out.println("Member BMI is " + bmi + " and that means you are " + result);
        return result;

    }
    private float toTwoDecimalPlaces(double num) {
        return (float) ((int) (num * 100) / 100.0);
    }
}
