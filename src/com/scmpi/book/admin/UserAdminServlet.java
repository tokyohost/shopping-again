package com.scmpi.book.admin;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import adminAction.UserAdminAction;

import com.scmpi.book.entity.Order;
import com.scmpi.book.entity.User;
import com.scmpi.book.service.OrderService;
import com.scmpi.book.service.UserService;
import com.scmpi.book.service.impl.OrderServiceImpl;
import com.scmpi.book.service.impl.UserServiceImpl;

public class UserAdminServlet extends HttpServlet {

	/*
	 * 后台用户管理路由
	 * 
	 */
	
	private static final long serialVersionUID = 1L;
    
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		
		res.setContentType("text/html");
		//获取session 存储商品订单信息
		HttpSession session = req.getSession(true);
		
		
		String action = req.getParameter("action");
		
		switch(action){
		case "home":
			//跳转到home
			new UserAdminAction().UserAdminServlet(req, res);
			break;
		case "search":
			//跳转到搜索
			new UserAdminAction().SearchUserServlet(req, res);
			break;
		case "change":
			//跳转到修改
			new UserAdminAction().changeUserServlet(req, res);;
			break;
		case "delete":
			//删除用户
			new UserAdminAction().deleteUserServlet(req, res);
			break;
		case "addnew":
			//添加新用户
			new UserAdminAction().addNewUserServlet(req, res);
			break;
			
		default :
			//跳转到home
			new UserAdminAction().UserAdminServlet(req, res);
			break;
		}
	
	}
}
