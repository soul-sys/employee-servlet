package com.lemon1234.util;

/*
 * 随机密码生成
 */
public class PasswordUtil {

	private static final String[] code = {"A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z",
			"a","b","c","d","e","f","g","h","i","j","k","l","m","n","o","p","q","r","s","t","u","v","w","x","y","z",
			"1","2","3","4","5","6","7","8","9","0",
			"+","-","*",".",",",".","[","]","~","!","@","#","$","%",
			"^","&","_","+","{","}",":",">","?","<"};
	
	
	public static String randomCode() {
		
		int count = 18;
		
		StringBuffer sBuffer = new StringBuffer();
		
		int codeLength = code.length;
		
		for(int i = 0; i < count; i++) {
			sBuffer.append(code[(int)(Math.random()*codeLength)]);
		}
		
		return sBuffer.toString();
	}
	
}
