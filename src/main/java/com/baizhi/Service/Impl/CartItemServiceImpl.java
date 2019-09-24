package com.baizhi.Service.Impl;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.baizhi.DAO.BookDAO;
import com.baizhi.Service.CartItemService;
import com.baizhi.Util.MybatisUtil;
import com.baizhi.entity.Book;
import com.baizhi.entity.CartItem;

public class CartItemServiceImpl implements CartItemService{

	@Override
	public void addCart(String id) {
		//获取book属性
		HttpSession session = ServletActionContext.getRequest().getSession();
		BookDAO bd = (BookDAO)MybatisUtil.getMapper(BookDAO.class);
		Book book = bd.selectBook(id);
		//判断是否有购物车，有则用，无则创
		Map<String, CartItem> map = (Map<String, CartItem>)session.getAttribute("cart");
		double total;
		double save;
		if (map==null) {
			map=new HashMap<String, CartItem>();
			CartItem cart=new CartItem();
			cart.setBook(book);
			cart.setCount(1);
			map.put(id, cart);
			total=book.getDprice();
			save=book.getPrice()-book.getDprice();
		}else {
			total=(Double)session.getAttribute("total");
			save=(Double)session.getAttribute("save");
			CartItem cart =null;
			//判断购物车中是否已经存在该商品
			if (map.containsKey(id)) {
				cart = map.get(id);
				cart.setCount(cart.getCount()+1);
			}else {
				cart=new CartItem();
				cart.setBook(book);
				cart.setCount(1);
			}
			map.put(id, cart);
			total=total+book.getDprice();
			save=save+(book.getPrice()-book.getDprice());
		}
		session.setAttribute("cart", map);
		session.setAttribute("total", total);
		session.setAttribute("save", save);
	}

	@Override
	public void updateCart(String id, int count) {
		//根据id查出book信息
		BookDAO bd = (BookDAO)MybatisUtil.getMapper(BookDAO.class);
		Book book = bd.selectBook(id);
		//获取session 获取购物车,总价格，节省价格
		HttpSession session = ServletActionContext.getRequest().getSession();
		Map<String, CartItem> map = (Map<String , CartItem>)session.getAttribute("cart");
		double total = (Double)session.getAttribute("total");
		double save = (Double)session.getAttribute("save");
		//进行数据修改
		CartItem cart = map.get(id);
		int oldCount = cart.getCount();
		cart.setCount(count);
		total=total-(oldCount*book.getDprice())+(count*book.getDprice());
		save=save-(oldCount*(book.getPrice()-book.getDprice()))+(count*((book.getPrice()-book.getDprice())));
		map.put(id, cart);
		//更新到session中
		session.setAttribute("cart", map);
		session.setAttribute("total", total);
		session.setAttribute("save", save);
	}

	@Override
	public void deleteCart(String id) {
		//获取book属性
		BookDAO bd = (BookDAO)MybatisUtil.getMapper(BookDAO.class);
		Book book = bd.selectBook(id);
		//获取购物车，总价，节省价
		HttpSession session = ServletActionContext.getRequest().getSession();
		Map<String, CartItem> map = (Map<String, CartItem>)session.getAttribute("cart");
		Double total = (Double)session.getAttribute("total");
		Double save = (Double)session.getAttribute("save");
		//获取book的数量（用于后面从总价和节省价中减去）
		CartItem cart = map.get(id);
		int oldCount = cart.getCount();
		
		map.remove(id);
		total=total-(oldCount*book.getDprice());
		save=save-(oldCount*(book.getPrice()-book.getDprice()));
		//判断购物车是否清空
		if (map.size()==0) {
			//删除购物车以及总价，节省价
			session.removeAttribute("cart");
			session.removeAttribute("total");
			session.removeAttribute("save");
		}else{
			//更新数据
			session.setAttribute("cart", map);
			session.setAttribute("total", total);
			session.setAttribute("save", save);
		}
		
	}

}
