package com.crazycode.service;

/**
 * @author Administrator
 */
public interface RedisService {
    /**
     * 操作字符串String类型的key
     *
     * @param key
     * @param value
     * @param timeout
     * @throws Exception
     */
    void setCacheKey(String key, String value, Long timeout) throws Exception;

    /**
     * 获取key指定的值
     *
     * @param key
     * @return
     * @throws Exception
     */
    String getCacheKey(String key) throws Exception;

    /**
     * 对指定key中的值进行减一操作
     *
     * @param key
     * @return
     * @throws Exception
     */
    Long descByKey(String key) throws Exception;

    /**
     * 获取指定key的剩余冻结时间
     *
     * @param key
     * @return
     * @throws Exception
     */
    Long getExpire(String key) throws Exception;

    /**
     * 删除指定的key
     *
     * @param key
     * @throws Exception
     */
    void delCacheKey(String key) throws Exception;
}
