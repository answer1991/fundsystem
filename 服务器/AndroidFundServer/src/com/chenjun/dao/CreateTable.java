package com.chenjun.dao;

import org.hibernate.cfg.Configuration;
import org.hibernate.tool.hbm2ddl.SchemaExport;

public class CreateTable {
	public static void main(String[] args) {
		SchemaExport export = new SchemaExport(new Configuration().configure());
		export.create(true, false);
	}
}
