/*******************************************************************************
 * Copyright (c) 2005, 2014 springside.github.io
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 *******************************************************************************/
package cn.aozhi.songify.web.music;

import java.util.Map;

import javax.servlet.ServletRequest;
import javax.validation.Valid;

import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import cn.aozhi.songify.entity.Music;
import cn.aozhi.songify.entity.User;
import cn.aozhi.songify.service.account.ShiroDbRealm.ShiroUser;
import cn.aozhi.songify.service.music.MusicService;
import org.springside.modules.web.Servlets;

import com.google.common.collect.Maps;

/**
 * Music管理的Controller, 使用Restful风格的Urls:
 * 
 * List page : GET /music/
 * Create page : GET /music/create
 * Create action : POST /music/create
 * Update page : GET /music/update/{id}
 * Update action : POST /music/update
 * Delete action : GET /music/delete/{id}
 * 
 * @author 
 */
@Controller
@RequestMapping(value = "/music")
public class MusicController {

	private static final String PAGE_SIZE = "3";

	private static Map<String, String> sortTypes = Maps.newLinkedHashMap();
	static {
		sortTypes.put("auto", "自动");
		sortTypes.put("musicName", "名称");
	}

	@Autowired
	private MusicService musicService;

	@RequestMapping(method = RequestMethod.GET)
	public String list(@RequestParam(value = "page", defaultValue = "1") int pageNumber,
			@RequestParam(value = "page.size", defaultValue = PAGE_SIZE) int pageSize,
			@RequestParam(value = "sortType", defaultValue = "auto") String sortType, Model model,
			ServletRequest request) {
		Map<String, Object> searchParams = Servlets.getParametersStartingWith(request, "search_");

		Page<Music> musics = musicService.getUserMusic( searchParams, pageNumber, pageSize, sortType);
		

		model.addAttribute("musics", musics);
		model.addAttribute("sortType", sortType);
		model.addAttribute("sortTypes", sortTypes);
		// 将搜索条件编码成字符串，用于排序，分页的URL
		model.addAttribute("searchParams", Servlets.encodeParameterStringWithPrefix(searchParams, "search_"));

		return "music/musicList";
	}

	@RequestMapping(value = "create", method = RequestMethod.GET)
	public String createForm(Model model) {
		model.addAttribute("music", new Music());
		model.addAttribute("action", "create");
		return "music/musicForm";
	}

	@RequestMapping(value = "create", method = RequestMethod.POST)
	public String create(@Valid Music newMusic, RedirectAttributes redirectAttributes) {
		musicService.saveMusic(newMusic);
		redirectAttributes.addFlashAttribute("message", "创建任务成功");
		return "redirect:/music/";
	}

	@RequestMapping(value = "update/{id}", method = RequestMethod.GET)
	public String updateForm(@PathVariable("id") Long id, Model model) {
		model.addAttribute("music", musicService.getMusic(id));
		model.addAttribute("action", "update");
		return "music/musicForm";
	}

	@RequestMapping(value = "update", method = RequestMethod.POST)
	public String update(@Valid @ModelAttribute("music") Music music, RedirectAttributes redirectAttributes) {
		musicService.saveMusic(music);
		redirectAttributes.addFlashAttribute("message", "更新任务成功");
		return "redirect:/music/";
	}

	@RequestMapping(value = "delete/{id}")
	public String delete(@PathVariable("id") Long id, RedirectAttributes redirectAttributes) {
		musicService.deleteMusic(id);
		redirectAttributes.addFlashAttribute("message", "删除任务成功");
		return "redirect:/music/";
	}

	/**
	 * 所有RequestMapping方法调用前的Model准备方法, 实现Struts2 Preparable二次部分绑定的效果,先根据form的id从数据库查出Music对象,再把Form提交的内容绑定到该对象上。
	 * 因为仅update()方法的form中有id属性，因此仅在update时实际执行.
	 */
	@ModelAttribute
	public void getMusic(@RequestParam(value = "id", defaultValue = "-1") Long id, Model model) {
		if (id != -1) {
			model.addAttribute("music", musicService.getMusic(id));
		}
	}

}
