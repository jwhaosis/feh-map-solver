package helpers;

import java.util.LinkedList;

public class LogHelper {
	
    public static void PRINT(LinkedList<String> testOutput) {
    	for(int i = 0; i < testOutput.size(); i++) {
    		System.out.println(testOutput.get(i));
    	}
    }

}
