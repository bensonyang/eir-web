package com.morningsidevc.utils;

import com.morningsidevc.po.CacheData;
import org.apache.commons.lang.StringUtils;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * User: liang.liu
 * <p/>
 * Date: 15-10-31, Time: 下午8:27
 */
public class MapCacheUtils {

    private Map<String, CacheData> cacheMap = new ConcurrentHashMap<String, CacheData>();

    private static MapCacheUtils instance = null;

    private MapCacheUtils() {
    }

    public static MapCacheUtils getInstance() {
        if (instance == null) {
            synchronized (MapCacheUtils.class) {
                if (instance == null) {
                    instance = new MapCacheUtils();
                }
            }
        }
        return instance;
    }

    public void add(String key, Object value, long validTime) {
        if (StringUtils.isBlank(key) || value == null || validTime <= 0) {
            return;
        }

        long currentTime = System.currentTimeMillis();

        cacheMap.put(key, new CacheData(value, currentTime + validTime));
    }

    public <T> T get(String key) {
        if (StringUtils.isBlank(key)) {
            return null;
        }

        CacheData data = cacheMap.get(key);
        if (data == null) {
            return null;
        }
        if (data.getValue() == null || data.getExpiredTime() < System.currentTimeMillis()) {
            cacheMap.remove(key);
            return null;
        }

        return (T) data.getValue();
    }

    public void remove(String key) {
        if (StringUtils.isBlank(key)) {
            return;
        }
        cacheMap.remove(key);
    }


}
