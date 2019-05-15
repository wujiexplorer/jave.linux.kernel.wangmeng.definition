package com.wangmeng.linux.algorithm.Banker;

public class Banker {
	Cpu cpu;
	Pcb pcb[];
	int sourceSize;
	int pcbSize;
	
	Banker(int sSize,int maxPSize)
	{
		sourceSize = sSize;
		pcbSize = 0;
		pcb = new Pcb[maxPSize];
	}
	
	public void initCpu(int source[])
	{
		cpu = new Cpu(sourceSize,source);
	}
	
	public void addPcb(int oSource[],int nSource[])
	{
		pcb[pcbSize] = new Pcb(sourceSize,oSource,nSource);
		pcbSize ++;
	}
	
	public Boolean PcbState()
	{
		for(int i = 0;i < pcbSize;i ++)
		{
			//System.out.print("++++++" + i + "***" + pcb[i].state + "\n\n");
			if(pcb[i].state == 'w')
			{
				//System.out.print("++++++" + i + "***" + pcb[i].state + "\n\n");
				return false;
			}
		}
		return true;
	}
	
	public String bankerControl()
	{
		String str = "��ȫ����:";
		Boolean flag;
		do
		{
			flag = false;
			int i;
			//System.out.print("fffffff\n\n");
			for(i = 0;i  < pcbSize && flag == false;i ++)
			{
				if(cpu.Compare(pcb[i].neededSource) && pcb[i].state == 'w')
				{
					pcb[i].state = 'r';
					cpu.Release(pcb[i].occupySource);
					pcb[i].state = 'f';
					str += (i + 1) + "   ";
					flag = true;
				}
			}
			if(!flag)
			{
				return "无安全序列可用！";
			}
			//System.out.print("fffffff" + PcbState() + "\n\n");
			
		}while(PcbState() == false);
		return str;
	}
}
