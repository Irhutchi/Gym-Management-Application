public class StudentMember extends Member  {


    private double studentId;
    private String collegeName;

    public StudentMember(String email, String name, String address,
                  String gender, float height, float startWeight, String chosenPackage) {
        super(email, name, address, gender, height, startWeight, chosenPackage);
        setStudentId(studentId);
        setCollegeName(collegeName);

    }

    public double getStudentId() {
        return studentId;
    }

    public void setStudentId(double studentId) {
        this.studentId = studentId;
    }

    public String getCollegeName() {
        return collegeName;
    }

    public void setCollegeName(String collegeName) {
        this.collegeName = collegeName;
    }

    @Override
    public String toString() {
        return "Member details: " + "\t" + " "
                + super.toString() + ", " //sending a call to the parent class Member
                + ", Student ID: " + studentId
                + ", College Name: " + collegeName;

    }
}
