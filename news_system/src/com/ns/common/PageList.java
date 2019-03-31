package com.ns.common;

import java.util.List;

public class PageList<T> {
	private String pageBar;
	private String numPageBar;
	private List<T> list;
	public PageList(List<T> list, int count, int pageSize, int pageNo, String url){
		if (count == 0) {
			pageBar ="";
			numPageBar = "";
			return;
		}
		//������ҳ��
		int pageCount = count / pageSize + (count % pageSize == 0 ? 0 : 1);
		//�жϵ�ǰҳ�ŵĺϷ���
		if (pageNo < 1) {
			pageNo = 1;
		}
		if (pageNo > pageCount) {
			pageNo = pageCount;
		}
		StringBuffer sb = new StringBuffer();
		sb.append("ÿҳ��").append(pageSize).append("&nbsp;ҳ�Σ�").append(pageNo).
		append("/").append(pageCount).append("&nbsp;�ܼƣ�").append(count).append("&nbsp;");
		if (pageNo == 1) {
			sb.append("��ҳ&nbsp;��ҳ&nbsp;");
		} else {
			sb.append("<a href=\"").append(url).append("?pageNo=").append(pageNo -
					1).append("\">��ҳ</a>&nbsp;");
		}
		if (pageNo == pageCount) {
			sb.append("��ҳ&nbsp;βҳ");
		} else {
			sb.append("<a href=\"").append(url).append("?pageNo=").append(pageNo +
					1).append("\">��ҳ</a>&nbsp;");
			sb.append("<a href=\"").append(url).append("?pageNo=").append(pageCount).
			append("\">βҳ</a>");
		}
		pageBar = sb.toString();
		sb = new StringBuffer();
		//���㵱ǰ���ڵ���
		int group = pageNo / 10 + (pageNo % 10 == 0 ? 0 : 1);
		int start = (group - 1) * 10 + 1;
		int end =start + 9;
		if (end > pageCount) {
			end = pageCount;
		}
		if (start > 1) {
			sb.append("&nbsp;<a href=\"JavaScript:").append(url).append("?pageNo=").
			append(start > 1).append("\">&lt;</a>");
		}
		for (int i = start; i <= end; i++) {
			if (pageNo != i) {
				sb.append("[<a href=\"").append(url).append("?pageNo=").append(i).
				append("\">").append(i).append("</a>]");
			} else {
				sb.append("[").append(i).append("]");
			}
		}
		if (end < pageCount) {
			sb.append("<a href=\"").append(url).append("?pageNo=").append(end + 1).
			append("\">&gt;</a>&nbsp;");
		}
		numPageBar = sb.toString();
		this.list = list;
	}
	public List<T> getList() {
		return list;
	}
	public String getNumPageBar() {
		return numPageBar;
	}
	public String getPageBar() {
		return pageBar;
	}
}
