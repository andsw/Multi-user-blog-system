package cn.jxufe.entity.vo.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author hsw
 * @date 2020/3/28 21:36
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BasicUserInfo {
    private Integer id;
    private String username;
    private String email;
    private String avatar;
    private Boolean gender;
}
