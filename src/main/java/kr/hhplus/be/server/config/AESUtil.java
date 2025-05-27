package kr.hhplus.be.server.config;

import java.nio.charset.StandardCharsets;
import java.security.spec.AlgorithmParameterSpec;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.springframework.stereotype.Component;

@Component
public class AESUtil {

	private String SECRET_KEY;
	private static final byte[] IV = { 2, 0, 1, 9, 1, 1, 2, 9, 1, 5, 4, 2, 5, 9, 0, 0 };
/*
	// AES256 암호화
	@SuppressFBWarnings(value = {"STATIC_IV", "CIPHER_INTEGRITY", "PADDING_ORACLE"})
	public String encryptAES256(String str) throws Exception {
		String resultStr = "";

		if ("".equals(CommonUtil.toNotNull(str))) {
			resultStr = "";
		} else {
			byte[] textBytes = str.getBytes(StandardCharsets.UTF_8);
			AlgorithmParameterSpec ivSpec = new IvParameterSpec(IV);
			SecretKeySpec newKey = new SecretKeySpec(SECRET_KEY.getBytes(StandardCharsets.UTF_8), "AES");
			Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
			cipher.init(Cipher.ENCRYPT_MODE, newKey, ivSpec);

			byte[] encData = cipher.doFinal(textBytes);

			String encStr = Base64.encodeBase64String(encData);
			encStr = encStr.replaceAll(System.lineSeparator(), "");

			resultStr = encStr;
		}

		return resultStr;
	}

	// AES256 복호화
	@SuppressFBWarnings(value = {"STATIC_IV", "CIPHER_INTEGRITY", "PADDING_ORACLE"})
	public String decryptAES256(String str) throws Exception {
		String resultStr = "";

		if ("".equals(CommonUtil.toNotNull(str))) {
			resultStr = "";
		} else {
			byte[] textBytes = Base64.decodeBase64(str);
			AlgorithmParameterSpec ivSpec = new IvParameterSpec(IV);
			SecretKeySpec newKey = new SecretKeySpec(SECRET_KEY.getBytes(StandardCharsets.UTF_8), "AES");
			Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
			cipher.init(Cipher.DECRYPT_MODE, newKey, ivSpec);
			resultStr = new String(cipher.doFinal(textBytes), StandardCharsets.UTF_8);
		}

		return resultStr ;
	}*/
}
