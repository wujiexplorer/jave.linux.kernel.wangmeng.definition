package com.wangmeng.linux.algorithm.Memeory;

public class MemPiece {
	public int MemStart;
	public int MemSize;
	public String PieceNum;
	
	public void InitMemPiece(int memstart,int memsize,String PieceNum){
		this.MemStart = memstart;
		this.MemSize = memsize;
		this.PieceNum = PieceNum;
	}
}
