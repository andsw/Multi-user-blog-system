package cn.jxufe.bean;

import java.io.Serializable;
import java.sql.Timestamp;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * subscription
 * @author
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class Subscription extends SubscriptionKey implements Serializable {
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