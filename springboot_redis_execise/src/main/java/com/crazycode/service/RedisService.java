package com.crazycode.service;

public interface RedisService {
    /**
     * 设置String类型的key
     * @param key
     * @param value
     * @param timeout
     * @throws Exception
     */
    public void setcacheKey(String key,String value,Long timeout) throws Exception;

    /**
     * 获取key指定的值
     * @param key
     * @return
     * @throws Exception
     */
    public String getcacheKey(String key) throws Exception;

    /**
     * 对指定key中的值进行减一操作
     * @param key
     * @throws Exception
     */
    public Long desccacheByKey(String key) throws Exception;

    /**
     * 获取指定key的剩余失效时间
     * @param key
     * @return
     * @throws Exception
     */
    public Long getUserExpire(String key) throws Exception;

    /**
     * 删除指定的key
     * @param key
     * @throws Exception
     */
    public void delCacheKey(String key) throws Exception;


}
