package cn.jxufe.entity.vo.homepage;

import java.io.Serializable;

import cn.jxufe.entity.User;
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

    // extract from user class!
    private Integer blogNum;
    private Integer readNum;
    private Long wordNum;
    private Integer corpusNum;
    private Integer subNum;
    private Integer fanNum;

    /* others by calculation*/
    private Integer loveNum;
    private Integer collectionNum;
    private Integer blogCollectedNum;

}
