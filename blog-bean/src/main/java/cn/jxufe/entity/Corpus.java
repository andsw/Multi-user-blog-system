package cn.jxufe.entity;

import java.io.Serializable;
import java.util.Date;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * corpus
 * @author PC
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class Corpus implements Serializable {
    private Integer id;

    @NotBlank(message = "文集的名字不能为空字符串")
    private String name;

    @NotNull(message = "未指定文集所属用户")
    private Integer userId;

    private Integer blogNum;

    /**
     * 文集创建时间
     */
    private Date createTime;

    private static final long serialVersionUID = 1L;
}