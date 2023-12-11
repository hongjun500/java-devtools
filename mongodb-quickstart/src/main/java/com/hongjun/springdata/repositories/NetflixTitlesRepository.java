package com.hongjun.springdata.repositories;

import com.hongjun.springdata.document.NetflixTitles;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.data.repository.ListPagingAndSortingRepository;

import java.util.List;

/**
 * @author hongjun500
 * @date 2023/12/11
 * @tool ThinkPadX1隐士
 * Created with 2022.2.2.IntelliJ IDEA
 * Description:
 */
public interface NetflixTitlesRepository extends ListCrudRepository<NetflixTitles, Object>, ListPagingAndSortingRepository<NetflixTitles, Object> {

	/**
	 * 根据 type 搜索
	 * @param type type = Movie or TV show
	 // * @param title title = ?
	 * @return list
	 */
	List<NetflixTitles> findByType(String type);

	/**
	 * 根据 title or description like ?
	 * @param title title
	 * @param description description
	 * @return list
	 */
	List<NetflixTitles> findAllByTitleLikeOrDescriptionLike(String title, String description);

	/**
	 * 根据 type 和 releaseYear 分页查询
	 * @param pageable 分页参数和排序
	 * @param type Movie or TV show
	 * @param releaseYear 上映年份
	 * @return page
	 */
	Page<NetflixTitles> findAllByTypeAndReleaseYear(Pageable pageable, String type, String releaseYear);

	/**
	 * 根据 showId 删除
	 * @param showId showId
	 * @return true/false
	 */
	Long deleteByShowId(String showId);

	List<NetflixTitles> findAllByShowId(String showId);
}
