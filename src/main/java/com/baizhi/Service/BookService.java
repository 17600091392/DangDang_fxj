package com.baizhi.Service;

import java.util.List;

import com.baizhi.entity.Book;

public interface BookService {
	List<Book> selectAll();
	void addBook(Book book);
	List<Book> selectByKey(String key,String content);
	Book selectBook(String id);
	void updateBook(Book book);
	void deleteBook(String id);
	List<Book> selectRecommend();
	List<Book> selectBySale();
	List<Book> selectByCreate();
	List<Book> selectBySaleAndCreate();
	List<Book> selectSecond(String fid,String sid,int pageNum,int pageSize);
	int selectCount(String fid,String sid, int pageSize);
}
