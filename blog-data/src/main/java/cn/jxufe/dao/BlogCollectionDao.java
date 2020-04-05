package cn.jxufe.dao;

import java.util.List;

import cn.jxufe.entity.Corpus;
import cn.jxufe.entity.vo.blog.CollectedBlog;

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

    List<Corpus> selectCorpusWhereCollectedBlogIn(Integer userId);

    /**
     * 获取所有收藏的文章
     * @author hsw
     * @date 17:27 2020/4/1
     * @param userId
     * @return java.util.List<cn.jxufe.entity.vo.blog.CollectedBlog>
     **/
    List<CollectedBlog> selectAllBlogsInCollection(Integer userId);
}
