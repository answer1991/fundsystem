package com.chenjun.fund.tags;

import java.io.IOException;
import java.util.List;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

import com.chenjun.fund.model.JjBaseInfo;

public class JjBaseInfosTag extends TagSupport {
	@Override
	public int doEndTag() throws JspException {
		JspWriter out = this.pageContext.getOut();
		@SuppressWarnings("unchecked")
		List<JjBaseInfo> jjBaseInfos = (List<JjBaseInfo>) this.pageContext
				.getRequest().getAttribute("jjBaseInfos");
		// System.out.println(jjjzs.size());
		if (jjBaseInfos != null) {
			for (int i = 0; i < jjBaseInfos.size(); i++) {
				JjBaseInfo jjBaseInfo = jjBaseInfos.get(i);
				try {
					out.print("{");
					out.print("\"dm\"=\"" + jjBaseInfo.getDm() + "\",");
					out.print("\"jjjc\"=\"" + jjBaseInfo.getJjjc() + "\",");
					out.print("\"tzlx\"=\"" + jjBaseInfo.getTzlx() + "\",");
					out.print("\"lastDayJz\"=\"" + jjBaseInfo.getLastDayJz()
							+ "\",");
					out.print("\"lastDayLjjz\"=\""
							+ jjBaseInfo.getLastDayLjjz() + "\",");
					out.print("\"lastDayFqjz\"=\""
							+ jjBaseInfo.getLastDayFqjz() + "\",");
					out.print("\"rq\"=\"" + jjBaseInfo.getRq() + "\",");
					out.print("\"preDayJz\"=\"" + jjBaseInfo.getPreDayJz()
							+ "\",");
					out.print("\"preDayFqjz\"=\"" + jjBaseInfo.getPreDayFqjz()
							+ "\"");
					if (i == (jjBaseInfos.size() - 1)) {
						out.print("}");
					} else {
						out.print("},");
					}
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} finally {
					try {
						out.flush();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}
		return super.doEndTag();
	}
}
