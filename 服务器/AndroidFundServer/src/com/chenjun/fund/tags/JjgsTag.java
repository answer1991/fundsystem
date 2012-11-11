package com.chenjun.fund.tags;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

import com.chenjun.fund.model.Jjgs;
import com.chenjun.utils.StringUtils;

public class JjgsTag extends TagSupport{
	@Override
	public int doEndTag() throws JspException {
JspWriter out = this.pageContext.getOut();
		
		Jjgs jjgs = (Jjgs)this.pageContext.getRequest().getAttribute("jjgs");
		
		if(jjgs != null){
			try {
				out.print("{");
				out.print("\"dm\"=\"" + StringUtils.replace(jjgs.getDm()) + "\",");
				out.print("\"jgmc\"=\"" + StringUtils.replace(jjgs.getJgmc()) + "\",");
				out.print("\"fddbr\"=\"" + StringUtils.replace(jjgs.getFddbr()) + "\",");
				out.print("\"slrq\"=\"" + StringUtils.replace(jjgs.getSlrq()) + "\",");
				out.print("\"zczb\"=\"" + StringUtils.replace(jjgs.getZczb()) + "\",");
				out.print("\"zcdz\"=\"" + StringUtils.replace(jjgs.getZcdz()) + "\",");
				out.print("\"zjl\"=\"" + StringUtils.replace(jjgs.getZjl()) + "\",");
				out.print("\"lxdh\"=\"" + StringUtils.replace(jjgs.getLxdh()) + "\",");
				out.print("\"lxcz\"=\"" + StringUtils.replace(jjgs.getLxcz()) + "\",");
				out.print("\"lxdz\"=\"" + StringUtils.replace(jjgs.getLxdz()) + "\",");
				out.print("\"yzbm\"=\"" + StringUtils.replace(jjgs.getYzbm()) + "\",");
				out.print("\"dzyx\"=\"" + StringUtils.replace(jjgs.getDzyx()) + "\",");
				out.print("\"gswz\"=\"" + StringUtils.replace(jjgs.getGswz()) + "\"");
				out.print("}");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			finally{
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
