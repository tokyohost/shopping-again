package com.scmpi.book.dao.impl;

import com.scmpi.book.dao.OrderDao;
import com.scmpi.book.dao.UserDao;
import com.scmpi.book.entity.Order;
import com.scmpi.book.entity.OrderItem;
import com.scmpi.book.entity.Product;
import com.scmpi.book.entity.User;
import com.scmpi.book.util.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class OrderDaoImpl implements OrderDao {

	public void insertOrder(Order o) throws Exception {
		
		try {
			
//			int ids = CheckId.getOid("order_item");
			User u = o.getUser();
			int uid = u.getUid();
			Getorder_uuid gu = new  Getorder_uuid();
			String uuid = gu.getuuid();	//��ȡuuid��Ϊ����Ψһʶ����
			String sql = "insert into `Order`(`user_id`,`Total_amount`,`order_date`,`order_status`,`uuid`)values("
				+ uid
				+ ","
				+ o.getTotal_amount()
				+ ", '"+o.getOrder_date()+"','"
				+ o.getOrder_status() + "','"+uuid+"')";
			System.out.println("SQL="+sql);
			DBUtil.Update(sql);	
//			o.setOrder_id(ids);	//�������Զ�������Ӧ�ò��û�ȡ
			
			
//			
			for (OrderItem oi : o.getItems()) {
				int pid=oi.getP().getPid();
				String sql1 = "insert into `Order_item`(`Oid`,`user_id`,`product_id`,`order_num`,`order_Subtotal`,`uuid`) values(NULL,"
						+ u.getUid() + ","+oi.getP().getPid()+","+oi.getOrder_num()+","+oi.getOrder_subtotal()+",'"+uuid+"')";
				DBUtil.Update(sql1);
//				oi.setOiid(itemid);	//oid������������
				
			}
			
			//���¶��µ���Ʒ���������޸�
			
			for (OrderItem oi : o.getItems()){
				int pid = oi.getP().getPid();	//��ȡ��Ҫ�޸ĵ���Ʒid
				/*
				 * (oi.getP().getPnumber()-oi.getOrder_num()) ��ȡ֮ǰ��Ʒ������ȥ�µ�����С������
				 */
				String changeSql = "UPDATE `product_item` SET  `pnumber` = "+(oi.getP().getPnumber()-oi.getOrder_num())+" WHERE `pid` = "+pid+";";
				
				DBUtil.Update(changeSql);
			}
			
			//���¶��û��������޸�
			float balance = u.getBalance()-o.getTotal_amount();	//�����µ����ȥ�ܼۺ��û����
			String cBalanceSql = "UPDATE `user` SET `balance` = "+balance+" WHERE `uid` = "+u.getUid()+";";
			DBUtil.Update(cBalanceSql);
			
		} catch (Exception e) {
             e.printStackTrace();
		}

	}
	
	
	public List<Order> queryOrder(User u) throws ClassNotFoundException, SQLException{
		//����ݿ��ȡ������Ϣ
		
		String sql = "select * from `Order` where `user_id`="+u.getUid();
		
		//��ȡ�������
		ResultSet rs = DBUtil.queryData(sql);
		
		
		List<Order> orderlist = new ArrayList<Order>();
		
			while(rs.next()){
				Order o = new Order();
				o.setOrder_id(rs.getInt("Order_id"));
				o.setUser_id(rs.getInt("user_id"));
				o.setTotal_amount(rs.getFloat("Total_amount"));
				o.setOrder_date(rs.getString("order_date"));
				o.setOrder_status(rs.getString("order_status"));
				o.setUuid(rs.getString("uuid"));
				
				
				
				orderlist.add(o);//��ӵ�����б���
				
			}
			//��ȡ��ϵ���󶩵�ÿһ��order_item
			for(Order o:orderlist){
				List<OrderItem> oid = new ArrayList<OrderItem>();
				String uuid = o.getUuid();
				//��ݶ���uuid��ѯ������
				String sql1 = "select * from `Order_item` where `uuid`='"+uuid+"';";

				rs = DBUtil.queryData(sql1);	//��ѯ������¸�ֵ
				
					while(rs.next()){
						OrderItem oi = new OrderItem();
						oi.setOid(rs.getInt("Oid"));
						oi.setUser_id(rs.getInt("user_id"));
						oi.setProduct_id(rs.getInt("product_id"));
						oi.setOrder_num(rs.getInt("order_num"));
						oi.setOrder_subtotal(rs.getFloat("order_Subtotal"));
						oi.setUuid(uuid);
						
						oid.add(oi);
					}
					
					//���product_id ��ѯ��Ʒ��Ϣ
					for(OrderItem oi:oid){
						int productid = oi.getProduct_id();	//��ȡproduct_id ��ѯproduct_item�е���Ʒ��
						
						String sql2 = "select * from `product_item` where `pid`="+productid+";";
						
						
						rs = DBUtil.queryData(sql2);	//��ѯ������¸�ֵ
						
						
							while(rs.next()){
								Product p = new Product();
								p.setPid(productid);
								p.setPname(rs.getString("pname"));
								p.setPclassifyid(String.valueOf(rs.getInt("pclassifyid")));
								p.setPdate(rs.getString("pdate"));
								p.setSuppliers(rs.getString("Suppliers"));
								p.setPnumber(rs.getInt("pnumber"));
								p.setPrice(rs.getFloat("price"));
								
								oi.setP(p);
							}
					}
					Set<OrderItem> cache = new HashSet<OrderItem>(oid);			//List�б�ת  Set�������飡
					o.setItems(cache);
			}
			
			
			return orderlist;
			
		
	}
	
	
	public List<Order> queryAllOrder() throws ClassNotFoundException, SQLException{
		//����ݿ��ȡ���ж�����Ϣ
		
		String sql = "select * from `Order`";
		
		//��ȡ�������
		ResultSet rs = DBUtil.queryData(sql);
		
		
		List<Order> orderlist = new ArrayList<Order>();
		
			while(rs.next()){
				Order o = new Order();
				o.setOrder_id(rs.getInt("Order_id"));
				o.setUser_id(rs.getInt("user_id"));
				o.setTotal_amount(rs.getFloat("Total_amount"));
				o.setOrder_date(rs.getString("order_date"));
				o.setOrder_status(rs.getString("order_status"));
				o.setUuid(rs.getString("uuid"));
				
				
				
				orderlist.add(o);//��ӵ�����б���
				
			}
			//��ȡ��ϵ���󶩵�ÿһ��order_item
			for(Order o:orderlist){
				List<OrderItem> oid = new ArrayList<OrderItem>();
				String uuid = o.getUuid();
				//��ݶ���uuid��ѯ������
				String sql1 = "select * from `Order_item` where `uuid`='"+uuid+"';";

				rs = DBUtil.queryData(sql1);	//��ѯ������¸�ֵ
				
					while(rs.next()){
						OrderItem oi = new OrderItem();
						oi.setOid(rs.getInt("Oid"));
						oi.setUser_id(rs.getInt("user_id"));
						oi.setProduct_id(rs.getInt("product_id"));
						oi.setOrder_num(rs.getInt("order_num"));
						oi.setOrder_subtotal(rs.getFloat("order_Subtotal"));
						oi.setUuid(uuid);
						
						oid.add(oi);
					}
					
					//���product_id ��ѯ��Ʒ��Ϣ
					for(OrderItem oi:oid){
						int productid = oi.getProduct_id();	//��ȡproduct_id ��ѯproduct_item�е���Ʒ��
						
						String sql2 = "select * from `product_item` where `pid`="+productid+";";
						
						
						rs = DBUtil.queryData(sql2);	//��ѯ������¸�ֵ
						
						
							while(rs.next()){
								Product p = new Product();
								p.setPid(productid);
								p.setPname(rs.getString("pname"));
								p.setPclassifyid(String.valueOf(rs.getInt("pclassifyid")));
								p.setPdate(rs.getString("pdate"));
								p.setSuppliers(rs.getString("Suppliers"));
								p.setPnumber(rs.getInt("pnumber"));
								p.setPrice(rs.getFloat("price"));
								
								oi.setP(p);
							}
					}
					Set<OrderItem> cache = new HashSet<OrderItem>(oid);			//List�б�ת  Set�������飡
					o.setItems(cache);
			}
			
			
			return orderlist;
			
		
	}

	public List<Order> queryLimitOrder() throws ClassNotFoundException, SQLException{
		//����ݿ��ȡ���ж�����Ϣ
		
		String sql = "select * from `Order` order by `Order_id` desc LIMIT 10";
		
		//��ȡ�������
		ResultSet rs = DBUtil.queryData(sql);
		
		
		List<Order> orderlist = new ArrayList<Order>();
		
			while(rs.next()){
				Order o = new Order();
				o.setOrder_id(rs.getInt("Order_id"));
				o.setUser_id(rs.getInt("user_id"));
				o.setTotal_amount(rs.getFloat("Total_amount"));
				o.setOrder_date(rs.getString("order_date"));
				o.setOrder_status(rs.getString("order_status"));
				o.setUuid(rs.getString("uuid"));
				
				
				
				orderlist.add(o);//��ӵ�����б���
				
			}
			//��ȡ��ϵ���󶩵�ÿһ��order_item
			for(Order o:orderlist){
				List<OrderItem> oid = new ArrayList<OrderItem>();
				String uuid = o.getUuid();
				//��ݶ���uuid��ѯ������
				String sql1 = "select * from `Order_item` where `uuid`='"+uuid+"';";

				rs = DBUtil.queryData(sql1);	//��ѯ������¸�ֵ
				
					while(rs.next()){
						OrderItem oi = new OrderItem();
						oi.setOid(rs.getInt("Oid"));
						oi.setUser_id(rs.getInt("user_id"));
						oi.setProduct_id(rs.getInt("product_id"));
						oi.setOrder_num(rs.getInt("order_num"));
						oi.setOrder_subtotal(rs.getFloat("order_Subtotal"));
						oi.setUuid(uuid);
						
						oid.add(oi);
					}
					
					//���product_id ��ѯ��Ʒ��Ϣ
					for(OrderItem oi:oid){
						int productid = oi.getProduct_id();	//��ȡproduct_id ��ѯproduct_item�е���Ʒ��
						
						String sql2 = "select * from `product_item` where `pid`="+productid+";";
						
						
						rs = DBUtil.queryData(sql2);	//��ѯ������¸�ֵ
						
						
							while(rs.next()){
								Product p = new Product();
								p.setPid(productid);
								p.setPname(rs.getString("pname"));
								p.setPclassifyid(String.valueOf(rs.getInt("pclassifyid")));
								p.setPdate(rs.getString("pdate"));
								p.setSuppliers(rs.getString("Suppliers"));
								p.setPnumber(rs.getInt("pnumber"));
								p.setPrice(rs.getFloat("price"));
								
								oi.setP(p);
							}
					}
					Set<OrderItem> cache = new HashSet<OrderItem>(oid);			//List�б�ת  Set�������飡
					o.setItems(cache);
			}
			
			
			return orderlist;
			
		
	}

	@Override
	public void updateOrder(Order o) throws Exception {
		// TODO �Զ���ɵķ������
		//�޸Ķ���״̬���ܽ��
		String sql="UPDATE `Order` SET  `Total_amount` = "+o.getTotal_amount()+", `order_status` = '"+o.getOrder_status()+"' WHERE `uuid` = '"+o.getUuid()+"';";
		
		DBUtil.Update(sql);
	}
	

}
