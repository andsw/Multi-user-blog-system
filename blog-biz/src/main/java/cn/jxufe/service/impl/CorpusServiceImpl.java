package cn.jxufe.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.jxufe.bean.Corpus;
import cn.jxufe.dao.CorpusDao;
import cn.jxufe.service.CorpusService;

/**
 * @author hsw
 * @date 2020/1/12 19:45
 */
@Service
public class CorpusServiceImpl implements CorpusService {

    @Autowired
    private CorpusDao corpusDao;

    @Override
    public Page<Corpus> listUserCorpusByPagination(Integer userId, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        return (Page<Corpus>) corpusDao.listByUserId(userId);
    }
}
