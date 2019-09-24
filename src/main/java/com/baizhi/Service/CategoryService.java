package com.baizhi.Service;

import java.util.List;

import com.baizhi.entity.Category;

public interface CategoryService {
	List<Category> selectAll();
	void addFirst(Category category);
	List<Category> selectFirst();
	void addSecond(Category category);
	void delete(String id);
	List<Category> selectSecond();
	List<Category> selectCategory();
	Category selectById(String fid);
}
