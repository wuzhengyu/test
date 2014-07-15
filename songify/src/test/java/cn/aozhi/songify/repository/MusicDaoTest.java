/*******************************************************************************
 * Copyright (c) 2005, 2014 springside.github.io
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 *******************************************************************************/
package cn.aozhi.songify.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springside.modules.test.spring.SpringTransactionalTestCase;

import cn.aozhi.songify.entity.Music;

@ContextConfiguration(locations = { "/applicationContext.xml" })
public class MusicDaoTest extends SpringTransactionalTestCase {

	@Autowired
	private MusicDao musicDao;

	@Test
	public void findTasksByUserId() throws Exception {
//		//Page<Task> tasks = musicDao.findByUserId(2L, new PageRequest(0, 100, Direction.ASC, "id"));
//		assertThat(tasks.getContent()).hasSize(5);
//		assertThat(tasks.getContent().get(0).getId()).isEqualTo(1);
//
//		tasks = musicDao.findByUserId(99999L, new PageRequest(0, 100, Direction.ASC, "id"));
//		assertThat(tasks.getContent()).isEmpty();
//		assertThat(tasks.getContent()).isEmpty();
		List<Music> musics=(List<Music>)musicDao.findAll();
		assertThat(musics.size()).isEqualTo(4);
	}
}
