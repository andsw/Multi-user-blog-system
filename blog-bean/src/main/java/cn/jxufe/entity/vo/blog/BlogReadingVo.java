package cn.jxufe.entity.vo.blog;

import cn.jxufe.entity.Blog;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 出来文章本身的内容
 * 后面还需要查询以下信息：作者信息，是否关注此作者，是否点击过喜欢，是否收藏过，然后点击评论就能看到评论
 * @author hsw
 * @date 2020/3/27 10:27
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BlogReadingVo {
    private Blog blog;

    private String username;
    private String avatar;
    private Boolean gender;

    private String content;
    private Integer collectNum;
    /**
     * 是否收藏过
     */
    private Boolean collected;
    private Integer loveNum;
    /**
     * 是否点赞过
     */
    private Boolean loved;
}
