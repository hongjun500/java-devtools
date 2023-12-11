package com.hongjun.web.request;

import jakarta.servlet.ReadListener;
import jakarta.servlet.ServletInputStream;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletRequestWrapper;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.io.*;

/**
 * @author hongjun500
 * @date 2022/12/29 17:25
 * @tool ThinkPadX1隐士
 * Created with 2022.2.2.IntelliJ IDEA
 * Description: 自定义处理请求信息
 */
@Slf4j
@Getter
@Setter
public class CustomHttpServletRequestWrapper extends HttpServletRequestWrapper {

	/**
	 * 保存 req body 的数据
	 */
	private String body;

	/**
	 * Constructs a request object wrapping the given request.
	 *
	 * @param request The request to wrap
	 * @throws IllegalArgumentException if the request is null
	 */
	public CustomHttpServletRequestWrapper(HttpServletRequest request) {
		super(request);
		StringBuilder stringBuilder = new StringBuilder();
		BufferedReader bufferedReader = null;
		InputStream inputStream = null;
		try {
			inputStream = request.getInputStream();
			if (inputStream != null) {
				bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
				char[] charBuffer = new char[128];
				int bytesRead = -1;
				while ((bytesRead = bufferedReader.read(charBuffer)) > 0) {
					stringBuilder.append(charBuffer, 0, bytesRead);
				}
			}
		} catch (Exception e) {
			log.warn("customHttpServletRequestWrapper Error:", e);
		}finally {
			if (inputStream != null) {
				try {
					inputStream.close();
				}
				catch (IOException e) {
					log.warn("customHttpServletRequestWrapper Error:", e);
				}
			}
			if (bufferedReader != null) {
				try {
					bufferedReader.close();
				}
				catch (IOException e) {
					log.warn("customHttpServletRequestWrapper Error:", e);
				}
			}
		}
		body = stringBuilder.toString();
	}


	@Override
	public ServletInputStream getInputStream() throws IOException {
		final ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(body.getBytes());
		return new ServletInputStream() {
			@Override
			public boolean isFinished() {
				return false;
			}
			@Override

			public boolean isReady() {
				return false;
			}
			@Override
			public void setReadListener(ReadListener readListener) {
			}
			@Override
			public int read() throws IOException {
				return byteArrayInputStream.read();
			}
		};
	}

	@Override
	public BufferedReader getReader() throws IOException {
		return new BufferedReader(new InputStreamReader(this.getInputStream()));
	}
}
