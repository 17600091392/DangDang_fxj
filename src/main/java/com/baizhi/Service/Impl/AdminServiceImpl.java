package com.baizhi.Service.Impl;

import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.baizhi.DAO.AdminDAO;
import com.baizhi.Service.AdminService;
import com.baizhi.Util.MybatisUtil;
import com.baizhi.entity.Admin;

public class AdminServiceImpl implements AdminService{

	@Override
	public void login(Admin admin) {
		AdminDAO ad = (AdminDAO)MybatisUtil.getMapper(AdminDAO.class);
		HttpSession session = ServletActionContext.getRequest().getSession();
		Admin login = ad.login(admin.getAdmin_name());
		if (login==null) {
			throw new RuntimeException("该用户不存在！");
		}
		if (!admin.getPassword().equals(login.getPassword())) {
			throw new RuntimeException("密码错误！");
		}
		
		session.setAttribute("admin", login);
		MybatisUtil.close();
	}

}
