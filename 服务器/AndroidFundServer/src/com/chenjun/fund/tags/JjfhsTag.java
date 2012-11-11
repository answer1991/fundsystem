package com.chenjun.fund.tags;

import java.io.IOException;
import java.util.List;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

import com.chenjun.fund.model.Jjfh;


public class JjfhsTag extends TagSupport {
	
	@Override
	public int doEndTag() throws JspException {
		JspWriter out = this.pageContext.getOut();

		List<Jjfh> jjfhs = (List<Jjfh>) this.pageContext.getRequest()
				.getAttribute("jjfhs");

		if (jjfhs != null) {
			try {
				for (int i = 0; i < jjfhs.size(); i++) {
					Jjfh jjfh = jjfhs.get(i);
					out.print("{");
					out.print("\"dm\"=\"" + jjfh.getDm() + "\",");
					out.print("\"ggrq\"=\"" + jjfh.getGgrq() + "\",");
					out.print("\"dwfh\"=\"" + jjfh.getDwfh() + "\",");
					out.print("\"gqdjr\"=\"" + jjfh.getGqdjr() + "\",");
					out.print("\"cxr\"=\"" + jjfh.getCxr() + "\",");
					out.print("\"pxr\"=\"" + jjfh.getPxr() + "\",");
					out.print("\"ztr\"=\"" + jjfh.getZtr() + "\",");
					out.print("\"jjfejs\"=\"" + jjfh.getJjfejs() + "\"");
					if (i == (jjfhs.size() - 1)) {
						out.print("}");
					} else {
						out.print("},");
					}
				}
			} catch (Exception e) {
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
		return super.doEndTag();
	}
}
