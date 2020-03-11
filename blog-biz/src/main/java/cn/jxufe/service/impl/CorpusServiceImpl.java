package cn.jxufe.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.jxufe.bean.Corpus;
import cn.jxufe.dao.CorpusDao;
import cn.jxufe.exception.CorpusException;
import cn.jxufe.service.CorpusService;

/**
 * @author hsw
 * @date 2020/3/10 3:08 下午
 */
@Service
public class CorpusServiceImpl implements CorpusService {

    @Autowired
    private CorpusDao corpusDao;

    @Override
    public boolean checkIfCorpusExists(Integer corpusId) {
        if (corpusId == null) {
            return false;
        }
        return corpusDao.selectExistByCorpusId(corpusId);
    }

    /**
     * 删除，TODO：用户数据更新
     * @param corpusId
     * @throws CorpusException
     */
    @Override
    public void deleteCorpus(Integer corpusId) throws CorpusException {
        if (corpusId == null) {
            throw new CorpusException("找不到文集");
        }
        corpusDao.deleteByCorpusId(corpusId);
    }

    /**
     * 添加，TODO：用户数据更新
     * @param corpus
     * @return
     */
    @Override
    public Corpus addCorpus(Corpus corpus) {
        return corpusDao.insertCorpus(corpus);
    }

    @Override
    public void changeCorpusName() {

    }
}
