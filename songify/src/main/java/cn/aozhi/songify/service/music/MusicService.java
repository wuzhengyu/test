/*******************************************************************************
 * Copyright (c) 2005, 2014 springside.github.io
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 *******************************************************************************/
package cn.aozhi.songify.service.music;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springside.modules.persistence.DynamicSpecifications;
import org.springside.modules.persistence.SearchFilter;
import org.springside.modules.persistence.SearchFilter.Operator;

import cn.aozhi.songify.entity.Music;
import cn.aozhi.songify.repository.MusicDao;

// Spring Bean的标识.
@Component
// 类中所有public函数都纳入事务管理的标识.
@Transactional
public class MusicService {

	private MusicDao musicDao;

	public Music getMusic(Long id) {
		return musicDao.findOne(id);
	}

	public void saveMusic(Music entity) {
		musicDao.save(entity);
	}

	public void deleteMusic(Long id) {
		musicDao.delete(id);
	}

	public List<Music> getAllMusic() {
		return (List<Music>) musicDao.findAll();
	}

	public Page<Music> getUserMusic( Map<String, Object> searchParams, int pageNumber, int pageSize,
			String sortType) {
		PageRequest pageRequest = buildPageRequest(pageNumber, pageSize, sortType);
		Specification<Music> spec = buildSpecification(searchParams);

		return musicDao.findAll(spec, pageRequest);
	}

	/**
	 * 创建分页请求.
	 */
	private PageRequest buildPageRequest(int pageNumber, int pagzSize, String sortType) {
		Sort sort = null;
		if ("auto".equals(sortType)) {
			sort = new Sort(Direction.DESC, "id");
		} else if ("title".equals(sortType)) {
			sort = new Sort(Direction.ASC, "music_name");
		}

		return new PageRequest(pageNumber - 1, pagzSize, sort);
	}

	/**
	 * 创建动态查询条件组合.
	 */
	private Specification<Music> buildSpecification( Map<String, Object> searchParams) {
		Map<String, SearchFilter> filters = SearchFilter.parse(searchParams);
		Specification<Music> spec = DynamicSpecifications.bySearchFilter(filters.values(), Music.class);
		return spec;
	}

	@Autowired
	public void setMusicDao(MusicDao MusicDao) {
		this.musicDao = MusicDao;
	}
}
