package com.wangmeng.linux.algorithm.Document;
import java.io.*;

public class UFD {
	String path;
	String userName;
	int fileNum;
	String fileName[];
	long fileLength[];
	int fileSize;
	
	public UFD(String path,String userName){
		this.path = path;
		this.userName = userName;
		fileSize = 0;
		fileName = new String [100];
		fileLength = new long [100];
		File myFile = new File(path + "//" + userName);
		File[] files=myFile.listFiles();
		fileNum = files.length;
        for(int i=0;i<files.length;i++)
        {
            if(files[i].isFile()){
                //System.out.print(" �ļ�:"+files[i].getName()+"\n");
                fileName[fileSize] = files[i].getName();
                fileLength[fileSize] = files[i].length();
                fileSize ++;
            }
        }
	}
}
