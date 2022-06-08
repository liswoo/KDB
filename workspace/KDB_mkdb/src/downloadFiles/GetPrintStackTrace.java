package downloadFiles;

import java.io.PrintWriter;
import java.io.StringWriter;

public class GetPrintStackTrace {
	
	 public static String getPrintStackTrace(Exception e) {
         
	        StringWriter errors = new StringWriter();
	        e.printStackTrace(new PrintWriter(errors));
	         
	        return errors.toString();
	         
	    }
}
