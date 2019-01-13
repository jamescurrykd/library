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
import javax.swing.JFormattedTextField;
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
import dao.ReaderDao;
import iframe.BookAddIFrame.AddBookActionListener;
import iframe.BookAddIFrame.CloseActionListener;
import iframe.BookAddIFrame.ISBNFocusListener;
import iframe.BookAddIFrame.ISBNkeyListener;
import iframe.BookAddIFrame.NumberListener;
import util.CreatecdIcon;
import util.MyDocument;

public class ReaderAddIFrame extends JInternalFrame{
	private JTextField name;
	private JTextField ISBN;
	private JTextField sex;
	private JFormattedTextField date;
	private JFormattedTextField bztime;
	private JTextField age;
	private JTextField identitycard;
	private JTextField maxnum;
	private JTextField tel;
	private JTextField keepmoney;
	private JTextField zj;
	private JTextField zy;
	private JButton buttonadd;
	private JButton buttonclose;
	public ReaderAddIFrame() {
		super();
		final BorderLayout borderLayout = new BorderLayout();//创建边框布局管理器
		getContentPane().setLayout(borderLayout);			//设置布局
		setIconifiable(true);							// 设置窗体可最小化
		setClosable(true);								// 设置窗体可关闭
		setTitle("读者信息添加");						// 设置窗体标题
		setBounds(100, 100, 396,380);					// 设置窗体位置和大小

		final JPanel mainPanel = new JPanel();			//创建中心面板
		mainPanel.setBorder(new EmptyBorder(5, 10, 5, 10));//设置边框
		final GridLayout gridLayout = new GridLayout(0, 4);//创建表格布局管理器
		gridLayout.setVgap(5);					//设置组件之间垂直距离
		gridLayout.setHgap(5);					//设置组件之间平行距离
		mainPanel.setLayout(gridLayout);		//设置布局
		getContentPane().add(mainPanel);		//将中心面板加入到窗体

		final JLabel ISBNLabel = new JLabel();	//创建图书编号标签
		ISBNLabel.setText("读者编号：");			//设置标签文本
		mainPanel.add(ISBNLabel);				//添加到中心面板

		ISBN = new JTextField("请输入读者编号",13);//创建书号文本框
		ISBN.setDocument(new MyDocument(13)); //设置书号文本框最大输入值为13
		ISBN.setColumns(13);//设置文本框长度
		ISBN.addFocusListener(new ISBNFocusListener());//注册监听器
		mainPanel.add(ISBN);

		final JLabel bookTypeLabel = new JLabel();//创建书籍类别标签
		bookTypeLabel.setHorizontalAlignment(SwingConstants.CENTER);//设置平行对齐方式
		bookTypeLabel.setText("姓名：");//设置标签文本
		mainPanel.add(bookTypeLabel);//添加到中心面板
		name = new JTextField();//创建书籍类别下拉框
		mainPanel.add(name);//添加到中心面板

		final JLabel bookNameLabel = new JLabel();//创建书名标签
		bookNameLabel.setText("性别：");//设置标签文本
		mainPanel.add(bookNameLabel);//添加到中心面板
		sex = new JTextField();//创建书名文本框
		mainPanel.add(sex);	//添加到中心面板

		final JLabel writerLabel = new JLabel();//创建作者标签
		writerLabel.setHorizontalAlignment(SwingConstants.CENTER);//设置平行对齐方式
		writerLabel.setText("年龄：");//设置标签文本
		mainPanel.add(writerLabel);//添加到中心面板
		age = new JTextField();//创建作者文本框
		mainPanel.add(age);//添加到中心面板
		
		final JLabel publisherLabel = new JLabel();//创建出版社标签
		publisherLabel.setText("证件号码：");//设置标签文本
		mainPanel.add(publisherLabel);//添加到中心面板
		identitycard = new JTextField();//创建出版社下拉框
		mainPanel.add(identitycard);//添加到中心面板

		final JLabel translatorLabel = new JLabel();//创建译者标签
		translatorLabel.setHorizontalAlignment(SwingConstants.CENTER);//设置平行对齐方式
		translatorLabel.setText("有效日期：");//设置标签文本
		mainPanel.add(translatorLabel);//添加到中心面板
		SimpleDateFormat myfmt=new SimpleDateFormat("yyyy-MM-dd");//设置日期格式
		date= new JFormattedTextField(myfmt);//创建日期输入框
		date.setValue(new java.util.Date());		//设置日期为当前系统时间
		mainPanel.add(date);//添加到中心面板

		final JLabel pubDateLabel = new JLabel();//创建出版日期标签
		pubDateLabel.setText("最大借书量：");//设置标签文本
		mainPanel.add(pubDateLabel);//添加到中心面板
		maxnum = new JTextField();//创建作者文本框
		mainPanel.add(maxnum);//添加到中心面板

		
		final JLabel priceLabel = new JLabel();//创建价格标签
		priceLabel.setHorizontalAlignment(SwingConstants.CENTER);//设置平行对齐方式
		priceLabel.setText("电话号码：");//设置标签文本
		mainPanel.add(priceLabel);//添加到中心面板
		tel = new JTextField();//创建作者文本框
		mainPanel.add(tel);//添加到中心面板
		
		
		final JLabel moneyLabel = new JLabel();//创建出版日期标签
		moneyLabel.setText("押金：");//设置标签文本
		mainPanel.add(moneyLabel);//添加到中心面板
		keepmoney=   new   JTextField();//创建价格文本框
		keepmoney.setDocument(new MyDocument(5));//设置价格文本框最大输入值为5
		keepmoney.addKeyListener(new NumberListener());//注册监听器
		mainPanel.add(keepmoney);//添加到中心面板
		
		
		final JLabel zjLabel = new JLabel();//创建价格标签
		zjLabel.setHorizontalAlignment(SwingConstants.CENTER);//设置平行对齐方式
		zjLabel.setText("证件类型：");//设置标签文本
		mainPanel.add(zjLabel);//添加到中心面板
		zj = new JTextField();//创建作者文本框
		mainPanel.add(zj);//添加到中心面板
		
		
		final JLabel zyLabel = new JLabel();//创建价格标签
		zyLabel.setText("职业：");//设置标签文本
		mainPanel.add(zyLabel);//添加到中心面板
		zy = new JTextField();//创建作者文本框
		mainPanel.add(zy);//添加到中心面板
		
		
		final JLabel bzLabel = new JLabel();//创建译者标签
		bzLabel.setHorizontalAlignment(SwingConstants.CENTER);//设置平行对齐方式
		bzLabel.setText("办证日期：");//设置标签文本
		mainPanel.add(bzLabel);//添加到中心面板
		bztime= new JFormattedTextField(myfmt);//创建日期输入框
		bztime.setValue(new java.util.Date());		//设置日期为当前系统时间
		mainPanel.add(bztime);//添加到中心面板

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
		imageLabel.setText("读者信息添加(LOGO图片)");//设置标签文本
		
		setVisible(true);											// 显示窗体可见
	}
	class ISBNFocusListener extends FocusAdapter {
		public void focusLost(FocusEvent e){
			if(!ReaderDao.selectreader(ISBN.getText().trim()).isEmpty()){
				JOptionPane.showMessageDialog(null, "添加书号重复！");
				return;
			}
		}
	}
	class CloseActionListener implements ActionListener {			// 添加关闭按钮的事件监听器
		public void actionPerformed(final ActionEvent e) {
			doDefaultCloseAction();
		}
	}
	class AddBookActionListener implements ActionListener {		// 添加按钮的单击事件监听器
		public void actionPerformed(final ActionEvent e) {
			if(ISBN.getText().length()==0){//判断是否输入了书籍编号
				JOptionPane.showMessageDialog(null, "编号不可以为空");
				return;
			}
			String ISBNs=ISBN.getText().trim();//获得书籍编号
			String Name=name.getText().trim();//获得译者信息
			String Sex=sex.getText().trim();//获得书籍名称
			String Age=age.getText().trim();//获得作者信息
			String Identitycard=identitycard.getText().trim();//获得出版社信息
			String Date=date.getText().trim();//获得出版日期
			String Bztime=bztime.getText().trim();//获得出版日期
			String Maxnum=maxnum.getText().trim();//获得价格信息
			String Tel=tel.getText().trim();
			String Keepmoney=keepmoney.getText().trim();
			String Zj=zj.getText().trim();
			int ZJ=Integer.parseInt(Zj);
			String Zy=zy.getText().trim();
			int i=ReaderDao.Insertreader(Name,Sex, Age,Identitycard,java.sql.Date.valueOf(Date), 
					Maxnum,Tel,Double.parseDouble(Keepmoney),ZJ,Zy,ISBNs, java.sql.Date.valueOf(Bztime));
			if(i==1){	//如果返回更新记录数为1，表示添加成功
				JOptionPane.showMessageDialog(null, "添加成功");
				doDefaultCloseAction();
			}
		}
	}
	class NumberListener extends KeyAdapter {
		public void keyTyped(KeyEvent e) {
			String numStr="0123456789."+(char)8;
			if(numStr.indexOf(e.getKeyChar())<0){
				e.consume();
			}
		}
	}
}
