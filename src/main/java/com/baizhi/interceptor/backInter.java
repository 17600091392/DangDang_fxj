package com.baizhi.interceptor;

import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.baizhi.entity.Admin;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.MethodFilterInterceptor;

public class backInter extends MethodFilterInterceptor{

	@Override
	protected String doIntercept(ActionInvocation ai) throws Exception {
		System.out.println("进入拦截器2");
		HttpSession session = ServletActionContext.getRequest().getSession();
		Admin admin = (Admin)session.getAttribute("admin");
		if (admin==null) {
			return "adminLogin";
		}
		ai.invoke();
		return null;
	}

}
