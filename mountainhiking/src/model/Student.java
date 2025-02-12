
package model;

import java.text.DecimalFormat;
import tools.Acceptable;


public class Student {
    // các properties
    private String id;
    private String name;
    private String phone;
    private String email;
    private String moutainCode;
    private double tutionFee;
    // constructor 

    public Student() {
    }

    public Student(String id, String name, String phone, String email, String moutainCode) {
        this.id = id.toUpperCase();
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.moutainCode = moutainCode;
        if(this.phone.matches(Acceptable.VNPT_VALID) || this.phone.matches(Acceptable.VIETTEL_VALID)){
            double free = (6000000 / 100) * 35;
            this.tutionFee = 6000000 - free;
        }else{
            this.tutionFee = 6000000;
        }
    }
    
    // getter and setter

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMoutainCode() {
        return moutainCode;
    }

    public void setMoutainCode(String moutainCode) {
        this.moutainCode = moutainCode;
    }

    public double getTutionFee() {
        return tutionFee;
    }

    public void setTutionFee(double tutionFee) {
        this.tutionFee = tutionFee;
    }
    
    
        
    // toString 
    public String toString(){
         // Định dạng số học phí
        DecimalFormat formatter = new DecimalFormat("#,###,###");
        String formattedFee = formatter.format(tutionFee);
        
        String str = String.format("%12s   |    %-12s       | %-12s| %8s     | %10s", 
                id , name, phone , moutainCode , formattedFee);
        return str;
    }
    
    public String objectSaved(){
        String str = String.format("%10s | %-10s  | %-10s | %10s | %5s "
                + "| %15.1f", 
                id , name, phone , email , moutainCode , tutionFee);
        return str;
    }
}
