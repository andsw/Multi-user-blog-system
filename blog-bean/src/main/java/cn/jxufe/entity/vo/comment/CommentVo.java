package cn.jxufe.entity.vo.comment;

import java.util.List;

import cn.jxufe.entity.Comment;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author hsw
 * @date 2020/4/11 21:42
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentVo {
    private List<Comment> parentList;
    private List<Comment> childrenList;
}
