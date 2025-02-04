package business;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.StringTokenizer;
import model.Mountain;

public class Mountains {

    public ArrayList<Mountain> mountainList = new ArrayList<>();
    // properties
    private String pathFile;

    // constructor 
    public Mountains() {
        
    }

    // method dataToObject
    public Mountain dataToObject(String line) {

        StringTokenizer st = new StringTokenizer(line, ",");
        // xử lí lần lượt các propos 
        String mountainCode = st.nextToken().trim();
        String mountain = st.nextToken().trim();
        String province = st.nextToken().trim();
        String description = st.nextToken().trim();
        Mountain mt = new Mountain(mountainCode, mountain, province, description);
        // đức ra object và trả ra object
        return mt;
    }

    // hàm dọc file
    public void readFromFile() {
        // tạo obj file
        File f = new File("D:\\Lab211\\lab1\\MountainList.csv");
        // nếu không tìm thấy file thì 
        if (!f.exists()) {
            System.out.println("file không tồn tại");
        }
        // đúc ra anh đọc file 
        try {
            BufferedReader reader = new BufferedReader(new FileReader(f));
            String line = reader.readLine(); // đọc thằng đầu tiên 
                   
            while (line != null) {
                Mountain moun = dataToObject(line);
                if (moun != null) {
                    mountainList.add(moun);
                }
                line = reader.readLine(); // đọc thằng tiếp theo
            }
            // dóng đổi tượng khi hoàn thành 
            reader.close();
        } catch (Exception e) {
            System.out.println("File lỗi rồi " + e);
        }
    }
    // hàm lấy một cái núi 
    public Mountain get(String mountainCode){
        for (Mountain mountain : mountainList) {
            if(mountain.getMountainCode().equals(mountainCode)){
                return mountain;
            }
        }
        return null;
    }
    // hàm kiểm tra mã code của núi có tồn tại hay không 
    public boolean isValidMountainCode(String mountainCode){
        for (Mountain mountain : mountainList) {
            if(mountain.getMountainCode().equals(mountainCode)){
                return true;
            }
        }
        // không thấy 
        return false;
    }

}
