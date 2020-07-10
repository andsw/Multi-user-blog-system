package cn.jxufe.entity.vo.comment;

import java.sql.Timestamp;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 文章评论的第一次加载，分别一级父评论五个以及各自的子评论五个，控制好量
 * @author hsw
 * @date 2020/4/12 11:03
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FistLoadedCommentVo {
    private Integer id;
    private String parentContent;
    private Integer commentUserId;
    private Timestamp parentCreateTime;

    // user info
    private String username;
    private String avatar;
    private Boolean gender;

    private List<CommentWithUserInfoVo> childrenComments;
}
