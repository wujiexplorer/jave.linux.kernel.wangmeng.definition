package com.wangmeng.linux.algorithm;

import com.wangmeng.linux.algorithm.Banker.BankerDlg;
import com.wangmeng.linux.algorithm.Document.DocumentDlg;
import com.wangmeng.linux.algorithm.Memeory.MemDlg;
import com.wangmeng.linux.algorithm.Process.ProDlg;
import com.wangmeng.linux.algorithm.Work.WorkDlg;

import java.awt.*;
import java.sql.*;
import java.awt.event.*;
import javax.swing.*;


public class Menu extends JFrame {

	int size = 0;
	int i = 0;
	int j = 0;
	
	public Menu(){
		super("菜单界面。。。。");
		JMenuBar bar = new JMenuBar();
		setJMenuBar(bar);
		JMenu fileMenu = new JMenu("实验菜单");
		JMenuItem workItem = new JMenuItem("进程调度");
		JMenuItem processItem = new JMenuItem("作业调度");
		JMenuItem memItem = new JMenuItem("内存分配");
		JMenuItem docItem = new JMenuItem("文件管理");
		JMenuItem bankerItem = new JMenuItem("银行家算法");
		fileMenu.add(workItem);
		fileMenu.add(processItem);
		fileMenu.add(memItem);
		fileMenu.add(docItem);
		fileMenu.add(bankerItem);
		bar.add(fileMenu);
		
		bankerItem.addActionListener(
				new ActionListener(){
					public void actionPerformed(ActionEvent e) {
						BankerDlg a = new BankerDlg();
					}	
				});
		
		memItem.addActionListener(
				new ActionListener(){
					public void actionPerformed(ActionEvent e) {
						MemDlg mDlg= new MemDlg();
					}	
				});
		
		docItem.addActionListener(
				new ActionListener(){
					public void actionPerformed(ActionEvent e) {
						DocumentDlg dDlg= new DocumentDlg();
					}	
				});
		
		
		processItem.addActionListener(
				new ActionListener(){
					public void actionPerformed(ActionEvent e) {
						ProDlg proDlg = new ProDlg();
					}	
				});
		
		workItem.addActionListener(
				new ActionListener(){
					public void actionPerformed(ActionEvent e) {
						WorkDlg workDlg = new WorkDlg();
					}	
				});
		
		setSize(400,300);
		setVisible(true);
	}
}