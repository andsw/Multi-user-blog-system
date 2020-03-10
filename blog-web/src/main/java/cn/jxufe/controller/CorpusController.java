package cn.jxufe.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.jxufe.dto.NormalResult;
import cn.jxufe.exception.CorpusException;
import cn.jxufe.service.CorpusService;

/**
 * @author hsw
 * @date 2020/3/10 4:11 下午
 */
@RestController
public class CorpusController {

    private final CorpusService corpusService;

    @Autowired
    public CorpusController(CorpusService corpusService) {
        this.corpusService = corpusService;
    }

    @PostMapping("/corpus")
    public NormalResult<?> addCorpus() {
        return null;
    }

    @DeleteMapping("/corpus/{corpusId}")
    public NormalResult<?> deleteCorpus(@PathVariable("corpusId") Integer corpusId) throws CorpusException {
        corpusService.deleteCorpus(corpusId);
        return NormalResult.success();
    }
}
