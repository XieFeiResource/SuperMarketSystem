package com.SuperMarketSystem.view;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
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

public class Addgoodframe extends JFrame {

	private JPanel contentPane;
	private JTextField textField_2;// 商品名称
	private JTextField textField_3;// 商品价格
	private JTextField textField_4;// 商品库存
	private JTextField textField_5;// 商品类型
	private Operatergoods operatergoods;
	private JLabel lblNewLabel;// 图片
	private String fileName;//从文件夹选取的文件的绝对路径
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Addgoodframe frame = new Addgoodframe(null);
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
	public Addgoodframe(Operatergoods operatergoods) {
		this.operatergoods = operatergoods;
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel label_1 = new JLabel("商品图片");
		label_1.setFont(new Font("黑体", Font.PLAIN, 12));
		label_1.setBounds(30, 50, 54, 15);
		contentPane.add(label_1);

		lblNewLabel = new JLabel();
		lblNewLabel.setBounds(20, 81, 76, 80);
		lblNewLabel.setBorder(BorderFactory.createLineBorder(Color.red));
		lblNewLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				JFileChooser file = new JFileChooser();// 实例化一个文件选择
				int resule = file.showOpenDialog(new JPanel());
				if (resule == file.APPROVE_OPTION) {
					fileName = file.getSelectedFile().getAbsolutePath();
					JOptionPane.showConfirmDialog(null, fileName, "选择的文件", JOptionPane.YES_OPTION);// 显示一个confirm的窗口
					System.out.println(fileName);
					lblNewLabel.setIcon(new ImageIcon(fileName));
				}
			}
		});
		contentPane.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("商品名称");
		lblNewLabel_1.setFont(new Font("黑体", Font.PLAIN, 12));
		lblNewLabel_1.setBounds(116, 62, 48, 15);
		contentPane.add(lblNewLabel_1);

		textField_2 = new JTextField();
		textField_2.setBounds(180, 62, 66, 21);
		contentPane.add(textField_2);
		textField_2.setColumns(10);

		JLabel label_3 = new JLabel("商品价格");
		label_3.setFont(new Font("黑体", Font.PLAIN, 12));
		label_3.setBounds(116, 106, 54, 15);
		contentPane.add(label_3);

		textField_3 = new JTextField();
		textField_3.setBounds(180, 103, 66, 21);
		contentPane.add(textField_3);
		textField_3.setColumns(10);

		JLabel label_4 = new JLabel("商品库存");
		label_4.setFont(new Font("黑体", Font.PLAIN, 12));
		label_4.setBounds(116, 143, 54, 15);
		contentPane.add(label_4);

		textField_4 = new JTextField();
		textField_4.setBounds(180, 140, 66, 21);
		contentPane.add(textField_4);
		textField_4.setColumns(10);

		JLabel label_5 = new JLabel("商品类型");
		label_5.setFont(new Font("黑体", Font.PLAIN, 12));
		label_5.setBounds(116, 179, 54, 15);
		contentPane.add(label_5);

		textField_5 = new JTextField();
		textField_5.setBounds(180, 176, 66, 21);
		contentPane.add(textField_5);
		textField_5.setColumns(10);

		JLabel label_6 = new JLabel("商品描述");
		label_6.setFont(new Font("黑体", Font.PLAIN, 12));
		label_6.setBounds(309, 50, 54, 15);
		contentPane.add(label_6);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(271, 75, 153, 104);
		contentPane.add(scrollPane);

		JTextArea textArea = new JTextArea();
		scrollPane.setViewportView(textArea);

		JLabel label_7 = new JLabel("$");
		label_7.setFont(new Font("黑体", Font.PLAIN, 15));
		label_7.setBounds(246, 104, 15, 18);
		contentPane.add(label_7);

		JButton button = new JButton("添加");
		button.setFont(new Font("黑体", Font.PLAIN, 18));
		button.setBounds(93, 228, 93, 23);
		button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				//String gno=textField_1.getText();
				String gname=textField_2.getText();
				String gprice=textField_3.getText();
				String gaccount=textField_4.getText();
				String gtype=textField_5.getText();
				String gdes=textArea.getText();
				int price=Integer.parseInt(gprice);
				int account=Integer.parseInt(gaccount);
				//int no=Integer.parseInt(gno);
				String sql="insert into good(gname,gprice,gaccount,gtype,gimagepath,gdes) values(?,?,?,?,?,?);";
				JDBCTools.update(sql,gname,price,account,gtype,fileName,gdes);
				JOptionPane.showMessageDialog(Addgoodframe.this, "添加成功！", "温馨提示", JOptionPane.INFORMATION_MESSAGE);
			}
		});
		contentPane.add(button);

		JButton button_1 = new JButton("去查看");
		button_1.setFont(new Font("黑体", Font.PLAIN, 18));
		button_1.setBounds(257, 228, 93, 23);
		button_1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String sql="select * from good;";
				List<Good> list = Addgoodframe.this.operatergoods.queryAllgood(sql);
				Object[][] data = Addgoodframe.this.operatergoods.Createdata(list);
				Showgoodframe show=new Showgoodframe(data);
				show.setVisible(true);
			}
		});
		contentPane.add(button_1);

		JLabel label = new JLabel("商品添加");
		label.setForeground(Color.GREEN);
		label.setFont(new Font("黑体", Font.PLAIN, 18));
		label.setBounds(161, 10, 100, 23);
		contentPane.add(label);
	}
}
