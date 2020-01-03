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

import com.scmpi.book.dao.ProductDao;
import com.scmpi.book.dao.impl.ProductDaoImpl;
import com.scmpi.book.entity.ErrorMsg;
import com.scmpi.book.entity.Product;
import com.scmpi.book.entity.ProductType;
import com.scmpi.book.service.ProductService;
import com.scmpi.book.service.impl.ProductServiceImpl;

public class SearchProductTypeServlet extends HttpServlet {

	/**
	 * 后台商品类型搜索
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
			String keyName = req.getParameter("qureyKey");
		
			if(keyName == ""){
				
				ErrorMsg em = new ErrorMsg();
				em.setMsg("查询失败，请检查输入是否正确！");
				em.setFoxurl("/servlet/ProductAdminServlet");
				session.setAttribute("ErrorMsg", em);
				req.getRequestDispatcher("/error.jsp").forward(req, res);
			}else{
				ProductService ps1=  new ProductServiceImpl();
				
				List<ProductType> typeList= ps1.getProductTypes();	//查询所有的商品类型
				List<ProductType> tList = new ArrayList<ProductType>();
				for(ProductType p:typeList){
					if(p.getcName().indexOf(keyName) != -1 || p.getCxinhao().indexOf(keyName) != -1){	//判断是否存在用户查询的关键字
						tList.add(p);
					}
					
				}
				
				session.setAttribute("ptype", tList);

				//页面跳转
				req.getRequestDispatcher("/admin/type.jsp").forward(req, res);
				
			}
			
		} catch (Exception e) {
			// TODO 自动生成的 catch 块
//			e.printStackTrace();
			ErrorMsg em = new ErrorMsg();
			em.setMsg("查询失败，请检查输入是否正确！");
			em.setFoxurl("/servlet/TypeAdminServlet");
			session.setAttribute("ErrorMsg", em);
			req.getRequestDispatcher("/error.jsp").forward(req, res);
		}
	}

}
