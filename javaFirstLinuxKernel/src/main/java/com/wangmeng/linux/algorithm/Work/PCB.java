package com.wangmeng.linux.algorithm.Work;

public class PCB {
	public String pcbName;
	public int priority;
	public int arriveTime;
	public int exeTime;
	public int usedTime;
	public char state;

	public String toString(){
		return "\n*名字:" + this.pcbName +
				"到达时间:" + this.arriveTime +
				"运行时间：" + this.exeTime + "\n" +
				"占用时间:" + this.usedTime +
				"PCB状态:" + this.state + "*\n";
	}
}
