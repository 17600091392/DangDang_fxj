package com.baizhi.Service.Impl;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.baizhi.DAO.UserDAO;
import com.baizhi.Service.UserService;
import com.baizhi.Util.MD5Utils;
import com.baizhi.Util.MybatisUtil;
import com.baizhi.entity.User;




public class UserServiceImpl implements UserService{

	@Override
	public void login(User user) {
		UserDAO ud = (UserDAO)MybatisUtil.getMapper(UserDAO.class);
		HttpSession session = ServletActionContext.getRequest().getSession();
		//根据email查询用户信息
		User login = ud.selectUser(user.getEmail());
		//如果为空则无此用户
		if (login==null) {
			throw new RuntimeException("查无此用户");
		}
		//将传入的密码先加盐再加密，然后判断
		String password=MD5Utils.getPassword(user.getPassword()+login.getSalt());
		if (!password.equals(login.getPassword())) {
			throw new RuntimeException("密码错误");
		}
		//判断账户状态，如果是冻结则报错
		if (login.getStatus().equals("冻结")) {
			throw new RuntimeException("账户冻结");
		}
		//登陆成功！将用户存入session
		session.setAttribute("user", login);
		MybatisUtil.close();
	}

	@Override
	public void logout() {
		HttpSession session = ServletActionContext.getRequest().getSession();
		//删除用户信息和购物车信息！
		session.removeAttribute("user");
		session.removeAttribute("cart");
	}

	@Override
	public void regist(User user) {
		UserDAO ud = (UserDAO)MybatisUtil.getMapper(UserDAO.class);
		HttpSession session = ServletActionContext.getRequest().getSession();
		//判断该邮箱是否已被注册过
		User u = ud.selectUser(user.getEmail());
		if (u!=null) {
			throw new RuntimeException("该邮箱以已注册");
		}
		//工具类中生成盐
		String salt=MD5Utils.getSalt();
		//密码先加盐再加密
		String password=MD5Utils.getPassword(user.getPassword()+salt);
		//将id，盐，加密后密码，状态，创建日期存入对象
		user.setId(UUID.randomUUID().toString());
		user.setSalt(salt);
		user.setPassword(password);
		user.setStatus("正常");
		user.setCreate_date(new Date());
		//存入数据库中
		try {
			ud.addUser(user);
			//注册完成直接登陆，所以存session
			session.setAttribute("user", user);
			MybatisUtil.commit();
		} catch (Exception e) {
			MybatisUtil.rollback();
		}
	}

	@Override
	public void activeUser(String activeCode) {
		UserDAO ud = (UserDAO)MybatisUtil.getMapper(UserDAO.class);
		HttpSession session = ServletActionContext.getRequest().getSession();
		//从sesssion中获取激活码，和传入的激活码进行比较
		String Code = (String)session.getAttribute("activeCode");
		if (!activeCode.equals(Code)) {
			throw new RuntimeException("激活码错误");
		}
		//从session中获取用户信息，将激活码存入对象
		User user = (User)session.getAttribute("user");
		user.setCode(activeCode);
		//在数据库加入激活码
		try {
			ud.addActive(user);
			MybatisUtil.commit();
		} catch (Exception e) {
			MybatisUtil.rollback();
		}
		
	}

	@Override
	public List<User> selectAll() {
		UserDAO ud = (UserDAO)MybatisUtil.getMapper(UserDAO.class);
		List<User> list = ud.selectAll();
		MybatisUtil.close();
		return list;
	}

	@Override
	public void updateStatus(String id) {
		UserDAO ud = (UserDAO)MybatisUtil.getMapper(UserDAO.class);
		User user = ud.selectById(id);
		if (user.getStatus().equals("正常")) {
			user.setStatus("冻结");
		}else {
			user.setStatus("正常");
		}
		try {
			ud.updateStatus(user);
			MybatisUtil.commit();
		} catch (Exception e) {
			MybatisUtil.rollback();
		}
		
	}

}
