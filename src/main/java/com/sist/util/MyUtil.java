package com.sist.util;

public class MyUtil {
	public static String getRenameNotMultiple(String oldName) {
		String fname ="";
		oldName = oldName.replace(".", ",");
		String []arr = oldName.split(",");
		try {
			long n = System.currentTimeMillis();
			fname = arr[0] + n + "." + arr[1];
		} catch (Exception e) {
			System.out.println("예외발생 getRenameNotMultiple :"+e.getMessage());
		}
		return fname;
	}
}
