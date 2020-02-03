package cn.jxufe.bean;

import org.apache.ibatis.type.Alias;

import java.io.Serializable;
import java.sql.Timestamp;

import lombok.Data;

/**
 * blog
 * @author
 */
@Data
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
     * 发布时间，每次更新后，变化成更新时间
     */
    private Timestamp releaseTime;

    /**
     * 编辑次数，即内容修改次数，默认0
     */
    private Byte editNum;

    /**
     * 点赞数
     */
    private Integer likeNum;

    private static final long serialVersionUID = 1L;
}