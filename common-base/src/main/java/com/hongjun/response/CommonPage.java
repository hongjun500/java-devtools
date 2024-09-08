package com.hongjun.response;

import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * @author hongjun500
 * @date 2023/11/22 9:21
 * @tool ThinkPadX1隐士
 * Created with 2022.2.2.IntelliJ IDEA
 * Description: 通用返回对象---分页信息
 */
@Data
public class CommonPage<T> implements Serializable {

    /**
     * 当前页
     */
    private Integer pageNum;

    /**
     * 每页数量
     */
    private Integer pageSize;

    /**
     * 过滤条件
     */
    private Predicate<T> predicate;

    /**
     * 总页数
     */
    private Integer totalPage;

    /**
     * 总条数
     */
    private Long total;

    /**
     * 数据
     */
    private List<T> data;

    /**
     * 创建分页对象
     *
     * @param data      数据
     * @param pageNum   当前页
     * @param pageSize  每页数量
     * @param totalPage 总页数
     * @param total     总条数
     */
    public static <T> CommonPage<T> create(List<T> data, Integer pageNum, Integer pageSize, Integer totalPage, Long total) {
        CommonPage<T> commonPage = new CommonPage<>();
        commonPage.setData(data);
        commonPage.setPageNum(pageNum);
        commonPage.setPageSize(pageSize < 1 ? 1 : pageSize);
        commonPage.setTotalPage(totalPage);
        commonPage.setTotal(total);
        return commonPage;
    }

    public static <T> CommonPage<T> create(List<T> data, Integer pageNum, Integer pageSize, Long total) {
        int totalPage = (int) Math.ceil((double) total / pageSize);
        return CommonPage.create(data, pageNum, pageSize, totalPage, total);
    }

    /**
     * 手动对分页数据进行封装
     *
     * @param data     分页数据
     * @param pageNum  当前页
     * @param pageSize 每页数量
     */
    public static <T> CommonPage<T> paginate(List<T> data, Integer pageNum, Integer pageSize) {
        if (data == null || data.isEmpty()) {
            return CommonPage.create(new ArrayList<>(), 1, pageSize, 1, 0L);
        }
        long total = data.size();
        pageSize = pageSize < 1 ? 1 : pageSize;
        int totalPage = (int) Math.ceil((double) total / pageSize);

        if (pageNum < 0) {
            pageNum = 1;
        } else if (pageNum > totalPage) {
            pageNum = totalPage;
        }

        // 数据偏移量
        int offset = pageNum > 0 ? ((pageNum - 1) * pageSize) : 0;

        int endIndex = Math.min(offset + pageSize, data.size());

        List<T> pageData = data.subList(offset, endIndex);

        return CommonPage.create(pageData, pageNum, pageSize, totalPage, total);
    }

    /**
     * 手动对分页数据进行封装
     * 带过滤条件
     *
     * @param predicate 过滤条件
     * @param data      分页数据
     * @param pageNum   当前页
     * @param pageSize  每页数量
     */
    public static <T> CommonPage<T> paginate(List<T> data, Integer pageNum, Integer pageSize, Predicate<T> predicate) {
        if (data == null || data.isEmpty()) {
            return CommonPage.paginate(data, pageNum, pageSize);
        }
        List<T> filteredData = data.stream()
                .filter(predicate)
                .collect(Collectors.toList());
        return CommonPage.paginate(filteredData, pageNum, pageSize);
    }
}

