package cn.aozhi.app.controller.menu;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import cn.aozhi.app.domain.menu.Menu;
import cn.aozhi.app.service.menu.MenuService;
import cn.aozhi.app.util.CommonUtil;
import cn.aozhi.app.util.Page;

@Controller
@RequestMapping("/menu")
public class MenuController {
	
	@Autowired
	private MenuService menuService;
	
	
	/**
	 * 列表（分页）
	 * @param request
	 * @param response
	 * @param currentPage
	 * @param pageSize
	 * @return
	 */
	@RequestMapping("/findMenuPage")
	public ModelAndView findPage(HttpServletRequest request){
		ModelAndView result = new ModelAndView("menu/menu_list");
		try{
			int currentPage = request.getParameter("currentPage")==null?1:Integer.parseInt(request.getParameter("currentPage"));
			int pageSize = request.getParameter("pageSize")==null?10:Integer.parseInt(request.getParameter("pageSize"));
			int startIndex = CommonUtil.getStartIndex(currentPage, pageSize);
			Map<String,Object> params =CommonUtil.getPageParams(startIndex, pageSize, currentPage, "Menu.findPage", "Menu.findPageCount");
			Page<Menu> page = menuService.getPage(currentPage, pageSize, params);
			result.addObject("page",page);
		}catch(Exception e){
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * 新增
	 * @param menu
	 * @return
	 */
	@RequestMapping("/save")
	@ResponseBody
	public boolean save(Menu menu) {
		boolean flag = false;
		try{
			menu.setId(CommonUtil.uuidGenerate());
			Menu m = (Menu) menuService.save("Menu.insert", menu);
			if(m!=null){
				flag = true;
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return flag;
	}
	
	
	/**
	 * 根据id删除
	 * @param id
	 * @return
	 */
	@RequestMapping("/delete")
	@ResponseBody
	public boolean delete(String id){
		boolean flag = false;
		try{
			int affect = menuService.delete("Menu.delete", id);
			if(affect>0){
				flag = true;
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return flag;
	}
	
	/**
	 * 根据id获取对象
	 * @param id
	 * @return
	 */
	@RequestMapping("/getById")
	@ResponseBody
	public ModelAndView getById(String id){
		ModelAndView result = new ModelAndView("menu/menu_edit");
		try{
			Menu menu = (Menu) menuService.getById("Menu.selectById",id);
			result.addObject("menu",menu);
		}catch(Exception e){
			e.printStackTrace();
		}
		return result;
	}
	
	
	 /**
	  * 更新
	  * @param menu
	  * @return
	  */
	@RequestMapping("/update")
	@ResponseBody
	public boolean update(Menu menu){
		boolean flag = false;
		try{
			int affect = menuService.update("Menu.update", menu);
			if(affect>0){
				flag = true;
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return flag;
	}
}
