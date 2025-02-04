
package tools;

import java.util.Scanner;


public class Inputter {
       // Inputter là claas chuyên đúc anh chuyện hỗ trợ nhập 
    private Scanner sc = new Scanner(System.in);

   // constructor 

    public Inputter() {
    }
    
   /* Nhập dữ liệu chuỗi trực tiếp từ bàn phím bởi người sử dụng */
    public  String getString(String mess) {
        System.out.print(mess);
        return sc.nextLine();
    }

    /* Nhập dữ liệu là số nguyên từ bàn phím bởi người sử dụng */
    public int getInt(String mess) {
        int result = 0;
        String temp = getString(mess);
        if (Acceptable.isValid(temp, Acceptable.INTEGER_VALID)) {
            result = Integer.parseInt(temp);
        }
        return result;
    }

    /* Nhập dữ liệu là số double từ bàn phím bởi người sử dụng */
    public double getDouble(String mess) {
        double result = 0;
        String temp = getString(mess);
        if (Acceptable.isValid(temp, Acceptable.DOUBLE_VALID)) {
            result = Double.parseDouble(temp);
        }
        return result;
    }

    /* Phương thức cho phép nhập dữ liệu và yêu cầu nhập lại nếu dữ liệu không đúng */
    public String inputAndLoop(String mess, String pattern) {
        String result = "";
        boolean more = true;
        do {
            result = getString(mess);
            more = !Acceptable.isValid(result, pattern);
            if (more) {
                System.out.println("Data is invalid! Re-enter...");
            }
        } while (more);
        return result;
    }

 
}
    

