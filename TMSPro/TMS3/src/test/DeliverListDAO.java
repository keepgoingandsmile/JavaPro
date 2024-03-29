package test;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.omg.CosNaming.NamingContextExtPackage.StringNameHelper;



public class DeliverListDAO {
	
	//新增发布单
	public String insertDeliverList(DeliverList dlist) {
		PreparedStatement pstmt = null;
		Connection conn = new DBConnection().dbconn();
		Boolean autoCommit = true;
		String result = null;
		try {
			autoCommit = conn.getAutoCommit();//获得当前状态
			conn.setAutoCommit(false);  //关闭自动提交功能
			String sql = "insert into ProcessPlatform..DeliverList(projectId,taskId,title,deliverNo,developer,tester,applicationName,content,comments,createUserId,updateUserId,createTime,updateTime)"
					+ " values (?,?,?,?,?,?,?,?,?,?,?,GETDATE(),GETDATE())";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, dlist.getProjectId());  
			pstmt.setInt(2, dlist.getTaskId());
			pstmt.setString(3, dlist.getTitle());
			pstmt.setString(4, dlist.getDeliverNo());
			pstmt.setInt(5, dlist.getDeveloper());
			pstmt.setInt(6, dlist.getTester());
			pstmt.setString(7, dlist.getApplicationName());
			pstmt.setString(8, dlist.getContent());
			pstmt.setString(9, dlist.getComments());
			pstmt.setInt(10, dlist.getCreateUserId());
			pstmt.setInt(11, dlist.getUpdateUserId());	
			pstmt.executeUpdate();
			conn.commit(); 
			conn.setAutoCommit(autoCommit); //恢复场景
			result = "Success";
		} catch (SQLException e) {
			System.out.println(e);
			result = e.toString();
			try {
				if (conn != null){
					conn.rollback();
					conn.setAutoCommit(autoCommit); //恢复场景
				}	
			} catch (Exception eroll) {
				eroll.printStackTrace();
			}finally{
				try{
					pstmt.close();
					conn.close();
				}catch(Exception eclose){
					eclose.printStackTrace();
				}
			}			
		}
		return result;
	}
	

//	//修改发布单
//	public String modifyDeliverList(DeliverList dlist){		
//		int deliverId = dlist.getId();  //获取待更新的发布单号	
//		PreparedStatement pstmt = null;
//		Connection conn = new DBConnection().dbconn();
//		Boolean autoCommit = true;
//		String result = null;
//		try {
//			autoCommit = conn.getAutoCommit();//获得当前状态
//			conn.setAutoCommit(false);  //关闭自动提交功能
//			String sql = "update deliverlist set title = ?, deliverNo = ?, developer = ?,tester = ?, applicationName = ?, content = ?, comments = ?, createUser = ? , updateUser = ?, updateTime = now() "
//					+ "where id = ?";			
//			pstmt = conn.prepareStatement(sql);
//			pstmt.setString(1, dlist.getTitle());
//			pstmt.setString(2, dlist.getDeliverNo());
//			pstmt.setString(3, dlist.getDeveloper());
//			pstmt.setString(4, dlist.getTester());
//			pstmt.setString(5, dlist.getApplicationName());
//			pstmt.setString(6, dlist.getContent());
//			pstmt.setString(7, dlist.getComments());
//			pstmt.setString(8, dlist.getCreateUserId());
//			pstmt.setString(9, dlist.getUpdateUserId());	
//			pstmt.setInt(10, deliverId);
//			pstmt.executeUpdate();
//			conn.commit(); 
//			conn.setAutoCommit(autoCommit); //恢复场景
//			result = "Success";
//		} catch (SQLException e) {
//			System.out.println(e);
//			result = e.toString();
//			try {
//				if (conn != null){
//					conn.rollback();
//					conn.setAutoCommit(autoCommit); //恢复场景
//				}	
//			} catch (Exception eroll) {
//				eroll.printStackTrace();
//			}finally{
//				try{
//					pstmt.close();
//					conn.close();
//				}catch(Exception eclose){
//					eclose.printStackTrace();
//				}
//			}			
//		}		
//		return result;		
//	}
//	
//	
//	//查询发布单表的所有信息
//	public Map<String, String> queryDeliverInfo(DeliverList dList){
//		Connection conn = new DBConnection().dbconn();
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
//		Map<String, String> map = new HashMap<String, String>();
//		String sql = "select *  from  ProcessPlatform..DeliverList where 1 = 1 ";  
//		if (dList.getId() != 0) {
//			sql = sql + "and Id = ?";
//		}
//		if (dList.getProjectId() != 0) {
//			sql = sql + "and projectId = ?";
//		}
//		if (dList.getTaskId() != 0) {
//			sql = sql + "and taskId = ?";
//		}
//		if (StringUtils.isNotEmpty(dList.getTitle())) {
//			sql = sql + "and title = ?";
//		}
//		if (StringUtils.isNotEmpty(dList.getDeliverNo())) {
//			sql = sql + "and deliverNo = ?";
//		}
//		if (StringUtils.isNotEmpty(dList.getDeveloper())) {
//			sql = sql + "and developer = ?";
//		}
//		if (StringUtils.isNotEmpty(dList.getTester())) {
//			sql = sql + "and tester = ?";
//		}
//		if (StringUtils.isNotEmpty(dList.getApplicationName())) {
//			sql = sql + "and applicationName = ?";
//		}
//		if (StringUtils.isNotEmpty(dList.getContent())) {
//			sql = sql + "and content = ?";
//		}
//		if (StringUtils.isNotEmpty(dList.getComments())) {
//			sql = sql + "and comments = ?";
//		}
//		if (StringUtils.isNotEmpty(dList.getCreateUserId())) {
//			sql = sql + "and createUserId = ?";
//		}
//		if (StringUtils.isNotEmpty(dList.getUpdateUserId())) {
//			sql = sql + "and updateUserId = ?";
//		}	
//		if (dList.getCreateTime() != null) {
//			sql = sql + "and createTime = ?";
//		}
//		if (dList.getUdpateTime() != null) {
//			sql = sql + "and udpateTime = ?";
//		}
//		try {
//			pstmt = conn.prepareStatement(sql);
//			int i = 1;
//		    if (dList.getId() != 0) {
//			   pstmt.setString(i, String.valueOf(dList.getId()));
//		       i++;
//		    }
//		    if (dList.getProjectId() != 0) {
//			   pstmt.setString(i, String.valueOf(dList.getProjectId()));
//		       i++;
//		    }
//		    if (dList.getTaskId() != 0) {
//			   pstmt.setString(i, String.valueOf(dList.getTaskId()));
//		       i++;
//		    }
//		    if (StringUtils.isNotEmpty(dList.getTitle())) {
//		    	pstmt.setString(i, dList.getTitle());
//			    i++;
//			}
//			if (StringUtils.isNotEmpty(dList.getDeliverNo())) {
//		    	pstmt.setString(i, dList.getDeliverNo());
//			    i++;
//			}
//			if (StringUtils.isNotEmpty(dList.getDeveloper())) {
//		    	pstmt.setString(i, dList.getDeveloper());
//			    i++;
//			}
//			if (StringUtils.isNotEmpty(dList.getTester())) {
//		    	pstmt.setString(i, dList.getTester());
//			    i++;
//			}
//			if (StringUtils.isNotEmpty(dList.getApplicationName())) {
//		    	pstmt.setString(i, dList.getApplicationName());
//			    i++;
//			}
//			if (StringUtils.isNotEmpty(dList.getContent())) {
//		    	pstmt.setString(i, dList.getContent());
//			    i++;
//			}
//			if (StringUtils.isNotEmpty(dList.getComments())) {
//		    	pstmt.setString(i, dList.getComments());
//			    i++;
//			}
//			if (StringUtils.isNotEmpty(dList.getCreateUserId())) {
//		    	pstmt.setString(i, dList.getCreateUserId());
//			    i++;
//			}
//			if (StringUtils.isNotEmpty(dList.getUpdateUserId())) {
//		    	pstmt.setString(i, dList.getUpdateUserId());
//			    i++;
//			}	
//			if (dList.getCreateTime() != null) {
//		    	pstmt.setDate(i, dList.getCreateTime());
//			    i++;
//			}
//			if (dList.getUdpateTime() != null) {
//		    	pstmt.setDate(i, dList.getUdpateTime());
//			    i++;
//			}
//			rs = pstmt.executeQuery();
//			while(rs.next()){
//				map.put("id", rs.getString("id")); //发布单ID
//				map.put("projectId", rs.getString("projectId")); //项目ID
//				map.put("taskId", rs.getString("taskId")); //任务ID
//				map.put("title", rs.getString("title")); //发布单标题
//				map.put("deliverNo", rs.getString("deliverNo")); //发布单号
//				map.put("developer", rs.getString("developer")); //开发
//				map.put("tester", rs.getString("tester")); //测试				
//				map.put("applicationName", rs.getString("applicationName")); //应用名称
//				map.put("content", rs.getString("content")); //发布内容
//				map.put("comments", rs.getString("comments")); //备注	
//				map.put("createUserId", rs.getString("createUserId")); //创建者
//				map.put("updateUserId", rs.getString("updateUserId")); //更新者
//				map.put("createTime", rs.getString("createTime")); //创建时间
//				map.put("updateTime", rs.getString("updateTime")); //更新时间
//			}
//		} catch (SQLException e) {
//			System.out.println(e);
//		}finally {
//			try {
//				rs.close();
//				pstmt.close();
//				conn.close();
//			} catch (SQLException e1) {
//				System.out.println(e1);
//			}					
//		}
//		return map;		
//	} 
//		

	//根据任务名称获取项目ID和任务ID
	public Map<String, Integer> getProAndTaskId(String taskName) {   	
		Connection conn = new DBConnection().dbconn();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Map<String, Integer> map = new HashMap<String, Integer>();
		String sql = "select projectId, taskId from ProcessPlatform..Task where taskName = ?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, taskName);
			rs = pstmt.executeQuery();
			while(rs.next()){
				map.put("projectId", Integer.valueOf(rs.getString("projectId")));
				map.put("taskId", Integer.valueOf(rs.getString("taskId")));  
			}
		} catch (SQLException e) {
			System.out.println(e);
		} finally{
			try{
				rs.close();
				pstmt.close();
				conn.close();
			}catch(Exception eclose){
				eclose.printStackTrace();
			}
		}	
		return map;			
	}
	
	//根据用户名获得用户Id
	public int getUserIdByName(String userName) {   	
		Connection conn = new DBConnection().dbconn();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int userId = 0;
		String sql = "select Id from ProcessPlatform..PP_User where UserName = ?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userName);
			rs = pstmt.executeQuery();
			while(rs.next()){
				userId = Integer.valueOf(rs.getString("Id"));
			}
		} catch (SQLException e) {
			System.out.println(e);
		} finally{
			try{
				rs.close();
				pstmt.close();
				conn.close();
			}catch(Exception eclose){
				eclose.printStackTrace();
			}
		}	
		return userId;			
	}
	
	
	
	
	
	//测试
	public static void main(String[] args) {
		DeliverList dList = new DeliverList(1, 1, "title3", "deliverNo3", 1, 1, "applicationName3","content3", "comments3", 1,1);
		DeliverListDAO dListDAO = new DeliverListDAO();
		String result= dListDAO.insertDeliverList(dList);
		System.out.println(result);
	}

}
















