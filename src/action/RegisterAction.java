package action;

import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts2.ServletActionContext;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import dao.UserInfoDao;

public class RegisterAction extends ActionSupport{
	public RegisterAction(){
		System.out.println("registerAction");
	}
	private String nickname;
	public String getNickName() {
		return nickname;
	}
	public void setNickName(String nickname) {
		System.out.println("registerAction setnickname");
		this.nickname = nickname;
	}
	private String account;
	public String getAccount() {
		System.out.println("registerAction getAccount");
		return account;
	}
	public void setAccount(String account) {
		System.out.println("registerAction setAccount");
		this.account = account;
	}
	private String password;
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		System.out.println("registerAction setpsd");
		this.password = password;
	}
	
	public String execute() throws Exception {
		
		HttpServletRequest request = ServletActionContext.getRequest();
		//ʹ��request
		HttpServletResponse response = ServletActionContext.getResponse();
		//ʹ��response
		ServletContext application = ServletActionContext.getServletContext();
		//ʹ��application
		Map session = ActionContext.getContext().getSession();
		//ʹ��session
				
		//session.put("account", "111");
		
		System.out.println("RegisterAction execute");
		System.out.println(account+password+nickname);
		
		UserInfoDao uid = new UserInfoDao();
		if(uid.register(account,password,nickname)){
			return "success";
		}
		return "fail";
	}
	

}
