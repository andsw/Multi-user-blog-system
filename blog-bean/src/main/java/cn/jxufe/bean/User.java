package cn.jxufe.bean;

import org.hibernate.validator.constraints.Length;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import cn.jxufe.validation.group.LoginGroup;
import cn.jxufe.validation.group.RegisterGroup;
import cn.jxufe.validation.group.SelectGroup;
import cn.jxufe.validation.group.UpdateGroup;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * user
 *
 * @author hsw
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class User implements Serializable {
    @NotNull(groups = {SelectGroup.class, UpdateGroup.class}, message = "userId不能为空！")
    private Integer id;

    @NotBlank(message = "邮箱内容不能为空！", groups = {RegisterGroup.class})
    @Email(groups = {RegisterGroup.class})
    private String email;

    @Length(min = 6, max = 16, message = "密码长度必须为6～16！",
        groups = {RegisterGroup.class, LoginGroup.class})
    private String password;

    /**
     * 密码加密的盐值
     */
    private String salt;

    @NotBlank(message = "用户名内容不能为空！", groups = {RegisterGroup.class})
    @Length(min = 4, max = 20, message = "username长度必须为4～20！",
        groups = {RegisterGroup.class})
    private String username;

    /**
     * 头像图片链接
     */
    private String avatar;

    /**
     * 个性签名
     */
    private String personMsg;

    /**
     * 0男，1女，默认0
     */
    private Boolean gender;

    /**
     * 博客数量
     */
    @Min(value = 0)
    private Integer blogNum;

    /**
     * 文集数量
     */
    @Min(value = 0)
    private Integer corpusNum;

    @Min(value = 0)
    private Long wordNum;

    @Min(value = 0)
    private Integer subNum;

    @Min(value = 0)
    private Integer fanNum;

    /**
     * 注册时间，不可以更改！！！只读
     */
    private Timestamp registerAt;

    private static final long serialVersionUID = 1L;
}