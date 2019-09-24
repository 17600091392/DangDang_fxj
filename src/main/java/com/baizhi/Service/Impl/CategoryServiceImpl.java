package com.baizhi.Service.Impl;

import java.util.List;
import java.util.UUID;

import com.baizhi.DAO.CategoryDAO;
import com.baizhi.Service.CategoryService;
import com.baizhi.Util.MybatisUtil;
import com.baizhi.entity.Category;

public class CategoryServiceImpl implements CategoryService{

	@Override
	public List<Category> selectAll() {
		CategoryDAO cd = (CategoryDAO)MybatisUtil.getMapper(CategoryDAO.class);
		List<Category> list = cd.selectAll();
		MybatisUtil.close();
		return list;
	}

	@Override
	public void addFirst(Category category) {
		CategoryDAO cd = (CategoryDAO)MybatisUtil.getMapper(CategoryDAO.class);
		try {
			category.setId(UUID.randomUUID().toString());
			category.setLevels(1);
			cd.addFirst(category);
			MybatisUtil.commit();
		} catch (Exception e) {
			MybatisUtil.rollback();
		}
	}

	@Override
	public List<Category> selectFirst() {
		CategoryDAO cd = (CategoryDAO)MybatisUtil.getMapper(CategoryDAO.class);
		List<Category> list = cd.selectFirst();
		MybatisUtil.close();
		return list;
	}

	@Override
	public void addSecond(Category category) {
		CategoryDAO cd = (CategoryDAO)MybatisUtil.getMapper(CategoryDAO.class);
		try {
			category.setId(UUID.randomUUID().toString());
			category.setLevels(2);
			cd.addSecond(category);
			MybatisUtil.commit();
		} catch (Exception e) {
			MybatisUtil.rollback();
		}
		
	}

	@Override
	public void delete(String id) {
		CategoryDAO cd = (CategoryDAO)MybatisUtil.getMapper(CategoryDAO.class);
		try {
			List<Category> list = cd.selectSon(id);
			if (list.size()==0) {
				cd.delete(id);
			}
			MybatisUtil.commit();
		} catch (Exception e) {
			MybatisUtil.rollback();
		}
		
	}

	@Override
	public List<Category> selectSecond() {
		CategoryDAO cd = (CategoryDAO)MybatisUtil.getMapper(CategoryDAO.class);
		List<Category> list = cd.selectSecond();
		MybatisUtil.close();
		return list;
	}

	@Override
	public List<Category> selectCategory() {
		CategoryDAO cd = (CategoryDAO)MybatisUtil.getMapper(CategoryDAO.class);
		List<Category> list = cd.selectCategory();
		MybatisUtil.close();
		return list;
	}

	@Override
	public Category selectById(String fid) {
		CategoryDAO cd = (CategoryDAO)MybatisUtil.getMapper(CategoryDAO.class);
		Category category = cd.selectById(fid);
		MybatisUtil.close();
		return category;
	}
	
}
