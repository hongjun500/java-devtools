package com.hongjun.service.impl;

import com.hongjun.dao.*;
import com.hongjun.model.*;
import com.hongjun.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author hongjun500
 * @date 2023/4/20 14:16
 * @tool ThinkPadX1隐士
 * Created with 2022.2.2.IntelliJ IDEA
 * Description:
 */
@Service
public class MovieServiceImpl implements MovieService {

	@Autowired
	private MovieRepository movieRepository;
	@Autowired
	private GenresRepository genresRepository;
	@Autowired
	private KeywordRepository keywordRepository;
	@Autowired
	private ProductionCompanyRepository productionCompanyRepository;
	@Autowired
	private ReleaseAddressRepository releaseAddressRepository;
	@Autowired
	private SpokenLanguageRepository spokenLanguageRepository;



	@Override
	@Transactional(rollbackFor = Exception.class)
	public int saveMovies(List<Movie> movieList) {
		List<Genres> genresList = movieList.stream().flatMap(obj -> obj.getGenres().stream().distinct()).distinct().toList();
		genresRepository.saveAll(genresList);
		List<Keyword> keywordList = movieList.stream().flatMap(obj -> obj.getKeywords().stream().distinct()).distinct().toList();
		keywordRepository.saveAll(keywordList);
		List<ProductionCompany> productionCompanyList = movieList.stream().flatMap(obj -> obj.getProductionCompanies().stream().distinct()).distinct().toList();
		productionCompanyRepository.saveAll(productionCompanyList);
		List<ReleaseAddress> releaseAddressList = movieList.stream().flatMap(obj -> obj.getReleaseAddress().stream().distinct()).distinct().toList();
		// releaseAddressRepository.saveAll(releaseAddressList);
		List<SpokenLanguage> spokenLanguageList = movieList.stream().flatMap(obj -> obj.getSpokenLanguages().stream().distinct()).distinct().toList();
		spokenLanguageRepository.saveAll(spokenLanguageList);
		movieList.forEach(obj ->{
			obj.getGenres().clear();
			obj.getKeywords().clear();
			obj.getProductionCompanies().clear();
			obj.getReleaseAddress().clear();
			obj.getSpokenLanguages().clear();
		});
		return movieRepository.saveAll(movieList).size();
	}
}
