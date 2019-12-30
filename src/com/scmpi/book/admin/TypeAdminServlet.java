package com.scmpi.book.admin;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.scmpi.book.entity.Product;
import com.scmpi.book.entity.ProductType;
import com.scmpi.book.service.ProductService;
import com.scmpi.book.service.impl.ProductServiceImpl;

public class TypeAdminServlet extends HttpServlet {

	/**
	 * 后台商品类型管理
	 * 
	 */
	
	private static final long serialVersionUID = 1L;
    
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		
		res.setContentType("text/html");
		//获取session 存储商品订单信息
		HttpSession session = req.getSession(true);
		//获取商品类型信息
		
		ProductService pt = new ProductServiceImpl();
		try {
			List<ProductType> ptype = pt.getProductTypes();
			
			session.setAttribute("ptype", ptype);
		} catch (Exception e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		
		
		
		
		//页面跳转
		req.getRequestDispatcher("/admin/type.jsp").forward(req, res);
		
	}

}
