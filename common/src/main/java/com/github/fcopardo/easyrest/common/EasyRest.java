package com.github.fcopardo.easyrest.common;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

public class EasyRest {

    private boolean DebugMode = true;
    private Platform platform;
    private LiteCachingStorage defaultQuickCache;

    private static EasyRest singleton;

    public static <X extends Platform> void build(X platform){
        if(singleton == null){
            singleton = new EasyRest(platform);
        }
    }

    public EasyRest get(){
        if(singleton == null){
            throw new NullPointerException("Error! the EasyRest instance needs to be initialized first by using the build(Platform platform) method");
        }
        return singleton;
    }

    private <X extends Platform> EasyRest(X platform){
        this.platform = platform;
    }

    public void deleteCache(){
        if(platform!=null) platform.deleteCache();
    }

    /**
     * Deletes the EasyRest cache of the specified types of answer, and older than @maximumTime
     * @param classes the response types to be deleted.
     * @param maximumTime The maximum caching time.
     */
    public void deleteCache(List<Class> classes, long maximumTime){
        if(platform!=null) platform.deleteCache(classes, maximumTime);
    }

    public boolean checkConnectivity(){
        return platform != null && platform.checkConnectivity();
    }
    

    public void setDebugMode(boolean debugMode){
        DebugMode = debugMode;
    }

    public boolean isDebugMode(){
        return DebugMode;
    }

    /**
     * Creates a SHA-1 hash from a given string.
     * @param password the string to be hashed.
     * @return a String representing the SHA-1 form of the argument.
     * @throws java.security.NoSuchAlgorithmException if the SHA-1 algorithm is absent from the JVM.
     */
    String getHashOne(String password)
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

    public void setQuickCachingAmount(int amount){
        if(defaultQuickCache == null) defaultQuickCache = new LiteCachingStorage();
        defaultQuickCache.setCachingSize(amount);
    }

    void cacheRequest(String name, Object entity){
        if(defaultQuickCache == null) defaultQuickCache = new LiteCachingStorage();
        defaultQuickCache.addRequest(name, entity);
    }

    Object getCachedRequest(String name){
        if(defaultQuickCache==null) defaultQuickCache = new LiteCachingStorage();
        if(defaultQuickCache.isCachedRequest(name)) return defaultQuickCache.getRequest(name);
        return null;
    }

    boolean isCachedRequest(String name){
        if(defaultQuickCache==null) defaultQuickCache = new LiteCachingStorage();
        return defaultQuickCache.isCachedRequest(name);
    }

}
