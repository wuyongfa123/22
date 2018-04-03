package com.wuyongfa.yifa.financialsystem.redis.test;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/** 
* @ClassName: UserRedis 
* @Description:  redis 提供5种数据类型的操作
* String ,hash ,list , set , zset
* @author mengfanzhu
* @date 2017年2月21日 下午2:01:43 
*/
@Repository
public class UserRedis {
    
    @Autowired
    private RedisTemplate<String, String> redisTemplate;
    
    private ObjectMapper objectMapper = new ObjectMapper();
    
    public void addUser(String key,Long time,User user) throws JsonProcessingException{
        redisTemplate.opsForValue().set(key, objectMapper.writeValueAsString(user),time,TimeUnit.MINUTES);
    }
    
    public void addUserList(String key,Long time,List<User> userList) throws JsonProcessingException{
        redisTemplate.opsForValue().set(key, objectMapper.writeValueAsString(userList),time,TimeUnit.MINUTES);
    }
    
    public User getUserByKey(String key) throws JsonParseException, JsonMappingException, IOException{
        User user = null;
        String userJson = redisTemplate.opsForValue().get(key);
        if(StringUtils.isNotEmpty(userJson)){
            user =  objectMapper.readValue(key, User.class);
        }
        return user;
    }
    
    public List<User> getUserListByKey(String key) throws JsonParseException, JsonMappingException, IOException{
        
        List<User> userList = null;
        String userJson = redisTemplate.opsForValue().get(key);
        if(StringUtils.isNotEmpty(userJson)){
        	JavaType javaType = objectMapper.getTypeFactory().constructParametricType(List.class, User.class);
            userList =  objectMapper.readValue(key, javaType);
        }
        return userList;
    }
    
    public void deleteByKey(String key){
        redisTemplate.opsForValue().getOperations().delete(key);
    }
}