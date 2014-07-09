package cn.aozhi.app.util;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.google.common.collect.Maps;

/**
 * 分页工具类
 * @author luxh
 *
 * @param <T>
 */
public class Page<T> implements Serializable {
	
	/**
	 * 记录结果
	 */
	private List<T> result;
	
	/**
	 * 当前页
	 */
	private int currentPage = 0;
	
	/**
	 * 每页记录数
	 */
	private int pageSize;
	
	/**
	 * 总记录数
	 */
	private int totalCount;
	
	/**
	 * 总页数
	 */
	private int pageCount;
	
	
	/**
	 * 上一页
	 */
	private int prePage;
	
	/**
	 * 下一页
	 */
	private int nextPage;
	
	/**
	 * 是否有上一页
	 */
	private boolean hasPerPage;
	
	/**
	 * 是否有下一页
	 */
	private boolean hasNextPage;

	/**
	 * 偏移量
	 */
	private int offset;
	
	public Page(){
	}
	
	public Page(int currentPage,int pageSize,int totalCount,List<T> result){
		this.pageSize = pageSize;
		this.currentPage = currentPage;
		this.result = result;
		this.totalCount = totalCount;
		
	
		
		//计算总页数
		 this.pageCount = (int)Math.ceil(totalCount / (double)pageSize);
        if(this.currentPage > this.pageCount) {
            this.currentPage = this.pageCount;
        }
		
    	this.prePage = currentPage-1;
		if(prePage<1){
			this.prePage = 1;
			this.hasPerPage = false;//没有上一页
		}else{
			this.hasPerPage = true;//有上一页
		}
        
		this.nextPage = currentPage+1;
		if(this.nextPage>pageCount){
			this.nextPage = pageCount;
			this.hasNextPage = false;//没有下一页
		}else{
			this.hasNextPage = true;//有下一页
		}
		
		
		//偏移量
		this.offset = (currentPage-1)*pageSize+1;
		
	}
	
	public List<T> getResult() {
		return result;
	}

	public void setResult(List<T> result) {
		this.result = result;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}

	public int getPrePage() {
		return prePage;
	}

	public void setPrePage(int prePage) {
		this.prePage = prePage;
	}

	public int getNextPage() {
		return nextPage;
	}

	public void setNextPage(int nextPage) {
		this.nextPage = nextPage;
	}

	public boolean isHasPerPage() {
		return hasPerPage;
	}

	public void setHasPerPage(boolean hasPerPage) {
		this.hasPerPage = hasPerPage;
	}

	public boolean isHasNextPage() {
		return hasNextPage;
	}

	public void setHasNextPage(boolean hasNextPage) {
		this.hasNextPage = hasNextPage;
	}

	public int getPageCount() {
		return pageCount;
	}

	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}

	public int getOffset() {
		return offset;
	}

	public void setOffset(int offset) {
		this.offset = offset;
	}

	/***********暂时没有使用**********/
	public String toJson() {
		return "{[currentPage:" + currentPage + ", pageSize:" + pageSize
				+ ", totalCount:" + totalCount + ", pageCount:" + pageCount
				+ ", prePage:" + prePage + ", nextPage:" + nextPage
				+ ", hasPerPage:" + hasPerPage + ", hasNextPage:" + hasNextPage+"]}";
	}
	
	public Map<String,Object> getPageData(){
		Map<String,Object> m = Maps.newHashMap();
		m.put("currentPage", currentPage);
		m.put("pageSize", pageSize);
		m.put("totalCount",totalCount);
		m.put("pageCount", pageCount);
		m.put("prePage", prePage);
		m.put("nextPage", nextPage);
		m.put("hasPerPage", hasPerPage);
		m.put("hasNextPage", hasNextPage);
		return m;
	}
	/***********暂时没有使用**********/
}
