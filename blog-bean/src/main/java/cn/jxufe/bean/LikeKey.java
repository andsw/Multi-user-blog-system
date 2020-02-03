package cn.jxufe.bean;

import java.io.Serializable;
import lombok.Data;

/**
 * like
 * @author
 */
@Data
public class LikeKey implements Serializable {
    private Integer userId;

    private Integer blogId;

    private static final long serialVersionUID = 1L;
}