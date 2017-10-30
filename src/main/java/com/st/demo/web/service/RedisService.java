package com.st.demo.web.service;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import redis.clients.jedis.Jedis;


public class RedisService {
	/**
     * ͨ��keyɾ�����ֽڣ�
     * @param key
     */
    public void del(byte [] key){
        this.getJedis().del(key);
    }
    /**
     * ͨ��keyɾ��
     * @param key
     */
    public void del(String key){
        this.getJedis().del(key);
    }

    /**
     * ���key value �������ô��ʱ��(byte)
     * @param key
     * @param value
     * @param liveTime
     */
    public void set(byte [] key,byte [] value,int liveTime){
        this.set(key, value);
        this.getJedis().expire(key, liveTime);
    }
    /**
     * ���key value �������ô��ʱ��
     * @param key
     * @param value
     * @param liveTime
     */
    public void set(String key,String value,int liveTime){
        this.set(key, value);
        this.getJedis().expire(key, liveTime);
    }
    /**
     * ���key value
     * @param key
     * @param value
     */
    public void set(String key,String value){
        this.getJedis().set(key, value);
    }
    /**���key value (�ֽ�)(���л�)
     * @param key
     * @param value
     */
    public void set(byte [] key,byte [] value){
        this.getJedis().set(key, value);
    }
    /**
     * ��ȡredis value (String)
     * @param key
     * @return
     */
    public String get(String key){
        String value = this.getJedis().get(key);
        return value;
    }
    /**
     * ��ȡredis value (byte [] )(�����л�)
     * @param key
     * @return
     */
    public byte[] get(byte [] key){
        return this.getJedis().get(key);
    }

    /**
     * ͨ������ƥ��keys
     * @param pattern
     * @return
     */
    public Set<String> keys(String pattern){
        return this.getJedis().keys(pattern);
    }

    /**
     * ���key�Ƿ��Ѿ�����
     * @param key
     * @return
     */
    public boolean exists(String key){
        return this.getJedis().exists(key);
    }
    /**
     * ���redis ��������
     * @return
     */
    public String flushDB(){
        return this.getJedis().flushDB();
    }
    /**
     * �鿴redis���ж�������
     */
    public long dbSize(){
        return this.getJedis().dbSize();
    }
    /**
     * ����Ƿ����ӳɹ�
     * @return
     */
    public String ping(){
        return this.getJedis().ping();
    }
    /**
     * ��ȡһ��jedis �ͻ���
     * @return
     */
    private Jedis getJedis(){
        if(jedis == null){
            return connectionFactory.getShardInfo().createResource();
        }
        return jedis;
    }
    private RedisService (){

    }
    //����redis�ͻ���
    private static Jedis jedis;
    @Autowired
    @Qualifier("connectionFactory")
    private JedisConnectionFactory connectionFactory;
}
