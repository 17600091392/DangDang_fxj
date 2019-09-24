package com.baizhi.Action;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.baizhi.Service.UserService;
import com.baizhi.Service.Impl.UserServiceImpl;
import com.baizhi.Util.MD5Utils;
import com.baizhi.entity.User;
import com.opensymphony.xwork2.ActionSupport;

public class UserAction extends ActionSupport{
	private User user;
	private String message;
	private String code;
	private String activeCode;
	private List<User> list;
	private String id;
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public List<User> getList() {
		return list;
	}

	public void setList(List<User> list) {
		this.list = list;
	}

	public void setActiveCode(String activeCode) {
		this.activeCode = activeCode;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String login(){
		UserService us=new UserServiceImpl();
		try {
			us.login(user);
			return "login";
		} catch (Exception e) {
			message=e.getMessage();
			return "error";
		}
	}
	
	public String logout(){
		UserService us=new UserServiceImpl();
		us.logout();
		return "login";
	}
	
	public String regist(){
		UserService us=new UserServiceImpl();
		HttpSession session = ServletActionContext.getRequest().getSession();
		//从session获取验证码，和输入的验证码进行对比
		String loginCode = (String)session.getAttribute("code");
		if (loginCode.equals(code)) {
			try {
				us.regist(user);
				return "ok";
			} catch (Exception e) {
				message=e.getMessage();
				return "regist";
			}
		}else {
			message="验证码错误";
			return "regist";
		}
		
	}
	
	public String getActiveCode(){
		HttpSession session = ServletActionContext.getRequest().getSession();
		//获取激活码，将激活码存入session
		String activeCode = MD5Utils.getNum();
		session.setAttribute("activeCode", activeCode);
		return "active";
	}
	
	public String active(){
		UserService us=new UserServiceImpl();
		try {
			us.activeUser(activeCode);
			return "registok";
		} catch (Exception e) {
			message=e.getMessage();
			return "active";
		}
		
	}
	
	public String selectAll(){
		UserService us=new UserServiceImpl();
		list = us.selectAll();
		return "show";
	}
	
	public String updateStatus(){
		UserService us=new UserServiceImpl();
		us.updateStatus(id);
		return "selectAll";
	}
}
