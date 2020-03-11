package cn.jxufe.dao;

import cn.jxufe.bean.Love;

/**
 * @author hsw
 * @date 2020/2/9 15:43
 */
public interface LoveDao {
    Integer insertLove(Love love);
    Boolean checkAlreadyThumbUp(Integer userId, Integer blogId);
    Integer selectCountByAuthorId(Integer userId);
    Integer selectCountByBlogId(Integer userId);
    int deleteLove(Integer userId, Integer blogId);

}
