package com.hongjun.service;

import com.hongjun.index.document.Movie;

import java.io.IOException;
import java.util.List;

public interface MovieIndexService {

    /**
     * 将csv文件转换为List数据
     * @param csvPath csv文件地址
     * @return
     */
    List<Movie> convertCSVtoList(String csvPath) throws IOException;
    List<Movie> convertCSVtoList2(String csvPath) throws IOException;
}
