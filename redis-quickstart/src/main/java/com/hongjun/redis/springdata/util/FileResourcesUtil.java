package com.hongjun.redis.springdata.util;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.text.csv.CsvUtil;
import lombok.Data;
import org.springframework.core.io.ClassPathResource;

import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;

/**
 * @author hongjun500
 * @date 2023/12/12 14:58
 * @tool ThinkPadX1隐士
 * Created with 2022.2.2.IntelliJ IDEA
 * Description: FileResourcesUtil
 */
@Data
public class FileResourcesUtil {
    public static List<Map<String, String>> readCSV(String pathResource) throws IOException {
        // ClassPathResource resource = new ClassPathResource("mongo/csv/netflix_titles.csv");
        ClassPathResource resource = new ClassPathResource(pathResource);
        File file = resource.getFile();
        if (!file.exists()) {
            throw new RuntimeException("文件不存在");
        }
        Reader reader = new InputStreamReader(FileUtil.getInputStream(file), StandardCharsets.UTF_8);
        return CsvUtil.getReader().readMapList(reader);
    }
}
