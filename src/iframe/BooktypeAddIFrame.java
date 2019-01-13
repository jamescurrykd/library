package iframe;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.text.SimpleDateFormat;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import JComPz.Item;
import dao.BookInfoDao;
import dao.BooktypeDao;
import iframe.BookAddIFrame.AddBookActionListener;
import iframe.BookAddIFrame.CloseActionListener;
import iframe.BookAddIFrame.ISBNFocusListener;
import iframe.BookAddIFrame.ISBNkeyListener;
import iframe.BookAddIFrame.NumberListener;
import model.BookType;
import util.CreatecdIcon;
import util.MyDocument;

public class BooktypeAddIFrame extends JInternalFrame {
	private JTextField id;
	private JTextField typeName;
	private JTextField days;
	private JTextField fk;
	private JButton buttonadd;
	private JButton buttonclose;


	public BooktypeAddIFrame() {
		super();
		final BorderLayout borderLayout = new BorderLayout();//创建边框布局管理器
		getContentPane().setLayout(borderLayout);			//设置布局
		setIconifiable(true);							// 设置窗体可最小化
		setClosable(true);								// 设置窗体可关闭
		setTitle("图书类别添加");						// 设置窗体标题
		setBounds(100, 100, 396, 260);					// 设置窗体位置和大小

		final JPanel mainPanel = new JPanel();			//创建中心面板
		mainPanel.setBorder(new EmptyBorder(5, 10, 5, 10));//设置边框
		final GridLayout gridLayout = new GridLayout(0, 4);//创建表格布局管理器
		gridLayout.setVgap(5);					//设置组件之间垂直距离
		gridLayout.setHgap(5);					//设置组件之间平行距离
		mainPanel.setLayout(gridLayout);		//设置布局
		getContentPane().add(mainPanel);		//将中心面板加入到窗体

		final JLabel idLabel = new JLabel();	//创建图书编号标签
		idLabel.setText("类别编号：");			//设置标签文本
		mainPanel.add(idLabel);				//添加到中心面板

		id = new JTextField();//创建书号文本框
		id.addFocusListener(new idFocusListener());//注册监听器
		mainPanel.add(id);

		final JLabel typeNameLabel = new JLabel();//创建书籍类别标签
		typeNameLabel.setHorizontalAlignment(SwingConstants.CENTER);//设置平行对齐方式
		typeNameLabel.setText("类别名称：");//设置标签文本
		mainPanel.add(typeNameLabel);//添加到中心面板

		typeName = new JTextField();//创建书名文本框
		mainPanel.add(typeName);	//添加到中心面板

		final JLabel daysLabel = new JLabel();//创建书名标签
		daysLabel.setText("可借天数：");//设置标签文本
		mainPanel.add(daysLabel);//添加到中心面板

		days = new JTextField();//创建书名文本框
		mainPanel.add(days);	//添加到中心面板

		final JLabel fkLabel = new JLabel();//创建作者标签
		fkLabel.setHorizontalAlignment(SwingConstants.CENTER);//设置平行对齐方式
		fkLabel.setText("迟还一天的罚款：");//设置标签文本
		mainPanel.add(fkLabel);//添加到中心面板

		fk = new JTextField();//创建作者文本框
		fk.addKeyListener(new NumberListener());//注册监听器
		mainPanel.add(fk);//添加到中心面板


		final JPanel bottomPanel = new JPanel();//创建底部面板
		bottomPanel.setBorder(new LineBorder(SystemColor.
							activeCaptionBorder, 1, false));//设置边框
		getContentPane().add(bottomPanel, BorderLayout.SOUTH);//添加到窗体中
		final FlowLayout flowLayout = new FlowLayout();//流布局管理器
		flowLayout.setVgap(2);	//设置组件之间垂直距离
		flowLayout.setHgap(30);//设置组件之间平行距离
		flowLayout.setAlignment(FlowLayout.RIGHT);//设置对齐方式
		bottomPanel.setLayout(flowLayout);//设置底部面板布局
		
		buttonadd= new JButton();//创建添加按钮
		buttonadd.addActionListener(new AddBookActionListener());//注册监听器
		buttonadd.setText("添加");//设置按钮文本
		bottomPanel.add(buttonadd);//添加到底部面板
		
		buttonclose = new JButton();//创建关闭按钮
		buttonclose.addActionListener(new CloseActionListener());//注册监听器
		buttonclose.setText("关闭");//设置按钮文本
		bottomPanel.add(buttonclose);//添加到底部面板

		final JLabel imageLabel = new JLabel();//图片标签
		ImageIcon bookAddIcon=CreatecdIcon.add("res/table1.jpg");//图片图标
		imageLabel.setIcon(bookAddIcon);//设置标签显示图片
		imageLabel.setPreferredSize(new Dimension(400, 80));//设置标签的大小
		imageLabel.setBorder(new LineBorder(SystemColor.
						activeCaptionBorder, 1, false));//设置边框
		getContentPane().add(imageLabel, BorderLayout.NORTH);//添加到窗体中
		imageLabel.setText("图书信息添加(LOGO图片)");//设置标签文本
		
		setVisible(true);											// 显示窗体可见
	}

	class idFocusListener extends FocusAdapter {
		public void focusLost(FocusEvent e) {
			if (!BooktypeDao.selectBooktype(id.getText().trim()).isEmpty()) {
				JOptionPane.showMessageDialog(null, "添加类别编号重复！");
				return;
			}
		}
	}


	class CloseActionListener implements ActionListener { // 添加关闭按钮的事件监听器
		public void actionPerformed(final ActionEvent e) {
			doDefaultCloseAction();
		}
	}

	class AddBookActionListener implements ActionListener { // 添加按钮的单击事件监听器
		public void actionPerformed(final ActionEvent e) {
			if (id.getText().length() == 0) {// 判断是否输入了书籍编号
				JOptionPane.showMessageDialog(null, "类别编号文本框不可以为空");
				return;
			}
			if (typeName.getText().length() == 0) {// 判断是否输入了书籍名称
				JOptionPane.showMessageDialog(null, "类别名称文本框不可以为空");
				return;
			}
			if (days.getText().length() == 0) {// 判断是否输入了作者
				JOptionPane.showMessageDialog(null, "可借天数文本框不可以为空");
				return;
			}
			if (fk.getText().length() == 0) {// 判断是否输入了出版日期
				JOptionPane.showMessageDialog(null, "罚款文本框不可以为空");
				return;
			}

			String ids = id.getText().trim();// 获得书籍编号
			String typeNames = typeName.getText().trim();// 获得译者信息
			String dayss = days.getText().trim();// 获得书籍名称
			String fks = fk.getText().trim();// 获得作者信息
			int i = BooktypeDao.Insertbook(ids, typeNames, dayss, Double.parseDouble(fks));
			if (i == 1) { // 如果返回更新记录数为1，表示添加成功
				JOptionPane.showMessageDialog(null, "添加成功");
				doDefaultCloseAction();
			}
		}
	}

	class NumberListener extends KeyAdapter {
		public void keyTyped(KeyEvent e) {
			String numStr = "0123456789." + (char) 8;
			if (numStr.indexOf(e.getKeyChar()) < 0) {
				e.consume();
			}
		}
	}

}
