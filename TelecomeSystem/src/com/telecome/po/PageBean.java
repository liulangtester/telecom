package com.telecome.po;

import java.util.ArrayList;
import java.util.List;

public class PageBean<T> {
	// 当前页数
	private int pageNum;
	// 总条数
	private int totalCount;
	// 总页数
	private int totalPage;
	// 每页显示数
	private int pageSize;
	// 搜索词
	private String search;
	// 分页集合
	private List<T> t = new ArrayList<T>();

	public int getPageNum() {
		if (pageNum < 1) {
			pageNum = 1;
		}
		return pageNum;
	}

	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}

	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}

	public int getTotalPage() {
		return (totalCount % pageSize) == 0 ? (totalCount / pageSize) : (totalCount / pageSize) + 1;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public String getSearch() {
		return search;
	}

	public void setSearch(String search) {
		this.search = search;
	}

	public List<T> getT() {
		return t;
	}

	public void setT(List<T> t) {
		this.t = t;
	}

}
