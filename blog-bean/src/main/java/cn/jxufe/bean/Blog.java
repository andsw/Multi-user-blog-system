package cn.jxufe.bean;

import java.io.Serializable;
import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * blog
 * @author hsw
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class Blog implements Serializable {
    private Integer id;

    /**
     * 文章标题，长度为64
     */
    private String title;

    /**
     * 所属文集id
     */
    private Integer corpusId;

    /**
     * 文章所属用户id
     */
    private Integer userId;

    /**
     * 文章字数，默认0
     */
    private Integer wordNum;

    /**
     * 阅读数量，默认0
     */
    private Integer readNum;

    /**
     * 点赞数
     */
    private Integer loveNum;

    /**
     * 评论数
     */
    private Integer commentNum;

    /**
     * 发布时间，每次更新后，变化成更新时间
     */
    private Timestamp releaseTime;

    private static final long serialVersionUID = 1L;
}