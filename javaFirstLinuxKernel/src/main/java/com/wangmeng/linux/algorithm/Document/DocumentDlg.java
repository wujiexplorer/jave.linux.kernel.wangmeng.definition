package com.wangmeng.linux.algorithm.Document;

import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Scanner;

import javax.swing.*;


public class DocumentDlg extends JFrame{
	JLabel nameLabel,fileLabel;
	private JTextField nameField,fileField;
	private JTextArea inputArea,readArea;
	private JButton loginButton,regButton,createButton,deleteButton,openButton,closeButton,writeButton,readButton;
	private DefaultListModel<String> listModel;
	JList<String> fileList;
	JScrollPane jsp;    //???????  
	String name;
	Box buttonBox,areaBox;
	String path ="src";
	MDF u=new MDF(path);
	UFD ufd;
	AFD afd;

	public DocumentDlg()
	{
		super("文件管理");
		Container con = getContentPane();
		con.setLayout(new FlowLayout());

		fileList = new JList();
		fileList.setVisibleRowCount(3);//默认显示行数
		jsp = new JScrollPane(fileList);
		createButton = new JButton("创建");
		deleteButton = new JButton("删除");
		openButton = new JButton("打开");
		closeButton = new JButton("关闭");
		writeButton = new JButton("写入");
		readButton = new JButton("读出");

		nameLabel = new JLabel("用户名：");
		con.add(nameLabel);
		nameField = new JTextField(5);
		loginButton = new JButton("登录");
		regButton = new JButton("注册");
		con.add(nameField);
		con.add(loginButton);
		con.add(regButton);

		loginButton.addActionListener(
				new ActionListener()
				{
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
						if(true == u.UserLogin(path,nameField.getText())){
							System.out.print("登陆成功\n");
							name = nameField.getText();
							ufd = new UFD(path,nameField.getText());
							listModel = new DefaultListModel<String>();
							for(int i =0;i < ufd.fileNum;i ++){
								listModel.addElement(ufd.fileName[i]);
							}
							fileList.setModel(listModel);

							afd = new AFD(path,nameField.getText());
							con.remove(nameLabel);
							con.remove(nameField);
							con.remove(loginButton);
							con.remove(regButton);

							con.add(new JLabel("创建文件名:"));
							fileField = new JTextField(5);
							con.add(fileField);
							con.add(jsp);
							buttonBox = Box.createHorizontalBox();
							buttonBox.add(createButton);
							buttonBox.add(deleteButton);
							buttonBox.add(openButton);
							buttonBox.add(closeButton);
							buttonBox.add(writeButton);
							buttonBox.add(readButton);
							con.add(buttonBox);

							areaBox = Box.createHorizontalBox();
							areaBox.add(new JLabel("输入框"));
							inputArea = new JTextArea(10,10);
							readArea = new JTextArea(10,10);
							areaBox.add(inputArea);
							areaBox.add(new JLabel("读出框"));
							areaBox.add(readArea);
							con.add(areaBox);

							con.repaint();

							setSize(400,300);
							setVisible(true);
						}
						else{
							JOptionPane.showMessageDialog(null, "登录失败", "注意",JOptionPane.WARNING_MESSAGE);
						}

					}
				});

		regButton.addActionListener(
				new ActionListener()
				{
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
						if(u.UserRegesiter(nameField.getText())){
							JOptionPane.showMessageDialog(null, "注册成功", "注意",JOptionPane.WARNING_MESSAGE);
						}
						else{
							JOptionPane.showMessageDialog(null, "注册失败", "注意",JOptionPane.WARNING_MESSAGE);
						}

					}
				});

		createButton.addActionListener(
				new ActionListener()
				{
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
						if(afd.CreateFile(fileField.getText() + ".txt"))
						{
							listModel.addElement(fileField.getText() + ".txt");
							JOptionPane.showMessageDialog(null, "创建成功", "注意",JOptionPane.WARNING_MESSAGE);
						}
						else
							JOptionPane.showMessageDialog(null, "创建失败", "注意",JOptionPane.WARNING_MESSAGE);

					}
				});

		deleteButton.addActionListener(
				new ActionListener()
				{
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
						if(afd.DeleteFile(fileList.getSelectedValue()))
						{
							listModel.removeElement(fileList.getSelectedValue());
							JOptionPane.showMessageDialog(null, "删除成功", "注意",JOptionPane.WARNING_MESSAGE);
						}
						else
							JOptionPane.showMessageDialog(null, "删除失败", "注意",JOptionPane.WARNING_MESSAGE);

					}
				});

		openButton.addActionListener(
				new ActionListener()
				{
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
						if(afd.OpenFile(fileList.getSelectedValue()))
							JOptionPane.showMessageDialog(null, "打开成功", "注意",JOptionPane.WARNING_MESSAGE);
						else
							JOptionPane.showMessageDialog(null, "打开失败", "注意",JOptionPane.WARNING_MESSAGE);

					}
				});

		closeButton.addActionListener(
				new ActionListener()
				{
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
						afd.closeFile(fileList.getSelectedValue());
						JOptionPane.showMessageDialog(null, "关闭成功", "注意",JOptionPane.WARNING_MESSAGE);
					}
				});

		writeButton.addActionListener(
				new ActionListener()
				{
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
						if(afd.WriteFile(fileList.getSelectedValue(),inputArea.getText()))
							JOptionPane.showMessageDialog(null, "写入成功", "注意",JOptionPane.WARNING_MESSAGE);
						else
							JOptionPane.showMessageDialog(null, "写入失败", "注意",JOptionPane.WARNING_MESSAGE);

					}
				});

		readButton.addActionListener(
				new ActionListener()
				{
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
						readArea.setText(afd.ReadFile(fileList.getSelectedValue()));

					}
				});


		setSize(400,300);
		setVisible(true);

	}
}
