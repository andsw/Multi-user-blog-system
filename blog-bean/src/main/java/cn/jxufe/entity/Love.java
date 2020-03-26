package cn.jxufe.entity;

import java.io.Serializable;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * love表
 *
 * @author hsw
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class Love implements Serializable {

    private Integer userId;

    private Integer blogId;

    private Integer authorId;

    /**
     * 记住每篇文章记录赞的人要有上限，即只保存最新的几个用户点赞记录！
     */
    private Date createTime;

    private static final long serialVersionUID = 1L;
}