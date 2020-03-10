package cn.jxufe.dao;

/**
 * @author hsw
 * @date 2020/3/10 3:09 下午
 */
public interface CorpusDao {
    boolean selectExistByCorpusId(Integer corpusId);

    void deleteByCorpusId(Integer corpusId);
}
