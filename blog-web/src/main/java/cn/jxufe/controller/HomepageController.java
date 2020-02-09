package cn.jxufe.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Arrays;
import java.util.List;

import cn.jxufe.bean.Blog;
import cn.jxufe.dto.NormalResult;
import cn.jxufe.service.BlogService;
import cn.jxufe.service.HomepageService;
import cn.jxufe.vo.homepage.HomepageUserVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 主页信息获取controller
 * 考虑是在service整合信息还是controller还是controller，最后还是决定用controller
 *
 * @author hsw
 * @date 2020/1/12 19:43
 */
@Controller
@Api(tags = "用户主页数据接口")
public class HomepageController {
    private BlogService blogService;
    private HomepageService homepageService;

    @Autowired
    public HomepageController(BlogService blogService, HomepageService homepageService) {
        this.blogService = blogService;
        this.homepageService = homepageService;
    }

    /**
     * 主页加载第一步先获取用户信息，比较快，再获取文章等内容！
     * @param userId userId
     * @return vo
     */
    @ApiOperation(value = "根据userId获取用户信息和一些其他统计数据（总字数，总点赞数...）")
    @ResponseBody
    @RequestMapping(value = "/home/user/{userId}", method = RequestMethod.GET)
    public NormalResult<HomepageUserVo> getHomepageUserInfo(@PathVariable("userId") Integer userId) {
        HomepageUserVo homepageUserVo;
//        try {
        homepageUserVo = homepageService.getHomepageUserVoByUserId(userId);
//        } catch (Exception e) {
//            return NormalResult.failureWithMessage("获取用户信息发生错误！");
//        }

        return NormalResult.successWithData(homepageUserVo);
    }

    /**
     * 主页加载第一步先获取用户信息，比较快，再获取文章等内容！
     *
     * @param userId userId
     * @param blogNum 个人热门文章top n，此为n
     * @return blog info list
     */
    @ApiOperation(value = "主页的热门文章，也即是主页第二步获取信息")
    @ResponseBody
    @RequestMapping(value = "/home/blog/{userId}", method = RequestMethod.GET)
    public NormalResult<List<Blog>> getHomepageHottestBlog(@PathVariable("userId") Integer userId, @RequestParam("blogNum") Integer blogNum) {
        final List<Blog> topHottestBlog;
        try {
            topHottestBlog = blogService.getTopHottestBlog(userId, blogNum);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            Arrays.stream(e.getStackTrace()).map(s -> s.toString() + "\n").forEach(System.out::println);
            return NormalResult.failureWithMessage("获取文章发生错误！");
        }

        return NormalResult.successWithData(topHottestBlog);
    }

}
