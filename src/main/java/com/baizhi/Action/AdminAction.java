package com.baizhi.Action;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.baizhi.Service.AdminService;
import com.baizhi.Service.Impl.AdminServiceImpl;
import com.baizhi.Util.SecurityCode;
import com.baizhi.Util.SecurityImage;
import com.baizhi.entity.Admin;
import com.opensymphony.xwork2.ActionSupport;

public class AdminAction extends ActionSupport{
	private Admin admin;
	private String code;
	private String message;
	
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

	public Admin getAdmin() {
		return admin;
	}

	public void setAdmin(Admin admin) {
		this.admin = admin;
	}

	public String login(){
		AdminService as=new AdminServiceImpl();
		HttpSession session = ServletActionContext.getRequest().getSession();
		String loginCode = (String)session.getAttribute("code");
		if (code.equals(loginCode)) {
			try {
				as.login(admin);
				return "success";
			} catch (Exception e) {
				message=e.getMessage();
				return "error";
			}
		}else {
			message="验证码错误!";
			return "error";
		}
	}
	
	public String code() throws IOException{
		HttpSession session = ServletActionContext.getRequest().getSession();
		String code = SecurityCode.getSecurityCode();
		System.out.println(code);
		session.setAttribute("code", code);
		
		BufferedImage image = SecurityImage.createImage(code);
		
		ServletOutputStream out = ServletActionContext.getResponse().getOutputStream();
		ImageIO.write(image, "png", out);
		return null;
		
	}
	
	public String logout(){
		HttpSession session = ServletActionContext.getRequest().getSession();
		session.invalidate();
		return "login";
		
	}
}
