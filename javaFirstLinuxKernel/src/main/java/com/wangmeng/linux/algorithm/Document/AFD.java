package com.wangmeng.linux.algorithm.Document;
import java.io.*;
import java.util.Date;

public class AFD {
	String dirName;
	String path;
	File currentFile;
	
	public AFD(String path,String dirName){
		this.dirName = dirName;
		this.path = path + "//" + dirName;
		this.currentFile = null;
	}
	
	public boolean CreateFile(String name){
		File myFile = new File(path + "//" + name);
		if(myFile.exists())
			return false;
		else{
			try{
				myFile.createNewFile();
			}
			catch(Exception e){
				e.printStackTrace();
				return false;
			}
			return true;
		}
	}
	
	public boolean DeleteFile(String name){
		File myFile = new File(path + "//" + name);
		if(!myFile.exists())
			return false;
		else{
			try{
				myFile.delete();
			}
			catch(Exception e){
				e.printStackTrace();
				return false;
			}
			return true;
		}
	}
	
	public boolean OpenFile(String name){
		this.currentFile = new File(path + "//" + name);
		if(!this.currentFile.exists())
			return false;
		else{
			return true;
		}
	}
	
	public String getProperty(String cmd){
		if("length".compareTo(cmd) == 0){
			return "" + this.currentFile.length();
		}
		if("date".compareTo(cmd) == 0){
			return "" + new Date(this.currentFile.lastModified());
		}
		else
			return "�������";
	}
	
	public void closeFile(String name){
		this.currentFile = null;
	}
	
	public String ReadFile(String name){
		String result = " ";
		if(null == this.currentFile)
			return "文件未打开";
		if(!this.currentFile.exists())
			return "文件不存在";
		else{
			try{
				FileReader fileReader = new FileReader(path + "//" + name);
				BufferedReader buffer = new BufferedReader(fileReader);
				String str = null;
				do{
					str = buffer.readLine();
					if(null != str){
						result += str + "\n";
					}
				}while(null != str);
				buffer.close();
				fileReader.close();
				return result;
			}
			catch(Exception e){
				e.printStackTrace();
				return "文件异常";
			}
		}
	}
	
	public boolean WriteFile(String name,String str){
		String result = "";
		if(null == this.currentFile){
			System.out.println("文件未打开\n");
			return false;
		}
		else{
			try{
				FileWriter fileWriter = new FileWriter(path + "//" + name,true);
				fileWriter.write(str + "\n");
				fileWriter.flush();
				fileWriter.close();
				return true;
			}
			catch(Exception e){
				e.printStackTrace();
				return false;
			}
		}
	}
	
}
