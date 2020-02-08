package cn.jxufe.bean;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * corpus
 * @author PC
 */
@Data
public class Corpus implements Serializable {
    private Integer id;

    private String name;

    private Integer userId;

    private Integer blogNum;

    /**
     * 文集创建时间
     */
    private Date createTime;

    private static final long serialVersionUID = 1L;
}