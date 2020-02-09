package cn.jxufe.bean;

import java.io.Serializable;
import java.sql.Timestamp;
import lombok.Data;

/**
 * collect
 * @author hsw
 */
@Data
public class BlogCollection implements Serializable {
    private Integer blogId;

    private Integer userId;

    private Integer authorId;

    /**
     * 记住这个功能，平常很喜欢将某些特别喜欢文章转载为自己的，这里可以实现直接将文章收藏为自己的，保存在自己的文集中。前端可以选择是否展示。
     */
    private Integer collectCorpusId;

    private static final long serialVersionUID = 1L;
}