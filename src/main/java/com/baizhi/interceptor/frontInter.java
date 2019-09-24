package com.baizhi.interceptor;

import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.baizhi.entity.User;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.MethodFilterInterceptor;

public class frontInter extends MethodFilterInterceptor{

	@Override
	protected String doIntercept(ActionInvocation ai) throws Exception {
		System.out.println("进入拦截器");
		HttpSession session = ServletActionContext.getRequest().getSession();
		User user = (User)session.getAttribute("user");
		if (user==null) {
			return "login";
		}else {
			if (user.getCode()==null||"".equals(user.getCode())) {
				return "active";
			}
			else {
				ai.invoke();
			}
		}
		return null;
	}

}
