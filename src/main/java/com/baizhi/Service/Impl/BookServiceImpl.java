package com.baizhi.Service.Impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.UUID;

import com.baizhi.DAO.BookDAO;
import com.baizhi.Service.BookService;
import com.baizhi.Util.MybatisUtil;
import com.baizhi.entity.Book;

public class BookServiceImpl implements BookService {

	@Override
	public List<Book> selectAll() {
		BookDAO bd = (BookDAO) MybatisUtil.getMapper(BookDAO.class);
		List<Book> list = bd.selectAll();
		MybatisUtil.close();
		return list;
	}

	@Override
	public void addBook(Book book) {
		BookDAO bd = (BookDAO) MybatisUtil.getMapper(BookDAO.class);
		try {
			book.setId(UUID.randomUUID().toString());
			book.setSale(0);
			book.setCreate_date(new Date());
			bd.addBook(book);
			MybatisUtil.commit();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			MybatisUtil.rollback();
		}
	}

	@Override
	public List<Book> selectByKey(String key, String content) {
		BookDAO bd = (BookDAO) MybatisUtil.getMapper(BookDAO.class);
		List<Book> list = bd.selectByKey(key, content);
		MybatisUtil.close();
		return list;
	}

	@Override
	public Book selectBook(String id) {
		BookDAO bd = (BookDAO) MybatisUtil.getMapper(BookDAO.class);
		Book book = bd.selectBook(id);
		MybatisUtil.close();
		return book;
	}

	@Override
	public void updateBook(Book book) {
		BookDAO bd = (BookDAO) MybatisUtil.getMapper(BookDAO.class);
		try {
			bd.updateBook(book);
			MybatisUtil.commit();
		} catch (Exception e) {
			MybatisUtil.rollback();
		}
	}

	@Override
	public void deleteBook(String id) {
		BookDAO bd = (BookDAO) MybatisUtil.getMapper(BookDAO.class);
		try {
			bd.deleteBook(id);
			MybatisUtil.commit();
		} catch (Exception e) {
			MybatisUtil.rollback();
		}
	}

	@Override
	public List<Book> selectRecommend() {
		BookDAO bd = (BookDAO) MybatisUtil.getMapper(BookDAO.class);
		List<Book> all = bd.selectAll();
		int a;
		int b;
		do {
			a = new Random().nextInt(all.size());
			b = new Random().nextInt(all.size());
		} while (a == b);

		List<Book> list = new ArrayList<Book>();
		list.add(all.get(a));
		list.add(all.get(b));
		MybatisUtil.close();
		return list;
	}

	@Override
	public List<Book> selectBySale() {
		BookDAO bd = (BookDAO) MybatisUtil.getMapper(BookDAO.class);
		List<Book> list = bd.selectBySale();
		MybatisUtil.close();
		return list;
	}

	@Override
	public List<Book> selectByCreate() {
		BookDAO bd = (BookDAO) MybatisUtil.getMapper(BookDAO.class);
		List<Book> list = bd.selectByCreate();
		MybatisUtil.close();
		return list;
	}

	@Override
	public List<Book> selectBySaleAndCreate() {
		BookDAO bd = (BookDAO) MybatisUtil.getMapper(BookDAO.class);
		List<Book> list = bd.selectBySaleAndCreate();
		MybatisUtil.close();
		return list;
	}

	@Override
	public List<Book> selectSecond(String fid, String sid, int pageNum,
			int pageSize) {
		BookDAO bd = (BookDAO) MybatisUtil.getMapper(BookDAO.class);
		int start = (pageNum - 1) * pageSize + 1;
		int end = pageNum * pageSize;
		List<Book> list = bd.selectSecond(fid, sid, start, end);
		MybatisUtil.close();
		return list;
	}

	@Override
	public int selectCount(String fid, String sid, int pageSize) {
		BookDAO bd = (BookDAO)MybatisUtil.getMapper(BookDAO.class);
		int bookCount = bd.selectCount(fid, sid);
		int count=bookCount%pageSize==0?bookCount/pageSize:bookCount/pageSize+1;
		MybatisUtil.close();
		return count;
	}

}