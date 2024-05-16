package human;


import java.util.Date;

public abstract class Human {
    private String firstName;
    private String lastName;
    private String gender;
    private Date dateOfBirth;
    private String iris;
    private String fingerPrint;
    private final HumanBody humanBody;
    private Head head;
    private Arm leftArm;
    private Arm rightArm;
    private Leg leftLeg;
    private Leg rightLeg;

    public Human(String firstname, String lastname, Date dateOfBirth, String iris, String fingerPrint) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.gender = gender;
        this.iris = iris;
        this.fingerPrint = fingerPrint;

        humanBody = new HumanBody();
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastname(String lastName) {
        this.lastName = lastName;
    }
    public String getGender() {return gender;}
    public void setGender(String gender) {this.gender = gender;}

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getIris() {
        return iris;
    }
    public void setIris(String iris) {
        this.iris = iris;
    }

    public String getFingerPrint() {
        return fingerPrint;
    }

    public void setFingerPrint(String fingerPrint) {
        this.fingerPrint = fingerPrint;
    }

    public void addArms(Arm arm01, Arm arm02) {
        humanBody.addLeftArm(arm01);
        humanBody.addRightArm(arm02);
    }
    public void addLegs(Leg leg01, Leg leg02) {
        humanBody.addLeftLeg(leg01);
        humanBody.addRightLeg(leg02);
    }

}
