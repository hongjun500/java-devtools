package com.hongjun;

import lombok.Builder;
import lombok.Data;
import lombok.experimental.Tolerate;

import java.io.PipedReader;

/**
 * @author hongjun500
 * @date 2022/12/30 16:51
 * @tool ThinkPadX1隐士
 * Created with 2022.2.2.IntelliJ IDEA
 * Description:
 */
@Data
@Builder
public class DataDTO extends TemplateParam{
	private String va;

	@Tolerate
	public DataDTO() {
	}
}
