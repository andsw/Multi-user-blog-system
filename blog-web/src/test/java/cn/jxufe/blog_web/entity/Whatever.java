package cn.jxufe.blog_web.entity;

import org.junit.jupiter.api.Test;

import cn.jxufe.dto.myenum.HttpCodeEnum;

/**
 * @author hsw
 * @date 2020/1/18 14:30
 */
public class Whatever {
    @Test
    public void testEnum() {
        System.out.println(HttpCodeEnum.OK_CODE.getCode());
    }
}
