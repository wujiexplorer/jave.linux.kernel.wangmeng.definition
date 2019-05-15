package com.wangmeng.linux.algorithm.Work;

public class CpuTime {
	public int nowTime;//��ǰʱ��
	public int cpuTime;//ʱ��Ƭ
	
	public void run(){
		nowTime += cpuTime;
	}
	public void initCpu(int NT,int CT){
		nowTime = NT;
		cpuTime = CT;
	}
}
