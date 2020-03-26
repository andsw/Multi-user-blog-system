package cn.jxufe.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import cn.jxufe.entity.Corpus;
import cn.jxufe.entity.dto.NormalResult;
import cn.jxufe.service.CorpusService;
import cn.jxufe.util.CookieUtil;

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
        final Corpus newCorpus = corpusService.addCorpus(corpus);
        return newCorpus == null ?
            NormalResult.failure() : NormalResult.successWithData(newCorpus);
    }

    @GetMapping(value = "/corpus")
    public NormalResult<?> getUserCorpus(@RequestParam("userId") Integer userId) {
        final List<Corpus> userCorpusList = corpusService.getUserCorpusList(userId);
        return NormalResult.successWithData(userCorpusList);
    }

    /**
     * 博客撰写界面获取简单corpus列表
     * @param userId
     * @return
     */
    @GetMapping(value = "/simple/corpus")
    public NormalResult<?> getCorpusInWriting(@RequestParam("userId") Integer userId) {
        return NormalResult.successWithData(corpusService.getUserCorpusSimpleList(userId));
    }

    @DeleteMapping("/corpus/{corpusId}")
    public NormalResult<?> deleteCorpus(@PathVariable("corpusId") Integer corpusId, HttpServletRequest request) {
        String userIdStr = CookieUtil.getCookieValue(request, "userId");
        if (userIdStr == null) {
            return NormalResult.failureWithMessage("userId cookie未找到！");
        }
        return corpusService.deleteCorpus(corpusId, Integer.parseInt(userIdStr)) ?
            NormalResult.success() : NormalResult.failure();
    }

    @PutMapping(value = "/corpus")
    public NormalResult<?> renameCorpus(Corpus corpus) {
        corpusService.renameCorpus(corpus);
        return NormalResult.success();
    }
}
