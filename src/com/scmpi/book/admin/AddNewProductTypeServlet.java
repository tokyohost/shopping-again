package com.scmpi.book.admin;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.scmpi.book.dao.ProductDao;
import com.scmpi.book.dao.impl.ProductDaoImpl;
import com.scmpi.book.entity.ErrorMsg;
import com.scmpi.book.entity.Product;
import com.scmpi.book.entity.ProductType;
import com.scmpi.book.service.ProductService;
import com.scmpi.book.service.impl.ProductServiceImpl;

public class AddNewProductTypeServlet extends HttpServlet {

	/**
	 * 后台商品类型添加
	 * 
	 */
	
	private static final long serialVersionUID = 1L;
    
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		
		res.setContentType("text/html");
		
		HttpSession session = req.getSession(true);
		
		ProductService ps = new ProductServiceImpl();
		try {
			ProductType pt = new ProductType();
//			pt.setCid(Integer.valueOf(req.getParameter("tid")));
			pt.setcName(req.getParameter("cname"));
			pt.setCxinhao(req.getParameter("cxinhao"));
			
			
		
		
		
			if(pt.getcName().equals("") || pt.getCxinhao().equals("")){
				
				ErrorMsg em = new ErrorMsg();
				em.setMsg("新增失败，请检查输入是否正确！");
				em.setFoxurl("/servlet/ProductAdminServlet");
				session.setAttribute("ErrorMsg", em);
				req.getRequestDispatcher("/error.jsp").forward(req, res);
			}else{
				ProductService ps1 = new ProductServiceImpl();
				
				ps1.insertProductType(pt);

				//页面跳转
				req.getRequestDispatcher("/servlet/TypeAdminServlet").forward(req, res);
				
			}
			
		} catch (Exception e) {
			// TODO 自动生成的 catch 块
//			e.printStackTrace();
			ErrorMsg em = new ErrorMsg();
			em.setMsg("新增失败，请检查输入是否正确！");
			em.setFoxurl("/servlet/ProductAdminServlet");
			session.setAttribute("ErrorMsg", em);
			req.getRequestDispatcher("/error.jsp").forward(req, res);

		}
		
		
		
		
		
		
	}
}
