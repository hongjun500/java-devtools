import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * @author hongjun500
 * @date 2023/11/10 18:08
 * @tool ThinkPadX1隐士
 * Created with 2022.2.2.IntelliJ IDEA
 * Description:
 */


public class CommonBaseTest {
	@Test
	void test() {
		System.out.println("Hello World!");
		try {
			System.out.println("Hello World!");
			File file = new File("/home/hongjun500/Downloads/");
			InputStream inputStream = new FileInputStream(file);
		}catch (IOException e) {
			e.printStackTrace();
		}
	}
}
