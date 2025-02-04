
package model;

import java.text.DecimalFormat;

public class StatisticalInfo {
    // các properties 
    private String mountainCode;
    private int numOfStudent;
    private double totalCost;
    // constructor 

    public StatisticalInfo() {
    }

    public StatisticalInfo(String mountainCode, int numOfStudent, double totalCost) {
        this.mountainCode = mountainCode;
        this.numOfStudent = numOfStudent;
        this.totalCost = totalCost;
    }
    
    // getter and setter 

    public String getMountainCode() {
        return mountainCode;
    }

    public void setMountainCode(String mountainCode) {
        this.mountainCode = mountainCode;
    }

    public int getNumOfStudent() {
        return numOfStudent;
    }

    public void setNumOfStudent(int numOfStudent) {
        this.numOfStudent = numOfStudent;
    }

    public double getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(double totalCost) {
        this.totalCost = totalCost;
    }
    // toString 
    public String toString (){
        DecimalFormat formatter = new DecimalFormat("#,###,###");
        String formattedTotal= formatter.format(totalCost);
        String str = String.format("%11s      | %11d              | %10s ",
                mountainCode , numOfStudent, formattedTotal);
        return str;
    }
}
