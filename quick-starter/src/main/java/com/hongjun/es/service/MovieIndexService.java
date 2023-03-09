package com.hongjun.es.service;

import com.hongjun.es.document.Movie;

import java.io.IOException;
import java.util.List;

public interface MovieIndexService {

    /**
     * 将csv文件转换为List数据
     * @param csvPath csv文件地址
     * @return
     */
    List<Movie> convertCSVtoList(String csvPath) throws IOException;

    <T> void setListToEs(List<T> list);
}
