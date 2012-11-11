package com.chenjun.utils;

import java.io.File;

import com.chenjun.network.HttpDownloader;

import android.os.Environment;

public class FileUtils {
	public static File getReportFile(){
		File destPath = new File(Environment.getExternalStorageDirectory(),
				HttpDownloader.CACHE_PATH);

		File destFile = new File(destPath, HttpDownloader.REPORT_NAME);
		
		return destFile;
	}
}
