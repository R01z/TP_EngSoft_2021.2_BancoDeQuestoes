package com.bancodequestoes.config.util;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;

public class DaoUtil {

  public static String encryptPass(String password) {
    try {
      MessageDigest digestor = MessageDigest.getInstance("SHA-256");
      byte[] encodedhash = digestor.digest(password.getBytes(StandardCharsets.UTF_8));
      StringBuilder encryptionValue = new StringBuilder(2 * encodedhash.length);
      for (int i = 0; i < encodedhash.length; i++) {
        String hexVal = Integer.toHexString(0xff & encodedhash[i]);
        if (hexVal.length() == 1) {
          encryptionValue.append('0');
        }
        encryptionValue.append(hexVal);
      }
      return encryptionValue.toString();
    } catch (Exception ex) {
      return ex.getMessage();
    }
  }
}

