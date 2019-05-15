package com.wangmeng.linux.algorithm.Memeory;
import java.util.ArrayList;
import java.util.List;


public class Memeory {
	public int MaxAddr;
	public List<MemPiece> Mem = new ArrayList<MemPiece>();
	public List<MemPiece> AviMem = new ArrayList<MemPiece>();

	public void InitMem(int MaxAddr){
		this.MaxAddr = MaxAddr;
	}
	
	public Boolean MemAlloc(int size,String num){
		int i;
		int NowAddr = 0;
		int NowSize = 0;
		MemPiece m;
		for(i = 0;i < Mem.size();i ++){
			NowSize = Mem.get(i).MemStart - NowAddr;
			if(size <= NowSize){
				m = new MemPiece();
				m.InitMemPiece(NowAddr, size,num);
				Mem.add(i,m);
				return true;
			}
			else{
				NowAddr = Mem.get(i).MemStart + Mem.get(i).MemSize;
			}
		}
		
		NowSize = MaxAddr - NowAddr;
		if(size <= NowSize){
			m = new MemPiece();
			m.InitMemPiece(NowAddr, size,num);
			Mem.add(m);
			return true;
		}
		return false;
	}
	
	public Boolean MemDelete(String num){
		for(int i = 0;i < Mem.size();i ++){
			if(Mem.get(i).PieceNum.compareTo(num) == 0){
				Mem.remove(i);
				return true;
			}
		}
		return false;
	}
	
	public Boolean MemSort()
	{
		for(int i = 0;i < Mem.size();i ++)
			for(int j = 0;i < Mem.size() - i - 1;j ++)
			{
				if(Mem.get(j).MemStart > Mem.get(j + 1).MemStart)
				{
					int temp;
					temp = Mem.get(j + 1).MemStart;
					Mem.get(j + 1).MemStart = Mem.get(j).MemStart;
					Mem.get(j).MemStart = temp;
				}
			}
		return true;
	}

	public String toString(){
		String outStr = new String();
		outStr += "内存占用情况:\n";
		for(int i = 0;i < Mem.size();i ++){
			outStr += "名为" + Mem.get(i).PieceNum + "的内存起始地址:";
			outStr += Mem.get(i).MemStart;
			outStr += "     占用内存大小:";
			outStr += Mem.get(i).MemSize;
			outStr += "\n";
		}
		return outStr;
	}

}
