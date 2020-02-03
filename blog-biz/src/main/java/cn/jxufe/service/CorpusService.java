package cn.jxufe.service;

import com.github.pagehelper.Page;

import java.util.List;

import cn.jxufe.bean.Corpus;

/**
 * @author hsw
 * @date 2020/1/12 19:45
 */
public interface CorpusService {
    Page<Corpus> listUserCorpusByPagination(Integer userId, Integer pageNum, Integer pageSize);
}
