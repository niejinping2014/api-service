package xyz.hollysys.api.util;

public class EncodeHelper {
	public static String getPassword(String... args) {
		StringBuilder sb = new StringBuilder();

		for (String arg : args) {
			sb.append(arg);
		}

		return EncoderHandler.encodeByMD5(sb.toString());
	}

	public static String getPassword(String account, String password, int salt) {
		StringBuilder sb = new StringBuilder();

		sb.append(account).append(password).append(salt);

		return EncoderHandler.encodeByMD5(sb.toString());
	}
	
	public static String getPassword(String account, String password){
		StringBuilder sb = new StringBuilder();

		sb.append(account).append(password);

		return EncoderHandler.encodeByMD5(sb.toString());
	}
	
	public static void main(String[] args){
		System.out.println(getPassword("njp","123345",1356));
		System.out.println(getPassword("njp","123345",1356).length());
	}
}
