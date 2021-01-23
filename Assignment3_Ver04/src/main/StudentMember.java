public class StudentMember extends Member  {


    private String studentId;
    private String collegeName;

    public StudentMember(String email, String name, String address,
                  String gender, float height, float startWeight, String chosenPackage, String studentId, String collegeName) {
        super(email, name, address, gender, height, startWeight, chosenPackage);
        this.studentId = studentId;
        this.collegeName = collegeName;


    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
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
