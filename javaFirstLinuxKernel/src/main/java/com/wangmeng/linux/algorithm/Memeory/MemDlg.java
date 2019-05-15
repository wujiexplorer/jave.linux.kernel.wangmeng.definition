package com.wangmeng.linux.algorithm.Memeory;

import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import java.util.Scanner;

import javax.swing.*;



public class MemDlg extends JFrame{
	private JLabel addrLabel,nameLabel,sizeLabel;
	private JTextField addrField,nameField,sizeField;
	private JButton addrButton,allocButton,deleteButton;
	private JTextArea resultArea;
	JScrollPane jsp;    //�����ؼ�  
	Box addBox,nameBox,sizeBox;
	Memeory mem;
	
	public MemDlg() {
		super("内存管理");
		Container con = getContentPane();
		con.setLayout(new FlowLayout());

		mem = new Memeory();
		addrLabel = new JLabel("最大地址");
		nameLabel = new JLabel("进程名");
		sizeLabel = new JLabel("进程占用内存");
		addrButton = new JButton("确定");
		allocButton = new JButton("分配");
		deleteButton = new JButton("回收");
		addrField = new JTextField(10);
		nameField = new JTextField(10);
		sizeField = new JTextField(10);
		resultArea = new JTextArea(10, 30);
		jsp = new JScrollPane(resultArea);

		addBox = Box.createHorizontalBox();
		addBox.add(addrLabel);
		addBox.add(addrField);

		nameBox = Box.createHorizontalBox();
		nameBox.add(nameLabel);
		nameBox.add(nameField);

		sizeBox = Box.createHorizontalBox();
		sizeBox.add(sizeLabel);
		sizeBox.add(sizeField);

		con.add(addBox);
		con.add(addrButton);

		addrButton.addActionListener(
				new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
						Integer num;
						num = new Integer(addrField.getText());
						mem.InitMem(num);
						con.remove(addBox);
						con.remove(addrButton);

						con.add(nameBox);
						con.add(sizeBox);
						con.add(allocButton);
						con.add(deleteButton);
						con.add(jsp);
						con.repaint();
						setSize(400, 300);
						setVisible(true);
					}
				});

		allocButton.addActionListener(
				new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
						Integer size = new Integer(sizeField.getText());
						if (mem.MemAlloc(size, nameField.getText()))
							resultArea.setText(mem.toString());
						else
							JOptionPane.showMessageDialog(null, "分配失败", "注意", JOptionPane.WARNING_MESSAGE);
					}
				});

		deleteButton.addActionListener(
				new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
						Integer num;
						num = new Integer(addrField.getText());
						if (mem.MemDelete(nameField.getText()))
							resultArea.setText(mem.toString());
						else
							JOptionPane.showMessageDialog(null, "回收失败", "注意", JOptionPane.WARNING_MESSAGE);
					}
				});

		setSize(400, 300);
		setVisible(true);
	}

}
