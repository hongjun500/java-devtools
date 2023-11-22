package com.hongjun.response;

import com.google.common.collect.Lists;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.function.Predicate;

import static org.junit.jupiter.api.Assertions.*;

class CommonPageTest {

	private final List<Integer> integers = Lists.newArrayList(1, 2, 3, 4, 5);

	@Test
	void testPaginate() {

		CommonPage<Integer> page = CommonPage.create(integers, 1, 2,  (long) integers.size());
		assertEquals(3, page.getTotalPage());
		page.getData().forEach(System.out::println);

		CommonPage<Integer> paginated = CommonPage.paginate(page.getData(), 2, 1);
		assertEquals(5, paginated.getTotalPage());
		assertEquals(1, paginated.getData().size());
		paginated.getData().forEach(System.out::println);

		// 过滤掉基数
		Predicate<Integer> predicate = integer -> integer % 2 == 0;

		CommonPage<Integer> paginated2 = CommonPage.paginate(page.getData(), 1, 0, predicate);
		assertEquals(2, paginated2.getTotalPage());

	}
}