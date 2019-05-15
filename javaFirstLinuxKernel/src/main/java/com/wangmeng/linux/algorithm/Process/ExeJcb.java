package com.wangmeng.linux.algorithm.Process;
import java.util.ArrayList;
import java.util.List;


public class ExeJcb {
	public List<JCB> waitList = new ArrayList<JCB>();
	public List<JCB> finishList = new ArrayList<JCB>();
	Boolean flag = false;
	
	public void add2WaitPcb(JCB j){
		for(int i = 0;i < waitList.size() && flag == false;i ++){
			if(j.confirmTime  <= waitList.get(i).confirmTime)
			{
				waitList.add(i, j);
				flag = true;
			}
		}
		if(flag == false)
			waitList.add(j);
		else
			flag = false;
	}
	
	public JCB getFirstJcb(){
		JCB p = null;
		if(waitList.size() == 0)
			return null;
		else{
			p = waitList.get(0);
			waitList.remove(0);
			return p;
		}	
	}
	
	public String Output(JCB J){
		String str = "";
		str += J.toString();
		//System.out.print(J);
		
		for(int i = 0;i < waitList.size();i ++){
			//System.out.print(waitList.get(i).toString());
			str += waitList.get(i).toString();
		}
		for(int i = 0;i < finishList.size();i ++){
			//System.out.print(finishList.get(i).toString());
			str += finishList.get(i).toString();
		}
		return str;
	}


	public String progressJcb(CpuTime cpu){
		JCB p = this.getFirstJcb();
		String str = "";

		if(p == null){
			str += "**********\n*当前时间:" + cpu.nowTime + "\n";
			p = finishList.get(finishList.size() - 1);
			finishList.remove(finishList.size() - 1);
			str += Output(p);
			JCB p1 = new JCB();
			finishList.add(p);
			finishList.add(p1);
			return str;
		}
		else{
			if(p.confirmTime <= cpu.nowTime){

				//output run state
				str += "**********\n*当前时间:" + cpu.nowTime + "\n";
				p.state = 'r';
				p.arriveTime = cpu.nowTime;
				str += Output(p);
				////

				cpu.run(p.exeTime);
				p.state = 'f';
				finishList.add(p);
				return str;
			}
			else {
				//System.out.print("**********\n*当前时间:" + cpu.nowTime);
				this.add2WaitPcb(p);
				cpu.run(1);
			}
			return str;
		}
	}

	public String progressJcbSJF(CpuTime cpu){
		JCB j = this.getFirstJcb();
		String str = "";

		if(j == null){
			str += "**********\n*当前时间:" + cpu.nowTime + "\n";
			j = finishList.get(finishList.size() - 1);
			finishList.remove(finishList.size() - 1);
			str += Output(j);
			JCB j1 = new JCB();
			finishList.add(j);
			finishList.add(j1);
			return str;
		}
		else{
			this.add2WaitPcb(j);
			JCB min = new JCB();
			min.exeTime = 65535;
			int mini = 0;
			Boolean flag = false;
			for(int i = 0;i < waitList.size() && waitList.get(i).confirmTime <= cpu.nowTime;i ++)
			{
				if(min.exeTime > waitList.get(i).exeTime)
				{
					min = waitList.get(i);
					mini = i;
					flag = true;
				}
			}
			if(flag)
			{
				waitList.remove(mini);
				str += "**********\n*当前时间:" + cpu.nowTime + "\n";
				min.state = 'r';
				min.arriveTime = cpu.nowTime;
				str += Output(min);
				////

				cpu.run(min.exeTime);
				min.state = 'f';
				finishList.add(min);
				return str;
			}
			else {
				//System.out.print("**********\n*当前时间:" + cpu.nowTime);
				//this.add2WaitPcb(p);
				cpu.run(1);
			}
			return str;
		}
	}

	public String progressJcbHRRN(CpuTime cpu){
		JCB j = this.getFirstJcb();
		String str = "";

		if(j == null){
			str += "**********\n*当前时间:" + cpu.nowTime + "\n";
			j = finishList.get(finishList.size() - 1);
			finishList.remove(finishList.size() - 1);
			str += Output(j);
			JCB j1 = new JCB();
			finishList.add(j);
			finishList.add(j1);
			return str;
		}
		else{
			this.add2WaitPcb(j);
			JCB min = new JCB();
			double minTime  = 65535;
			int mini = 0;
			Boolean flag = false;
			for(int i = 0;i < waitList.size() && waitList.get(i).confirmTime <= cpu.nowTime;i ++)
			{
				double time = (cpu.nowTime - waitList.get(i).confirmTime + waitList.get(i).exeTime)
						/ (double) waitList.get(i).exeTime;
				if(minTime > time)
				{
					min = waitList.get(i);
					mini = i;
					flag = true;
				}
			}
			if(flag)
			{
				waitList.remove(mini);
				str += "**********\n*当前时间:" + cpu.nowTime + "\n";
				min.state = 'r';
				min.arriveTime = cpu.nowTime;
				str += Output(min);
				////

				cpu.run(min.exeTime);
				min.state = 'f';
				finishList.add(min);
				return str;
			}
			else {

				//this.add2WaitPcb(p);
				cpu.run(1);
			}
			return str;
		}
	}



}
