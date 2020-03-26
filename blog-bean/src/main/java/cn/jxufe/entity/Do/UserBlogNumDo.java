package cn.jxufe.entity.Do;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 在数据库的blog表中一下统计三个数据时需要的实体类
 * 比如主页获取信息
 * @author hsw
 * @date 2020/3/26 17:35
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserBlogNumDo {
    private Integer blogNum;
    private Integer readNum;
    private Long wordNum;
}
