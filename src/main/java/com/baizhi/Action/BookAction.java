package com.baizhi.Action;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;

import com.baizhi.Service.BookService;
import com.baizhi.Service.CategoryService;
import com.baizhi.Service.Impl.BookServiceImpl;
import com.baizhi.Service.Impl.CategoryServiceImpl;
import com.baizhi.entity.Book;
import com.baizhi.entity.Category;
import com.opensymphony.xwork2.ActionSupport;

public class BookAction extends ActionSupport {
	private List<Book> list;
	private Book book;
	private File cover;
	private String coverFileName;
	private String coverContentType;
	private String key;
	private String content;
	private String id;
	private List<Category> Categorylist;
	private List<Book> recommend;
	private List<Book> hots;
	private List<Book> news;
	private List<Book> newAndHot;
	private String fid;
	private String sid;
	private int pageNum;
	private int pageSize = 3;
	private int count;

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public int getPageNum() {
		return pageNum;
	}

	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public String getSid() {
		return sid;
	}

	public void setSid(String sid) {
		this.sid = sid;
	}

	private Category category;

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public String getFid() {
		return fid;
	}

	public void setFid(String fid) {
		this.fid = fid;
	}

	public List<Book> getRecommend() {
		return recommend;
	}

	public void setRecommend(List<Book> recommend) {
		this.recommend = recommend;
	}

	public List<Book> getHots() {
		return hots;
	}

	public void setHots(List<Book> hots) {
		this.hots = hots;
	}

	public List<Book> getNews() {
		return news;
	}

	public void setNews(List<Book> news) {
		this.news = news;
	}

	public List<Book> getNewAndHot() {
		return newAndHot;
	}

	public void setNewAndHot(List<Book> newAndHot) {
		this.newAndHot = newAndHot;
	}

	public List<Category> getCategorylist() {
		return Categorylist;
	}

	public void setCategorylist(List<Category> categorylist) {
		Categorylist = categorylist;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public File getCover() {
		return cover;
	}

	public void setCover(File cover) {
		this.cover = cover;
	}

	public String getCoverFileName() {
		return coverFileName;
	}

	public void setCoverFileName(String coverFileName) {
		this.coverFileName = coverFileName;
	}

	public String getCoverContentType() {
		return coverContentType;
	}

	public void setCoverContentType(String coverContentType) {
		this.coverContentType = coverContentType;
	}

	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
	}

	public List<Book> getList() {
		return list;
	}

	public void setList(List<Book> list) {
		this.list = list;
	}

	public String selectAll() {
		BookService bs = new BookServiceImpl();
		list = bs.selectAll();
		return "book";
	}

	public String addBook() {
		BookService bs = new BookServiceImpl();
		String realPath = ServletActionContext.getServletContext().getRealPath(
				"/back/img");
		try {
			FileUtils
					.copyFile(cover, new File(realPath + "//" + coverFileName));
		} catch (IOException e) {
			e.printStackTrace();
		}
		book.setCover(coverFileName);
		bs.addBook(book);
		return "show";
	}

	public String selectByKey() {
		BookService bs = new BookServiceImpl();
		list = bs.selectByKey(key, content);
		return "book";
	}

	public String selectBook() {
		BookService bs = new BookServiceImpl();
		CategoryService cs = new CategoryServiceImpl();
		Categorylist = cs.selectSecond();
		book = bs.selectBook(id);
		return "update";
	}

	public String deleteBook() {
		BookService bs = new BookServiceImpl();
		bs.deleteBook(id);
		return "show";
	}

	public String updateBook() {
		BookService bs = new BookServiceImpl();
		if (coverFileName != null) {
			String realPath = ServletActionContext.getServletContext()
					.getRealPath("/back/img");
			try {
				FileUtils.copyFile(cover, new File(realPath + "//"
						+ coverFileName));
				System.out.println(realPath + "//" + coverFileName);
			} catch (IOException e) {
				e.printStackTrace();
			}
			book.setCover(coverFileName);
		}
		bs.updateBook(book);
		return "show";
	}

	public String main() {
		CategoryService cs = new CategoryServiceImpl();
		BookService bs = new BookServiceImpl();
		Categorylist = cs.selectCategory();
		recommend = bs.selectRecommend();
		hots = bs.selectBySale();
		news = bs.selectByCreate();
		newAndHot = bs.selectBySaleAndCreate();
		return "main";
	}

	public String selectOneBook() {
		BookService bs = new BookServiceImpl();
		book = bs.selectBook(id);
		return "showBook";
	}

	public String second() {
		if (pageNum==0) {
			pageNum=1;
		}
		CategoryService cs = new CategoryServiceImpl();
		BookService bs = new BookServiceImpl();
		category = cs.selectById(fid);
		list = bs.selectSecond(fid, sid, pageNum, pageSize);
		count= bs.selectCount(fid, sid, pageSize);
		return "second";
	}
}
