package action;

import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import dao.UserInfoDao;

public class LoginAction extends ActionSupport {
	public LoginAction() {
		System.out.println("LoginAction");
	}

	private String account;

	public String getAccount() {
		System.out.println("LoginAction getAccount");
		return account;
	}

	public void setAccount(String account) {
		System.out.println("LoginAction setAccount");
		this.account = account;
		System.out.println(account);
	}

	private String password;

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String execute() throws Exception {

		HttpServletRequest request = ServletActionContext.getRequest();

		HttpServletResponse response = ServletActionContext.getResponse();

		ServletContext application = ServletActionContext.getServletContext();

		Map session = ActionContext.getContext().getSession();

		// session.put("account", "111");

		System.out.println("LoginAction execute");
		System.out.println(account);
		UserInfoDao uid = new UserInfoDao();
		if (uid.login(account, password).equals("")) {
			return "fail";
		} else {
//			try {

				HttpSession s = request.getSession();

				s.setAttribute("name", account);
				s.setAttribute("nickname", uid.login(account, password));

//			} catch (Exception e) {
//				// TODO: handle exception
//			}
			return "success";
		}
	}

	public String login2register() {
		return "move";

	}
}
