package cn.jxufe.entity;

import java.io.Serializable;
import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * subscription
 *
 * @author hsw
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class Subscription implements Serializable {
    /**
     * 被关注的用户
     */
    private Integer subId;

    /**
     * 粉丝id
     */
    private Integer fanId;

    /**
     * 关注时间
     */
    private Timestamp subTime;

    /**
     * 是否接收动态消息提醒，默认不接收
     */
    private Boolean receiveMsg;

    private static final long serialVersionUID = 1L;
}