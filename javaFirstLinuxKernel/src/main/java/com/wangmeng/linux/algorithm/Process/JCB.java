package com.wangmeng.linux.algorithm.Process;

public class JCB {
	public String jcbName;
	public int confirmTime;
	public int exeTime;
	public int needSource;
	public int arriveTime;
	public char state;

	public String toString(){
		int zhouzhuanTime = 0;
		double daiquanTime = 0;
		if(this.arriveTime != -1)
		{
			zhouzhuanTime = arriveTime + exeTime - confirmTime;
		}
		if(this.arriveTime != -1)
		{
			daiquanTime = zhouzhuanTime / (double)exeTime;
		}
		return "名字:" + this.jcbName +
				"提交时间:" + this.confirmTime +
				"到达时间:" + this.arriveTime + "\n" +
				"运行时间：" + this.exeTime +
				"占用资源:" + this.needSource +
				"周转时间：" + zhouzhuanTime + "\n" +
				"带权周转时间:" + daiquanTime +
				"PCB状态:" + this.state + "*\n\n";
	}
}
