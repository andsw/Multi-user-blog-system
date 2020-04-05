package cn.jxufe.entity.vo.user;

import lombok.Data;

/**
 * @author hsw
 * @date 2020/4/5 15:47
 */
@Data
public class SubscribeUser {
    private Integer id;
    private String username;
    private String email;
    private String avatar;
    private Boolean gender;
}
