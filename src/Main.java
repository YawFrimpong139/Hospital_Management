
public class Main {
    public static void main(String[] args) {

        try{
            System.out.println("Adding new patients...");
            PatientDAO.AddPatient("PAT001","Osei", "Benard", "Oyarifa Park", "233-567-9045");
            PatientDAO.AddPatient("PAT002","Ocran", "Jane", "Kaneshie Lane", "233-564-8767");


        }catch(Exception e){
            e.printStackTrace();
        }
        //Adding Patient



        }
    }
