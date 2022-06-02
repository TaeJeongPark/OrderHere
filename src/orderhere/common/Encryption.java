package orderhere.common;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;

/**
* @packageName	: orderhere.common
* @fileName		: Encryption.java
* @author		: TaeJeong Park
* @date			: 2022.05.21
* @description	: SHA512 암호화 클래스
* ===========================================================
* DATE				AUTHOR				NOTE
* -----------------------------------------------------------
* 2022.05.21		TaeJeong Park		최초 생성
* 2022.05.21		TaeJeong Park		기능 구현 완료
*/
public class Encryption {

	// Salt 생성
	public static String Salt() {

		String salt = "";

		try {
			SecureRandom random = SecureRandom.getInstance("SHA1PRNG");
			byte[] bytes = new byte[16];
			random.nextBytes(bytes);
			salt = new String(Base64.getEncoder().encode(bytes));
		} catch (NoSuchAlgorithmException e) {
			System.out.println("난수 생성 오류");
			e.printStackTrace();
		}

		return salt;

	}

	// SHA512 암호화
	public static String SHA512(String password, String hash) {

		String salt = hash + password;
		String hex = null;

		try {
			MessageDigest msg = MessageDigest.getInstance("SHA-512");
			msg.update(salt.getBytes());

			hex = String.format("%128x", new BigInteger(1, msg.digest()));
		} catch (Exception e) {
			System.out.println("암호화 오류");
			e.printStackTrace();
		}

		return hex;

	}

}
