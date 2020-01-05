package adminAction;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.scmpi.book.entity.User;
import com.scmpi.book.service.UserService;
import com.scmpi.book.service.impl.UserServiceImpl;

public class UserAdminAction {
	/**
	 * 后台用户管理动作
	 * 
	 */
	
	
	public static void UserAdminServlet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		//显示所有用户
		
		res.setContentType("text/html");
		//获取session 
		HttpSession session = req.getSession(true);
		
		UserService user = new UserServiceImpl();
		
		
		try {
			List<User> uList = user.queryAllUser();
			session.setAttribute("uList", uList);
			session.setAttribute("userCount", user.getUserCount());
		} catch (Exception e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		
		//页面跳转
		req.getRequestDispatcher("/admin/user.jsp").forward(req, res);
		
		
		
		
	}
	
	public static void addNewUserServlet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		//新增用户
		
		res.setContentType("text/html");
		//获取session 
		HttpSession session = req.getSession(true);
		
		UserService user = new UserServiceImpl();
		
		
		
		//do new user
		User u = new User();
		u.setUname(req.getParameter("uname"));
		u.setUpasswd(req.getParameter("upasswd"));
		u.setUemail(req.getParameter("uemail"));
		u.setUaddress(req.getParameter("uaddress"));
		u.setUphone(req.getParameter("uphone"));
		u.setUsex(req.getParameter("usex"));
		u.setBirthday(req.getParameter("birthday"));
		u.setBalance(Float.parseFloat(req.getParameter("balance")));
		u.setDiscount(Integer.parseInt(req.getParameter("discount")));
		u.setIntegral(Integer.parseInt(req.getParameter("integral")));
		
		
		try {
			user.addUser(u);
			
			List<User> uList = user.queryAllUser();
			session.setAttribute("uList", uList);
			session.setAttribute("userCount", user.getUserCount());
		} catch (Exception e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		
		//页面跳转
		req.getRequestDispatcher("/admin/user.jsp").forward(req, res);
		
		
		
		
	}
	
	public static void SearchUserServlet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		//搜索用户
		
		res.setContentType("text/html");
		//获取session 
		HttpSession session = req.getSession(true);
		
		UserService user = new UserServiceImpl();
		String Keyname = req.getParameter("qureyKey");
		
		try {
			List<User> uList = user.queryAllUser();
			List<User> sList = new ArrayList<User>();
			
			
			for(User u : uList){
				if(u.getUname().indexOf(Keyname) != -1 || String.valueOf(u.getUid()).indexOf(Keyname) != -1){
					
					sList.add(u);
				}
				
			}
			session.setAttribute("uList", sList);
			session.setAttribute("userCount", user.getUserCount());
		} catch (Exception e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		
		//页面跳转
		req.getRequestDispatcher("/admin/user.jsp").forward(req, res);
	}
	
	public static void changeUserServlet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		//修改用户信息
		
		res.setContentType("text/html");
		//获取session 
		HttpSession session = req.getSession(true);
		
		UserService user = new UserServiceImpl();
		User u = new User(
				req.getParameter("uname"),
				req.getParameter("upasswd"),
				req.getParameter("uemail"),
				req.getParameter("usex"),
				req.getParameter("birthday"),
				req.getParameter("uphone"),
				req.getParameter("uaddress"),
				0,0,0);
		
		u.setBalance(Float.parseFloat(req.getParameter("balance")));
		u.setDiscount(Integer.parseInt(req.getParameter("discount")));
		u.setIntegral(Integer.parseInt(req.getParameter("integral")));
		u.setUid(Integer.parseInt(req.getParameter("uid")));
		
		
		try {
			//修改用户信息
			user.updateUser(u);
			
		} catch (Exception e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		
		//跳转
		UserAdminServlet(req, res);
		
	}
	
	public static void deleteUserServlet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		//删除用户
		
		res.setContentType("text/html");
		//获取session 
		HttpSession session = req.getSession(true);
		
		UserService user = new UserServiceImpl();
		User u = new User(
				req.getParameter("uname"),
				req.getParameter("upasswd"),
				req.getParameter("uemail"),
				req.getParameter("usex"),
				req.getParameter("birthday"),
				req.getParameter("uphone"),
				req.getParameter("uaddress"),
				0,0,0);
		
		u.setBalance(Float.parseFloat(req.getParameter("balance")));
		u.setDiscount(Integer.parseInt(req.getParameter("discount")));
		u.setIntegral(Integer.parseInt(req.getParameter("integral")));
		u.setUid(Integer.parseInt(req.getParameter("uid")));
		
		
		try {
			//删除用户信息
			user.deleteUser(u);;
			
		} catch (Exception e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		
		//跳转
		UserAdminServlet(req, res);
		
	}

}
