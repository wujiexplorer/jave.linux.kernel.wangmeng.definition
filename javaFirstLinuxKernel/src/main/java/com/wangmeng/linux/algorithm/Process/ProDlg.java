package com.wangmeng.linux.algorithm.Process;

import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import java.util.Scanner;

import javax.swing.*;




public class ProDlg extends JFrame{
	private JLabel nameLabel,admitLabel,exeLabel,sourceLabel;
	private JTextField nameField,admitField,exeField,sourceField;
	private JButton exeButton,addButton,clearButton;
	private JTextArea processArea,resultArea;
	private JList modeList;
	JScrollPane jsp,jsp2;    //滚动控件
	Box nameBox,admitBox,exeBox,sourceBox,buttonBox,processBox,resultBox;
	String mode[] = {"先来先服务","短作业优先","高响应比优先"};
	CpuTime cpu = new CpuTime();
	ExeJcb jp = new ExeJcb();
	JCB pcb[] = new JCB[100];
	int size = 0;

	public ProDlg()
	{
		super("作业调度");
		Container con = getContentPane();
		con.setLayout(new FlowLayout());

		cpu.initCpu(0);


		modeList = new JList(mode);
		modeList.setSelectedIndex(0);

		nameLabel = new JLabel("进程名");
		admitLabel = new JLabel("提交时间");
		exeLabel = new JLabel("运行时间");
		sourceLabel = new JLabel("占用资源");

		addButton = new JButton("添加");
		exeButton = new JButton("运行");
		clearButton = new JButton("清空进程列表");

		nameField = new JTextField(10);
		admitField = new JTextField(10);
		exeField = new JTextField(10);
		sourceField = new JTextField(10);

		processArea = new JTextArea(10,30);
		resultArea = new JTextArea(10,30);
		jsp2 = new JScrollPane(processArea);
		jsp = new JScrollPane(resultArea);

		nameBox = Box.createHorizontalBox();
		nameBox.add(nameLabel);
		nameBox.add(nameField);

		admitBox = Box.createHorizontalBox();
		admitBox.add(admitLabel);
		admitBox.add(admitField);

		exeBox = Box.createHorizontalBox();
		exeBox.add(exeLabel);
		exeBox.add(exeField);

		sourceBox = Box.createHorizontalBox();
		sourceBox.add(sourceLabel);
		sourceBox.add(sourceField);

		buttonBox = Box.createHorizontalBox();
		buttonBox.add(addButton);
		buttonBox.add(exeButton);
		buttonBox.add(clearButton);

		processBox = Box.createHorizontalBox();
		processBox.add(new JLabel("进程列表"));
		processBox.add(jsp2);

		resultBox = Box.createHorizontalBox();
		resultBox.add(new JLabel("调度结果"));
		resultBox.add(jsp);

		con.add(nameBox);
		con.add(admitBox);
		con.add(exeBox);
		con.add(sourceBox);
		con.add(modeList);
		con.add(buttonBox);
		con.add(processBox);
		con.add(resultBox);

		addButton.addActionListener(
				new ActionListener()
				{
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
						pcb[size] = new JCB();
						pcb[size].jcbName = nameField.getText();
						pcb[size].confirmTime  = new Integer(admitField.getText());
						pcb[size].exeTime = new Integer(exeField.getText());;
						pcb[size].needSource = new Integer(sourceField.getText());;
						pcb[size].arriveTime = -1;
						pcb[size].state = 'w';
						jp.add2WaitPcb(pcb[size]);
						processArea.append("名字:" + nameField.getText()
								+ "提交时间：" + admitField.getText()
								+ "运行时间：" + exeField.getText()
								+ "占用资源：" + sourceField.getText()
								+ "\n");
						size ++;
					}
				});

		exeButton.addActionListener(
				new ActionListener()
				{
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
						String str = "";
						if(size != 0 && modeList.getSelectedValue().equals(mode[0]))
							while(jp.finishList.size() != size + 1)
								str += "*" + jp.progressJcb(cpu);
						if(size != 0 && modeList.getSelectedValue().equals(mode[1]))
							while(jp.finishList.size() != size + 1)
								str += "*" + jp.progressJcbSJF(cpu);
						if(size != 0 && modeList.getSelectedValue().equals(mode[2]))
							while(jp.finishList.size() != size + 1)
								str += "*" + jp.progressJcbHRRN(cpu);
						resultArea.setText(str);
						JCB jcp1 = new JCB();
						double zhouzhuanTime = 0;
						double daiquanTime = 0;
						for(int i = 0;i < size;i ++){
							jcp1 = jp.finishList.get(i);
							zhouzhuanTime += jcp1.arriveTime + jcp1.exeTime - jcp1.confirmTime;
							daiquanTime += zhouzhuanTime / jcp1.exeTime;
						}
						resultArea.append("平均周转时间 = " + zhouzhuanTime / size
								+ "平均带权周转时间 = " + daiquanTime / size);

						cpu.initCpu(0);
						size = 0;
						processArea.removeAll();
						processArea.setText("");
					}
				});

		clearButton.addActionListener(
				new ActionListener()
				{
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
						cpu.initCpu(0);
						size = 0;
						processArea.removeAll();
						processArea.setText("");
					}
				});

		setSize(500,600);
		setVisible(true);
	}
}
