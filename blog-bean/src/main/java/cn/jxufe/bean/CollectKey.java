package cn.jxufe.bean;

import java.io.Serializable;
import lombok.Data;

/**
 * collect
 * @author 
 */
@Data
public class CollectKey implements Serializable {
    private Integer blogId;

    private Integer userId;

    private static final long serialVersionUID = 1L;
}