package com.wangmeng.linux.algorithm.Banker;

public class Cpu {
	public int cpuSource[];
	int size;
	
	Cpu(int size,int source[])
	{
		this.size = size;
		cpuSource = new int[size];
		for(int i = 0;i < size;i ++)
		{
			cpuSource[i] = source[i];
		}
	}
	
	public Boolean Compare(int source[])
	{
		for(int i = 0;i < size;i ++)
		{
			if(source[i] > cpuSource[i])
				return false;
			else
				;
		}
		return true;
	}
	
	public void Release(int source[])
	{
		for(int i = 0;i < size;i ++)
		{
			cpuSource[i] += source[i];
		}
	}
	
}
