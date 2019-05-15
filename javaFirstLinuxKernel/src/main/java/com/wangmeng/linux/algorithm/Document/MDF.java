package com.wangmeng.linux.algorithm.Document;
import java.io.*;

public class MDF {
	String filePath;
	String name[];
	String userPath[];
	int userSize;
	
	public MDF(String filepath){
		filePath = filepath + "//UserList.txt";
		userSize = 0;
		name = new String[100];
		//���ļ�
		try{
			File fileC = new File(filePath);
			if(!fileC.exists())
				fileC.createNewFile();
			FileReader fileReader = new FileReader(filePath);
			BufferedReader buffer = new BufferedReader(fileReader);
			String str = null;
			do{
				str = buffer.readLine();
				if(null != str){
					name[userSize] = new String(str);
					userSize ++;
				}
			}while(null != str);
			buffer.close();
			fileReader.close();
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public boolean UserLogin(String filePath,String Name){
		for(int i = 0;i < userSize;i ++){
			if(Name.compareTo(name[i]) == 0){
				File myFile = new File(filePath + "//" + Name);
				try{
					if(!myFile.exists()){
						myFile.mkdir();
					}
				}
				catch(Exception e){
					e.printStackTrace();
				}
				return true;
			}
		}
		return false;
	}
		
	
	public boolean UserRegesiter(String Name){
		try{
			FileWriter fileWriter = new FileWriter(filePath,true);
			for(int i = 0;i < userSize;i ++){
				if(Name.compareTo(name[i]) == 0){
					return false;
				}
			}
			name[userSize] = new String(Name);
			userSize ++;
			fileWriter.write(Name + "\n");
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
