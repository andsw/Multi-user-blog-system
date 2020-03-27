package cn.jxufe.dao;

import java.util.List;

import cn.jxufe.entity.Love;

/**
 * @author hsw
 * @date 2020/2/9 15:43
 */
public interface LoveDao {
    Integer insertLove(Love love);
    Boolean checkAlreadyThumbUp(Integer userId, Integer blogId);
    Integer selectCountByAuthorId(Integer userId);
    Integer selectCountByBlogId(Integer blogId);
    int deleteLove(Integer userId, Integer blogId);
    List<Integer> selectLoverByBlogId(Integer blogId);

}
