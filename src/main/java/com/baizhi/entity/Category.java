package com.baizhi.entity;

import java.io.Serializable;
import java.util.List;

public class Category implements Serializable{
	private String id;
	private String name;
	private String parent_id;
	private int levels;
	private Category father;
	private List<Category> son;
	
	public List<Category> getSon() {
		return son;
	}
	public void setSon(List<Category> son) {
		this.son = son;
	}
	public Category getFather() {
		return father;
	}
	public void setFather(Category father) {
		this.father = father;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getParent_id() {
		return parent_id;
	}
	public void setParent_id(String parent_id) {
		this.parent_id = parent_id;
	}
	public int getLevels() {
		return levels;
	}
	public void setLevels(int levels) {
		this.levels = levels;
	}
	@Override
	public String toString() {
		return "Category [id=" + id + ", name=" + name + ", parent_id="
				+ parent_id + ", levels=" + levels + ", father=" + father
				+ ", son=" + son + "]";
	}
	
}
