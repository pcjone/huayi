/**
 * 
 */
package com.huayi.common.redis;

import org.apache.commons.lang3.StringUtils;

/**
 * 
* @ClassName: JedisShardInfo 
* @Description: 连接配置
* @author panlei
* @date 2017年10月18日 下午2:03:46 
*
 */
public class JedisShardInfo extends redis.clients.jedis.JedisShardInfo {

    public JedisShardInfo(String host, int port) {
        super(host, port);
    }
    
    public void setPassword(String password) {
        if (StringUtils.isNotBlank(password)) {
            super.setPassword(password);
        }
    }
}
