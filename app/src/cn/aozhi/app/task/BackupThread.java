package cn.aozhi.app.task;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;

public class BackupThread implements Runnable{
	
	private File srcFile;
	private File destFile;
	
	public BackupThread(File srcFile,File destFile){
		this.srcFile = srcFile;
		this.destFile = destFile;
	}
	
	@Override
	public void run() {
		try {
			FileUtils.copyFile(srcFile, destFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
}
