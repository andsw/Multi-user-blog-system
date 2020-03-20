package cn.jxufe.dao;

import java.util.List;

import cn.jxufe.bean.Blog;

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
}