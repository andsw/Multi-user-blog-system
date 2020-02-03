package cn.jxufe.bean;

import org.apache.ibatis.type.Alias;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * like
 * @author hsw
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class Like extends LikeKey implements Serializable {
    /**
     * 记住每篇文章记录赞的人要有上限，即只保存最新的几个用户点赞记录！
     */
    private Date createTime;

    private static final long serialVersionUID = 1L;
}