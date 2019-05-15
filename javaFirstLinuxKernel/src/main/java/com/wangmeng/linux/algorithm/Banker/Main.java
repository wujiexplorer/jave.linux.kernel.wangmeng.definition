package com.wangmeng.linux.algorithm.Banker;
import java.util.Scanner;

/*public class Main {

	public static void main(String[] args) {
		//BankerFrame bf = new BankerFrame();
		
		// TODO Auto-generated method stub
		Scanner input = new Scanner(System.in);
		int nSource[],oSource[];
		int sourceSize;
		System.out.print("������ռ����Դ�������");
		sourceSize = input.nextInt();
		nSource = new int[sourceSize];
		oSource = new int[sourceSize];
		Banker banker = new Banker(sourceSize,100);
		
		System.out.print("������cpu��ǰʣ����Դ\n");
		System.out.print("��" + sourceSize + "����Դ��");
		for(int j = 0;j < sourceSize;j ++)
		{
			oSource[j] = input.nextInt();
		}
		banker.initCpu(oSource);
		
		System.out.print("������Ҫ��ӵĽ�����:");
		int num = input.nextInt();
		for(int i = 0;i < num;i ++)
		{
			System.out.print("\n\n����ռ��" + sourceSize + "����Դ\n");
			System.out.print("���������ռ����Դ����ǰ��" + (i + 1) + "������)��");
			for(int j = 0;j < sourceSize;j ++)
			{
				oSource[j] = input.nextInt();
			}
			
			System.out.print("�����������Ҫ��Դ��");
			for(int j = 0;j < sourceSize;j ++)
			{
				nSource[j] = input.nextInt();
			}
			banker.addPcb(oSource, nSource);
		}
		System.out.print("\n\n***********\n" + banker.bankerControl() + "\n***********\n\n");
		
	}

}*/
