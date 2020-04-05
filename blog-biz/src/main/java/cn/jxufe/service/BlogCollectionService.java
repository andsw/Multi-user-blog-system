package cn.jxufe.service;

import java.util.List;

import cn.jxufe.entity.Corpus;
import cn.jxufe.entity.vo.blog.CollectedBlog;

/**
 * @author hsw
 * @date 2020/4/1 14:01
 */
public interface BlogCollectionService {
    List<Corpus> getUserFavorites(Integer userId);

    List<CollectedBlog> getCollectedByUserId(Integer userId);
}
