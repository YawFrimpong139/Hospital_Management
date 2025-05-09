public class Patient {
    private int PatientID;

    private String PatientNumber;
    private String Surname;
    private String FirstName;
    private String Address;
    private String PhoneNumber;

    public int getPatientID() {
        return PatientID;
    }

    public void setPatientID(int patientID) {
        this.PatientID = patientID;
    }

    public String getPatientNumber() {
        return PatientNumber;
    }

    public void setPatientNumber(String patientNumber) {
        this.PatientNumber = patientNumber;
    }

    public String getSurname() {
        return Surname;
    }

    public void setSurname(String surname) {
        this.Surname = surname;
    }

    public String getFirstName() {
        return FirstName;
    }

    public void setFirstName(String firstName) {
        this.FirstName = firstName;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        this.Address = address;
    }

    public String getPhoneNumber() {
        return PhoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.PhoneNumber = phoneNumber;
    }

    @Override
    public String toString() {
        return "Patient{" +
                " PatientID = " + PatientID +
                ", PatientNumber = '" + PatientNumber + '\'' +
                ", Surname = '" + Surname + '\'' +
                ", FirstName = '" + FirstName + '\'' +
                ", Address = '" + Address + '\'' +
                ", PhoneNumber = '" + PhoneNumber + '\'' +
                '}';
    }



}
