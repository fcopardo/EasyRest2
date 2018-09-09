package com.github.pardo.EasyRest.common;

import java.util.LinkedHashMap;

public class LiteCachingStorage {

    private LinkedHashMap<String, Object> cachedRequests = new LinkedHashMap<>();
    private int cachingSize = 100;
    private int cachedRequestAmount = 0;


    public void addRequest(String name, Object entity){
        if(cachedRequestAmount < cachingSize) {
            cachedRequests.put(name, entity);
        }
        else{
            for(String s: cachedRequests.keySet()){
                cachedRequests.remove(s);
                break;
            }
            cachedRequests.put(name, entity);
        }
        cachedRequestAmount++;
    }

    public Object getRequest(String name){
        if (cachedRequests.containsKey(name)) return cachedRequests.get(name);
        return null;
    }

    public boolean isCachedRequest(String name){
        return cachedRequests.containsKey(name);
    }

    public int getCachingSize() {
        return cachingSize;
    }

    public void setCachingSize(int cachingSize) {
        this.cachingSize = cachingSize;
    }
}
