package com.chenjun.fund.tags;

import java.io.IOException;
import java.util.List;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

import com.chenjun.fund.model.Jjcf;

public class JjcfsTag extends TagSupport {
	@Override
	public int doEndTag() throws JspException {
		JspWriter out = this.pageContext.getOut();

		List<Jjcf> jjcfs = (List<Jjcf>) this.pageContext.getRequest()
				.getAttribute("jjcfs");

		if (jjcfs != null) {
			try {
				for (int i = 0; i < jjcfs.size(); i++) {
					Jjcf jjcf = jjcfs.get(i);
					out.print("{");
					out.print("\"dm\"=\"" + jjcf.getDm() + "\",");
					out.print("\"ggrq\"=\"" + jjcf.getGgrq() + "\",");
					out.print("\"cfrq\"=\"" + jjcf.getCfrq() + "\",");
					out.print("\"cfqjz\"=\"" + jjcf.getCfqjz() + "\",");
					out.print("\"cfhjz\"=\"" + jjcf.getCfhjz() + "\",");
					out.print("\"cfbl\"=\"" + jjcf.getCfbl() + "\"");
					if (i == (jjcfs.size() - 1)) {
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
