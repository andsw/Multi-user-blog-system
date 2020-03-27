package cn.jxufe.dao;

import java.util.List;

/**
 * @author hsw
 * @date 2020/2/9 15:27
 */
public interface BlogCollectionDao {
    /**
     * 查找用户的文章被收藏次数
     * @param userId userId
     * @return 次数
     */
    Integer selectCountByAuthorId(Integer userId);

    /**
     * 查找用户收藏别人的文章数量
     * @param userId userId
     * @return int
     */
    Integer selectCountByUserId(Integer userId);

    List<Integer> selectCollectorByBlogId(Integer blogId);
}
