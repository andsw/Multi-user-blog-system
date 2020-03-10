package cn.jxufe.service;

import cn.jxufe.bean.Corpus;
import cn.jxufe.exception.CorpusException;

/**
 * @author hsw
 * @date 2020/3/10 3:05 下午
 */
public interface CorpusService {
    boolean checkIfCorpusExists(Integer corpusId);

    void deleteCorpus(Integer corpusId) throws CorpusException;

    Corpus addCorpus(Corpus corpus);
}
