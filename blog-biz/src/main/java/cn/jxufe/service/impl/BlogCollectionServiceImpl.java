package cn.jxufe.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.jxufe.dao.BlogCollectionDao;
import cn.jxufe.service.BlogCollectionService;

/**
 * @author hsw
 * @date 2020/4/1 14:02
 */
@Service
public class BlogCollectionServiceImpl implements BlogCollectionService {
    @Autowired
    private BlogCollectionDao blogCollectionDao;

}
