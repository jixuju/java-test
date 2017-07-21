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
 * Created by jiangxj on 2017/7/13.
 * 控制访问速率模式-通过redis+lua自实现令牌桶，使用RedisTemplate执行
 */
@Service
public class TokenBucketLuaRedisTemplate {
    @Autowired
    private RedisTemplate   redisTemplate;

    public final boolean access(final String luaScriptPath,final String identity,final RateLimitPolicy policy) {
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

        String key = policy.genBucketKey(identity);
        List<String> keys = new ArrayList<>();
        keys.add(key);
        String[] argvs = new String[] {String.valueOf(policy.getIntervalPerPermit()),
                String.valueOf(System.currentTimeMillis()),
                String.valueOf(policy.getMaxBurstTokens()),
                String.valueOf(policy.getCapacity()),
                String.valueOf(policy.getIntervalInMills())};

        Long result = (Long) redisTemplate.execute(redisScript, redisTemplate.getStringSerializer(),redisTemplate.getStringSerializer(),keys, argvs);
        return result == 1L;
    }
}
