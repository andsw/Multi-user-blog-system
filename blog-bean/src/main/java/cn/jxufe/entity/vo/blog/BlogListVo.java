package cn.jxufe.entity.vo.blog;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author hsw
 * @date 2020/3/28 22:35
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BlogListVo {
    private List<BlogListItemVo> blogList;
    private boolean myBlog;
    private Integer totalPageNum;
}
