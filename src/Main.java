import java.util.List;
import java.util.Objects;

public class Main {
    public static void main(String[] args) {

        try {

            //========= INSERTING DATA=========================
            System.out.println("Adding new patients...");
            PatientDAO.AddPatient("PAT001","Osei", "Benard", "Oyarifa Park", "233-567-9045");
            PatientDAO.AddPatient("PAT002","Ocran", "Jane", "Kaneshie Lane", "233-564-8767");

            //========= SHOWING ALL PATIENTS=========================
            System.out.println("\nAll patients:");
            List<Patient> patients = PatientDAO.getAllPatients();

            for (Patient patient : patients) {
                System.out.println(patient);
                System.out.println("----------");
            }

            //========= UPDATING AND DELETING OF DATA=========================
            if (!patients.isEmpty()) {
                // Test update
                String patientNumber = patients.get(0).getPatientNumber();
                System.out.println("\nUpdating patient #" + patientNumber);
                PatientDAO.updatePatient(patientNumber, "Smith", "Johnathan", "123 Main St Apt 4", "555-4321");

//                // Verify update
                System.out.println("\nUpdated patient:");
                Patient updatedPatient = PatientDAO.getAllPatients().stream()
                        .filter(p -> Objects.equals(p.getPatientNumber(), patientNumber))
                        .findFirst()
                        .orElse(null);
                System.out.println(updatedPatient);
//
//                // Test delete
                System.out.println("\nDeleting patient #" + patientNumber);
                PatientDAO.deletePatient(patientNumber);

                // Verify delete
                System.out.println("\nRemaining patients:");
                PatientDAO.getAllPatients().forEach(System.out::println);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}
