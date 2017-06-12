package action;

import java.sql.Timestamp;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts2.ServletActionContext;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import dao.InfoDao;

import entity.Info;

public class InfoAction extends ActionSupport{

	private Info info;

	public Info getInfo() {
		return info;
	}

	public void setInfo(Info info) {
		this.info = info;
	}
	
	public String save() throws Exception {

		HttpServletRequest request = ServletActionContext.getRequest();
		// request
		HttpServletResponse response = ServletActionContext.getResponse();
		// response
		ServletContext application = ServletActionContext.getServletContext();
		// application
		Map session = ActionContext.getContext().getSession();
		// session
		System.out.print(info.getContents());
		InfoDao infoDao = new InfoDao();
		info.setTime(new Timestamp(System.currentTimeMillis()));
		info.setNickName(request.getSession().getAttribute("nickname").toString());
		int result = infoDao.save(info);
		System.out.println("InfoAction execute");
		if (result==1) {
			return "success";
		}
		return "fail";
	}
}
