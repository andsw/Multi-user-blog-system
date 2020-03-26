package cn.jxufe.dao;

import java.util.List;

import cn.jxufe.entity.Blog;
import cn.jxufe.entity.Do.UserBlogNumDo;

public interface BlogDao {

    List<Blog> listByUserId(Integer userId);

    Blog selectByBlogId(Integer blogId);

    /**
     *
     * @param userId userId
     * @param blogNum 可为null
     * @return 总的被阅读数量
     */
    Integer selectSumReadNumByUserIdLimit(Integer userId, Integer blogNum);

    List<Blog> selectBlogByUserIdSortedByReadNum(Integer userId);

    Integer deleteByBlogId(Integer blogId);

    Integer insertBlog(Blog blog);

    /**
     * 字数，文章数，阅读数，
     * TODO: 记住sum，max等聚合函数可以添加列来提高效率
     * @param userId
     * @return
     */
    UserBlogNumDo selectNumInfoByUserId(Integer userId);
}