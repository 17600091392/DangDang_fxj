package com.baizhi.Action;

import java.util.List;

import com.baizhi.Service.CategoryService;
import com.baizhi.Service.Impl.CategoryServiceImpl;
import com.baizhi.entity.Category;
import com.opensymphony.xwork2.ActionSupport;

public class CategoryAction extends ActionSupport{
	 private List<Category> list;
	 private Category category;
	 private String id;
	 

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public List<Category> getList() {
		return list;
	}

	public void setList(List<Category> list) {
		this.list = list;
	}

	public String selectAll(){
		 CategoryService cs=new CategoryServiceImpl();
		 list = cs.selectAll();
		 return "success"; 
	 }
	
	public String addFirst(){
		CategoryService cs=new CategoryServiceImpl();
		cs.addFirst(category);
		return "add"; 
	}
	
	public String selectFirst(){
		CategoryService cs=new CategoryServiceImpl();
		list = cs.selectFirst();
		return "addSecond";
	}
	
	public String addSecond(){
		CategoryService cs=new CategoryServiceImpl();
		cs.addSecond(category);
		return "add"; 
	}
	
	public String delete(){
		CategoryService cs=new CategoryServiceImpl();
		cs.delete(id);
		return "add";
	}
	
	public String selectSecond(){
		CategoryService cs=new CategoryServiceImpl();
		list = cs.selectSecond();
		return "addBook";
	}
}
