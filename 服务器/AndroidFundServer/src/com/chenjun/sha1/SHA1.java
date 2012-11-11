package com.chenjun.sha1;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class SHA1 {
	
	public static String sha1(String strSrc) {
		MessageDigest md = null;
		String strDes = null;

		byte[] bt = strSrc.getBytes();
		try {
			md = MessageDigest.getInstance("SHA-1");
			md.update(bt);
			strDes = bytes2Hex(md.digest()); // to HexString
		} catch (NoSuchAlgorithmException e) {
			System.out.println("Invalid algorithm.");
			return null;
		}
		return strDes;
	}

	public static String bytes2Hex(byte[] bts) {
		StringBuffer sb = new StringBuffer();
		String tmp = null;
		for (int i = 0; i < bts.length; i++) {
			tmp = (Integer.toHexString(bts[i] & 0xFF));
			if (tmp.length() == 1) {
				sb.append("0");
			}
			sb.append(tmp);
		}
		return sb.toString();
	}
}
