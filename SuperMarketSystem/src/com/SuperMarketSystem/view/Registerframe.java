package com.SuperMarketSystem.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.SuperMarketSystem.dbuntils.JDBCTools;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;

public class Registerframe extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JPasswordField passwordField;
	private JPasswordField passwordField_1;
	private Loginframe loginframe;
	private ResultSet resultSet;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Registerframe frame = new Registerframe(null);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	public Registerframe(Loginframe loginframe) {
		this();
		this.loginframe=loginframe;
	}
	/**
	 * Create the frame.
	 */
	public Registerframe() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel label = new JLabel("用户名");
		label.setFont(new Font("黑体", Font.PLAIN, 18));
		label.setBounds(72, 45, 54, 15);
		contentPane.add(label);
		
		textField = new JTextField();
		textField.setBounds(152, 44, 188, 21);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JLabel label_1 = new JLabel("密码");
		label_1.setFont(new Font("黑体", Font.PLAIN, 18));
		label_1.setBounds(72, 88, 54, 15);
		contentPane.add(label_1);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(151, 87, 189, 21);
		contentPane.add(passwordField);
		
		JLabel label_2 = new JLabel("确认密码");
		label_2.setFont(new Font("黑体", Font.PLAIN, 18));
		label_2.setBounds(72, 131, 72, 15);
		contentPane.add(label_2);
		
		passwordField_1 = new JPasswordField();
		passwordField_1.setBounds(152, 130, 188, 21);
		contentPane.add(passwordField_1);
		
		JButton button = new JButton("注册");
		button.setFont(new Font("黑体", Font.PLAIN, 18));
		button.setBounds(82, 190, 93, 23);
		button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String account=textField.getText();
				String password=passwordField.getText();
				String sql="insert into user values(?,?);";
				JDBCTools.update(sql, account,password);
				JOptionPane.showMessageDialog(Registerframe.this, "注册成功，去登陆！", "温馨提示", JOptionPane.INFORMATION_MESSAGE);
			}
		});
		contentPane.add(button);
		
		JButton button_1 = new JButton("登录");
		button_1.setFont(new Font("黑体", Font.PLAIN, 18));
		button_1.setBounds(241, 192, 93, 23);
		button_1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Registerframe.this.loginframe.setVisible(true);
				Registerframe.this.setVisible(false);
			}
		});
		contentPane.add(button_1);
	}
}
