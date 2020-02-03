package cn.jxufe.bean;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * user
 *
 * @author
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class User implements Serializable {
    private Integer id;

    private String email;

    private String password;

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
    private Integer blogNum;

    /**
     * 文集数量
     */
    private Integer corpusNum;

    private static final long serialVersionUID = 1L;
}