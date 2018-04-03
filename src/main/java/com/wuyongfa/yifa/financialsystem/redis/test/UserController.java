package com.wuyongfa.yifa.financialsystem.redis.test;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wuyongfa.yifa.financialsystem.response.Response.Response;

@RestController
@RequestMapping("/v1")
public class UserController {
	@Autowired
    private UserRedisService userRedisService;
/** 
     * @Title: UserController
     * @Description: 初始化redis数据
     * @return  
     * @author mengfanzhu
     * @throws 
     */
    @RequestMapping("/initRedisdata")
    public Response initRedisData(){
        userRedisService.redisInitData();
        return new Response().success("true");
    }
    @RequestMapping("/getUserRedisByLoginName/{loginName}")
    public Response getUserRedisByLoginName(@PathVariable String loginName){
        
        User user = userRedisService.getUserRedis(loginName);
        
        return new Response().success(user);
    }
}
