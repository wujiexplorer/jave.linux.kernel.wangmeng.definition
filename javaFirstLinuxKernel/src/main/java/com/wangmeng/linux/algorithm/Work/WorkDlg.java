package com.wangmeng.linux.algorithm.Work;

import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import java.util.Scanner;

import javax.swing.*;



public class WorkDlg extends JFrame{
	private JLabel nameLabel,admitLabel,exeLabel,priorityLabel;
	private JTextField nameField,admitField,exeField,priorityField;
	private JButton exeButton,addButton,clearButton;
	private JTextArea processArea,resultArea;
	JScrollPane jsp,jsp2;    //滚动控件
	Box nameBox,admitBox,exeBox,priorityBox,buttonBox,processBox,resultBox;
	CpuTime cpu = new CpuTime();
	ExePcb ep = new ExePcb();
	PCB pcb[] = new PCB[100];
	int size = 0;

	public WorkDlg()
	{
		super("进程调度");
		Container con = getContentPane();
		con.setLayout(new FlowLayout());

		cpu.initCpu(0,1);

		nameLabel = new JLabel("进程名");
		admitLabel = new JLabel("提交时间");
		exeLabel = new JLabel("运行时间");
		priorityLabel = new JLabel("优先级");

		addButton = new JButton("添加");
		exeButton = new JButton("运行");
		clearButton = new JButton("清空进程列表");

		nameField = new JTextField(10);
		admitField = new JTextField(10);
		exeField = new JTextField(10);
		priorityField = new JTextField(10);

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

		priorityBox = Box.createHorizontalBox();
		priorityBox.add(priorityLabel);
		priorityBox.add(priorityField);

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
		con.add(priorityBox);
		con.add(buttonBox);
		con.add(processBox);
		con.add(resultBox);

		addButton.addActionListener(
				new ActionListener()
				{
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
						pcb[size] = new PCB();
						pcb[size].pcbName = nameField.getText();
						pcb[size].priority = new Integer(priorityField.getText());
						pcb[size].arriveTime = new Integer(admitField.getText());
						pcb[size].exeTime = new Integer(exeField.getText());
						pcb[size].state = 'w';
						ep.add2WaitPcb(pcb[size]);
						size ++;
						processArea.append("名字:" + nameField.getText()
								+ "提交时间：" + admitField.getText()
								+ "运行时间：" + exeField.getText()
								+ "优先：" + priorityField.getText()
								+ "数量" + size
								+ "\n");
					}
				});

		exeButton.addActionListener(
				new ActionListener()
				{
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
						String str = "";
						resultArea.setText(str);

						while(ep.finishList.size() != size + 1){
							str += "+++++++\n" + ep.progressPcb(cpu);
							//resultArea.append(ep.progressPcb(cpu));
						}
						resultArea.setText(str);
						cpu.initCpu(0,1);
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
						cpu.initCpu(0,1);
						size = 0;
						processArea.removeAll();
						processArea.setText("");
					}
				});

		setSize(500,600);
		setVisible(true);
	}
}
