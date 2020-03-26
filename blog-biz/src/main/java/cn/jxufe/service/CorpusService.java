package cn.jxufe.service;

import java.util.List;

import cn.jxufe.entity.Corpus;

/**
 * @author hsw
 * @date 2020/3/10 3:05 下午
 */
public interface CorpusService {
    List<Corpus> getUserCorpusList(Integer userId);

    List<Corpus> getUserCorpusSimpleList(Integer userId);

    boolean checkIfCorpusExists(Integer corpusId);

    boolean deleteCorpus(Integer corpusId, Integer userId);

    Corpus addCorpus(Corpus corpus);

    void renameCorpus(Corpus corpus);

    boolean updateBlogNum(Integer corpusId, int n);
}
