package cn.jxufe.dao;

import java.util.List;

import cn.jxufe.bean.Corpus;

/**
 * @author hsw
 * @date 2020/3/10 3:09 下午
 */
public interface CorpusDao {

    List<Corpus> selectCorpusByUserId(Integer userId);

    List<Corpus> selectSimpleInfoByUserId(Integer userId);

    boolean selectExistByCorpusId(Integer corpusId);

    Integer deleteByCorpusId(Integer corpusId);

    Integer insertCorpus(Corpus corpus);

    /**
     * name
     * @Param corpus
     */
    Integer updateCorpusName(Corpus corpus);
}
