package com.baizhi.Service.Impl;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.baizhi.DAO.AddressDAO;
import com.baizhi.DAO.BookDAO;
import com.baizhi.DAO.ItemDAO;
import com.baizhi.DAO.OrderDAO;
import com.baizhi.Service.OrderService;
import com.baizhi.Util.MybatisUtil;
import com.baizhi.entity.Address;
import com.baizhi.entity.Book;
import com.baizhi.entity.CartItem;
import com.baizhi.entity.Item;
import com.baizhi.entity.Order;
import com.baizhi.entity.User;

public class OrderServiceImpl implements OrderService{

	@Override
	public void addOrder(Address address) {
		//获取所用到的DAO
		AddressDAO ad = (AddressDAO)MybatisUtil.getMapper(AddressDAO.class);
		OrderDAO od = (OrderDAO)MybatisUtil.getMapper(OrderDAO.class);
		ItemDAO td = (ItemDAO)MybatisUtil.getMapper(ItemDAO.class);
		BookDAO bd = (BookDAO)MybatisUtil.getMapper(BookDAO.class);
		//获取session中资源
		HttpSession session = ServletActionContext.getRequest().getSession();
		Map<String, CartItem> map = (Map<String, CartItem>)session.getAttribute("cart");
		User user = (User)session.getAttribute("user");
		double total = (Double)session.getAttribute("total");
		
		try {
			//判断是否是新地址
			if (address.getId()==null||"".equals(address.getId())) {
				//是新地址则添加入库
				address.setId(UUID.randomUUID().toString());
				address.setUser_id(user.getId());
				ad.addAddress(address);
				System.out.println("新地址");
			}else{
				//不是新地址进行第二次判断，判断地址有无修改
				System.out.println("没修改的旧地址");
				Address add = ad.selectOne(address.getId());
				if (!add.equals(address)) {
					//修改地址
					ad.updateAddress(address);
					System.out.println("修改过的旧地址");
				}
				//没修改则不进行操作
			}
			//获取日期utilDate(后面有用)
			Date date=new Date();
			//订单表入库
			Order order=new Order();
			order.setId(UUID.randomUUID().toString());
			order.setOrder_no(date.getTime()+"");
			order.setCreate_date(date);
			order.setStatus("已付款");
			order.setUser_id(user.getId());
			order.setAddress_id(address.getId());
			order.setTotal(total);
			od.addOrder(order);
			//订单项表入库（一个订单表对应多个订单项，一个订单项对应一份商品信息）
			Set<String> ids = map.keySet();
			//有几个商品就有几个订单项表，所以用for循环，循环购物车
			for (String id : ids) {
				CartItem cart = map.get(id);
				Item item = new Item();
				item.setId(UUID.randomUUID().toString());
				item.setBook_id(id);
				item.setCount(cart.getCount());
				item.setCreate_date(date);
				item.setOrder_id(order.getId());
				td.addItem(item);
				//修改商品信息（销量加，库存减）
				Book book = bd.selectBook(id);
				book.setSale(book.getSale()+cart.getCount());
				book.setStock(book.getStock()-cart.getCount());
				bd.updateOne(book);
			}
			//删除购物车，存入订单号，用于下一个页面
			session.removeAttribute("cart");
			session.removeAttribute("save");
			session.setAttribute("orderNo", order.getOrder_no());
			
			MybatisUtil.commit();
			System.out.println("已提交");
		} catch (Exception e) {
			System.out.println("出问题回滚了");
			MybatisUtil.rollback();
		}
		
	}

	@Override
	public List<Order> selectAll() {
		OrderDAO od = (OrderDAO)MybatisUtil.getMapper(OrderDAO.class);
		List<Order> list = od.selectAll();
		MybatisUtil.close();
		return list;
	}

	@Override
	public Order selectOne(String id) {
		OrderDAO od = (OrderDAO)MybatisUtil.getMapper(OrderDAO.class);
		Order order = od.selectOne(id);
		MybatisUtil.close();
		return order;
	}

	@Override
	public List<Item> selectItem(String id) {
		OrderDAO od = (OrderDAO)MybatisUtil.getMapper(OrderDAO.class);
		List<Item> list = od.selectItem(id);
		MybatisUtil.close();
		return list;
	}
	
}
