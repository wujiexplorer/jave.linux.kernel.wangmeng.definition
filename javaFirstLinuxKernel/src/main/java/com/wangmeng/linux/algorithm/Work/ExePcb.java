package com.wangmeng.linux.algorithm.Work;
import java.util.ArrayList;
import java.util.List;


public class ExePcb {
	public List<PCB> waitList = new ArrayList<PCB>();
	public List<PCB> confuseList = new ArrayList<PCB>();
	public List<PCB> finishList = new ArrayList<PCB>();
	Boolean flag = false;
	
	public void initConfuseList(PCB p){
		for(int i = 0;i < confuseList.size() && flag == false;i ++){
			if(p.priority >= confuseList.get(i).priority)
			{
				confuseList.add(i, p);
				flag = true;
			}
		}
		if(flag == false)
			confuseList.add(p);
		else
			flag = false;
	}
	
	public void add2WaitPcb(PCB p){
		for(int i = 0;i < waitList.size() && flag == false;i ++){
			if(p.priority >= waitList.get(i).priority)
			{
				waitList.add(i, p);
				flag = true;
			}
		}
		if(flag == false)
			waitList.add(p);
		else
			flag = false;
	}
	
	public PCB getFirstPcb(){
		PCB p = null;
		if(waitList.size() == 0)
			return null;
		else{
			p = waitList.get(0);
			waitList.remove(0);
			return p;
		}	
	}
	
	public String Output(PCB p){
		String str = "";
		str += p.toString();
		for(int i = 0;i < waitList.size();i ++){
			str += waitList.get(i).toString();
		}
		for(int i = 0;i < confuseList.size();i ++){
			str += confuseList.get(i).toString();
		}
		for(int i = 0;i < finishList.size();i ++){
			str += finishList.get(i).toString();
		}
		return str;
	}
	
	public String progressPcb(CpuTime cpu){
		
		String str = "";
		for(int i = 0;i < confuseList.size();i ++){
			if(confuseList.get(i).arriveTime >= cpu.nowTime){
				this.add2WaitPcb(confuseList.get(i));
				confuseList.remove(i);
			}
		}
		
		PCB p = this.getFirstPcb();
		
		if(p == null){
			//System.out.print("**********\n");
			p = finishList.get(finishList.size() - 1);
			finishList.remove(finishList.size() - 1);
			str += Output(p);
			PCB p1 = new PCB();
			finishList.add(p);
			finishList.add(p1);
			return str;
		}
		else{
			if(p.arriveTime <= cpu.nowTime){
				
				//output run state
				//System.out.print("**********\n");
				p.state = 'r';
				str += Output(p);
				////
				
				if(p.exeTime - p.usedTime <= cpu.cpuTime){
					p.state = 'f';
					p.usedTime += cpu.cpuTime;
					cpu.run();
					finishList.add(p);
					return str;
				}
				else{
					p.state = 'w';
					p.priority -= 1;
					p.usedTime += cpu.cpuTime;
					cpu.run();
					this.add2WaitPcb(p);
					return str;
				}
			}
			else{
				confuseList.add(p);
				cpu.run();
				return str;
			}
		}
	}
	
	
}
