package com.github.fcopardo.easyrest.common;

import com.github.fcopardo.easyrest.api.PlatformContract;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

public interface Platform extends PlatformContract {

    void deleteCache();
    void deleteCache(List<Class> classes, long maximumTime);
    boolean checkConnectivity();
    String getBasePath();
    String getFullPath();

    /**
     * Creates a SHA-1 hash from a given string.
     * @param password the string to be hashed.
     * @return a String representing the SHA-1 form of the argument.
     * @throws java.security.NoSuchAlgorithmException if the SHA-1 algorithm is absent from the JVM.
     */
    default String getHashOne(String password)
            throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("SHA-1");
        md.update(password.getBytes());

        byte byteData[] = md.digest();

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < byteData.length; i++) {
            sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16)
                    .substring(1));
        }
        return sb.toString();
    }

}
