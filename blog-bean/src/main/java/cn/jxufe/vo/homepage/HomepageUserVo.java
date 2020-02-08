package cn.jxufe.vo.homepage;

import java.io.Serializable;
import java.sql.Timestamp;

import cn.jxufe.bean.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author hsw
 * @date 2020/2/7 9:32 下午
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class HomepageUserVo implements Serializable {
    /* from user */
    private User user;

    /* others by calculation*/
    private Integer readNum;
    private Integer likeNum;
    private Integer collectionNum;
    private Integer blogCollectedNum;

}
