
package model;


public class Mountain {
    // các properties 
    private String mountainCode;
    private String mountain;
    private String province;
    private String description;
    // constructor 

    public Mountain() {
    }

    public Mountain(String mountainCode, String mountain, String province, String description) {
        this.mountainCode = mountainCode;
        this.mountain = mountain;
        this.province = province;
        this.description = description;
    }
    
    // getter and setter 

    public String getMountainCode() {
        return mountainCode;
    }

    public void setMountainCode(String mountainCode) {
        this.mountainCode = mountainCode;
    }

    public String getMountain() {
        return mountain;
    }

    public void setMountain(String mountain) {
        this.mountain = mountain;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    
    // toString 
    public String toString(){
        String str = String.format("%-15s| %-20s| %-15s| %-15s", 
                mountainCode,mountain,province,description);
        return str;
    }
}
