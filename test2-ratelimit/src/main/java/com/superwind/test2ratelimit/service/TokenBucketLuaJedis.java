package com.superwind.test2ratelimit.service;

import com.google.common.base.Preconditions;
import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.springframework.util.ResourceUtils;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Created by jiangxj on 2017/7/14.
 * 控制访问速率模式-通过redis+lua自实现令牌桶，使用Jedis执行
 */
public class TokenBucketLuaJedis {
    private JedisPool       jedisPool;
    private String          scriptSha1;
    private RateLimitPolicy policy;

    public TokenBucketLuaJedis() {
        JedisPoolConfig poolConfig = new JedisPoolConfig();
        poolConfig.setMaxIdle(10);
        poolConfig.setMaxWaitMillis(2000);
        poolConfig.setMinIdle(5);
        jedisPool = new JedisPool(poolConfig,"192.168.1.1", 6379,5000,"123456");
    }

    public final boolean access(final String identity) {
        Preconditions.checkNotNull(jedisPool, "Redis not initialized");
        Preconditions.checkNotNull(policy, "Policy not initialized");

        String key = policy.genBucketKey(identity);

        try (Jedis jedis = jedisPool.getResource()) {
            long result = (long) jedis.evalsha(scriptSha1, 1, key,
                    String.valueOf(policy.getIntervalPerPermit()),
                    String.valueOf(System.currentTimeMillis()),
                    String.valueOf(policy.getMaxBurstTokens()),
                    String.valueOf(policy.getCapacity()),
                    String.valueOf(policy.getIntervalInMills()));

            return result == 1L;
        }
    }

    public final boolean init(String luaScriptPath,RateLimitPolicy policy){
        Preconditions.checkNotNull(policy);
        this.policy = policy;

        byte[] scriptBytes = new byte[0];
        try {
            scriptBytes = getContent(luaScriptPath);
        } catch (IOException e) {
            e.printStackTrace();
        }
        String luaScript = new String(scriptBytes);
        try (Jedis jedis = this.jedisPool.getResource()) {
            this.scriptSha1 = jedis.scriptLoad(luaScript);
        }
        return true;
    }

    public byte[] getContent(String filePath) throws IOException {
        File file = ResourceUtils.getFile(filePath);
        long fileSize = file.length();
        if (fileSize > Integer.MAX_VALUE) {
            System.out.println("file too big...");
            return null;
        }
        FileInputStream fi = new FileInputStream(file);
        byte[] buffer = new byte[(int) fileSize];
        int offset = 0;
        int numRead = 0;
        while (offset < buffer.length
                && (numRead = fi.read(buffer, offset, buffer.length - offset)) >= 0) {
            offset += numRead;
        }
        // 确保所有数据均被读取
        if (offset != buffer.length) {
            throw new IOException("Could not completely read file "
                    + file.getName());
        }
        fi.close();
        return buffer;
    }
}
