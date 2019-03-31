package com.ns.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;

public abstract class NsBaseAction extends HttpServlet{
	private static final long serialVersionUID = 1L;
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("doGet");
		doPost(req, resp);
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("doPost");
		processRequest(req, resp);
	}
	protected void processRequest(HttpServletRequest request,HttpServletResponse response)
			throws ServletException, IOException{
		response.setContentType("text/html;charset=utf-8");
		request.setCharacterEncoding("utf-8");
		boolean isMultipart = ServletFileUpload.isMultipartContent(request);
		if(isMultipart){
			upfile(request,response);		//�ϴ��ļ�
		}else{
			String method = request.getParameter("method");
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
	protected void upfile(HttpServletRequest request,HttpServletResponse response)
			throws ServletException, IOException{
	}
	protected void manage(HttpServletRequest request,HttpServletResponse response)
			throws ServletException, IOException{
	}
	protected void browse(HttpServletRequest request,HttpServletResponse response)
			throws ServletException, IOException{
	}
	protected void show(HttpServletRequest request,HttpServletResponse response)
			throws ServletException, IOException{
	}
	protected void showall(HttpServletRequest request,HttpServletResponse response)
			throws ServletException, IOException{
	}
	protected void add(HttpServletRequest request,HttpServletResponse response)
			throws ServletException, IOException{
	}
	protected void willEdit(HttpServletRequest request,HttpServletResponse response)
			throws ServletException, IOException{
	}
	protected void edit(HttpServletRequest request,HttpServletResponse response)
			throws ServletException, IOException{
	}
	protected void delete(HttpServletRequest request,HttpServletResponse response)
			throws ServletException, IOException{
	}
	
}
