package com.chenjun.activities;

import android.content.Intent;

/**
 * 能进入查看某个具体基金信息的父类Activity必须实现此接口
 * 目前，实现这个接口的有两个父类Activity，即“基金排名”Activity和“搜索基金”Activity。
 * @author zet
 *
 */
public interface GoToFundInfoActivityGroup {
	/**
	 * 每个能查询基金具体信息（能跳转到FundInfoActivityGroup这个Activity）的父类Activity都要实现这个方法。
	 * 以避免查看的这个基金不存在这种情况，即基金代码和基金简称是可靠的，客户端才能用这个可靠的代码去请求基金的具体信息
	 * 
	 * @param intent
	 */
	public abstract void handle(Intent intent);
}
