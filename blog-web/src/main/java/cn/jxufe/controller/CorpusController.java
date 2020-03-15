package cn.jxufe.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import cn.jxufe.bean.Corpus;
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
    public NormalResult<?> addCorpus(Corpus corpus) {
        final Corpus insertedCorpus = corpusService.addCorpus(corpus);
        return NormalResult.successWithData(insertedCorpus);
    }

    @DeleteMapping("/corpus/{corpusId}")
    public NormalResult<?> deleteCorpus(@PathVariable("corpusId") Integer corpusId) throws CorpusException {
        corpusService.deleteCorpus(corpusId);
        return NormalResult.success();
    }

    @GetMapping(value = "/corpus")
    public NormalResult<?> getUserCorpus(@RequestParam("userId") Integer userId) {
        final List<Corpus> userCorpusList = corpusService.getUserCorpusList(userId);
        return NormalResult.successWithData(userCorpusList);
    }

    @GetMapping(value = "/simple/corpus")
    public NormalResult<?> getCorpusInWriting(@RequestParam("userId") Integer userId) {
        return NormalResult.successWithData(corpusService.getUserCorpusSimpleList(userId));
    }
}
