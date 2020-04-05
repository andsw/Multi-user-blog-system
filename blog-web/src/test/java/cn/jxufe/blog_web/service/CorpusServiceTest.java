package cn.jxufe.blog_web.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import cn.jxufe.entity.Corpus;
import cn.jxufe.service.CorpusService;

/**
 * @author hsw
 * @date 2020/1/17 22:38
 */
@SpringBootTest
public class CorpusServiceTest {

    @Autowired
    private CorpusService corpusService;

    @Test
    public void testAddCorpus() {
        Corpus corpus = new Corpus().setName("delete").setUserId(1);
        corpusService.addCorpus(corpus);
        System.out.println(corpus);
    }

    @Test
    public void testDeleteCorpus() {
        corpusService.deleteCorpus(30, 1);
    }

    @Test
    public void testRenameCorpus() {
        Corpus corpus = new Corpus().setId(19).setName("想法");
        corpusService.renameCorpus(corpus);
    }
}
