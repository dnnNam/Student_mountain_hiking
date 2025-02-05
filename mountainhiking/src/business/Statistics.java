
package business;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import model.StatisticalInfo;
import model.Student;


public class Statistics extends HashMap<String, StatisticalInfo> {
      private final String HEADER_TABLE = 
          "|----------------------------------------------------------|\n" +
          "|    PEAK NAME   |   Number Of Participants |  Total Cost  |\n" +
          "|----------------------------------------------------------|\n";
      
      private final String FOOTER_TABLE = 
          "|----------------------------------------------------------|";
      
      // constructor 

    public Statistics() {
        super();
    }

    public Statistics(List<Student> list) {
        super();
        statisticalize(list);
    }  
    // phương thức thống kê student theo danh sách mountainCode
    public final void statisticalize(List<Student> l){
        for (Student i: l) {
            if(this.containsKey(i.getMoutainCode())){
                StatisticalInfo x = this.get(i.getMoutainCode());
                x.setNumOfStudent(x.getNumOfStudent()+ 1);
                x.setTotalCost(x.getTotalCost()+i.getTutionFee());
            }else{
                StatisticalInfo z = new StatisticalInfo(i.getMoutainCode(),
                                                        1, i.getTutionFee());
                this.put(i.getMoutainCode(), z);
            }
        }
    }
    // phương thức hiển thị danh sách thống kê 
    public void show(){
        System.out.println(HEADER_TABLE);
        for (StatisticalInfo i : this.values()) {
            System.out.println(i);
        }
        System.out.println(FOOTER_TABLE);
    }
    
    
       
}
