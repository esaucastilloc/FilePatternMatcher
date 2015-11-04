package com.siteimprove.regularexp.utils;

import java.io.Closeable;

public class Utils {

	public static void close(Closeable obj){
   	 if(obj != null){
   		 try {
				obj.close();
			} catch (Exception e) {
				// Nothing to be done
			}
   	 }
   	 
    }
}
