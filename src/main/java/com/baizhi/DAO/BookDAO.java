package com.baizhi.DAO;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.baizhi.entity.Book;

public interface BookDAO {
	List<Book> selectAll();
	void addBook(Book book);
	List<Book> selectByKey(@Param("key")String key,@Param("content")String content);
	Book selectBook(String id);
	void updateBook(Book book);
	void deleteBook(String id);
	List<Book> selectBySale();
	List<Book> selectByCreate();
	List<Book> selectBySaleAndCreate();
	List<Book> selectSecond(@Param("fid")String fid,@Param("sid")String sid,@Param("start")int start,@Param("end")int end);
	int selectCount(@Param("fid")String fid,@Param("sid")String sid);
	void updateOne(Book book);
}
