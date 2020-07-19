package ceping.action;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import ceping.dao.JiluDao;
import ceping.dao.JiluitemDao;
import ceping.dao.NeirongDao;
import ceping.dao.TongjiDao;
import ceping.dao.UserDao;
import ceping.model.Jilu;
import ceping.model.Jiluitem;
import ceping.model.Neirong;
import ceping.model.Tongji;
import ceping.model.User;
import ceping.util.Arith;
import ceping.util.Pager;
import ceping.util.Util;

import com.opensymphony.xwork2.ActionSupport;

public class ManageAction extends ActionSupport {

	private static final long serialVersionUID = -4304509122548259589L;

	private UserDao userDao;

	private String url = "./";

	public UserDao getUserDao() {
		return userDao;
	}

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}


	
	
	
//登入请求
	public String login() throws IOException {
		
		HttpServletRequest request = ServletActionContext.getRequest();
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String  role = request.getParameter("role");
		User user = userDao.selectBean(" where username = '" + username
				+ "' and password= '" + password + "' and role= "+role +" and deletestatus=0 ");
		if (user != null) {
			HttpSession session = request.getSession();
			session.setAttribute("user", user);
			this.setUrl("index.jsp");
			return "redirect";
		} else {
			HttpServletResponse response = ServletActionContext.getResponse();
			response.setCharacterEncoding("gbk");
			response.setContentType("text/html; charset=gbk");
			response
					.getWriter()
					.print(
							"<script language=javascript>alert('用户名或者密码错误');window.location.href='login.jsp';</script>");
		}
		return null;
	}
//用户退出
	public String loginout() {
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();
		session.removeAttribute("user");
		this.setUrl("login.jsp");
		return SUCCESS;
	}
//跳转到修改密码页面
	public String changepwd() {
		this.setUrl("user/user.jsp");
		return SUCCESS;
	}
//修改密码操作
	public void changepwd2() throws IOException {
HttpServletRequest request = ServletActionContext.getRequest();
		
		HttpSession session = request.getSession();
		User u = (User)session.getAttribute("user");
		String password1 = request.getParameter("password1");
		String password2 = request.getParameter("password2");
		User bean = userDao.selectBean(" where username= '"+u.getUsername()+"' and password= '"+password1+"' and deletestatus=0");
		if(bean!=null){
			bean.setPassword(password2);
			userDao.updateBean(bean);
			HttpServletResponse response = ServletActionContext.getResponse();
			response.setCharacterEncoding("gbk");response.setContentType("text/html; charset=gbk");
			response
					.getWriter()
					.print(
							"<script language=javascript>alert('修改成功');window.location.href='method!changepwd';</script>");
		}else{
			HttpServletResponse response = ServletActionContext.getResponse();
			response.setCharacterEncoding("gbk");response.setContentType("text/html; charset=gbk");
			response
					.getWriter()
					.print(
							"<script language=javascript>alert('原密码错误');window.location.href='method!changepwd';</script>");
		}
	}
	
	
	private NeirongDao neirongDao;

	public NeirongDao getNeirongDao() {
		return neirongDao;
	}

	public void setNeirongDao(NeirongDao neirongDao) {
		this.neirongDao = neirongDao;
	}
	
	//评估内容列表
	public String neironglist() {
		HttpServletRequest request = ServletActionContext.getRequest();
		String neirong = request.getParameter("neirong");

		
		StringBuffer sb = new StringBuffer();
		sb.append(" where ");

		if (neirong != null && !"".equals(neirong)) {

			sb.append("neirong like '%" + neirong + "%'");
			sb.append(" and ");
			request.setAttribute("neirong", neirong);
		}

		
		
		
		sb.append("  deletestatus=0 order by id desc ");
		String where = sb.toString();


		int currentpage = 1;
		int pagesize = 10;
		if (request.getParameter("pagenum") != null) {
			currentpage = Integer.parseInt(request.getParameter("pagenum"));
		}
		int total = neirongDao.selectBeanCount(where.replaceAll(" order by id desc ", ""));
		request.setAttribute("list", neirongDao.selectBeanList((currentpage - 1)
				* pagesize, pagesize, where));
		request.setAttribute("pagerinfo", Pager.getPagerNormal(total, pagesize,
				currentpage, "method!neironglist", "共有" + total + "条记录"));
		request.setAttribute("url", "method!neironglist");
		request.setAttribute("url2", "method!neirong");
		request.setAttribute("title", "评估内容管理");
		this.setUrl("neirong/neironglist.jsp");
		return SUCCESS;

	}
//跳转到添加评估内容页面
	public String neirongadd() {
		HttpServletRequest request = ServletActionContext.getRequest();
		request.setAttribute("url", "method!neirongadd2");
		request.setAttribute("title", "评估内容添加");
		this.setUrl("neirong/neirongadd.jsp");
		return SUCCESS;
	}
//添加评估内容操作
	public void neirongadd2() throws IOException {
		HttpServletRequest request = ServletActionContext.getRequest();
		String neirong = request.getParameter("neirong");
	
		Neirong bean = new Neirong();
		bean.setNeirong(neirong);
		neirongDao.insertBean(bean);
		
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("gbk");response.setContentType("text/html; charset=gbk");
		response
				.getWriter()
				.print(
						"<script language=javascript>alert('操作成功');window.location.href='method!neironglist';</script>");
		
	}
//跳转到更新评估内容页面
	public String neirongupdate() {
		HttpServletRequest request = ServletActionContext.getRequest();
		Neirong bean = neirongDao.selectBean(" where id= "
				+ request.getParameter("id"));
		request.setAttribute("bean", bean);
		request.setAttribute("url", "method!neirongupdate2?id="+bean.getId());
		request.setAttribute("title", "评估内容更新");
		this.setUrl("neirong/neirongupdate.jsp");
		return SUCCESS;
	}
//更新评估内容操作
	public void neirongupdate2() throws IOException {
		HttpServletRequest request = ServletActionContext.getRequest();
		String neirong = request.getParameter("neirong");
		Neirong bean = neirongDao.selectBean(" where id= "
				+ request.getParameter("id"));
		bean.setNeirong(neirong);
		
		neirongDao.updateBean(bean);
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("gbk");response.setContentType("text/html; charset=gbk");
		response
				.getWriter()
				.print(
						"<script language=javascript>alert('操作成功');window.location.href='method!neironglist';</script>");
	}
//删除评估内容操作
	public void neirongdelete() throws IOException {
		HttpServletRequest request = ServletActionContext.getRequest();
		Neirong bean = neirongDao.selectBean(" where id= "
				+ request.getParameter("id"));
		bean.setDeletestatus(1);
		neirongDao.updateBean(bean);
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("gbk");response.setContentType("text/html; charset=gbk");
		response
				.getWriter()
				.print(
						"<script language=javascript>alert('操作成功');window.location.href='method!neironglist';</script>");
	}
	
	//跳转到查看评估内容页面
	public String neirongupdate3() {
		HttpServletRequest request = ServletActionContext.getRequest();
		Neirong bean = neirongDao.selectBean(" where id= "
				+ request.getParameter("id"));
		request.setAttribute("bean", bean);
		request.setAttribute("title", "评估内容查看");
		this.setUrl("neirong/neirongupdate3.jsp");
		return SUCCESS;
	}
	
	
	
	//学生注册操作
	public void register2() throws Exception{
		HttpServletRequest request = ServletActionContext.getRequest();
		
		HttpServletResponse response = ServletActionContext.getResponse();
		
		String username = java.net.URLDecoder.decode(request.getParameter("username"), "utf-8");

		User bean = userDao.selectBean(" where username='"+username+"' and  deletestatus=0 ");
		if(bean==null){
			bean = new User();
			bean.setUsername(username);
			
			bean.setPassword(request.getParameter("password"));
			String truename = java.net.URLDecoder.decode(request.getParameter("truename"), "utf-8");
			String banji = java.net.URLDecoder.decode(request.getParameter("banji"), "utf-8");
			String shouji = java.net.URLDecoder.decode(request.getParameter("shouji"), "utf-8");
			String xueyuan = java.net.URLDecoder.decode(request.getParameter("xueyuan"), "utf-8");
			String zhuanye = java.net.URLDecoder.decode(request.getParameter("zhuanye"), "utf-8");
			bean.setTruename(truename);
			bean.setBanji(banji);
			bean.setCreatetime(new Date());
			bean.setRole(3);
			bean.setShouji(shouji);
			bean.setTruename(truename);
			bean.setXueyuan(xueyuan);
			bean.setZhuanye(zhuanye);
			
			

			userDao.insertBean(bean);
			
			response.setCharacterEncoding("utf-8");
			
			response.getWriter().write("注册新用户成功！您的用户名"+bean.getUsername()+",请妥善保管！");
		}else{
			response.setCharacterEncoding("utf-8");
			response.getWriter().write("该用户名已经存在，请重新注册！");
		}
	}
	
	
	//教师注册操作
	public void register4() throws Exception{
		HttpServletRequest request = ServletActionContext.getRequest();
		
		HttpServletResponse response = ServletActionContext.getResponse();
		
		String username = java.net.URLDecoder.decode(request.getParameter("username"), "utf-8");

		User bean = userDao.selectBean(" where username='"+username+"' and  deletestatus=0 ");
		if(bean==null){
			bean = new User();
			bean.setUsername(username);
			
			bean.setPassword(request.getParameter("password"));
			String truename = java.net.URLDecoder.decode(request.getParameter("truename"), "utf-8");
			String shouji = java.net.URLDecoder.decode(request.getParameter("shouji"), "utf-8");
			String xueyuan = java.net.URLDecoder.decode(request.getParameter("xueyuan"), "utf-8");
			String zhichen = java.net.URLDecoder.decode(request.getParameter("zhichen"), "utf-8");
			bean.setTruename(truename);
			bean.setCreatetime(new Date());
			bean.setRole(2);
			bean.setShouji(shouji);
			bean.setTruename(truename);
			bean.setXueyuan(xueyuan);
			bean.setZhichen(zhichen);
			
			

			userDao.insertBean(bean);
			
			response.setCharacterEncoding("utf-8");
			
			response.getWriter().write("注册新用户成功！您的用户名"+bean.getUsername()+",请妥善保管！");
		}else{
			response.setCharacterEncoding("utf-8");
			response.getWriter().write("该用户名已经存在，请重新注册！");
		}
	}
	
	
	
	//学生用户列表
	public String userlist() {
		HttpServletRequest request = ServletActionContext.getRequest();
		String username = request.getParameter("username");
		String truename = request.getParameter("truename");

		
		StringBuffer sb = new StringBuffer();
		sb.append(" where ");

		if (username != null && !"".equals(username)) {

			sb.append("username like '%" + username + "%'");
			sb.append(" and ");
			request.setAttribute("username", username);
		}
		
		if (truename != null && !"".equals(truename)) {

			sb.append("truename like '%" + truename + "%'");
			sb.append(" and ");
			request.setAttribute("truename", truename);
		}

		
		
		
		sb.append("  role=3  and deletestatus=0 order by id desc ");
		String where = sb.toString();


		int currentpage = 1;
		int pagesize = 10;
		if (request.getParameter("pagenum") != null) {
			currentpage = Integer.parseInt(request.getParameter("pagenum"));
		}
		int total = userDao.selectBeanCount(where.replaceAll(" order by id desc ", ""));
		request.setAttribute("list", userDao.selectBeanList((currentpage - 1)
				* pagesize, pagesize, where));
		request.setAttribute("pagerinfo", Pager.getPagerNormal(total, pagesize,
				currentpage, "method!userlist", "共有" + total + "条记录"));
		request.setAttribute("url", "method!userlist");
		request.setAttribute("url2", "method!user");
		request.setAttribute("title", "学生用户管理");
		this.setUrl("user/userlist.jsp");
		return SUCCESS;

	}
	
	
	//删除学生用户操作
	public void userdelete() throws IOException {
		HttpServletRequest request = ServletActionContext.getRequest();
		User bean = userDao.selectBean(" where id= "
				+ request.getParameter("id"));
		bean.setDeletestatus(1);
		userDao.updateBean(bean);
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("gbk");response.setContentType("text/html; charset=gbk");
		response
				.getWriter()
				.print(
						"<script language=javascript>alert('操作成功');window.location.href='method!userlist';</script>");
	}
	
	
	
	
	//教师用户列表
	public String userlist2() {
		HttpServletRequest request = ServletActionContext.getRequest();
		String username = request.getParameter("username");
		String truename = request.getParameter("truename");

		
		StringBuffer sb = new StringBuffer();
		sb.append(" where ");

		if (username != null && !"".equals(username)) {

			sb.append("username like '%" + username + "%'");
			sb.append(" and ");
			request.setAttribute("username", username);
		}
		
		if (truename != null && !"".equals(truename)) {

			sb.append("truename like '%" + truename + "%'");
			sb.append(" and ");
			request.setAttribute("truename", truename);
		}

		
		
		
		sb.append("  role=2 and deletestatus=0 order by id desc ");
		String where = sb.toString();


		int currentpage = 1;
		int pagesize = 10;
		if (request.getParameter("pagenum") != null) {
			currentpage = Integer.parseInt(request.getParameter("pagenum"));
		}
		int total = userDao.selectBeanCount(where.replaceAll(" order by id desc ", ""));
		request.setAttribute("list", userDao.selectBeanList((currentpage - 1)
				* pagesize, pagesize, where));
		request.setAttribute("pagerinfo", Pager.getPagerNormal(total, pagesize,
				currentpage, "method!userlist2", "共有" + total + "条记录"));
		request.setAttribute("url", "method!userlist2");
		request.setAttribute("url2", "method!user");
		request.setAttribute("title", "教师用户管理");
		this.setUrl("user/userlist2.jsp");
		return SUCCESS;

	}
	
	
	//删除教师用户操作
	public void userdelete3() throws IOException {
		HttpServletRequest request = ServletActionContext.getRequest();
		User bean = userDao.selectBean(" where id= "
				+ request.getParameter("id"));
		bean.setDeletestatus(1);
		userDao.updateBean(bean);
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("gbk");response.setContentType("text/html; charset=gbk");
		response
				.getWriter()
				.print(
						"<script language=javascript>alert('操作成功');window.location.href='method!userlist2';</script>");
	}
	
	
	
	
	
	//评估内容列表
	public String neironglist2() throws IOException {
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("gbk");response.setContentType("text/html; charset=gbk");
		HttpSession session = request.getSession();
		User user = (User)session.getAttribute("user");
		
		Jilu jilu = jiluDao.selectBean(" where fromuser.id= "+user.getId()+" and touser.id= "+user.getId());
		if(jilu!=null){
			response
					.getWriter()
					.print(
							"<script language=javascript>alert('您已经进行过自我评估！不可重复操作');window.location.href='method!jilulist';</script>");
			return null;
		}
		
		
		StringBuffer sb = new StringBuffer();
		sb.append(" where ");

		sb.append("  deletestatus=0 order by id desc ");
		String where = sb.toString();


		int currentpage = 1;
		int pagesize = 100;
		if (request.getParameter("pagenum") != null) {
			currentpage = Integer.parseInt(request.getParameter("pagenum"));
		}
	
		request.setAttribute("list", neirongDao.selectBeanList((currentpage - 1)
				* pagesize, pagesize, where));

		request.setAttribute("title", "自我评估");
		this.setUrl("neirong/neironglist2.jsp");
		return SUCCESS;

	}
	
	private JiluDao jiluDao;

	public JiluDao getJiluDao() {
		return jiluDao;
	}

	public void setJiluDao(JiluDao jiluDao) {
		this.jiluDao = jiluDao;
	}
	
	
	private JiluitemDao jiluitemDao;

	public JiluitemDao getJiluitemDao() {
		return jiluitemDao;
	}

	public void setJiluitemDao(JiluitemDao jiluitemDao) {
		this.jiluitemDao = jiluitemDao;
	}
	
	
	
	//自我评估操作
	public void jiluadd() throws IOException {
		HttpServletRequest request = ServletActionContext.getRequest();
		Jilu bean = new Jilu();
		bean.setDenfen(0);
		HttpSession session = request.getSession();
		User user = (User)session.getAttribute("user");
		bean.setFromuser(user);
		bean.setShijian(Util.getTime());
		bean.setTouser(user);
		bean.setLeixing("教师自评");
		jiluDao.insertBean(bean);
		
		List<Neirong> list = neirongDao.selectBeanList(0, 9999, " where deletestatus=0 order by id desc");
		for(int i=0;i<list.size();i++){
			String neirongid = request.getParameter("neirongid_"+i);
			String dengji = request.getParameter("dengji_"+i);
			Jiluitem item = new Jiluitem();
			item.setDengji(dengji);
			item.setDefen(ManageAction.defen(dengji));
			item.setJilu(bean);
			item.setNeirong(neirongDao.selectBean(" where id= "+neirongid));
			item.setShijian(Util.getTime());
			
			jiluitemDao.insertBean(item);
			bean.setDenfen(bean.getDenfen()+item.getDefen());

		}
		jiluDao.updateBean(bean);
	
		
		
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("gbk");response.setContentType("text/html; charset=gbk");
		response
				.getWriter()
				.print(
						"<script language=javascript>alert('操作成功');window.location.href='method!jilulist';</script>");
		
	}
	
	private static int defen(String dengji){
		int defen = 0;
		if("不及格".equals(dengji)){
			defen = 0;
		}else if("及格".equals(dengji)){
			defen = 6;
		}else if("良好".equals(dengji)){
			defen = 8;
		}else if("优秀".equals(dengji)){
			defen = 10;
		}
		return defen;
	}
	
	
	
	//自我评估记录列表
	public String jilulist() {
		HttpServletRequest request = ServletActionContext.getRequest();
		

		
		StringBuffer sb = new StringBuffer();
		sb.append(" where ");

		HttpSession session = request.getSession();
		User user = (User)session.getAttribute("user");
		
		sb.append("  fromuser.id="+user.getId()+" and touser.id="+user.getId()+" order by id desc ");
		String where = sb.toString();
		int currentpage = 1;
		int pagesize = 10;
		if (request.getParameter("pagenum") != null) {
			currentpage = Integer.parseInt(request.getParameter("pagenum"));
		}
		int total = jiluDao.selectBeanCount(where.replaceAll(" order by id desc ", ""));
		request.setAttribute("list", jiluDao.selectBeanList((currentpage - 1)
				* pagesize, pagesize, where));
		request.setAttribute("pagerinfo", Pager.getPagerNormal(total, pagesize,
				currentpage, "method!jilulist", "共有" + total + "条记录"));

		request.setAttribute("title", "自我评估记录查询");
		this.setUrl("jilu/jilulist.jsp");
		return SUCCESS;

	}
	
	
	
	//评估明细列表
	public String jiluitemlist() {
		HttpServletRequest request = ServletActionContext.getRequest();
		String jiluid = request.getParameter("jiluid");

		StringBuffer sb = new StringBuffer();
		sb.append(" where ");

		sb.append("  jilu.id="+jiluid+" order by id desc ");
		String where = sb.toString();


		int currentpage = 1;
		int pagesize = 10;
		if (request.getParameter("pagenum") != null) {
			currentpage = Integer.parseInt(request.getParameter("pagenum"));
		}
		int total = jiluitemDao.selectBeanCount(where.replaceAll(" order by id desc ", ""));
		request.setAttribute("list", jiluitemDao.selectBeanList((currentpage - 1)
				* pagesize, pagesize, where));
		request.setAttribute("pagerinfo", Pager.getPagerNormal(total, pagesize,
				currentpage, "method!jiluitemlist", "共有" + total + "条记录"));
		request.setAttribute("url", "method!jiluitemlist");
		request.setAttribute("url2", "method!jiluitem");
		request.setAttribute("title", "评估明细管理");
		this.setUrl("jiluitem/jiluitemlist.jsp");
		return SUCCESS;

	}
	
	
	//教师用户列表
	public String userlist3() {
		HttpServletRequest request = ServletActionContext.getRequest();
		String username = request.getParameter("username");
		String truename = request.getParameter("truename");

		
		StringBuffer sb = new StringBuffer();
		sb.append(" where ");

		if (username != null && !"".equals(username)) {

			sb.append("username like '%" + username + "%'");
			sb.append(" and ");
			request.setAttribute("username", username);
		}
		
		if (truename != null && !"".equals(truename)) {

			sb.append("truename like '%" + truename + "%'");
			sb.append(" and ");
			request.setAttribute("truename", truename);
		}

		
		HttpSession session = request.getSession();
		User user = (User)session.getAttribute("user");
		
		sb.append("  id!="+user.getId()+" and  role=2 and deletestatus=0 order by id desc ");
		String where = sb.toString();


		int currentpage = 1;
		int pagesize = 10;
		if (request.getParameter("pagenum") != null) {
			currentpage = Integer.parseInt(request.getParameter("pagenum"));
		}
		int total = userDao.selectBeanCount(where.replaceAll(" order by id desc ", ""));
		request.setAttribute("list", userDao.selectBeanList((currentpage - 1)
				* pagesize, pagesize, where));
		request.setAttribute("pagerinfo", Pager.getPagerNormal(total, pagesize,
				currentpage, "method!userlist3", "共有" + total + "条记录"));
		request.setAttribute("url", "method!userlist3");
		request.setAttribute("url2", "method!user");
		request.setAttribute("title", "评估教师列表");
		this.setUrl("user/userlist3.jsp");
		return SUCCESS;

	}
	
	
	//评估内容列表
	public String neironglist3() throws IOException {
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("gbk");response.setContentType("text/html; charset=gbk");
		HttpSession session = request.getSession();
		User user = (User)session.getAttribute("user");
		String touserid = request.getParameter("touserid");
		request.setAttribute("touserid", touserid);
		
		Jilu jilu = jiluDao.selectBean(" where fromuser.id= "+user.getId()+" and touser.id= "+touserid);
		if(jilu!=null){
			response
					.getWriter()
					.print(
							"<script language=javascript>alert('您已经进行对该教师进行评估！不可重复操作');window.location.href='method!jilulist2';</script>");
			return null;
		}
		
		
		StringBuffer sb = new StringBuffer();
		sb.append(" where ");

		sb.append("  deletestatus=0 order by id desc ");
		String where = sb.toString();


		int currentpage = 1;
		int pagesize = 100;
		if (request.getParameter("pagenum") != null) {
			currentpage = Integer.parseInt(request.getParameter("pagenum"));
		}
	
		request.setAttribute("list", neirongDao.selectBeanList((currentpage - 1)
				* pagesize, pagesize, where));

		request.setAttribute("title", "教师互评");
		this.setUrl("neirong/neironglist3.jsp");
		return SUCCESS;

	}
	
	
	//教师互评操作
	public void jiluadd2() throws IOException {
		HttpServletRequest request = ServletActionContext.getRequest();
		String touserid = request.getParameter("touserid");
		request.setAttribute("touserid", touserid);
		Jilu bean = new Jilu();
		bean.setDenfen(0);
		HttpSession session = request.getSession();
		User user = (User)session.getAttribute("user");
		bean.setFromuser(user);
		bean.setShijian(Util.getTime());
		bean.setTouser(userDao.selectBean(" where id= "+touserid));
		bean.setLeixing("教师互评");
		jiluDao.insertBean(bean);
		
		List<Neirong> list = neirongDao.selectBeanList(0, 9999, " where deletestatus=0 order by id desc");
		for(int i=0;i<list.size();i++){
			String neirongid = request.getParameter("neirongid_"+i);
			String dengji = request.getParameter("dengji_"+i);
			Jiluitem item = new Jiluitem();
			item.setDengji(dengji);
			item.setDefen(ManageAction.defen(dengji));
			item.setJilu(bean);
			item.setNeirong(neirongDao.selectBean(" where id= "+neirongid));
			item.setShijian(Util.getTime());
			
			jiluitemDao.insertBean(item);
			bean.setDenfen(bean.getDenfen()+item.getDefen());

		}
		jiluDao.updateBean(bean);
	
		
		
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("gbk");response.setContentType("text/html; charset=gbk");
		response
				.getWriter()
				.print(
						"<script language=javascript>alert('操作成功');window.location.href='method!jilulist2';</script>");
		
	}
	
	
	//发起的评估记录列表
	public String jilulist2() {
		HttpServletRequest request = ServletActionContext.getRequest();
		String touser = request.getParameter("touser");

		
		StringBuffer sb = new StringBuffer();
		sb.append(" where ");

		if (touser != null && !"".equals(touser)) {

			sb.append("touser.truename like '%" + touser + "%'");
			sb.append(" and ");
			request.setAttribute("touser", touser);
		}

		HttpSession session = request.getSession();
		User user = (User)session.getAttribute("user");
		
		sb.append("  fromuser.id="+user.getId()+" and touser.id!="+user.getId()+" order by id desc ");
		String where = sb.toString();
		int currentpage = 1;
		int pagesize = 10;
		if (request.getParameter("pagenum") != null) {
			currentpage = Integer.parseInt(request.getParameter("pagenum"));
		}
		int total = jiluDao.selectBeanCount(where.replaceAll(" order by id desc ", ""));
		request.setAttribute("list", jiluDao.selectBeanList((currentpage - 1)
				* pagesize, pagesize, where));
		request.setAttribute("pagerinfo", Pager.getPagerNormal(total, pagesize,
				currentpage, "method!jilulist2", "共有" + total + "条记录"));

		request.setAttribute("title", "发起的评估记录查询");
		this.setUrl("jilu/jilulist2.jsp");
		return SUCCESS;

	}
	
	
	//收到的评估记录列表
	public String jilulist3() {
		HttpServletRequest request = ServletActionContext.getRequest();
		
		String fromuser = request.getParameter("fromuser");

		
		StringBuffer sb = new StringBuffer();
		sb.append(" where ");

		if (fromuser != null && !"".equals(fromuser)) {

			sb.append("fromuser.truename like '%" + fromuser + "%'");
			sb.append(" and ");
			request.setAttribute("fromuser", fromuser);
		}

		HttpSession session = request.getSession();
		User user = (User)session.getAttribute("user");
		
		sb.append("  fromuser.id!="+user.getId()+" and touser.id="+user.getId()+" and fromuser.role=2  order by id desc ");
		String where = sb.toString();
		int currentpage = 1;
		int pagesize = 10;
		if (request.getParameter("pagenum") != null) {
			currentpage = Integer.parseInt(request.getParameter("pagenum"));
		}
		int total = jiluDao.selectBeanCount(where.replaceAll(" order by id desc ", ""));
		request.setAttribute("list", jiluDao.selectBeanList((currentpage - 1)
				* pagesize, pagesize, where));
		request.setAttribute("pagerinfo", Pager.getPagerNormal(total, pagesize,
				currentpage, "method!jilulist3", "共有" + total + "条记录"));

		request.setAttribute("title", "收到的评估记录查询");
		this.setUrl("jilu/jilulist3.jsp");
		return SUCCESS;

	}
	
	
	//评估教师列表
	public String userlist4() {
		HttpServletRequest request = ServletActionContext.getRequest();
		String username = request.getParameter("username");
		String truename = request.getParameter("truename");

		
		StringBuffer sb = new StringBuffer();
		sb.append(" where ");

		if (username != null && !"".equals(username)) {

			sb.append("username like '%" + username + "%'");
			sb.append(" and ");
			request.setAttribute("username", username);
		}
		
		if (truename != null && !"".equals(truename)) {

			sb.append("truename like '%" + truename + "%'");
			sb.append(" and ");
			request.setAttribute("truename", truename);
		}

		
		HttpSession session = request.getSession();
		User user = (User)session.getAttribute("user");
		
		sb.append("  id!="+user.getId()+" and  role=2 and deletestatus=0 order by id desc ");
		String where = sb.toString();


		int currentpage = 1;
		int pagesize = 10;
		if (request.getParameter("pagenum") != null) {
			currentpage = Integer.parseInt(request.getParameter("pagenum"));
		}
		int total = userDao.selectBeanCount(where.replaceAll(" order by id desc ", ""));
		request.setAttribute("list", userDao.selectBeanList((currentpage - 1)
				* pagesize, pagesize, where));
		request.setAttribute("pagerinfo", Pager.getPagerNormal(total, pagesize,
				currentpage, "method!userlist4", "共有" + total + "条记录"));
		request.setAttribute("url", "method!userlist4");
		request.setAttribute("url2", "method!user");
		request.setAttribute("title", "评估教师列表");
		this.setUrl("user/userlist4.jsp");
		return SUCCESS;

	}
	
	
	
	//评估内容列表
	public String neironglist4() throws IOException {
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("gbk");response.setContentType("text/html; charset=gbk");
		HttpSession session = request.getSession();
		User user = (User)session.getAttribute("user");
		String touserid = request.getParameter("touserid");
		request.setAttribute("touserid", touserid);
		
		Jilu jilu = jiluDao.selectBean(" where fromuser.id= "+user.getId()+" and touser.id= "+touserid);
		if(jilu!=null){
			response
					.getWriter()
					.print(
							"<script language=javascript>alert('您已经进行对该教师进行评估！不可重复操作');window.location.href='method!jilulist4';</script>");
			return null;
		}
		
		
		StringBuffer sb = new StringBuffer();
		sb.append(" where ");

		sb.append("  deletestatus=0 order by id desc ");
		String where = sb.toString();


		int currentpage = 1;
		int pagesize = 100;
		if (request.getParameter("pagenum") != null) {
			currentpage = Integer.parseInt(request.getParameter("pagenum"));
		}
	
		request.setAttribute("list", neirongDao.selectBeanList((currentpage - 1)
				* pagesize, pagesize, where));

		request.setAttribute("title", "学生评教");
		this.setUrl("neirong/neironglist4.jsp");
		return SUCCESS;

	}
	
	
	//学生评教操作
	public void jiluadd3() throws IOException {
		HttpServletRequest request = ServletActionContext.getRequest();
		String touserid = request.getParameter("touserid");
		request.setAttribute("touserid", touserid);
		Jilu bean = new Jilu();
		bean.setDenfen(0);
		HttpSession session = request.getSession();
		User user = (User)session.getAttribute("user");
		bean.setFromuser(user);
		bean.setShijian(Util.getTime());
		bean.setTouser(userDao.selectBean(" where id= "+touserid));
		bean.setLeixing("学生评教");
		jiluDao.insertBean(bean);
		
		List<Neirong> list = neirongDao.selectBeanList(0, 9999, " where deletestatus=0 order by id desc");
		for(int i=0;i<list.size();i++){
			String neirongid = request.getParameter("neirongid_"+i);
			String dengji = request.getParameter("dengji_"+i);
			Jiluitem item = new Jiluitem();
			item.setDengji(dengji);
			item.setDefen(ManageAction.defen(dengji));
			item.setJilu(bean);
			item.setNeirong(neirongDao.selectBean(" where id= "+neirongid));
			item.setShijian(Util.getTime());
			
			jiluitemDao.insertBean(item);
			bean.setDenfen(bean.getDenfen()+item.getDefen());

		}
		jiluDao.updateBean(bean);
	
		
		
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("gbk");response.setContentType("text/html; charset=gbk");
		response
				.getWriter()
				.print(
						"<script language=javascript>alert('操作成功');window.location.href='method!jilulist4';</script>");
		
	}
	
	
	//评教记录列表
	public String jilulist4() {
		HttpServletRequest request = ServletActionContext.getRequest();
		String touser = request.getParameter("touser");

		
		StringBuffer sb = new StringBuffer();
		sb.append(" where ");

		if (touser != null && !"".equals(touser)) {

			sb.append("touser.truename like '%" + touser + "%'");
			sb.append(" and ");
			request.setAttribute("touser", touser);
		}

		HttpSession session = request.getSession();
		User user = (User)session.getAttribute("user");
		
		sb.append("  fromuser.id="+user.getId()+"  order by id desc ");
		String where = sb.toString();
		int currentpage = 1;
		int pagesize = 10;
		if (request.getParameter("pagenum") != null) {
			currentpage = Integer.parseInt(request.getParameter("pagenum"));
		}
		int total = jiluDao.selectBeanCount(where.replaceAll(" order by id desc ", ""));
		request.setAttribute("list", jiluDao.selectBeanList((currentpage - 1)
				* pagesize, pagesize, where));
		request.setAttribute("pagerinfo", Pager.getPagerNormal(total, pagesize,
				currentpage, "method!jilulist4", "共有" + total + "条记录"));

		request.setAttribute("title", "评教记录查询");
		this.setUrl("jilu/jilulist4.jsp");
		return SUCCESS;

	}
	
	
	
	//收到的评估记录列表
	public String jilulist5() {
		HttpServletRequest request = ServletActionContext.getRequest();
		
		String fromuser = request.getParameter("fromuser");

		
		StringBuffer sb = new StringBuffer();
		sb.append(" where ");

		if (fromuser != null && !"".equals(fromuser)) {

			sb.append("fromuser.truename like '%" + fromuser + "%'");
			sb.append(" and ");
			request.setAttribute("fromuser", fromuser);
		}

		HttpSession session = request.getSession();
		User user = (User)session.getAttribute("user");
		
		sb.append("  fromuser.id!="+user.getId()+" and touser.id="+user.getId()+" and fromuser.role=3  order by id desc ");
		String where = sb.toString();
		int currentpage = 1;
		int pagesize = 10;
		if (request.getParameter("pagenum") != null) {
			currentpage = Integer.parseInt(request.getParameter("pagenum"));
		}
		int total = jiluDao.selectBeanCount(where.replaceAll(" order by id desc ", ""));
		request.setAttribute("list", jiluDao.selectBeanList((currentpage - 1)
				* pagesize, pagesize, where));
		request.setAttribute("pagerinfo", Pager.getPagerNormal(total, pagesize,
				currentpage, "method!jilulist5", "共有" + total + "条记录"));

		request.setAttribute("title", "收到的评教记录查询");
		this.setUrl("jilu/jilulist5.jsp");
		return SUCCESS;

	}
	
	private TongjiDao tongjiDao;

	public TongjiDao getTongjiDao() {
		return tongjiDao;
	}

	public void setTongjiDao(TongjiDao tongjiDao) {
		this.tongjiDao = tongjiDao;
	}
	
	
	//评估统计列表
	public String tongjilist() {
		HttpServletRequest request = ServletActionContext.getRequest();
		
		List<Tongji> list3 = tongjiDao.selectBeanList(0, 9999, "  ");
		for(Tongji t:list3){
			tongjiDao.deleteBean(t);
		}
		HttpSession session = request.getSession();
		User user = (User)session.getAttribute("user");
		Tongji bean = new Tongji();
		bean.setUser(user);
	
		//自评
		Jilu  jilu = jiluDao.selectBean(" where fromuser.id=  "+user.getId()+" and touser.id= "+user.getId());
		if(jilu!=null){
			bean.setZiping(jilu.getDenfen());
		}
		

		//互评
		List<Jilu> list1 = jiluDao.selectBeanList(0, 9999, " where fromuser.id!="+user.getId()+" and  fromuser.role=2 and touser.id="+user.getId());
		if(list1.size()>0){
			int zongfen = 0;
			for(Jilu j:list1){
				zongfen = zongfen +j.getDenfen();
			}
			bean.setHuping(zongfen);
			bean.setShuliang1(list1.size());
			bean.setPingjun1(Arith.div(bean.getHuping(), list1.size(), 2));
			
		}
		
		
		//评教
		List<Jilu> list2 = jiluDao.selectBeanList(0, 9999, " where fromuser.id!="+user.getId()+" and  fromuser.role=3 and touser.id="+user.getId());
		if(list2.size()>0){
			int zongfen = 0;
			for(Jilu j:list2){
				zongfen = zongfen +j.getDenfen();
			}
			bean.setPingjiao(zongfen);
			bean.setShuliang2(list2.size());
			bean.setPingjun2(Arith.div(bean.getPingjiao(), list2.size(), 2));
		}
		
		
		tongjiDao.insertBean(bean);
		
		StringBuffer sb = new StringBuffer();
		sb.append(" where ");


		sb.append("  user.id="+user.getId()+"  order by id desc ");
		String where = sb.toString();
		int currentpage = 1;
		int pagesize = 10;
		if (request.getParameter("pagenum") != null) {
			currentpage = Integer.parseInt(request.getParameter("pagenum"));
		}
		int total = tongjiDao.selectBeanCount(where.replaceAll(" order by id desc ", ""));
		request.setAttribute("list", tongjiDao.selectBeanList((currentpage - 1)
				* pagesize, pagesize, where));
		request.setAttribute("pagerinfo", Pager.getPagerNormal(total, pagesize,
				currentpage, "method!tongjilist", "共有" + total + "条记录"));

		request.setAttribute("title", "评估统计");
		this.setUrl("tongji/tongjilist.jsp");
		return SUCCESS;

	}
	
	
	//评估记录查询
	public String jilulist6() {
		HttpServletRequest request = ServletActionContext.getRequest();
		String fromuser = request.getParameter("fromuser");
		String touser = request.getParameter("touser");
		String leixing = request.getParameter("leixing");
		
		StringBuffer sb = new StringBuffer();
		sb.append(" where ");

		if (fromuser != null && !"".equals(fromuser)) {

			sb.append("fromuser.truename like '%" + fromuser + "%'");
			sb.append(" and ");
			request.setAttribute("fromuser", fromuser);
		}
		
		if (touser != null && !"".equals(touser)) {

			sb.append("touser.truename like '%" + touser + "%'");
			sb.append(" and ");
			request.setAttribute("touser", touser);
		}
		
		if (leixing != null && !"".equals(leixing)) {

			sb.append("leixing like '%" + leixing + "%'");
			sb.append(" and ");
			request.setAttribute("leixing", leixing);
		}

		
		sb.append("  1=1 order by id desc ");
		String where = sb.toString();
		int currentpage = 1;
		int pagesize = 10;
		if (request.getParameter("pagenum") != null) {
			currentpage = Integer.parseInt(request.getParameter("pagenum"));
		}
		int total = jiluDao.selectBeanCount(where.replaceAll(" order by id desc ", ""));
		request.setAttribute("list", jiluDao.selectBeanList((currentpage - 1)
				* pagesize, pagesize, where));
		request.setAttribute("pagerinfo", Pager.getPagerNormal(total, pagesize,
				currentpage, "method!jilulist6", "共有" + total + "条记录"));

		request.setAttribute("title", "教师自评查询");
		this.setUrl("jilu/jilulist6.jsp");
		return SUCCESS;

	}
	
	
	//评估统计列表
	public String tongjilist2() {
		HttpServletRequest request = ServletActionContext.getRequest();
		
		List<Tongji> list3 = tongjiDao.selectBeanList(0, 9999, "  ");
		for(Tongji t:list3){
			tongjiDao.deleteBean(t);
		}
		
		List<User> list = userDao.selectBeanList(0, 9999, " where role=2 and deletestatus=0 ");
		for(User user:list){
			Tongji bean = new Tongji();
			bean.setUser(user);
		
			//自评
			Jilu  jilu = jiluDao.selectBean(" where fromuser.id=  "+user.getId()+" and touser.id= "+user.getId());
			if(jilu!=null){
				bean.setZiping(jilu.getDenfen());
			}
			

			//互评
			List<Jilu> list1 = jiluDao.selectBeanList(0, 9999, " where fromuser.id!="+user.getId()+" and  fromuser.role=2 and touser.id="+user.getId());
			if(list1.size()>0){
				int zongfen = 0;
				for(Jilu j:list1){
					zongfen = zongfen +j.getDenfen();
				}
				bean.setHuping(zongfen);
				bean.setShuliang1(list1.size());
				bean.setPingjun1(Arith.div(bean.getHuping(), list1.size(), 2));
				
			}
			
			
			//评教
			List<Jilu> list2 = jiluDao.selectBeanList(0, 9999, " where fromuser.id!="+user.getId()+" and  fromuser.role=3 and touser.id="+user.getId());
			if(list2.size()>0){
				int zongfen = 0;
				for(Jilu j:list2){
					zongfen = zongfen +j.getDenfen();
				}
				bean.setPingjiao(zongfen);
				bean.setShuliang2(list2.size());
				bean.setPingjun2(Arith.div(bean.getPingjiao(), list2.size(), 2));
			}
			
			
			tongjiDao.insertBean(bean);
		}
		
		
		String truename = request.getParameter("truename");
		
		StringBuffer sb = new StringBuffer();
		sb.append(" where ");

		if (truename != null && !"".equals(truename)) {

			sb.append("user.truename like '%" + truename + "%'");
			sb.append(" and ");
			request.setAttribute("truename", truename);
		}
		
		
		sb.append("  1=1  order by id desc ");
		String where = sb.toString();
		int currentpage = 1;
		int pagesize = 10;
		if (request.getParameter("pagenum") != null) {
			currentpage = Integer.parseInt(request.getParameter("pagenum"));
		}
		int total = tongjiDao.selectBeanCount(where.replaceAll(" order by id desc ", ""));
		request.setAttribute("list", tongjiDao.selectBeanList((currentpage - 1)
				* pagesize, pagesize, where));
		request.setAttribute("pagerinfo", Pager.getPagerNormal(total, pagesize,
				currentpage, "method!tongjilist2", "共有" + total + "条记录"));

		request.setAttribute("title", "评估统计");
		this.setUrl("tongji/tongjilist2.jsp");
		return SUCCESS;

	}
	
}
