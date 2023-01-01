package com.hongjun.filter;

import cn.hutool.http.ContentType;
import com.hongjun.req.CustomHttpServletRequestWrapper;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.log4j.Log4j2;

import java.io.IOException;
import java.util.Objects;

/**
 * @author hongjun500
 * @date 2022/12/29 17:23
 * @tool ThinkPadX1隐士
 * Created with 2022.2.2.IntelliJ IDEA
 * Description: 自定义的过滤器
 */
@Log4j2
public class CustomFilter implements Filter {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		CustomHttpServletRequestWrapper customHttpServletRequestWrapper = null;
		try {
			// 排除文件上传的情况
			if (ContentType.MULTIPART.getValue().equals(request.getContentType())) {
				return;
			}
			HttpServletRequest req = (HttpServletRequest) request;
			customHttpServletRequestWrapper = new CustomHttpServletRequestWrapper(req);
		} catch (Exception e) {
			log.warn("CustomFilter Error:", e);
		}
		chain.doFilter(Objects.isNull(customHttpServletRequestWrapper) ? request: customHttpServletRequestWrapper, response);
	}
}
