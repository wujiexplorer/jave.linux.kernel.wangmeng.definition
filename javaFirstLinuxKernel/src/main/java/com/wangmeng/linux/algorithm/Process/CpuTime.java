package com.wangmeng.linux.algorithm.Process;

public class CpuTime {
	public int nowTime;//��ǰʱ��
	
	public void run(int runTime){
		nowTime += runTime;
	}
	public void initCpu(int NT){
		nowTime = NT;
	}
}
