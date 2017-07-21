package com.superwind.test2ratelimit.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.scripting.support.ResourceScriptSource;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by jiangxj on 2017/7/15.
 */
@Service
public class UnitTimesIncr {
    @Autowired
    private RedisTemplate redisTemplate;

    public final boolean access(final String luaScriptPath,final String identity) {
        DefaultRedisScript<Long> redisScript = new DefaultRedisScript<>();
        FileSystemResource fileSystemResource = null;
        try {
            File file = ResourceUtils.getFile(luaScriptPath);
            fileSystemResource = new FileSystemResource(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return false;
        }
        redisScript.setScriptSource(new ResourceScriptSource(fileSystemResource));
        redisScript.setResultType(Long.class);// Must Set
        String key = identity;
        List<String> keys = new ArrayList<>();
        keys.add(key);
        String[] argvs = new String[] {"20000","15"};

        Long result = (Long) redisTemplate.execute(redisScript, redisTemplate.getStringSerializer(),redisTemplate.getStringSerializer(),keys, argvs);
        return result == 1L;
    }
}
