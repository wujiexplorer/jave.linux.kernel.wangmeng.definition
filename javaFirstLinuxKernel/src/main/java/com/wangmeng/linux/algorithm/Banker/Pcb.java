package com.wangmeng.linux.algorithm.Banker;

public class Pcb {
	public int occupySource[];
	public int neededSource[];
	int size;
	char state;
	
	Pcb(int size,int oSource[],int nSource[])
	{
		this.size = size;
		occupySource = new int[size];
		neededSource = new int[size];
		state = 'w';
		for(int i = 0;i < size;i ++)
		{
			occupySource[i] = oSource[i];
			neededSource[i] = nSource[i];
		}
	}	
}
