package another_class;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList; 
 
public class PrintStackTraceTest {
 
    public static void main(String[] args) {
         
        String errorStr = null;
        String result = null;
        
         
        try{
             
            //강제적으로 에러 발생
            int number = Integer.parseInt("a");
           
        }catch(Exception e){
            //String으로 받음
            errorStr = getPrintStackTrace(e);
                                     
        }
        
        if(errorStr != null) {
        
             System.out.println(errorStr.substring(0, 9));
             result = errorStr.substring(0, 9);
             
        }
       
 
    }
     
    public static String getPrintStackTrace(Exception e) {
         
        StringWriter errors = new StringWriter();
        e.printStackTrace(new PrintWriter(errors));
         
        return errors.toString();
         
    }
 
}