package br.com.lottus.library.infrastructure.security.redis;

import br.com.lottus.library.application.ports.out.TokenBlocklistPort;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.time.Duration;

@Component
public class RedisTokenBlocklistAdapter implements TokenBlocklistPort {
    private final StringRedisTemplate redisTemplate;
    private static final String KEY_PREFIX = "blocklist:jit:";

    public RedisTokenBlocklistAdapter(StringRedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @Override
    public void addToBlockList(String jti, Duration ttl) {
        if(ttl.isPositive()) {
            redisTemplate.opsForValue().set(KEY_PREFIX + jti, ":blocked", ttl);
        }
    }

    @Override
    public boolean isBlocked(String jti) {
        return redisTemplate.hasKey(KEY_PREFIX + jti);
    }
}
