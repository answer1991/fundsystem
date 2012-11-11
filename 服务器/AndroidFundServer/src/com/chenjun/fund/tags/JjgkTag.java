package com.chenjun.fund.tags;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

import com.chenjun.fund.model.Jjgk;
import com.chenjun.utils.StringUtils;

public class JjgkTag extends TagSupport{
	@Override
	public int doEndTag() throws JspException {
		JspWriter out = this.pageContext.getOut();
		
		Jjgk jjgk = (Jjgk)this.pageContext.getRequest().getAttribute("jjgk");
		
		if(jjgk != null){
			try {
				out.print("{");
				out.print("\"dm\"=\"" + StringUtils.replace(jjgk.getDm()) + "\",");
				out.print("\"jjmc\"=\"" + StringUtils.replace(jjgk.getJjmc()) + "\",");
				out.print("\"jjjc\"=\"" + StringUtils.replace(jjgk.getJjjc()) + "\",");
				out.print("\"pyjc\"=\"" + StringUtils.replace(jjgk.getPyjc()) + "\",");
				out.print("\"jjlx\"=\"" + StringUtils.replace(jjgk.getJjlx()) + "\",");
				out.print("\"jjglr\"=\"" + StringUtils.replace(jjgk.getJjglr()) + "\",");
				out.print("\"jjtgr\"=\"" + StringUtils.replace(jjgk.getJjtgr()) + "\",");
				out.print("\"glfl\"=\"" + StringUtils.replace(jjgk.getGlfl()) + "\",");
				out.print("\"tgfl\"=\"" + StringUtils.replace(jjgk.getTgfl()) + "\",");
				out.print("\"tzlx\"=\"" + StringUtils.replace(jjgk.getTzlx()) + "\",");
				out.print("\"clrq\"=\"" + StringUtils.replace(jjgk.getClrq()) + "\",");
				out.print("\"kfsgqsr\"=\"" + StringUtils.replace(jjgk.getKfsgqsr()) + "\",");
				out.print("\"kfshqsr\"=\"" + StringUtils.replace(jjgk.getKfshqsr()) + "\",");
				out.print("\"dbsgjexx\"=\"" + StringUtils.replace(jjgk.getDbsgjexx()) + "\",");
				out.print("\"dbshfexx\"=\"" + StringUtils.replace(jjgk.getDbshfexx()) + "\",");
				out.print("\"tzfg\"=\"" + StringUtils.replace(jjgk.getTzfg()) + "\",");
				out.print("\"tzmb\"=\"" + StringUtils.replace(jjgk.getTzmb()) + "\",");
				out.print("\"tzfw\"=\"" + StringUtils.replace(jjgk.getTzfw()) + "\",");
				out.print("\"jeshblrd\"=\"" + StringUtils.replace(jjgk.getJeshblrd()) + "\",");
				out.print("\"jeshtk\"=\"" + StringUtils.replace(jjgk.getJeshtk()) + "\",");
				out.print("\"scfxts\"=\"" + StringUtils.replace(jjgk.getScfxts()) + "\",");
				out.print("\"glfxts\"=\"" + StringUtils.replace(jjgk.getGlfxts()) + "\",");
				out.print("\"jsfxts\"=\"" + StringUtils.replace(jjgk.getJsfxts()) + "\",");
				out.print("\"fltqbz\"=\"" + StringUtils.replace(jjgk.getFltqbz()) + "\",");
				out.print("\"jjfqr\"=\"" + StringUtils.replace(jjgk.getJjfqr()) + "\",");
				out.print("\"xsdlr\"=\"" + StringUtils.replace(jjgk.getXsdlr()) + "\",");
				out.print("\"tzcl\"=\"" + StringUtils.replace(jjgk.getTzcl()) + "\",");
				out.print("\"jjzztk\"=\"" + StringUtils.replace(jjgk.getJjzztk()) + "\",");
				out.print("\"yjbjjz\"=\"" + StringUtils.replace(jjgk.getYjbjjz()) + "\",");
				out.print("\"sgzt\"=\"" + StringUtils.replace(jjgk.getSgzt()) + "\"");
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
