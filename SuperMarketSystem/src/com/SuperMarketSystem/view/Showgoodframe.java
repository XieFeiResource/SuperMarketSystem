package com.SuperMarketSystem.view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import java.awt.Font;

public class Showgoodframe extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private Mainframe mainframe;
	private Object[][] data;
	private DefaultTableModel model;
	private JScrollPane scrollPane ;
	
	public JTable getTable() {
		return table;
	}


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Showgoodframe frame = new Showgoodframe(null);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	/**
	 * Create the frame.
	 */
	public Showgoodframe(Object[][] data) {
		this.data=data;
		setBounds(100, 100, 642, 377);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel label = new JLabel("商品展示");
		label.setFont(new Font("黑体", Font.PLAIN, 18));
		label.setBounds(256, 10, 93, 15);
		contentPane.add(label);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 36, 606, 215);
		contentPane.add(scrollPane);
		
		table = new JTable();
		String[] biaotou=new String[] {"商品编号", "商品名称", "商品价格", "商品库存", "商品类型", "商品图片", "商品描述"};
		model=new DefaultTableModel(data,biaotou);
		table.setModel(model);
		table.getColumnModel().getColumn(5).setPreferredWidth(102);
		table.getColumnModel().getColumn(6).setPreferredWidth(155);
		scrollPane.setViewportView(table);
	}
}
