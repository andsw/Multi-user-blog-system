package cn.jxufe.bean;

import java.io.Serializable;
import lombok.Data;

/**
 * blog_content
 * @author
 */
@Data
public class BlogContent implements Serializable {
    /**
     * 文章内容所属文章id
     */
    private Integer blogId;

    /**
     * 内容，markdown的文本形式，最大64kb，字符集utf8mb4兼容utf8，且支持更多内容比如emoji标签
     */
    private String content;

    private static final long serialVersionUID = 1L;
}