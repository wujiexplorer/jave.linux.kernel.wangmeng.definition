package com.wangmeng.linux.algorithm.Banker;

import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Scanner;

import javax.swing.*;

public class BankerDlg extends JFrame{
	JLabel initLabel,cpuLabel;
	private JTextField initField,inputField[],occupyField[],neededField[];
	private JTextArea resultArea;
	private JButton initButton,cpuButton,addButton,exeButton;
	@SuppressWarnings("rawtypes")
	private DefaultListModel<String> listModel;
	private JList<String> list;
	Box cpuBox,occupyBox,neededBox;
	int nSource[],oSource[];
	int sourceSize;
	Banker banker;
	
	public BankerDlg()
	{
		super("银行家算法");
		Container con = getContentPane();
		con.setLayout(new FlowLayout());
		list = new JList<String>();
		listModel = new DefaultListModel<String>();
		listModel.addElement("进程列表");
		list.setModel(listModel);
		list.setSize(10,10);
		
		initButton = new JButton("确定");
		cpuButton = new JButton("确定");
		addButton = new JButton("添加");
		exeButton = new JButton("运行");
		
		initLabel = new JLabel("占用资源类别数");
		initField = new JTextField(5);
		con.add(initLabel);
		con.add(initField);
		con.add(initButton);
		
		initButton.addActionListener(
				new ActionListener()
				{
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
						Integer num = new Integer(initField.getText());
						sourceSize = num;
						nSource = new int[sourceSize];
						oSource = new int[sourceSize];
						banker = new Banker(sourceSize,100);
						con.remove(initLabel);
						con.remove(initField);
						con.remove(initButton);
						
						cpuLabel = new JLabel("cpu当前剩余资源");
						cpuBox = Box.createHorizontalBox();
						inputField = new JTextField [sourceSize];
						for(int i = 0;i < sourceSize;i ++){
							inputField[i] = new JTextField(5);
							cpuBox.add(inputField[i]);
						}
						//cpuButton = new JButton("ȷ��");
						con.add(cpuLabel);
						con.add(cpuBox);
						con.add(cpuButton);
						
						setSize(400,300);
						setVisible(true);
					}
				});
		
		cpuButton.addActionListener(
				new ActionListener()
				{
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
						String str = "cpu��Դ ";
						Integer num;
						for(int j = 0;j < sourceSize;j ++)
						{
							num = Integer.valueOf(inputField[j].getText());
							oSource[j] = num;
							str += " " + num;
						}
						listModel.addElement(str);
						banker.initCpu(oSource);
						con.remove(cpuLabel);
						con.remove(cpuBox);
						con.remove(cpuButton);
						
						occupyBox = Box.createHorizontalBox();
						JLabel occupyLabel = new JLabel("进程占用资源");
						occupyBox.add(occupyLabel);
						occupyField = new JTextField [sourceSize];
						for(int i = 0;i < sourceSize;i ++){
							occupyField[i] = new JTextField(5);
							occupyBox.add(occupyField[i]);
						}
						con.add(occupyBox);
						
						JLabel neededLabel = new JLabel("进程需要资源");
						neededBox = Box.createHorizontalBox();
						neededBox.add(neededLabel);
						neededField = new JTextField [sourceSize];
						for(int i = 0;i < sourceSize;i ++){
							neededField[i] = new JTextField(5);
							neededBox.add(neededField[i]);
						}
						
						Box buttonBox = Box.createHorizontalBox();
						resultArea = new JTextArea("运行结果",10,10);
						con.add(neededBox);
						buttonBox.add(addButton);
						buttonBox.add(exeButton);
						con.add(buttonBox);
						Box areaBox = Box.createHorizontalBox();
						areaBox.add(list);
						//areaBox.add(new JLabel("���"));
						areaBox.add(resultArea);
						con.add(areaBox);
						con.repaint();
						setSize(400,600);
						setVisible(true);
					}
				});
		
		addButton.addActionListener(
				new ActionListener()
				{
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
						Integer num1,num2;
						String str1 = "占用资源",str2 = "需要资源";
						for(int j = 0;j < sourceSize;j ++)
						{
							num1 = Integer.valueOf(occupyField[j].getText());
							num2 = Integer.valueOf(neededField[j].getText());
							str1 += " " + num1;
							str2 += " " + num2;
							oSource[j] = num1;
							nSource[j] = num2;
						}
						str1 += "\n";
						str2 += "\n";		
						listModel.addElement("进程" + banker.pcbSize + str1 + str2);
						banker.addPcb(oSource, nSource);
					}
				});
		
		exeButton.addActionListener(
				new ActionListener()
				{
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
						resultArea.setText(banker.bankerControl());			
					}
				});
		/*
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
		System.out.print("\n\n***********\n" + banker.bankerControl() + "\n***********\n\n");*/
		
		
		setSize(400,300);
		setVisible(true);
		
	}
}
