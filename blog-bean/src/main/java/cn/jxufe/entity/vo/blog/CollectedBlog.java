package cn.jxufe.entity.vo.blog;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author hsw
 * @date 2020/4/1 16:05
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CollectedBlog {
    private Integer blogId;
    private String blogTitle;
    private Integer readNum;
    private Integer loveNum;
    private Integer commentNum;

    // user info
    private Integer userId;
    private String avatar;
    private String username;

    // corpus info
    private Integer corpusId;
    private String corpusName;
}
