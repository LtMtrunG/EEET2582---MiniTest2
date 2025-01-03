package eeet2582.week3.helper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Set;

@Component
public class JedisHelper {
    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    public void createRedisString(String key, Object value) {
        redisTemplate.opsForValue()
                .set(key, value);
    }

    public void createHash (
            String redisKey,
            String hashKey,
            Object hashValue
    ) {
        redisTemplate.opsForHash()
                .putIfAbsent(redisKey, hashKey, hashValue);
    }

    public void createSortedSet (
            String redisKey,
            Object entry,
            double score
    ) {
        redisTemplate.opsForZSet()
                .addIfAbsent(redisKey, entry, score);
    }

    public Object getStrValueByKey(String key) {
        return redisTemplate
                .opsForValue()
                .get(key);
    }

    public Map<Object, Object> getHashByKey(String key) {
        return redisTemplate.opsForHash()
                .entries(key);
    }

    public Object getHashValueByKeys(
            String redisKey, String hashKey
    ) {
        return (Object) redisTemplate
                .opsForHash()
                .get(redisKey, hashKey);
    }

    public Set<Object> getElementsByScoreRange(
            String redisKey,
            double min,
            double max
    ) {
        return redisTemplate.opsForZSet()
                .rangeByScore(redisKey, min, max);
    }

    public void deleteHash(String redisKey, String hashKey) {
        redisTemplate.opsForHash().delete(redisKey, hashKey);
    }

    public void deleteKey(String key) {
        redisTemplate.delete(key);
    }
}
