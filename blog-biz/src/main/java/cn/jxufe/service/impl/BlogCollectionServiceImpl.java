package cn.jxufe.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import cn.jxufe.dao.BlogCollectionDao;
import cn.jxufe.entity.Corpus;
import cn.jxufe.entity.vo.blog.CollectedBlog;
import cn.jxufe.service.BlogCollectionService;

/**
 * @author hsw
 * @date 2020/4/1 14:02
 */
@Service
public class BlogCollectionServiceImpl implements BlogCollectionService {
    @Autowired
    private BlogCollectionDao blogCollectionDao;

    @Override
    public List<Corpus> getUserFavorites(Integer userId) {
        return blogCollectionDao.selectCorpusWhereCollectedBlogIn(userId);
    }

    @Override
    public List<CollectedBlog> getCollectedByUserId(Integer userId) {
        return blogCollectionDao.selectAllBlogsInCollection(userId);
    }
}
