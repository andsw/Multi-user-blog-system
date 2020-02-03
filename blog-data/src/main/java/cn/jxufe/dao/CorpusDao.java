package cn.jxufe.dao;

import java.util.List;

import cn.jxufe.bean.Corpus;

public interface CorpusDao {
    int deleteByPrimaryKey(Integer id);

    int insert(Corpus record);

    int insertSelective(Corpus record);

    Corpus selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Corpus record);

    int updateByPrimaryKey(Corpus record);

    List<Corpus> listByUserId(Integer userId);
}