package com.scmpi.book.admin;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.scmpi.book.entity.ErrorMsg;
import com.scmpi.book.entity.Product;
import com.scmpi.book.entity.ProductType;
import com.scmpi.book.service.ProductService;
import com.scmpi.book.service.impl.ProductServiceImpl;

public class ChangeTypeServlet extends HttpServlet {

	/**
	 * 后台商品类型修改
	 * 
	 */
	
	private static final long serialVersionUID = 1L;
    
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		
		res.setContentType("text/html");
		//获取session 存储商品订单信息
		HttpSession session = req.getSession(true);
		
		ProductService ps = new ProductServiceImpl();
		
		ProductType pt = new ProductType();
		pt.setCid(Integer.parseInt(req.getParameter("tid")));
		pt.setcName(req.getParameter("tname"));
		pt.setCxinhao(req.getParameter("txinhao"));
		
		
		
		
		try {
			ps.updateProductType(pt);
		} catch (Exception e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
			ErrorMsg em = new ErrorMsg();
			em.setMsg("修改失败");
			em.setFoxurl("/servlet/ProductAdminServlet");
			session.setAttribute("ErrorMsg", em);
			req.getRequestDispatcher("/error.jsp").forward(req, res);
		}
		
		
		//页面跳转
		req.getRequestDispatcher("/servlet/TypeAdminServlet").forward(req, res);
		
		
		
		
	}

}
