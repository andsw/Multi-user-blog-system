package cn.jxufe.bean;

import org.apache.ibatis.type.Alias;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * like
 *
 * @author hsw
 */
@Data
public class Like implements Serializable {

    private Integer userId;

    private Integer blogId;

    /**
     * 记住每篇文章记录赞的人要有上限，即只保存最新的几个用户点赞记录！
     */
    private Date createTime;

    private static final long serialVersionUID = 1L;
}