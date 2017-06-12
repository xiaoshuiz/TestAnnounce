package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

public class MainAction extends ActionSupport{
	public MainAction(){
		System.out.println("MainAction");
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
	
	
	public String main2editor() {
		
		/*
		 * 判断登陆情况
		 */
		
		try{
			HttpServletRequest request =ServletActionContext.getRequest();
		    HttpSession s = request.getSession(); 
		   	String nickname=s.getAttribute("nickname").toString();
		   	if(nickname.equals(""))
		   	{
		   		return "failmove2editor";
		   	}
		   	else
		   	{
		   		
		   		return "successmove2editor";
		   	}
		   	}
		   	catch(Exception e)
		   	{
		   		return "failmove2login";
		   	}
		
		
//		if()
//		{
//			return "successmove2editor";
//		}
//		else {
//			return "falsemove2editor";
//		}
//		
	}
public String main2login() {
		
		/*
		 * 判断登陆情况
		 */
	try{
		HttpServletRequest request =ServletActionContext.getRequest();
	    HttpSession s = request.getSession(); 
	   	String nickname=s.getAttribute("nickname").toString();
	   	if(nickname.equals(""))
	   	{
	   		return "successmove2login";
	   	}
	   	else
	   	{
	   		s.setAttribute("name", "");
	   		s.setAttribute("nickname", "");
	   		return "failmove2login";
	   	}
	   	}
	   	catch(Exception e)
	   	{
	   		return "successmove2login";
	   	}
	
	
	
	

	}
	

}
