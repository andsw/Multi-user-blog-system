package cn.jxufe.entity.vo.comment;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * @author hsw
 * @date 2020/4/12 16:10
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class CommentWithUserInfoVo {
    private Integer commentId;
    private String content;

    private Integer userId;
    private String username;
    private String avatar;
    private Boolean gender;

    private Integer parentId;
    private Date createTime;
}
