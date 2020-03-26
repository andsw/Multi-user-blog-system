package cn.jxufe.web.interceptor;

import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Objects;

import cn.jxufe.entity.dto.NormalResult;
import cn.jxufe.exception.BlogWritingException;
import cn.jxufe.exception.LoginException;
import cn.jxufe.exception.RegisterException;

/**
 * @author hsw
 * @date 2020/2/25 8:58 下午
 */
@ControllerAdvice
@ResponseBody
public class GlobalExceptionInterceptor  {

    /**
     * @param e
     * @return
     */
    @ExceptionHandler(Exception.class)
    public NormalResult<?> exceptionHandler(Exception e) {
        if (e instanceof MethodArgumentNotValidException) {
            // 因为hibernate-validation返回的内容过于复杂繁多，所以这里拦截了其参数检验结果做了简化
            String message = Objects.requireNonNull(((MethodArgumentNotValidException) e).getBindingResult().getFieldError()).getDefaultMessage();
            return NormalResult.failureWithMessage(message);
        } else if (e instanceof RegisterException) {
            // 注册异常
            return NormalResult.failureWithMessage(e.getMessage());
        } else if (e instanceof LoginException) {
            return NormalResult.failureWithMessage(e.getMessage());
        }
        return null;
    }

    /**
     * 文章操作异常处理
     * @param e
     * @return
     */
    public NormalResult<?> blogExceptionHandler(Exception e) {
        if (e instanceof BlogWritingException) {
            return NormalResult.failureWithMessage(e.getMessage());
        }
        return null;
    }

}
