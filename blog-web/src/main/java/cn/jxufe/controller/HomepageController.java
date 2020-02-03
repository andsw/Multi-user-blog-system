package cn.jxufe.controller;

import com.github.pagehelper.Page;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.jxufe.bean.Blog;
import cn.jxufe.bean.Corpus;
import cn.jxufe.bean.User;
import cn.jxufe.dto.NormalResult;
import cn.jxufe.service.BlogService;
import cn.jxufe.service.CorpusService;
import cn.jxufe.service.UserService;
import cn.jxufe.vo.homepage.HomepageVO;

/**
 * 主页信息获取controller
 * 考虑是在service整合信息还是controller还是controller，最后还是决定用controller
 *
 * @author hsw
 * @date 2020/1/12 19:43
 */
@Controller
public class HomepageController {
    private UserService userService;
    private BlogService blogService;
    private CorpusService corpusService;

    @Autowired
    public HomepageController(UserService userService, BlogService blogService, CorpusService corpusService) {
        this.userService = userService;
        this.blogService = blogService;
        this.corpusService = corpusService;
    }

    @CrossOrigin
    @ResponseBody
    @RequestMapping(value = "/home/{userId}", method = RequestMethod.GET)
    public NormalResult<HomepageVO> getHomepageInfo(@PathVariable("userId") Integer userId) {
        User homepageUser = userService.getHomepageUser(userId);
        if (homepageUser == null) {
            return NormalResult.failureWithMessage("获取用户数据失败！");
        }
        Page<Blog> blogs = blogService.listUserBlogByPagination(userId,0, 10);
        Page<Corpus> corpuses = corpusService.listUserCorpusByPagination(userId, 0, 10);

        int blogNum = (int) blogs.getTotal();
        int corpusNum = (int) corpuses.getTotal();
        boolean isTwoNumSame = homepageUser.getBlogNum().equals(blogNum)
            && homepageUser.getCorpusNum().equals(corpusNum);
        // 更新文章数和文集数目
        if (!isTwoNumSame) {
            homepageUser.setBlogNum(blogNum);
            homepageUser.setCorpusNum(corpusNum);
            userService.updateUserByUserIdSelective(new User().setId(homepageUser.getId())
                .setBlogNum(blogNum)
                .setCorpusNum(corpusNum));
        }

//        return new NormalResult<>(200, "获取成功",
//            new HomepageInfo(new User(userId, "123@qq.com", "", "bugyj",
//                "https://ss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=2900885930,693083626&fm=26&gp=0.jpg"
//                , ",", false),null, null));
        return NormalResult.successWithData(new HomepageVO(homepageUser, blogs, corpuses));
    }
}
