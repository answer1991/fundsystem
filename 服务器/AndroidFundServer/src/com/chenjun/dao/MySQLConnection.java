package com.chenjun.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.chenjun.fund.model.JjBaseInfo;
import com.chenjun.fund.model.Jjcf;
import com.chenjun.fund.model.Jjfh;
import com.chenjun.fund.model.Jjgk;
import com.chenjun.fund.model.Jjgs;
import com.chenjun.fund.model.Jjjz;
import com.chenjun.fund.model.JjjzIncreaseInfo;
import com.chenjun.fund.model.SimpleJjgk;

public class MySQLConnection {
	private Connection conn;
	private Statement statement;
	private final String connUrl = "jdbc:mysql://127.0.0.1:3306/jj?useUnicode=true&characterEncoding=UTF-8";
	private final String username = "root";
	private final String password = "a010203";

	public MySQLConnection() {
		initConnection();
	}
	
	@Override
	protected void finalize() throws Throwable {
		closeConnection();
		super.finalize();
	}
	/**
	 * 更新数据库方法，插入一条SQL语句，返回影响的行数。
	 * 
	 * @param sql
	 * @return 影响的行数
	 */
	public int update(String sql) {
		int i = 0;
		try {
			i = statement.executeUpdate(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return i;
	}

	/**
	 * 查询所有的基金净值信息
	 * 
	 * @return
	 */
	public ArrayList<Jjjz> queryAll() {
		ArrayList<Jjjz> jjjzs = new ArrayList<Jjjz>();
		ResultSet rs = null;
		try {
			String sql = "SELECT * FROM jjjz";
			rs = statement.executeQuery(sql);
			while (rs.next()) {
				Jjjz jjjz = new Jjjz();
				jjjz.setDm(rs.getString("dm"));
				jjjz.setRq(rs.getString("rq"));
				jjjz.setJz(rs.getString("jz"));
				jjjz.setLjjz(rs.getString("ljjz"));
				jjjz.setFqjz(rs.getString("fqjz"));
				jjjzs.add(jjjz);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return jjjzs;
	}

	/**
	 * 查询基金净值最后一天的信息
	 * 
	 * @param dm
	 * @return
	 */
	public Jjjz queryJjjzLastDay(String dm) {
		Jjjz jjjz = null;
		ResultSet rs = null;
		try {
			String sql = "SELECT * FROM jjjz WHERE dm = '" + dm
					+ "' AND rq = (SELECT max(rq) FROM jjjz)";
			rs = statement.executeQuery(sql);
			if (rs.next()) {
				jjjz = new Jjjz();
				jjjz.setDm(rs.getString("dm"));
				jjjz.setRq(rs.getString("rq"));
				jjjz.setJz(rs.getString("jz"));
				jjjz.setLjjz(rs.getString("ljjz"));
				jjjz.setFqjz(rs.getString("fqjz"));
				// System.out.println(rs.getString("fqjz"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return jjjz;
	}

	/**
	 * 查询基金净值最后第二天的信息
	 * 
	 * @param dm
	 * @return
	 */
	public Jjjz queryJjjzPreLastDay(String dm) {
		Jjjz jjjz = null;
		ResultSet rs = null;
		try {
			String sql = "SELECT * FROM jjjz WHERE dm = '"
					+ dm
					+ "' AND rq = (SELECT max(rq) FROM jjjz Where rq < (SELECT max(rq) from jjjz))";
			rs = statement.executeQuery(sql);
			if (rs.next()) {
				jjjz = new Jjjz();
				jjjz.setDm(rs.getString("dm"));
				jjjz.setRq(rs.getString("rq"));
				jjjz.setJz(rs.getString("jz"));
				jjjz.setLjjz(rs.getString("ljjz"));
				jjjz.setFqjz(rs.getString("fqjz"));
				// System.out.println(rs.getString("fqjz"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return jjjz;
	}

	/**
	 * 根据基金代码，查询对应基金的所有净值信息
	 * 
	 * @param dm
	 * @return
	 */
	public ArrayList<Jjjz> queryJjjzsByDm(String dm) {
		ArrayList<Jjjz> jjjzs = new ArrayList<Jjjz>();
		ResultSet rs = null;
		try {
			String sql = "SELECT * FROM jjjz WHERE dm = '" + dm + "'";
			rs = statement.executeQuery(sql);
			while (rs.next()) {
				Jjjz jjjz = new Jjjz();
				jjjz.setDm(rs.getString("dm"));
				jjjz.setRq(rs.getString("rq"));
				jjjz.setJz(rs.getString("jz"));
				jjjz.setLjjz(rs.getString("ljjz"));
				jjjz.setFqjz(rs.getString("fqjz"));
				// System.out.println(rs.getString("fqjz"));
				jjjzs.add(jjjz);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return jjjzs;
	}
	
	
	/**
	 * 根据基金代码，查询对应基金的所有净值信息
	 * 
	 * @param dm
	 * @return
	 */
	public ArrayList<Jjjz> queryJjjzsByDmAndLastDay(String dm, String lastDay) {
		ArrayList<Jjjz> jjjzs = new ArrayList<Jjjz>();
		ResultSet rs = null;
		try {
			String sql = "SELECT * FROM jjjz WHERE dm = '" + dm + "' AND rq > '" + lastDay + "'";
			rs = statement.executeQuery(sql);
			while (rs.next()) {
				Jjjz jjjz = new Jjjz();
				jjjz.setDm(rs.getString("dm"));
				jjjz.setRq(rs.getString("rq"));
				jjjz.setJz(rs.getString("jz"));
				jjjz.setLjjz(rs.getString("ljjz"));
				jjjz.setFqjz(rs.getString("fqjz"));
				// System.out.println(rs.getString("fqjz"));
				jjjzs.add(jjjz);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return jjjzs;
	}
	

	/**
	 * 根据给定的基金代码和天数，查询对应的基金的给定天数的净值信息
	 * 
	 * @param dm
	 * @param dayCount
	 * @return
	 */
	public ArrayList<Jjjz> queryJjjzsByDmAndDayCount(String dm, int dayCount) {
		ArrayList<Jjjz> jjjzs = queryJjjzsByDm(dm);

		if (dayCount >= jjjzs.size()) {
			return jjjzs;
		}

		ArrayList<Jjjz> result = new ArrayList<Jjjz>();

		int offset = jjjzs.size() - dayCount;
		for (int i = 0; i < dayCount; i++) {
			result.add(jjjzs.get(offset + i));
		}
		return result;
	}

	/**
	 * 查询基金概况
	 * 
	 * @param dm
	 * @return
	 */
	public Jjgk queryJjgk(String dm) {
		Jjgk jjgk = null;
		ResultSet rs = null;

		try {
			String sql = "SELECT * FROM jjgk WHERE dm = '" + dm + "'";
			rs = statement.executeQuery(sql);
			if (rs.next()) {
				jjgk = new Jjgk();
				jjgk.setDm(rs.getString("dm"));
				jjgk.setJjmc(rs.getString("jjmc"));
				jjgk.setJjjc(rs.getString("jjjc"));
				jjgk.setPyjc(rs.getString("pyjc"));
				jjgk.setJjlx(rs.getString("jjlx"));
				jjgk.setJjglr(rs.getString("jjglr"));
				jjgk.setJjtgr(rs.getString("jjtgr"));
				jjgk.setGlfl(rs.getString("glfl"));
				jjgk.setTgfl(rs.getString("tgfl"));
				jjgk.setTzlx(rs.getString("tzlx"));
				jjgk.setClrq(rs.getString("clrq"));
				jjgk.setKfsgqsr(rs.getString("kfsgqsr"));
				jjgk.setKfshqsr(rs.getString("kfshqsr"));
				jjgk.setDbsgjexx(rs.getString("dbsgjexx"));
				jjgk.setDbshfexx(rs.getString("dbshfexx"));
				jjgk.setTzfg(rs.getString("tzfg"));
				jjgk.setTzmb(rs.getString("tzmb"));
				jjgk.setTzfw(rs.getString("tzfw"));
				jjgk.setJeshblrd(rs.getString("jeshblrd"));
				jjgk.setJeshtk(rs.getString("jeshtk"));
				jjgk.setScfxts(rs.getString("scfxts"));
				jjgk.setGlfxts(rs.getString("glfxts"));
				jjgk.setJsfxts(rs.getString("jsfxts"));
				jjgk.setFltqbz(rs.getString("fltqbz"));
				jjgk.setJjfqr(rs.getString("jjfqr"));
				jjgk.setXsdlr(rs.getString("xsdlr"));
				jjgk.setTzcl(rs.getString("tzcl"));
				jjgk.setJjzztk(rs.getString("jjzztk"));
				jjgk.setYjbjjz(rs.getString("yjbjjz"));
				jjgk.setSgzt(rs.getString("sgzt"));

				// System.out.println(rs.getString("fqjz"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return jjgk;
	}

	/**
	 * 查询基金分红
	 */
	public List<Jjfh> queryJjfh(String dm) {
		List<Jjfh> jjfhs = new ArrayList<Jjfh>();
		ResultSet rs = null;

		try {
			String sql = "SELECT * FROM jjfh WHERE dm = '" + dm + "'";
			rs = statement.executeQuery(sql);
			while (rs.next()) {
				Jjfh jjfh = new Jjfh();
				jjfh.setDm(rs.getString("dm"));
				jjfh.setGgrq(rs.getString("ggrq"));
				jjfh.setDwfh(rs.getString("dwfh"));
				jjfh.setGqdjr(rs.getString("gqdjr"));
				jjfh.setCxr(rs.getString("cxr"));
				jjfh.setPxr(rs.getString("pxr"));
				jjfh.setZtr(rs.getString("ztr"));
				jjfh.setJjfejs(rs.getString("jjfejs"));

				jjfhs.add(jjfh);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return jjfhs;
	}

	/**
	 * 查询基金拆分
	 */
	public List<Jjcf> queryJjcf(String dm) {
		List<Jjcf> jjcfs = new ArrayList<Jjcf>();
		ResultSet rs = null;

		try {

			String sql = "SELECT * FROM jjcf WHERE dm = '" + dm + "'";
			rs = statement.executeQuery(sql);
			while (rs.next()) {
				Jjcf jjcf = new Jjcf();
				jjcf.setDm(rs.getString("dm"));
				jjcf.setGgrq(rs.getString("ggrq"));
				jjcf.setCfrq(rs.getString("cfrq"));
				jjcf.setCfqjz(rs.getString("cfqjz"));
				jjcf.setCfhjz(rs.getString("cfhjz"));
				jjcf.setCfbl(rs.getString("cfbl"));

				jjcfs.add(jjcf);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return jjcfs;
	}

	/**
	 * 查询基金公司
	 * 
	 * @param dm
	 * @return
	 */
	public Jjgs queryJjgs(String dm) {
		Jjgs jjgs = null;
		ResultSet rs = null;

		try {
			String sql = "SELECT * FROM jjgs WHERE dm = '" + dm + "'";
			rs = statement.executeQuery(sql);
			if (rs.next()) {
				jjgs = new Jjgs();
				jjgs.setDm(rs.getString("dm"));
				jjgs.setJgmc(rs.getString("jgmc"));
				jjgs.setFddbr(rs.getString("fddbr"));
				jjgs.setSlrq(rs.getString("slrq"));
				jjgs.setZczb(rs.getString("zczb"));
				jjgs.setZcdz(rs.getString("zcdz"));
				jjgs.setZjl(rs.getString("zjl"));
				jjgs.setLxdh(rs.getString("lxdh"));
				jjgs.setLxcz(rs.getString("lxcz"));
				jjgs.setLxdz(rs.getString("lxdz"));
				jjgs.setYzbm(rs.getString("yzbm"));
				jjgs.setDzyx(rs.getString("dzyx"));
				jjgs.setGswz(rs.getString("gswz"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return jjgs;
	}

	/**
	 * 基金涨跌信息
	 */
	public JjjzIncreaseInfo queryJjjzIncreaseInfo(String dm) {
		System.out.println("queryJjjzIncreaseInfo start");
		
		Jjjz lastDayJjjz = queryJjjzLastDay(dm);
		Jjjz preDayJjjz = queryJjjzPreLastDay(dm);

		if (lastDayJjjz == null || preDayJjjz == null) {
			return null;
		}

		JjjzIncreaseInfo jjjzIncreaseInfo = new JjjzIncreaseInfo();

		jjjzIncreaseInfo.setDm(lastDayJjjz.getDm());
		jjjzIncreaseInfo.setLastDayJz(lastDayJjjz.getJz());
		jjjzIncreaseInfo.setLastDayLjjz(lastDayJjjz.getLjjz());
		jjjzIncreaseInfo.setLastDayFqjz(lastDayJjjz.getFqjz());
		jjjzIncreaseInfo.setRq(lastDayJjjz.getRq());
		jjjzIncreaseInfo.setPreDayJz(preDayJjjz.getJz());
		jjjzIncreaseInfo.setPreDayFqjz(preDayJjjz.getFqjz());

		System.out.println("queryJjjzIncreaseInfo end");
		
		return jjjzIncreaseInfo;
	}
	
	/**
	 * 查询简单基金概况信息
	 */
	public SimpleJjgk querySimpleJjgk(String dm){
		System.out.println("querySimpleJjgk start!");
		
		SimpleJjgk simpleJjgk = null;
		ResultSet rs = null;
		
		try {
			String sql = "SELECT dm,jjjc,tzlx FROM jjgk WHERE dm = '" + dm + "'";
			rs = statement.executeQuery(sql);
			if (rs.next()) {
				simpleJjgk = new SimpleJjgk();
				simpleJjgk.setDm(rs.getString("dm"));
				simpleJjgk.setJjjc(rs.getString("jjjc"));
				simpleJjgk.setTzlx(rs.getString("tzlx"));
				// System.out.println(rs.getString("fqjz"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("querySimpleJjgk end!");
		return simpleJjgk;
	}
	
	/**
	 * 基金基本信息查询
	 */
	public JjBaseInfo queryJjBaseInfo(String dm) {
		System.out.println("queryJjBaseInfo start!");
		
		SimpleJjgk simplejjgk = querySimpleJjgk(dm);
		JjjzIncreaseInfo jjjzIncreaseInfo = queryJjjzIncreaseInfo(dm);

		if (simplejjgk == null || jjjzIncreaseInfo == null) {
			return null;
		}

		JjBaseInfo jjBaseInfo = new JjBaseInfo();

		jjBaseInfo.setDm(simplejjgk.getDm());
		jjBaseInfo.setJjjc(simplejjgk.getJjjc());
		jjBaseInfo.setTzlx(simplejjgk.getTzlx());
		jjBaseInfo.setLastDayJz(jjjzIncreaseInfo.getLastDayJz());
		jjBaseInfo.setLastDayLjjz(jjjzIncreaseInfo.getLastDayLjjz());
		jjBaseInfo.setLastDayFqjz(jjjzIncreaseInfo.getLastDayFqjz());
		jjBaseInfo.setRq(jjjzIncreaseInfo.getRq());
		jjBaseInfo.setPreDayJz(jjjzIncreaseInfo.getPreDayJz());
		jjBaseInfo.setPreDayFqjz(jjjzIncreaseInfo.getPreDayFqjz());

		System.out.println("queryJjBaseInfo end!");
		
		return jjBaseInfo;
	}

	/**
	 * 请求多个基金的基本信息
	 * 
	 * @param dms
	 *            用逗号隔开的代码串
	 * @return
	 */
	public List<JjBaseInfo> queryJjBaseInfos(String dms) {
		System.out.println(dms);
		
		String[] dmArray = dms.split(",");

		List<JjBaseInfo> jjBaseInfos = new ArrayList<JjBaseInfo>();

		JjBaseInfo jjBaseInfo = null;
		for (int i = 0; i < dmArray.length; i++) {
			jjBaseInfo = queryJjBaseInfo(dmArray[i]);

			if (jjBaseInfo != null) {
				jjBaseInfos.add(jjBaseInfo);
			}
		}

		return jjBaseInfos;
	}

	/**
	 * 搜索，返回多个基金的基本信息
	 * 
	 * @param
	 * @return
	 */
	public List<JjBaseInfo> querySearch(String info) {
//		System.out.println(info);

		List<String> dms = new ArrayList<String>();

		ResultSet rs = null;
		try {
			String sql = "SELECT dm FROM jjgk WHERE dm LIKE '%" + info + "%'";
			rs = statement.executeQuery(sql);
			while (rs.next()) {
				String dm = rs.getString("dm");
				dms.add(dm);
			}

			sql = "SELECT dm FROM jjgk WHERE jjmc LIKE '%" + info + "%'";
			rs = statement.executeQuery(sql);
			while (rs.next()) {
				String dm = rs.getString("dm");
				dms.add(dm);
			}

			sql = "SELECT dm FROM jjgk WHERE jjjc LIKE '%" + info + "%'";
			rs = statement.executeQuery(sql);
			while (rs.next()) {
				String dm = rs.getString("dm");
				dms.add(dm);
			}

			sql = "SELECT dm FROM jjgk WHERE pyjc LIKE '%" + info + "%'";
			rs = statement.executeQuery(sql);
			while (rs.next()) {
				String dm = rs.getString("dm");
				dms.add(dm);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < dms.size(); i++) {
			sb.append(dms.get(i));
			if (i != (dms.size() - 1)) {
				sb.append(",");
			}
		}

//		System.out.println(sb.toString());

		return queryJjBaseInfos(sb.toString());
	}

	private void initConnection() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(connUrl, username, password);
			statement = conn.createStatement();
//			System.out.println("connect successfully!");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void closeConnection() {
		if (conn != null) {
			try {
				conn.close();
				conn = null;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
