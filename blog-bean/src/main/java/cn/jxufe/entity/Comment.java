package cn.jxufe.entity;

import java.io.Serializable;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * TODO: 2.0 评论还需要点赞...
 * comment
 * @author hsw
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class Comment implements Serializable {
    private Integer id;

    private String content;

    /**
     * 评论所属文章
     */
    private Integer blogId;

    /**
     * 评论用户id
     */
    private Integer userId;

    /**
     * 回复的评论id，默认0，如果是直接评论父级的评论，则于parent_id相同
     */
    private Integer replyId;

    /**
     * 所属父级评论，，默认0时属于的父级评论表示本身为父级评论
     */
    private Integer parentId;

    /**
     * 评论时间
     */
    private Date createTime;

    private static final long serialVersionUID = 1L;
}