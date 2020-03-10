package cn.jxufe.vo.blog;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * @author hsw
 * @date 2020/3/10 3:31 下午
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class BlogInsertionVo {
    @NotNull(message = "userId不能为空")
    private Integer userId;
    @NotNull(message = "corpusId不能为空")
    private Integer corpusId;
    @NotBlank(message = "标题不能为空")
    private String title;
    private Integer wordNum;

    @NotBlank(message = "文章内容不能为空")
    private String content;
}
