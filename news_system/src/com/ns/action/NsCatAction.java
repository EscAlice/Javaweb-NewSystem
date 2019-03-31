package com.ns.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;

import com.ns.common.MyException;
import com.ns.common.MyFactory;
import com.ns.iservice.INsCatService;
import com.ns.iservice.INsDcService;
import com.ns.iservice.INsNewsService;
import com.ns.pojo.NsCat;
import com.ns.pojo.NsDc;
import com.ns.pojo.NsNewsDc;

public class NsCatAction extends NsBaseAction{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private INsCatService catService =(INsCatService)MyFactory.getBean("catService");
	private INsNewsService newsService =(INsNewsService)MyFactory.getBean("newsService");
	private INsDcService dcService =(INsDcService)MyFactory.getBean("dcService");
	@Override
	protected void processRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html;charset=utf-8");
		request.setCharacterEncoding("utf-8");
		boolean isMultipart = ServletFileUpload.isMultipartContent(request);
		if(isMultipart){
			upfile(request,response);		//�ϴ��ļ�
		}else{
			String method = request.getParameter("method");
			System.out.println("CatAction:"+method);
			if(method.equals("manage")){
				manage(request,response);		//�������showall��������У�Ȼ��Ž�PageList
			}else if(method.equals("browse")){
				browse(request,response);		//Ԥ��������show��֮������תҳ��
			}else if(method.equals("show")){
				show(request,response);			//��ʾ,���ں����ݿ⽻������ѯ
			}else if(method.equals("showall")){
				showall(request,response);		//����
			}else if(method.equals("add")){
				add(request,response);			//���
			}else if(method.equals("willEdit")){
				willEdit(request,response);		//Ҫ�޸�
			}else if(method.equals("edit")){
				edit(request,response);			//�޸�
			}else if(method.equals("delete")){
				delete(request,response);		//ɾ��
			}
		}
	}
	@Override
	protected void show(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
			int catId = Integer.parseInt(request.getParameter("catId"));
			List<NsDc> dclist = dcService.findDcByCatId(catId);
			List<NsNewsDc> newsdclist = newsService.findNewsByCatId(catId);
			request.setAttribute("newsdclist", newsdclist);
			request.setAttribute("dclist", dclist);
			RequestDispatcher rd = request.getRequestDispatcher("/news/catnews.jsp");
			rd.forward(request, response);
		} catch (MyException e) {
			// TODO: handle exceptionW
			request.setAttribute("msg", e.getMessage()+"<a href=\"JavaScript:window.history.back()\">����</a>");
			RequestDispatcher rd = request.getRequestDispatcher("/common/message.jsp");
			rd.forward(request, response);
		}
	}
	@Override
	protected void showall(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
			List<NsCat> catlist = catService.findCatAll();
			request.setAttribute("catlist", catlist);
			RequestDispatcher rd = request.getRequestDispatcher("/cat/manage.jsp");
			rd.forward(request, response);
		} catch (MyException e) {
			// TODO: handle exception
			request.setAttribute("msg", e.getMessage()+"<a href=\"JavaScript:window.history.back()\">����</a>");
			RequestDispatcher rd = request.getRequestDispatcher("/common/message.jsp");
			rd.forward(request, response);
		}
	}
	@Override
	protected void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		NsCat cat = new NsCat();
		cat.setCat_name(request.getParameter("catName"));
		try {
			catService.addCat(cat);
			showall(request, response);
		} catch (MyException e) {
			// TODO: handle exception
			request.setAttribute("msg", e.getMessage()+"<a href=\"JavaScript:window.history.back()\">����</a>");
			RequestDispatcher rd = request.getRequestDispatcher("/common/message.jsp");
			rd.forward(request, response);
		}
	}
	@Override
	protected void willEdit(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
			NsCat cat = catService.findCatById(Integer.parseInt(request.getParameter("catId")));
			request.setAttribute("cat", cat);
			RequestDispatcher rd = request.getRequestDispatcher("/cat/edit.jsp");
			rd.forward(request, response);
		} catch (MyException e) {
			// TODO: handle exception
			request.setAttribute("msg", e.getMessage()+"<a href=\"JavaScript:window.history.back()\">����</a>");
			RequestDispatcher rd = request.getRequestDispatcher("/common/message.jsp");
			rd.forward(request, response);
		}
	}
	@Override
	protected void edit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		NsCat cat = new NsCat();
		cat.setCat_id(Integer.parseInt(request.getParameter("catId")));
		cat.setCat_name(request.getParameter("catName"));
		try {
			catService.editCat(cat);
			showall(request, response);
		} catch (MyException e) {
			// TODO: handle exception
			request.setAttribute("msg", e.getMessage()+"<a href=\"JavaScript:window.history.back()\">����</a>");
			RequestDispatcher rd = request.getRequestDispatcher("/common/message.jsp");
			rd.forward(request, response);
		}
	}
	@Override
	protected void delete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		int id = Integer.parseInt(request.getParameter("catId"));
		try {
			catService.deleteCat(id);
			showall(request, response);
		} catch (MyException e) {
			// TODO: handle exception
			request.setAttribute("msg", e.getMessage()+"<a href=\"JavaScript:window.history.back()\">����</a>");
			RequestDispatcher rd = request.getRequestDispatcher("/common/message.jsp");
			rd.forward(request, response);
		}
	}
}
