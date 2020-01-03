package com.scmpi.book.service.impl;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.scmpi.book.dao.*;
import com.scmpi.book.dao.impl.*;
import com.scmpi.book.entity.User;
import com.scmpi.book.service.UserService;
import com.scmpi.book.util.DBUtil;
public class UserServiceImpl implements UserService {
    private UserDao dao=new UserDaoImpl();
    /**
     * 登录
     */
	public User login(String name, String password) throws Exception{
		User u=dao.queryByName(name);
		if(u!=null){
			if(u.getUpasswd().equals(password)){
				return u;
			}else{
				return null;
			}
		}else{
			return null;
		}
	}
	/**
	 * 添加用户信息
	 */
	public void addUser(User u) throws Exception {
		
		dao.addUser(u);
	}
	@Override
	public User getUser(int uid) throws Exception {
		// TODO 自动生成的方法存根
		//查询用户信息
		User u = null;
		String sql = "select * from `user` where `uid` = "+uid+";";
		ResultSet rs = DBUtil.queryData(sql);
		while(rs.next()){
			u = new User(rs.getInt("uid"), rs.getString("uname"), rs.getString("upasswd"), rs.getString("uemail"), rs.getString("usex"), rs.getString("birthday"), rs.getString("uphone"), rs.getString("uaddress"), rs.getFloat("balance"), rs.getInt("discount"), rs.getInt("integral"));
		}
		
		return u;
	}
	@Override
	public int getUserCount() throws Exception {
		// TODO 自动生成的方法存根
		//获取总用户数量
		int count =0;
		String sql = "select count(*)Alluser from `user`;";
		
		ResultSet rs = DBUtil.queryData(sql);
		
		while(rs.next()){
			count= rs.getInt("Alluser");
		}
		return count;
	}
	@Override
	public List<User> queryAllUser() throws Exception {
		// TODO 自动生成的方法存根
		//查询所有用户信息
		List<User> uList = new ArrayList<User>();
		User u;
		String sql = "select * from `user`;";
		ResultSet rs = DBUtil.queryData(sql);
		while(rs.next()){
			u = new User(rs.getInt("uid"), rs.getString("uname"), rs.getString("upasswd"), rs.getString("uemail"), rs.getString("usex"), rs.getString("birthday"), rs.getString("uphone"), rs.getString("uaddress"), rs.getFloat("balance"), rs.getInt("discount"), rs.getInt("integral"));
			uList.add(u);
		}
		
		return uList;
	}
	@Override
	public void updateUser(User u) throws Exception {
		// TODO Auto-generated method stub
		//修改用户信息
		
		String sql = "UPDATE `user` SET `uname` = '"+u.getUname()
				+"', `upasswd` = '"+u.getUpasswd()
				+"', `uemail` = '"+u.getUemail()
				+"', `usex` = '"+u.getUsex()
				+"', `birthday` = '"+u.getBirthday()
				+"', `uphone` = '"+u.getUphone()
				+"', `uaddress` = '"+u.getUaddress()
				+"', `balance` = "+u.getBalance()
				+", `discount` = "+u.getDiscount()
				+", `integral` = "+u.getIntegral()
				+" WHERE `uid` = "+u.getUid()+";";
		DBUtil.Update(sql);
		
	}
	@Override
	public void deleteUser(User u) throws Exception {
		// TODO Auto-generated method stub
		/**
		 * 删除用户
		 * 首先依次删除外键引用的表信息
		 * 依次删除以下表信息
		 * 1、cart
		 * 2、Order_item
		 * 3、Order
		 * 4、user
		 * 最后完成删除用户
		 * 
		 */
		String sql = "delete from `cart` where user_id="+u.getUid()+";";
		String sql2 = "delete from `Order_item` where  user_id = "+u.getUid()+";";
		String sql3 = "delete from `Order` where user_id = "+u.getUid()+";";
		String sql4 = "delete from `user` where uid = "+u.getUid()+";";
		
		DBUtil.Update(sql);
		DBUtil.Update(sql2);
		DBUtil.Update(sql3);
		DBUtil.Update(sql4);
		
	}

}
