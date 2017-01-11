package com.peiyu.mem.redis;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import redis.clients.jedis.BinaryClient;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.ScanResult;

import java.util.*;

/**
 * Created by Administrator on 2016/11/30.
 */
public class JedisTemplate {
    private static final Log log = LogFactory.getLog(JedisTemplate.class);
    private JedisPool jedisPool;
    public JedisTemplate(JedisPool jedisPool) {
        this.jedisPool = jedisPool;
    }
    /***********************************String开始**************************************/
    /**
     * 添加一条记录
     * @param key
     * @param value
     */
    public void set(String key, String value) {
        Jedis jedis = getJedis();
        try {
            jedis.set(key, value);
        } catch (Exception e) {
            if (jedis != null) {
              jedis.close();
            }
        } finally {
            jedis.close();
        }
    }
    /**
     * 添加一条记录并设置时间
     * @param key 键
     * @param value 值
     * @param expiredseconds 有效时间
     */
    public void set(String key, String value, int expiredseconds) {
        Jedis jedis = getJedis();
        try {
            jedis.setex(key, expiredseconds, value);
        } catch (Exception e) {
            if (jedis != null) {
                jedis.close();
            }
        } finally {
            jedis.close();
        }
    }
    /**
     * 根据key查询value
     * @param key
     * @return
     */
    public String get(String key) {
        String value = "";
        Jedis jedis = getJedis();
        try {
            String tmp = jedis.get(key);
            value = tmp != null ? tmp : value;
        } catch (Exception e) {
            if (jedis != null) {
                jedis.close();
            }
        } finally {
            jedis.close();
        }
        return value;
    }
    public void delete(String key) {
        Jedis jedis = getJedis();
        jedis.del(key);
    }
    public void delete(String[] keys){
        Jedis jedis=getJedis();
        jedis.del(keys);
    }

    /**
     * 验证key是否存在
     * @param key
     * @return
     */
    public boolean exists(String key) {
        Jedis jedis = getJedis();
        try {
            return jedis.exists(key);
        } catch (Exception e) {
            if (jedis != null) {
                jedis.close();
            }
        } finally {
            jedis.close();
        }
        return false;
    }
    /***********************************String结束**************************************/

    /***********************************Hash开始**************************************/
    /**
     * 设置hset,key-fieldKey-value值
     * @param key
     * @param fieldKey
     * @param value
     */
    public void hset(String key, String fieldKey, String value) {
        Jedis jedis = getJedis();
        try {
            jedis.hset(key, fieldKey, value);
        } catch (Exception e) {
            if (jedis != null) {
                jedis.close();
            }
        } finally {
            jedis.close();
        }
    }

    /**
     * 批量设置hash的属性
     * @param key
     * @param fields
     * @param values
     */
    public void hmset(String key, String[] fields, String[] values) {
        Jedis jedis = getJedis();
        try {
            Map<String, String> hash = new HashMap<String, String>();
            for (int i = 0; i < fields.length; i++) {
                hash.put(fields[i], values[i]);
            }
            jedis.hmset(key, hash);
        } catch (Exception e) {
            if (jedis != null) {
                jedis.close();
            }
        } finally {
            jedis.close();
        }
    }
    public void hmset(String key,Map<String,String> map){
        Jedis jedis=getJedis();
        try {
            jedis.hmset(key,map);
        }catch (Exception e) {
            if (jedis != null) {
                jedis.close();
            }
        } finally {
            jedis.close();
        }
    }

    /**
     * 哈希表 key 中的域 field 的值设置为 value ，
     * 当且仅当域 field 不存在。若域 field 已经存在，该操作无效。
     * 操作成功返回1，操作失败返回0
     * @param key
     * @param fieldKey
     * @param value
     */
    public long hsetNX(String key,String fieldKey,String value) {
        Jedis jedis = getJedis();
        long result = 0;
        try {
            result = jedis.hsetnx(key, fieldKey, value);
        } catch (Exception e) {
            if (jedis != null) {
                jedis.close();
            }
        } finally {
            jedis.close();
        }
        return result;
    }
    /**
     * 从hset中hget出对应的key-field对应的value值
     * @param key
     * @param fieldKey
     * @return
     */
    public String hget(String key, String fieldKey) {
        String value = "";
        Jedis jedis = getJedis();
        try {
            String tmp = jedis.hget(key, fieldKey);
            value = tmp != null ? tmp : value;
        } catch (Exception e) {
            if (jedis != null) {
                jedis.close();
            }
        } finally {
            jedis.close();
        }
        return value;
    }

    /**
     * 批量获取属性的值
     * @param key
     * @param fields
     * @return
     */
    public List<String> hmget(String key, String... fields) {
        Jedis jedis = getJedis();
        try {
            List<String> returnValue = jedis.hmget(key, fields);
            return returnValue;
        } catch (Exception e) {
            if (jedis != null) {
                jedis.close();
            }
        } finally {
            jedis.close();
        }
        return null;
    }
    /**
     * 获取在哈希表中指定 key 的所有字段和值
     * @param key
     * @return
     */
    public Map<String, String> hgetAll(String key) {
        Map<String, String> map = new HashMap<String, String>();
        Jedis jedis = getJedis();
        try {
            Map<String, String> tmpmap = jedis.hgetAll(key);
            map = tmpmap != null ? tmpmap : map;
        } catch (Exception e) {
            if (jedis != null) {
                jedis.close();
            }
        } finally {
            jedis.close();
        }
        return map;
    }

    /**
     * 删除hash的属性
     * @param key
     * @param fields
     */
    public void hdel(String key,String... fields){
        Jedis jedis=getJedis();
        try {
            jedis.hdel(key,fields);
        }catch (Exception e) {
            if (jedis != null) {
                jedis.close();
            }
        } finally {
            jedis.close();
        }
    }

    /**
     *  查看哈希表 key 中，指定的字段是否存在。
     * @param key
     * @param field
     * @return
     */
    public boolean hexists(String key,String field) {
        Jedis jedis = getJedis();
        try {
            return jedis.hexists(key, field);
        } catch (Exception e) {
            if (jedis != null) {
                jedis.close();
            }
        } finally {
            jedis.close();
        }
        return false;
    }

    /**
     *  为哈希表 key 中的指定字段的整数值加上增量 increment
     * @param key
     * @param field
     * @param incerement 正负数、0、正整数
     */
    public void hincrBy(String key,String field,long incerement){
        Jedis jedis=getJedis();
        try {
            jedis.hincrBy(key,field,incerement);
        }catch (Exception e) {
            if (jedis != null) {
                jedis.close();
            }
        } finally {
            jedis.close();
        }
    }

    /**
     * 获取所有哈希表中的字段
     * @param key
     * @return
     */
    public Set<String> hkeys(String key) {
        Jedis jedis = getJedis();
        try {
            Set<String> result = jedis.hkeys(key);
            return result;
        }catch (Exception e) {
            if (jedis != null) {
                jedis.close();
            }
        } finally {
            jedis.close();
        }
        return null;
    }

    /**
     * 获取哈希表中的值
     * @param key
     * @return
     */
    public List<String> hvals(String key) {
        Jedis jedis = getJedis();
        try {
            List<String> result = jedis.hvals(key);
            return result;
        } catch (Exception e) {
            if (jedis != null) {
                jedis.close();
            }
        } finally {
            jedis.close();
        }
        return null;
    }

    /**
     * 获取哈希表中字段的数量，当key不存在时，返回0
     * @param key
     * @return
     */
    public Long hlen(String key) {
        Jedis jedis = getJedis();
        try {
            Long result = jedis.hlen(key);
            return result;
        } catch (Exception e) {
            if (jedis != null) {
                jedis.close();
            }
        } finally {
            jedis.close();
        }
        return 0L;
    }

    /**
     * 迭代哈希表中的键值对
     * @param key
     * @param cursor
     * @return
     */
    public ScanResult<Map.Entry<String,String>> hscan(String key, String cursor) {
        Jedis jedis = getJedis();
        try {
            ScanResult<Map.Entry<String, String>> scanResult = jedis.hscan(key, cursor);
            return scanResult;
        } catch (Exception e) {
            if (jedis != null) {
                jedis.close();
            }
        } finally {
            jedis.close();
        }
        return null;
    }
    /***********************************hash结束**************************************/


    /***********************************list开始**************************************/
    /**
     * 将一个值插入到列表头部，value可以重复，返回列表的长度
     * @param key
     * @param value
     * @return 返回List的长度
     */
    public Long lpush(String key,String value) {
        Jedis jedis = getJedis();
        try {
            Long result = jedis.lpush(key, value);
            return result;
        } catch (Exception e) {
            if (jedis != null) {
                jedis.close();
            }
        } finally {
            jedis.close();
        }
        return null;
    }

    /**
     * 将多个值插入到列表头部，value可以重复
     * @param key
     * @param values
     * @return 返回List的长度
     */
    public Long lpush(String key,String[] values) {
        Jedis jedis = getJedis();
        try {
            Long result = jedis.lpush(key, values);
            return result;
        }catch (Exception e){
            if (jedis!=null){
                jedis.close();
            }
        }finally {
            jedis.close();
        }
       return null;
    }

    /**
     *  获取List列表
     * @param key
     * @param start 开始索性
     * @param end 结束索性
     * @return
     */
    public List<String> lrange(String key,long start,long end) {
        Jedis jedis = getJedis();
        List<String> result = jedis.lrange(key, start, end);
        return result;
    }

    /**
     *  通过索引获取列表中的元素
     * @param key
     * @param index 索引，0表示最新的一个元素
     * @return
     */
    public String lindex(String key,long index){
        Jedis jedis=getJedis();
        String result=jedis.lindex(key,index);
        return result;
    }

    /**
     * 获取列表长度，key为空时返回0
     * @param key
     * @return
     */
    public Long llen(String key) {
        Jedis jedis = getJedis();
        Long result = jedis.llen(key);
        return result;
    }

    /**
     * 在列表的元素前或者后插入元素，返回List的长度
     * @param key
     * @param where
     * @param pivot 以该元素作为参照物，是在它之前，
     * 还是之后（pivot：枢轴;中心点，中枢;[物]支点，支枢;[体]回转运动。）
     * @param value
     * @return
     */
    public Long linsert(String key, BinaryClient.LIST_POSITION where,
                        String pivot,String value) {
        Jedis jedis = getJedis();
        Long result = jedis.linsert(key, where, pivot, value);
        return result;
    }

    /**
     * 将一个值插入到已存在的列表头部，当成功时，
     * 返回List的长度；当不成功（即key不存在时，返回0）
     * @param key
     * @param value
     * @return
     */
    public Long lpushx(String key,String value){
        Jedis jedis = getJedis();
        Long result=jedis.lpushx(key,value);
        return result;
    }

    /**
     * 将一个或多个值插入到已存在的列表头部，
     * 当成功时，返回List的长度；当不成功（即key不存在时，返回0）
     * @param key
     * @param values
     * @return
     */
    public Long lpushx(String key,String[] values) {
        Jedis jedis = getJedis();
        Long result = jedis.lpushx(key, values);
        return result;
    }

    /**
     * 通过索引设置列表元素的值，当超出索引时会抛错。成功设置返回true
     * @param key
     * @param index 索性
     * @param value
     */
    public void lset(String key,long index,String value){
        Jedis jedis=getJedis();
        jedis.lset(key,index,value);
    }
    /**
     * 移除列表元素，返回移除的元素数量
     * @param key
     * @param count 标识，表示动作或者查找方向
     * <li>当count=0时，移除所有匹配的元素；</li>
     * <li>当count为负数时，移除方向是从尾到头；</li>
     * <li>当count为正数时，移除方向是从头到尾；</li>
     * @param value 匹配的元素
     * @return
     */
    public Long lrem(String key,long count,String value) {
        Jedis jedis = getJedis();
        Long result = jedis.lrem(key, count, value);
        return result;
    }

    /**
     * 对一个列表进行修剪(trim)，就是说，
     * 让列表只保留指定区间内的元素，不在指定区间之内的元素都将被删除。
     * @param key
     * @param start
     * <li>可以为负数（-1是列表的最后一个元素，-2是列表倒数第二的元素。）</li>
     * <li>如果start大于end，则返回一个空的列表，即列表被清空</li>
     * @param end
     * <li>可以为负数（-1是列表的最后一个元素，-2是列表倒数第二的元素。）</li>
     * <li>可以超出索引，不影响结果</li>
     */
    public void ltrim(String key,long start,long end) {
        Jedis jedis = getJedis();
        jedis.ltrim(key, start, end);
    }

    /**
     * 移除并获取第一个元素，当列表不存在或者为空时，返回Null
     * @param key
     * @return
     */
    public String lpop(String key) {
        Jedis jedis = getJedis();
        try {
            String result = jedis.lpop(key);
            return result;
        } catch (Exception e) {
            if (jedis != null) {
                jedis.close();
            }
        } finally {
            jedis.close();
        }
        return null;
    }

    /**
     * 移除并获取列表最后一个元素，当列表不存在或者为空时，返回Null
     * @param key
     * @return
     */
    public String rpop(String key){
        Jedis jedis=getJedis();
        String result=jedis.rpop(key);
        return result;
    }

    /**
     *  在列表中的尾部添加一个值，返回列表的长度
     * @param key
     * @param value
     * @return
     */
    public Long rpush(String key,String value) {
        Jedis jedis = getJedis();
        Long result = jedis.rpush(key, value);
        return result;
    }

    /**
     * 在列表中的尾部添加多个值，返回列表的长度
     * @param key
     * @param values
     * @return
     */
    public Long rpush(String key,String[] values) {
        Jedis jedis = getJedis();
        Long result = jedis.rpush(key, values);
        return result;
    }

    /**
     *仅当列表存在时，才会向列表中的尾部添加一个值，返回列表的长度
     * @param key
     * @param value
     * @return
     */
    public Long rpushx(String key,String value){
        Jedis jedis = getJedis();
        Long result = jedis.rpushx(key, value);
        return result;
    }

    /**
     * 移除列表的最后一个元素，并将该元素添加到另一个列表并返回
     * @param sourceKey 源列表的key，当源key不存在时，结果返回Null
     * @param targetKey 目标列表的key，当目标key不存在时，会自动创建新的
     * @return
     */
    public String rpopLpush(String sourceKey,String targetKey){
        Jedis jedis = getJedis();
        String result=jedis.rpoplpush(sourceKey, targetKey);
        return result;
    }

    /**
     * 移出并获取列表的【第一个元素】，
     * 如果列表没有元素会阻塞列表直到等待超时或发现可弹出元素为止
     * @param timeout
     * @param keys
     * <li>当有多个key时，只要某个key值的列表有内容，即马上返回，不再阻塞。</li>
     * <li>当所有key都没有内容或不存在时，则会阻塞，直到有值返回或者超时。</li>
     * <li>当超期时间到达时，keys列表仍然没有内容，则返回Null</li>
     * @return
     */
    public List<String> blpop(int timeout,String... keys){
        Jedis jedis = getJedis();
        List<String> result=jedis.blpop(timeout, keys);
        return result;
    }

    /**
     * 移出并获取列表的【最后一个元素】，
     * 如果列表没有元素会阻塞列表直到等待超时或发现可弹出元素为止。
     * @param timeout
     * @param keys
     * <li>当有多个key时，只要某个key值的列表有内容，即马上返回，不再阻塞。</li>
     * <li>当所有key都没有内容或不存在时，则会阻塞，直到有值返回或者超时。</li>
     * <li>当超期时间到达时，keys列表仍然没有内容，则返回Null</li>
     * @return
     */
    public List<String> brpop(int timeout, String... keys){
        Jedis jedis = getJedis();
        List<String> result = jedis.brpop(timeout, keys);
        return result;
    }

    /**
     *  从列表中弹出列表最后一个值，将弹出的元素插入到另外一个列表中并返回它；
     *   如果列表没有元素会阻塞列表直到等待超时或发现可弹出元素为止。
     * @param sourceKey 源列表的key，当源key不存在时，则会进行阻塞
     * @param targetKey 目标列表的key，当目标key不存在时，会自动创建新的
     * @param timeout 单位为秒
     * @return
     */
    public String brpopLpush(String sourceKey, String targetKey, int timeout){
        Jedis jedis = getJedis();
        String result = jedis.brpoplpush(sourceKey, targetKey, timeout);
        return result;
    }
    /***********************************list结束**************************************/


    /***********************************set开始**************************************/
    /**
     * 向集合添加一个或多个成员，返回添加成功的数量
     * @param key
     * @param members
     * @return
     */
    public Long sadd(String key,String... members) {
        Jedis jedis = getJedis();
        Long value = jedis.sadd(key, members);
        return value;
    }
    /**
     * 获取集合的成员数
     * @param key
     * @return
     */
    public Long scard(String key) {
        Jedis jedis = getJedis();
        Long result = jedis.scard(key);
        return result;
    }

    /**
     * 返回集合中的所有成员
     * @param key
     * @return
     */
    public Set<String> smembers(String key){
        Jedis jedis=getJedis();
        Set<String> result=jedis.smembers(key);
        return result;
    }

    /**
     * 判断 member 元素是否是集合 key 的成员，在集合中返回True
     * @param key
     * @param member
     * @return
     */
    public boolean sIsMember(String key,String member){
        Jedis jedis=getJedis();
        boolean result=jedis.sismember(key, member);
        return result;
    }

    /**
     *  返回给定所有集合的差集（获取第一个key中与其它key不相同的值，
     *  当只有一个key时，就返回这个key的所有值）
     * @param keys
     * @return
     */
    public Set<String> sdiff(String... keys){
        Jedis jedis=getJedis();
        Set<String> result=jedis.sdiff(keys);
        return result;
    }

    /**
     * 返回给定所有集合的差集并存储在 targetKey中，
     * 类似sdiff，只是该方法把返回的差集保存到targetKey中
     * @param targetKey
     * @param keys
     */
    public void sdiffStore(String targetKey,String... keys){
        Jedis jedis=getJedis();
        jedis.sdiffstore(targetKey,keys);
    }

    /**
     * 返回给定所有集合的交集（获取第一个key中与其它key相同的值，
     * 要求所有key都要有相同的值，如果没有相同，返回Null。
     * 当只有一个key时，就返回这个key的所有值）
     * @param keys
     * @return
     */
    public Set<String> sinter(String... keys) {
        Jedis jedis = getJedis();
        Set<String> result = jedis.sinter(keys);
        return result;
    }

    /**
     * 返回给定所有集合的交集并存储在 targetKey中，类似sinter
     * @param targetKey
     * @param keys
     */
    public void sinterStore(String targetKey,String... keys){
        Jedis jedis=getJedis();
        jedis.sinterstore(targetKey,keys);
    }

    /**
     * 移除并返回集合中的一个随机元素
     *<li>当set为空或者不存在时，返回Null</li>
     * @param key
     * @return
     */
    public String spop(String key) {
        Jedis jedis = getJedis();
        String result = jedis.spop(key);
        return result;
    }

    /**
     * 返回集合中一个或多个随机数
     * <li>当count大于set的长度时，set所有值返回，不会抛错。</li>
     * <li>当count等于0时，返回[]</li>
     * <li>当count小于0时，也能返回。如-1返回一个，-2返回两个</li>
     * @param key
     * @param count
     * @return
     */
    public List<String> srandMember(String key,int count) {
        Jedis jedis = getJedis();
        List<String> result = jedis.srandmember(key, count);
        return result;
    }

    /**
     * 移除集合中一个或多个成员
     * @param key
     * @param members
     */
    public void srem(String key,String... members){
        Jedis jedis=getJedis();
        jedis.srem(key,members);
    }
    /**
     * 将 member 元素从 sourceKey 集合移动到 targetKey 集合
     * @param sourceKey
     * @param targetKey
     * @param member
     */
    public void smove(String sourceKey,String targetKey,String member){
        Jedis jedis=getJedis();
        jedis.smove(sourceKey,targetKey,member);
    }

    /**
     * 返回所有给定集合的并集，相同的只会返回一个
     * @param keys
     * @return
     */
    public Set<String> sunion(String... keys) {
        Jedis jedis = getJedis();
        Set<String> result = jedis.sunion(keys);
        return result;
    }

    /**
     * 所有给定集合的并集存储在targetKey集合中
     *<li>注：合并时，只会把keys中的集合返回，不包括targetKey本身</li>
     * <li>如果targetKey本身是有值的，合并后原来的值是没有的，因为把keys的集合重新赋值给targetKey</li>
     * <li>要想保留targetKey本身的值，keys要包含原来的targetKey</li>
     * @param targerKey
     * @param keys
     */
    public void sunionStore(String targerKey,String... keys){
        Jedis jedis = getJedis();
        jedis.sunionstore(targerKey,keys);
    }
    /**
     * <pre>
     * 查询匹配keys全部key键值列表
     * redis里边的keys* 命令
     */
    public Set<String> keys(String keysPattern) {
        Set<String> set = new HashSet<String>();
        Jedis jedis = getJedis();
        try {
            Set<String> tmp = jedis.keys(keysPattern);
            set = tmp != null ? tmp : set;
        } catch (Exception e) {
            if (jedis != null) {
                jedis.close();
            }
        } finally {
            jedis.close();
        }
        return set;
    }
    /***********************************set结束**************************************/

    /**
     * 清空数据库
     */
    public void flushDB() {
        Jedis jedis = getJedis();
        try {
            jedis.flushDB();
        } catch (Exception e) {
            if (jedis != null) {
                jedis.close();
            }
        } finally {
            jedis.close();
        }
    }
    private Jedis getJedis() {
        JedisPool jedisPool = getJedisPool();
        if (jedisPool != null) {
            return jedisPool.getResource();
        }
        return null;
    }
    public JedisPool getJedisPool() {
        return jedisPool;
    }
    public void setJedisPool(JedisPool jedisPool) {
        this.jedisPool = jedisPool;
    }
}
