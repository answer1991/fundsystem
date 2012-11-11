package com.chenjun.fund.tags;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

import com.chenjun.fund.model.Jjjz;

public class JjjzsTag extends TagSupport{
	/**
	 * 
	 */
	
	//private static final long serialVersionUID = -3960750420890640308L;
	@Override
	public int doStartTag() throws JspException {
		JspWriter out = this.pageContext.getOut();
		
		ArrayList<Jjjz> jjjzs = (ArrayList<Jjjz>)this.pageContext.getRequest().getAttribute("jjjzs");
		//System.out.println(jjjzs.size());
		if(jjjzs != null){
			for(int i = 0; i < jjjzs.size(); i++){
				Jjjz jjjz = jjjzs.get(i);
				try {
					out.print("{");
					out.print("\"dm\"=\"" + jjjz.getDm() + "\",");
					out.print("\"rq\"=\"" + jjjz.getRq() + "\",");
					out.print("\"jz\"=\"" + jjjz.getJz() + "\",");
					out.print("\"ljjz\"=\"" + jjjz.getLjjz() + "\",");
					out.print("\"fqjz\"=\"" + jjjz.getFqjz() + "\"");
					if(i == (jjjzs.size() - 1)){
						out.print("}");
					}
					else{
						out.print("},");
					}
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
		}
		return super.doStartTag();
	}
}
