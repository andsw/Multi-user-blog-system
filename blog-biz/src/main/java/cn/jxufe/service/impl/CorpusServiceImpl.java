package cn.jxufe.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import cn.jxufe.bean.Corpus;
import cn.jxufe.dao.CorpusDao;
import cn.jxufe.dao.UserDao;
import cn.jxufe.service.CorpusService;

/**
 * @author hsw
 * @date 2020/3/10 3:08 下午
 */
@Service
public class CorpusServiceImpl implements CorpusService {

    private final CorpusDao corpusDao;
    private final UserDao userDao;

    @Autowired
    public CorpusServiceImpl(CorpusDao corpusDao, UserDao userDao) {
        this.corpusDao = corpusDao;
        this.userDao = userDao;
    }

    @Override
    public List<Corpus> getUserCorpusList(Integer userId) {
        return corpusDao.selectCorpusByUserId(userId);
    }

    /**
     * 只获取文集名字和id，避免获取过多冗余数据导致性能下降
     * @param userId
     * @return
     */
    @Override
    public List<Corpus> getUserCorpusSimpleList(Integer userId) {
        return corpusDao.selectSimpleInfoByUserId(userId);
    }

    @Override
    public boolean checkIfCorpusExists(Integer corpusId) {
        if (corpusId == null) {
            return false;
        }
        return corpusDao.selectExistByCorpusId(corpusId);
    }

    /**
     * 删除
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public boolean deleteCorpus(Integer corpusId, Integer userId) {
        if (corpusDao.deleteByCorpusId(corpusId) == 1) {
            userDao.minusNumByUserIdSelective(userId, "corpusNum", 1);
            return true;
        }
        return false;
    }

    /**
     * 添加
     * @param corpus
     * @return 文集主键
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public Corpus addCorpus(Corpus corpus) {
        if (corpusDao.insertCorpus(corpus) == 1) {
            userDao.plusNumByUserIdSelective(corpus.getUserId(), "corpus_num", 1);
            return corpus;
        }
        return null;
    }

    @Override
    public void renameCorpus(Corpus corpus) {
        corpusDao.updateCorpusName(corpus);
    }
}
