package com.ns.tag;

import java.io.IOException;

import javax.servlet.http.HttpSession;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import com.ns.pojo.NsUser;

public class NsUserCheckTag extends TagSupport{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer right;
	
	public void setRight(Integer right) {
		this.right = right;
	}
	
	public Integer getRight() {
		return right;
	}

	@Override
	public int doEndTag() throws JspException {
		// TODO Auto-generated method stub
		HttpSession session = this.pageContext.getSession();
		NsUser user = (NsUser) session.getAttribute("user");
		if(user != null && user.getUser_right().intValue() <= right.intValue()){
			return TagSupport.EVAL_PAGE;
		}
		try {
			this.pageContext.getOut().print("��û�е�¼����Ȩ�޲������㲻�ܷ��ʣ�");
		} catch (IOException e) {
			// TODO: handle exception
		}
		return TagSupport.SKIP_PAGE;
	}
	
}
