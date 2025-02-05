
package business;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.OutputStreamWriter;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import model.Student;
import tools.Acceptable;
import tools.Inputter;


public class Students{
    public ArrayList<Student> stuList = new ArrayList<>();
    // các properties 
    private boolean isSaved;
    private Inputter inp = new Inputter();
    
    
    // cái phễu rỗng
    public Students(){
       
    }

    public boolean isIsSaved() {
        return isSaved;
    }
    
    
    // hàm add 1 học sinh 
    public void add(Student x){
        stuList.add(x); // thêm sinh viên vào mảng 
    }
    // hàm tìm kiếm học sinh theo id 
    public Student searchById(String id){
        for (Student item : stuList) {
            if(item.getId().equals(id)){
                return item;
            }
        }
        // nếu không thấy 
        return null;
    }
    
    // hàm tìm theo tên 
    public void searchByName(String name){
        List<Student> nameStu = new ArrayList<>();
       
        for (Student item : stuList) {
            if(item.getName().contains(name.trim())){
                nameStu.add(item);
            }
        }
        
        String str = String.format(
          "  |------------------------------------------------------------------|\n" +
          "  | Student ID |  Name         |  Phone      | Mountain     | Fee  \n" +
          "  |------------------------------------------------------------------|\n");
        String str1 = String.format(    
          "  |------------------------------------------------------------------|");
        if(nameStu.isEmpty()){
            System.out.println("No one matches the search criteria!");
        }else{
            
            System.out.println(str);
            for (Student student : nameStu) {
                System.out.println(student.toString());
            }
            System.out.println(str1);
        }
        
    }
    // hàm update student 
    public  void update(Student x){
        // tìm xem học sinh này có tồn tại hay không 
        Student st1 = searchById(x.getId());
        String newPhone = x.getPhone();
        if(st1 == null){
            System.out.println("This student has not registered yet!!!");
        }else{
            System.out.println("Updating...");
            st1.setName(x.getName());
            st1.setPhone(x.getPhone());
            st1.setEmail(x.getEmail());
            st1.setMoutainCode(x.getMoutainCode());
            double newFee;
            if (Acceptable.isValid(newPhone, Acceptable.VNPT_VALID) || Acceptable.isValid(newPhone, Acceptable.VIETTEL_VALID)) {
                double free = (6000000 / 100) * 35;
                newFee = 6000000 - free;
            } else {
                newFee = 6000000;
            }
            st1.setTutionFee(newFee);
            // nhập các thông tin muốn updating 
            int index = stuList.indexOf(st1);
            if(index != -1){
                stuList.set(index, st1);
                System.out.println("Updating successfullly");
            }else{
                System.out.println("This student has not registered yet!!!");
            }
            
            
        }
    }   
    
    // hàm show các student 
    public void showAll(){
         String str = String.format(
          "|-------------------------------------------------------------------------|\n" +
          "|  Student ID  |   Name      |   Phone     |   Peak Code  |     Fee     \n" +
          "|-------------------------------------------------------------------------|\n");
           String str1 = String.format(    
          "|-------------------------------------------------------------------------|");
        if(stuList.isEmpty()){
            System.out.println("No students have registered yet!!!");
        }else{
            System.out.println(str);
            for (Student student : stuList) {
                System.out.println(student.toString());
            }
            System.out.println(str1);
        }
    }
    // hàm xóa một sinh viên 
    public void delete(String id){
        // tìm cần sinh viên cần xóa 
        Student st = searchById(id);
        // kiểm tra xem có sinh viên hay không 
        if(st == null){
            System.out.println("This student has not registered yet!!!");
        }else{
            System.out.println("The information student before delete");
            DecimalFormat formatter = new DecimalFormat("#,###,###");
            String formattedFee = formatter.format(st.getTutionFee());
            String str = String.format(
                    "------------------------------------------------------\n"
                    + "Student ID: %s\n"
                    + "Name: %s\n"
                    + "Phone: %s\n"
                    + "Mountain: %s\n"
                    + "Fee: %s\n"
                    + "------------------------------------------------------",
                    st.getId(), st.getName(), st.getPhone(), st.getMoutainCode(),formattedFee );
            System.out.println(str); 
            String result = inp.getString("Are you sure you want to delete this registration? (Y/N): ");
            if(result.equalsIgnoreCase("y")){
                stuList.remove(st);
                System.out.println("The registration has been successfully deleted.");
            }else{
                return;
            }
 
        }
    }
    // hàm lọc ra các sinh viên trong campus 
    public List<Student> filterByCampusCode(String campusCode){
        List<Student> tmpList = new ArrayList<>();
        if(campusCode.isEmpty()){
            System.out.println("Data is invalid! Re-enter..."); 
        }
        String keyId= "";
        for (Student stu : stuList) {
            keyId = stu.getId().substring(0, 2);
            if(keyId.contains(campusCode)){
                tmpList.add(stu);
            }
        }
        return tmpList;
    }
    
    // hàm thống kê các sinh viên theo đỉnh núi 
    public void statisticalizeByMountainPeak(){
        Statistics st = new Statistics(stuList);
        st.show();
    }
    
    
    
    // hàm đọc file 
    public void readFromFile() {
        // tạo obj file
        File f = new File("D:\\Lab211\\lab1\\StudentList.csv");
        // nếu không tìm thấy file thì 
        if (!f.exists()) {
            System.out.println("file không tồn tại");
        }
        // đúc ra anh đọc file 
        try {
            BufferedReader reader = new BufferedReader(new FileReader(f));
            String line = reader.readLine(); // đọc thằng đầu tiên 
            while (line != null) {
                StringTokenizer st = new StringTokenizer(line, "|");
                // xử lí lần lượt các propos 
                String id = st.nextToken().trim();
                String name = st.nextToken().trim();
                String phoneNumber = st.nextToken().trim();
                String email = st.nextToken().trim();
                String peakCode = st.nextToken().trim();
                // đúc ra sinh viên 
                Student st1 = new Student(id, name,phoneNumber, email, peakCode);
                if (st1 != null) {
                    stuList.add(st1);
                }
                 line =  reader.readLine(); // đọc thằng tiếp theo 
               
            }
//            dóng đổi tượng khi hoàn thành 
            reader.close();
    
        } catch (Exception e) {
            System.out.println("File lỗi rồi " + e);
        }
    }
    
    // hàm save file 
    public void saveToFile(){
        // tạo object file 
        File f = new File("D:\\Lab211\\lab1\\StudentList.csv");
        try {
            // tạo ra anh ghi file 
            OutputStreamWriter writer = new OutputStreamWriter(new FileOutputStream(f));
            // duyệt mảng 
            for (Student student : stuList) {
                writer.write(student.objectSaved());
                writer.write("\n");
            }
            writer.flush(); // lưu rồi tắt
            this.isSaved = true;
        } catch (Exception e) {
            System.out.println("File lỗi rồi nè: " + e);
        }
    }
    
    public boolean isSaved(){
      String result = inp.getString("Do you want to save the changes before exiting? (Y/N): ");
      if(result.matches("yY")){
          return true;
      }else{
          return false;
      }
    }
            
       
}
