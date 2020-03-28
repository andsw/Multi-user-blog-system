package cn.jxufe.entity.vo.blog;

import cn.jxufe.entity.Blog;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 和BlogReadingVo一样，只是少了content！
 * @author hsw
 * @date 2020/3/28 22:08
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BlogListItemVo {
    private Blog blog;
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
