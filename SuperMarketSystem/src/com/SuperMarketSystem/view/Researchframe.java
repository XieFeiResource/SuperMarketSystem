package com.SuperMarketSystem.view;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import com.SuperMarketSystem.bean.Good;
import com.SuperMarketSystem.dbuntils.JDBCTools;
import com.SuperMarketSystem.handler.Operatergoods;

public class Researchframe extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;//商品编号
	private JTextField textField_2;//商品名称
	private JTextField textField_3;//商品价格
	private JTextField textField_4;//商品库存
	private JTextField textField_5;//商品类型
	private Operatergoods operatergoods;
	private JLabel lblNewLabel;//图片
	private JTextArea textArea;//商品描述
	private Object[][] data;//商品描述
	public JTextField getTextField() {
		return textField;
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Researchframe frame = new Researchframe(null);
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
	public Researchframe(Operatergoods operatergoods) {
		this.operatergoods=operatergoods;
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		textField = new JTextField();
		textField.setBounds(132, 25, 170, 21);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JLabel label = new JLabel("请输入商品名");
		label.setFont(new Font("黑体", Font.PLAIN, 17));
		label.setBounds(20, 28, 102, 15);
		contentPane.add(label);
		
		JButton button = new JButton("查询");
		button.setFont(new Font("黑体", Font.PLAIN, 18));
		button.setBounds(316, 24, 93, 23);
		button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String sql="select * from good where gname=?;";
				String name=textField.getText();
				List<Good> list = operatergoods.queryAllgood(sql,name);
				data = operatergoods.Createdata(list);
				if(data.length<1) {
					JOptionPane.showMessageDialog(Researchframe.this, "该商品不存在！", "温馨提示", JOptionPane.ERROR_MESSAGE);
				}else {
				lblNewLabel.setIcon(new ImageIcon((String) data[0][5]));
				textField_1.setText(data[0][0].toString());
				textField_2.setText(data[0][1].toString());
				textField_3.setText(data[0][2].toString());
				textField_4.setText(data[0][3].toString());
				textField_5.setText(data[0][4].toString());
				textArea.setText(data[0][6].toString());
				}
			}
		});
		contentPane.add(button);
		
		JLabel label_1 = new JLabel("商品图片");
		label_1.setFont(new Font("黑体", Font.PLAIN, 12));
		label_1.setBounds(20, 76, 54, 15);
		contentPane.add(label_1);
		
		lblNewLabel = new JLabel();
		lblNewLabel.setBounds(20, 101, 76, 80);
		lblNewLabel.setBorder(BorderFactory.createLineBorder(Color.red));
		contentPane.add(lblNewLabel);
		
		JLabel label_2 = new JLabel("商品编号");
		label_2.setFont(new Font("黑体", Font.PLAIN, 12));
		label_2.setBounds(116, 76, 54, 15);
		contentPane.add(label_2);
		
		textField_1 = new JTextField();
		textField_1.setBounds(175, 73, 66, 21);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("商品名称");
		lblNewLabel_1.setFont(new Font("黑体", Font.PLAIN, 12));
		lblNewLabel_1.setBounds(116, 103, 48, 15);
		contentPane.add(lblNewLabel_1);
		
		textField_2 = new JTextField();
		textField_2.setBounds(175, 101, 66, 21);
		contentPane.add(textField_2);
		textField_2.setColumns(10);
		
		JLabel label_3 = new JLabel("商品价格");
		label_3.setFont(new Font("黑体", Font.PLAIN, 12));
		label_3.setBounds(116, 135, 54, 15);
		contentPane.add(label_3);
		
		textField_3 = new JTextField();
		textField_3.setBounds(175, 132, 66, 21);
		contentPane.add(textField_3);
		textField_3.setColumns(10);
		
		JLabel label_4 = new JLabel("商品库存");
		label_4.setFont(new Font("黑体", Font.PLAIN, 12));
		label_4.setBounds(116, 166, 54, 15);
		contentPane.add(label_4);
		
		textField_4 = new JTextField();
		textField_4.setBounds(175, 163, 66, 21);
		contentPane.add(textField_4);
		textField_4.setColumns(10);
		
		JLabel label_5 = new JLabel("商品类型");
		label_5.setFont(new Font("黑体", Font.PLAIN, 12));
		label_5.setBounds(116, 202, 54, 15);
		contentPane.add(label_5);
		
		textField_5 = new JTextField();
		textField_5.setBounds(175, 199, 66, 21);
		contentPane.add(textField_5);
		textField_5.setColumns(10);
		
		JLabel label_6 = new JLabel("商品描述");
		label_6.setFont(new Font("黑体", Font.PLAIN, 12));
		label_6.setBounds(304, 79, 54, 15);
		contentPane.add(label_6);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(271, 101, 153, 104);
		contentPane.add(scrollPane);
		
		textArea = new JTextArea();
		scrollPane.setViewportView(textArea);
		
		JLabel label_7 = new JLabel("$");
		label_7.setFont(new Font("黑体", Font.PLAIN, 15));
		label_7.setBounds(246, 133, 15, 18);
		contentPane.add(label_7);
		
		JButton button_1 = new JButton("删除");
		button_1.setFont(new Font("黑体", Font.PLAIN, 18));
		button_1.setBounds(77, 228, 93, 23);
		button_1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int isDelete=JOptionPane.showConfirmDialog(Researchframe.this, "确定删除？", "温馨提示", JOptionPane.INFORMATION_MESSAGE);
				if(isDelete == JOptionPane.YES_OPTION){
				String sql="delete from good where gno=?;";
				JDBCTools.update(sql, data[0][0]);
				JOptionPane.showMessageDialog(Researchframe.this, "删除成功！", "温馨提示", JOptionPane.INFORMATION_MESSAGE);
				}
			}
		});
		contentPane.add(button_1);
	}
}
