package com.SuperMarketSystem.view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.SuperMarketSystem.bean.User;
import com.SuperMarketSystem.dbuntils.JDBCTools;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Color;
import javax.swing.ImageIcon;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;

public class Loginframe extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JPasswordField passwordField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Loginframe frame = new Loginframe();
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
	public Loginframe() {
		setBounds(100, 100, 450, 300);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel label = new JLabel("超市管理系统");
		label.setForeground(Color.GREEN);
		label.setFont(new Font("黑体", Font.PLAIN, 25));
		label.setBounds(147, 20, 150, 30);
		contentPane.add(label);
		
		JLabel label_1 = new JLabel("用户名");
		label_1.setIcon(new ImageIcon("resource/images/user.png"));
		label_1.setFont(new Font("黑体", Font.PLAIN, 18));
		label_1.setBounds(61, 88, 96, 30);
		contentPane.add(label_1);
		
		textField = new JTextField();
		textField.setBounds(167, 95, 188, 21);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JLabel label_2 = new JLabel("密码");
		label_2.setIcon(new ImageIcon("resource/images/key.png"));
		label_2.setFont(new Font("黑体", Font.PLAIN, 18));
		label_2.setBounds(61, 152, 81, 21);
		contentPane.add(label_2);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(167, 154, 188, 21);
		contentPane.add(passwordField);
		
		JButton btnNewButton = new JButton("登录");
		btnNewButton.setFont(new Font("黑体", Font.PLAIN, 20));
		btnNewButton.setBounds(103, 207, 93, 23);
		btnNewButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String account=textField.getText();
				String password=passwordField.getText();
				System.out.println(account+":  "+password);
				String sql="select account,password from user where account=? and password=?;";
				User loginuser=JDBCTools.get(User.class, sql, account,password);
				System.out.println(loginuser);
				if(loginuser!=null) {
					Mainframe m=new Mainframe();
					Loginframe.this.dispose();
					m.setVisible(true);
				}else {
					JOptionPane.showMessageDialog(Loginframe.this, "密码与账号不匹配！", "温馨提示", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		contentPane.add(btnNewButton);
		
		JButton button = new JButton("注册");
		button.setFont(new Font("黑体", Font.PLAIN, 20));
		button.setBounds(254, 207, 93, 23);
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Registerframe register=new Registerframe(Loginframe.this);
				register.setVisible(true);
				Loginframe.this.setVisible(false);
			}
		});
		contentPane.add(button);
	}
}
