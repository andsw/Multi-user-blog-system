package cn.jxufe.vo.homepage;

import com.github.pagehelper.Page;

import java.io.Serializable;
import java.util.List;

import cn.jxufe.bean.Blog;
import cn.jxufe.bean.Corpus;
import cn.jxufe.bean.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author hsw
 * @date 2020/1/11 18:00
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class HomepageVO implements Serializable {
    private User user;
    private Page<Blog> blogs;
    private Page<Corpus> corpuses;
}
