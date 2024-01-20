public class StudentRecords implements StudentRecordsInterface {
    private String name;
    private int studentID;
    private String major;

    public StudentRecords(String name, int studentID, String major) {
        this.name = name;
        this.studentID = studentID;
        this.major = major;
    }

    public String getName() {
        return name;
    }

    public int getStudentID() {
        return studentID;
    }

    public String getMajor() {
        return major;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setStudentID(int studentID) {
        this.studentID = studentID;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    @Override
    public int compareTo(int id) {
        if (this.studentID == id) {
            return 0;
        } else if (this.studentID > id) {
            return 1;
        } else {
            return -1;
        }
    }

}
