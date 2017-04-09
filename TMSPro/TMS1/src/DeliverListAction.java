import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class DeliverListAction extends ActionSupport{
	
	private static final long serialVersionUID = 1L;

	//添加一个发布单
	public void addDeliverList(){
		String projectName = ServletActionContext.getRequest().getParameter("projectName");
		String taskName = ServletActionContext.getRequest().getParameter("taskName");
		String title = ServletActionContext.getRequest().getParameter("title");
		String deliverNo = ServletActionContext.getRequest().getParameter("deliverNo");
		String developer = ServletActionContext.getRequest().getParameter("developer");
		String tester = ServletActionContext.getRequest().getParameter("tester");
		String applicationName = ServletActionContext.getRequest().getParameter("applicationName");
		String content = ServletActionContext.getRequest().getParameter("content");
		String comments = ServletActionContext.getRequest().getParameter("comments");
		String createUser = ServletActionContext.getRequest().getParameter("createUser");
		
		DeliverListDAO dListDAO = new DeliverListDAO();		
		String proId = dListDAO.getProjectId(projectName);
		String taskId = dListDAO.getProjectId(taskName);
		
		DeliverList dList = new DeliverList();
		dList.setProjectId(Integer.valueOf(proId)); 
		dList.setTaskId(Integer.valueOf(taskId));
		dList.setTitle(title);
		dList.setDeliverNo(deliverNo);
		dList.setDeveloper(developer);
		dList.setTester(tester);
		dList.setApplicationName(applicationName);
		dList.setContent(content);
		dList.setComments(comments);
		dList.setCreateUser(createUser);
		
		String result = dListDAO.addDeliverList(dList);
		
		JSONArray jsona=new JSONArray();
		JSONObject jO=new JSONObject();
		if(result.contains("Success")){
			jO.put("status", "0");
			jO.put("message", "success");
			jsona.add(jO);	
			//return "success";
		}else{
			ServletActionContext.getRequest().setAttribute("errorMsg", result);
			jO.put("status", "1");
			jO.put("message", result);
			jsona.add(jO);	
			//return "error";
		}
		try{
			ServletActionContext.getResponse().setHeader("content-type", "application/json");
			ServletActionContext.getResponse().setCharacterEncoding("UTF-8");
			ServletActionContext.getResponse().getWriter().write(jsona.toString());
		}catch(Exception e){
			e.printStackTrace();
		}	
	}

	//更新发布单内容
	public void modifyDeliverList(){
		String deliverId = ServletActionContext.getRequest().getParameter("deliverId");
		String projectName = ServletActionContext.getRequest().getParameter("projectName");
		String taskName = ServletActionContext.getRequest().getParameter("taskName");
		String title = ServletActionContext.getRequest().getParameter("title");
		String deliverNo = ServletActionContext.getRequest().getParameter("deliverNo");
		String developer = ServletActionContext.getRequest().getParameter("developer");
		String tester = ServletActionContext.getRequest().getParameter("tester");
		String applicationName = ServletActionContext.getRequest().getParameter("applicationName");
		String content = ServletActionContext.getRequest().getParameter("content");
		String comments = ServletActionContext.getRequest().getParameter("comments");
		String createUser = ServletActionContext.getRequest().getParameter("createUser");
		String updateUser = ServletActionContext.getRequest().getParameter("updateUser");   //怎么获取？？
		
		DeliverListDAO dListDAO = new DeliverListDAO();		
		String proId = dListDAO.getProjectId(projectName);
		String taskId = dListDAO.getProjectId(taskName);
		
		DeliverList dList = new DeliverList();
		dList.setId(Integer.valueOf(deliverId));
		dList.setProjectId(Integer.valueOf(proId)); 
		dList.setTaskId(Integer.valueOf(taskId));
		dList.setTitle(title);
		dList.setDeliverNo(deliverNo);
		dList.setDeveloper(developer);
		dList.setTester(tester);
		dList.setApplicationName(applicationName);
		dList.setContent(content);
		dList.setComments(comments);
		dList.setCreateUser(createUser);
		dList.setUpdateUser(updateUser);
		
		String result = dListDAO.modifyDeliverList(dList);
		
		JSONArray jsona=new JSONArray();
		JSONObject jO=new JSONObject();
		if(result.contains("Success")){
			jO.put("status", "0");
			jO.put("message", "success");
			jsona.add(jO);	
			//return "success";
		}else{
			ServletActionContext.getRequest().setAttribute("errorMsg", result);
			jO.put("status", "1");
			jO.put("message", result);
			jsona.add(jO);	
			//return "error";
		}
		try{
			ServletActionContext.getResponse().setHeader("content-type", "application/json");
			ServletActionContext.getResponse().setCharacterEncoding("UTF-8");
			ServletActionContext.getResponse().getWriter().write(jsona.toString());
		}catch(Exception e){
			e.printStackTrace();
		}
	
	}
	
	
	
	
}