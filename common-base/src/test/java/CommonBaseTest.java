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
	void test()  {
		System.out.println("神魔恋捏!");
		try {
			File file = new File("J:\\elasticsearch-8.5.3\\config\\certs\\http_ca.crt");
			System.out.println(file.exists());

			InputStream in = new FileInputStream(file);
			System.out.println(in.read());
		}catch (IOException e){
			e.printStackTrace();
			// throw new RuntimeException();
		}
	}
}
