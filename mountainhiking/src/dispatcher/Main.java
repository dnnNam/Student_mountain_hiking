package dispatcher;

import business.Mountains;
import business.Students;
import java.util.ArrayList;
import java.util.List;
import model.Student;
import tools.Acceptable;
import tools.Inputter;
import ui.Menu;

public class Main {

    public static void main(String[] args) {

        // tạo ra anh quản lý student 
        Students studentManagement = new Students();

        // tạo ra anh menu 
        Menu menu = new Menu("Mountain Hiking Challenge Registration.");
        // thêm option nha 
        menu.addNewOption("New Registration:");
        menu.addNewOption("Update Registration Information");
        menu.addNewOption("Display Registered List");
        menu.addNewOption("Delete Registration Information");
        menu.addNewOption("Search Participants by Name");
        menu.addNewOption("Filter Data by Campus");
        menu.addNewOption("Statistics of Registration Numbers by Location");
        menu.addNewOption("Save Data to File");
        menu.addNewOption("Exit the Program");

        Inputter inp = new Inputter();
        Mountains mn = new Mountains();
        studentManagement.readFromFile();
        mn.readFromFile();
        int choice = 0;
        while (true) {
            menu.print();
            choice = menu.getChoice();
            switch (choice) {
                case 1: {
                    String id = "";
                    boolean isDup = false;
                    Student st = null;
                    do {
                        id = inp.inputAndLoop("Input your id: ", Acceptable.STU_ID_VALID);
                        st = studentManagement.searchById(id.toUpperCase());
                        if (st == null) {
                            isDup = true;
                        }
                    } while (!isDup);
                    String name = inp.inputAndLoop("Input your name: ", Acceptable.NAME_VALID);
                    String phoneNumber = inp.inputAndLoop("Input your phone number: ", Acceptable.PHONE_VALID);
                    String email = inp.inputAndLoop("Input your email: ", Acceptable.EMAIL_VALID);
                    String mountainCode = "";
                    boolean isTrue = false;
                    while (!isTrue) {
                        mountainCode = inp.getString("Input your mountain code: ");
                        isTrue = mn.isValidMountainCode(mountainCode);

                    }

                    Student st1 = new Student(id, name, phoneNumber, email, mountainCode);
                    studentManagement.add(st1);
                    // thông  báo 
                    System.out.println("Adding successfully!!!");
                    break;

                }
                case 2: {
                    String id = "";
                    Student st = null;
                    id = inp.inputAndLoop("Input your id wanna to update: ", Acceptable.STU_ID_VALID);
                    st = studentManagement.searchById(id.toUpperCase());
                    if (st == null) {
                        System.out.println("This student has not registered yet");
                    } else {
                        String name = inp.inputAndLoop("Input your name: ", Acceptable.NAME_VALID);
                        String phoneNumber = inp.inputAndLoop("Input your phone number: ", Acceptable.PHONE_VALID);
                        String email = inp.inputAndLoop("Input your email: ", Acceptable.EMAIL_VALID);
                        String mountainCode = "";
                        boolean isTrue = false;
                        while (!isTrue) {
                            mountainCode = inp.getString("Input your mountain code: ");
                            isTrue = mn.isValidMountainCode(mountainCode);

                        }
                        Student st1 = new Student(id, name, phoneNumber, email, mountainCode);
                        studentManagement.update(st1);
                    }

                    break;
                }

                case 3: {
                    studentManagement.showAll();
                    break;
                }
                case 4: {
                    String id = inp.inputAndLoop("Input your id you wanna delete: ", Acceptable.STU_ID_VALID);
                    studentManagement.delete(id);
                    break;
                }
                case 5: {
                    String name = inp.getString("Input full name or a part of name: ");
                    studentManagement.searchByName(name);
                    break;
                }
                case 6: {
                    String campusCode = inp.getString("Input your campusCode wannna to find(e.g., CE,DE,HE,SE,QE): ");

                    List<Student> stuListCampusCode = new ArrayList<>();
                    stuListCampusCode = studentManagement.filterByCampusCode(campusCode.toUpperCase());
                    if(stuListCampusCode.isEmpty()){
                        System.out.println("No student have registered under this campus");
                    }else{
                        for (Student student : stuListCampusCode) {
                            System.out.println(student.toString());
                        }
                    }
                   
                    break;
                }
                case 7: {
                    studentManagement.statisticalizeByMountainPeak();
                    break;
                }
                case 8: {
                    studentManagement.saveToFile();
                    System.out.println("Registration data has been successfully saved to `StudentList.csv`.");

                    break;
                }
                case 9: {
                    boolean isSaved = studentManagement.isIsSaved();

                    if (isSaved == false) {
                        String result = "";
                        result = inp.getString("Do you want to save the changes before exiting? (Y/N): ");

                        if (result.matches("[yY]")) {
                            studentManagement.saveToFile();
                            return;
                        } else {
                            return;
                        }
                    } else {
                        return;
                    }
                }
                default: {
                    System.out.println("This function is not available");
                    break;
                }
            }
        }
    }
}
