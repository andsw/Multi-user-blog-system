package cn.jxufe.bean;

import java.io.Serializable;
import lombok.Data;

/**
 * subscription
 * @author
 */
@Data
public class SubscriptionKey implements Serializable {
    /**
     * 被关注的用户
     */
    private Integer subId;

    /**
     * 粉丝id
     */
    private Integer fanId;

    private static final long serialVersionUID = 1L;
}