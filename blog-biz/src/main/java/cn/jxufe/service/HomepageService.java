package cn.jxufe.service;

import cn.jxufe.vo.homepage.HomepageUserVo;

/**
 * 此service专门为主页服务，因为可能会有对多个表的操作，且希望能以一个事物的方式提交DB查询，
 * 所以在service整合而非Controller调用多个service！
 * @author hsw
 * @date 2020/2/7 10:00 下午
 */
public interface HomepageService {
    HomepageUserVo getHomepageUserVoByUserId(Integer userId);
}
