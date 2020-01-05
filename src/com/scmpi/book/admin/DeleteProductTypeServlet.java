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
import com.scmpi.book.service.ProductService;
import com.scmpi.book.service.impl.ProductServiceImpl;

public class DeleteProductTypeServlet extends HttpServlet {

	/**
	 * 商品类型删除
	 * 
	 * 
	 */
	
private static final long serialVersionUID = 1L;
    
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		
		res.setContentType("text/html");
		//删除商品类型
		HttpSession session = req.getSession(true);
		
		ProductService ps = new ProductServiceImpl();
		
		int typeid =Integer.parseInt(req.getParameter("tid"));
		
		
		try {
			ps.deleteProductType(typeid);
			
		} catch (Exception e) {
			e.printStackTrace();
			ErrorMsg em = new ErrorMsg();
			em.setMsg("无法删除，因为当前ProdcutType中存在商品");
			em.setFoxurl("/shopping/servlet/TypeAdminServlet");
			session.setAttribute("ErrorMsg", em);
			req.getRequestDispatcher("/shopping/error.jsp").forward(req, res);
		}
		
		
		req.getRequestDispatcher("/servlet/TypeAdminServlet").forward(req, res);
		
		
		
		
	}
	
	
}
