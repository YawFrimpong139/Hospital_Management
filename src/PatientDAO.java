import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PatientDAO {

    //========================CRUD OPERATIONS============================
    //Inserting a new Patient
    public static void AddPatient(String PatientNumber, String Surname, String FirstName, String Address, String PhoneNumber)throws SQLException {
        String sql = "Insert into Patient (PatientNumber, Surname,FirstName, Address, PhoneNumber ) values (?,?,?,?,?)";

        try(Connection connection = DBConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)
        ){
            statement.setString(1, PatientNumber);
            statement.setString(2, Surname);
            statement.setString(3, FirstName);
            statement.setString(4, Address);
            statement.setString(5, PhoneNumber);

            int affectedRows = statement.executeUpdate();

            if(affectedRows == 0){
                throw new SQLException("Creating Patient failed, no rows affected!!");
            }

            try(ResultSet generatedKeys = statement.getGeneratedKeys()){
                if(generatedKeys.next()){
                    System.out.println("Patient Added Successfully with ID:" + generatedKeys.getInt(1));
                }else{
                    System.out.println("Creating Patient failed, no ID obtained.");
                }
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    public static List<Patient> getAllPatients() throws SQLException{
        List<Patient> patients = new ArrayList<>();
        String sql = "SELECT * FROM Patient";

        try(Connection connection = DBConnection.getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql)
        ){
            while(resultSet.next()){
                Patient patient = new Patient();
                patient.setPatientID(resultSet.getInt("PatientID"));
                patient.setSurname(resultSet.getString("Surname"));
                patient.setFirstName(resultSet.getString("FirstName"));
                patient.setAddress(resultSet.getString("Address"));
                patient.setPhoneNumber(resultSet.getString("PhoneNumber"));
                patient.setPatientNumber(resultSet.getString("PatientNumber"));

                patients.add(patient);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return patients;

    }


    //Updating a Patient
    public static void updatePatient(String PatientNumber, String Surname, String FirstName, String Address, String PhoneNumber) throws Exception{
        String sql = "UPDATE Patient SET Surname = ?, FirstName = ?, Address = ?, PhoneNumber = ? WHERE PatientNumber = ?";

        try(Connection connection = DBConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql)){

            //statement.setString(1, PatientNumber);
            statement.setString(1, Surname);
            statement.setString(2, FirstName);
            statement.setString(3, Address);
            statement.setString(4, PhoneNumber);
            statement.setString(5, PatientNumber);
            //statement.setInt(6, PatientID);

            int affectedRows = statement.executeUpdate();

            if(affectedRows == 0){
                throw new SQLException("Updating patient failed, no rows affected");
            }

            System.out.println("Patient Updated Successfully");
        }
    }

    public static void deletePatient(String PatientNumber) throws Exception{
        //First check if the patient has admission records
        String checksql = "SELECT COUNT(*) FROM Admission WHERE PatientID = ? ";
        String deletesql = "DELETE FROM Patient WHERE PatientNumber = ?";

        try(Connection connection = DBConnection.getConnection();
            PreparedStatement checkStatement = connection.prepareStatement(checksql);
            PreparedStatement deleteStatement = connection.prepareStatement(deletesql)){

            int patientId = getPatientIdByNumber(connection, PatientNumber);

            checkStatement.setInt(1, patientId);
            ResultSet rs = checkStatement.executeQuery();

            if(rs.next() && rs.getInt(1) > 0){
                throw new SQLException("Cannot delete patient with existing admission.");
            }

            deleteStatement.setString(1, PatientNumber);

            int affectedRows = deleteStatement.executeUpdate();

            if(affectedRows == 0){
                throw new SQLException("Deleting patient failed, no rows affected.");
            }

            System.out.println("Patient deleted successfully!");
        }
    }

    private static int getPatientIdByNumber(Connection connection, String patientNumber) throws SQLException {
        String sql = "SELECT PatientID FROM Patient WHERE PatientNumber = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, patientNumber);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt("PatientID");
            }
            throw new SQLException("Patient not found with number: " + patientNumber);
        }
    }
}
