package iframe;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import JComPz.Item;
import JComPz.MapPz;
import dao.BookInfoDao;
import dao.ReaderDao;
import iframe.BookModiAndDelIFrame.NumberListener;
import iframe.BookModiAndDelIFrame.TableListener;
import iframe.BookModiAndDelIFrame.UpdateBookActionListener;
import model.BookInfo;
import model.BookType;
import model.Reader;
import util.CreatecdIcon;
import util.MyDocument;

public class ReaderModiAndDelIFrame extends JInternalFrame{
	private JTable table;
	private JFormattedTextField keepMoney;
	private JFormattedTextField date;
	private JFormattedTextField bztime;
	private JTextField name;
	private JTextField sex;
	private JTextField age;
	private JTextField identityCard;
	private JTextField maxNum;
	private JTextField tel;
	private JTextField zj;
	private JTextField zy;
	private JTextField ISBN;
	Map map=new HashMap();
	private String[] columnNames;
	private Map m=MapPz.getMap();

	private Object[][] getFileStates(List list){//取数据库中图书相关信息放入表格中
		String[] columnNames = { "读者编号", "读者姓名", "读者性别", "读者年龄", "证件号码", "会员证有效日期",
				"最大借书量", "电话号码","押金","证件类型","职业","办证日期" };
		Object[][] results=new Object[list.size()][columnNames.length];//二维数组用来保存所有记录
		
		for(int i=0;i<list.size();i++){//遍历list
			Reader reader=(Reader)list.get(i);//取出图书记录
			results[i][0]=reader.getISBN();//设置图书编号
			results[i][1]=reader.getName();//设置图书类别名称
			results[i][2]=reader.getSex();//设置图书名称
			results[i][3]=reader.getAge();	//设置图书作者
			results[i][4]=reader.getIdentityCard();//设置图书译者
			results[i][5]=reader.getDate();//设置出版社
			results[i][6]=reader.getMaxNum();//设置出版日期
			results[i][7]=reader.getTel();//设置书籍价格
			results[i][8]=reader.getKeepMoney();
			results[i][9]=reader.getZj();
			results[i][10]=reader.getZy();
			results[i][11]=reader.getBztime();
		}
		return results;//范围二维数组的书籍记录
	         		
	}
	public ReaderModiAndDelIFrame() {
		super();
		final BorderLayout borderLayout = new BorderLayout();//边框布局管理器
		getContentPane().setLayout(borderLayout);//使用边框布局管理器
		setIconifiable(true);// 设置窗体可最小化
		setClosable(true);// 设置窗体可关闭
		setTitle("读者信息修改");// 设置窗体标题
		setBounds(100, 100, 593, 406);// 设置窗体位置和大小

		

		final JPanel mainPanel = new JPanel();//主面板
		final BorderLayout borderLayout_1 = new BorderLayout();//边框布局管理器
		borderLayout_1.setVgap(5);//设置组件之间垂直距离
		mainPanel.setLayout(borderLayout_1);//使用边框布局管理器
		mainPanel.setBorder(new EmptyBorder(5, 10, 5, 10));//设置边框
		getContentPane().add(mainPanel);//将主面板添加到窗体中

		final JScrollPane scrollPane = new JScrollPane();//滚动面板
		mainPanel.add(scrollPane);//将滚动面板添加到主面板中

		Object[][] results=getFileStates(ReaderDao.selectreaderInfo());//获得书籍记录
		columnNames = new String[]{"读者编号", "读者姓名", "读者性别", "读者年龄", "证件号码", "会员证有效日期",
				"最大借书量", "电话号码","押金","证件类型","职业","办证日期" };//列名列表
		table = new JTable(results,columnNames);//创建表格
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);//自适应窗体
		//鼠标单击表格中的内容产生事件,将表格中的内容放入文本框中
		table.addMouseListener(new TableListener());
		scrollPane.setViewportView(table);//将表格添加到滚动面板中

		final JPanel bookPanel = new JPanel();//书籍修改面板
		mainPanel.add(bookPanel, BorderLayout.SOUTH);//添加到主面板底端
		final GridLayout gridLayout = new GridLayout(0, 6);//网格布局
		gridLayout.setVgap(5);//设置组件之间垂直距离
		gridLayout.setHgap(5);//设置组件之间平行距离
		bookPanel.setLayout(gridLayout);//设置书籍添加面板布局
		
		final JLabel ISBNLabel = new JLabel();//创建图书编号标签
		ISBNLabel.setHorizontalAlignment(SwingConstants.CENTER);//水平居中
		ISBNLabel.setText("读者编号：");//设置标签文本
		bookPanel.add(ISBNLabel);//添加到书籍修改面板
		ISBN = new JTextField();//创建书号文本框
		ISBN.setDocument(new MyDocument(13)); //书号文本框最大输入值为13
		bookPanel.add(ISBN);//添加到书籍修改面板
		
		final JLabel nameLabel = new JLabel();//创建书籍类别标签
		nameLabel.setHorizontalAlignment(SwingConstants.CENTER);//水平居中
		nameLabel.setText("读者姓名：");//设置标签文本
		bookPanel.add(nameLabel);//添加到书籍修改面板
		name = new JTextField();//创建书号文本框
		bookPanel.add(name);
		
		
		final JLabel sexLabel = new JLabel();//创建书名标签
		sexLabel.setHorizontalAlignment(SwingConstants.CENTER);//水平居中
		sexLabel.setText("读者性别：");//设置标签文本
		bookPanel.add(sexLabel);//添加到书籍修改面板
		sex = new JTextField();//书名文本框
		bookPanel.add(sex);//添加到书籍修改面板
		
		final JLabel ageLabel = new JLabel();//创建作者标签
		ageLabel.setHorizontalAlignment(SwingConstants.CENTER);//水平居中
		ageLabel.setText("读者年龄：");//设置标签文本
		bookPanel.add(ageLabel);//添加到书籍修改面板
		age = new JTextField();//作者文本框
		bookPanel.add(age);
		
		final JLabel idenLabel = new JLabel();//创建出版社标签
		idenLabel.setHorizontalAlignment(SwingConstants.CENTER);//水平居中
		idenLabel.setText("证件号码：");//设置标签文本
		bookPanel.add(idenLabel);//添加到书籍修改面板
		identityCard = new JTextField();//出版社文本框
		bookPanel.add(identityCard);//添加到书籍修改面板
		
		final JLabel dateLabel = new JLabel();//创建出版日期标签
		dateLabel.setHorizontalAlignment(SwingConstants.CENTER);//水平居中
		dateLabel.setText("有效日期：");//设置标签文本
		bookPanel.add(dateLabel);//添加到书籍修改面板
		SimpleDateFormat myfmt=new SimpleDateFormat("yyyy-MM-dd");//设置日期格式
		date= new JFormattedTextField();//创建日期输入框
		date.setValue(myfmt.format(new java.util.Date()));//设置日期为当前系统时间
		bookPanel.add(date);//添加到书籍修改面板
		
		final JLabel maxLabel = new JLabel();//创建译者标签
		maxLabel.setHorizontalAlignment(SwingConstants.CENTER);//水平居中
		maxLabel.setText("最大借书量：");//设置标签文本
		bookPanel.add(maxLabel);//添加到书籍修改面板
		maxNum = new JTextField();//译者文本框
		bookPanel.add(maxNum);//添加到书籍修改面板
		
		final JLabel telLabel = new JLabel();//创建译者标签
		telLabel.setHorizontalAlignment(SwingConstants.CENTER);//水平居中
		telLabel.setText("电话：");//设置标签文本
		bookPanel.add(telLabel);//添加到书籍修改面板
		tel = new JTextField();//译者文本框
		bookPanel.add(tel);//添加到书籍修改面板
		
		final JLabel pubDateLabel = new JLabel();//创建出版日期标签
		pubDateLabel.setHorizontalAlignment(SwingConstants.CENTER);//水平居中
		pubDateLabel.setText("办证日期：");//设置标签文本
		bookPanel.add(pubDateLabel);//添加到书籍修改面板
		bztime= new JFormattedTextField();//创建日期输入框
		bztime.setValue(myfmt.format(new java.util.Date()));//设置日期为当前系统时间
		bookPanel.add(bztime);//添加到书籍修改面板
		
		final JLabel priceLabel = new JLabel();//创建单价标签
		priceLabel.setHorizontalAlignment(SwingConstants.CENTER);//水平居中
		priceLabel.setText("押金：");//设置标签文本
		bookPanel.add(priceLabel);//添加到书籍修改面板
		keepMoney= new JFormattedTextField();//价格文本框
		keepMoney.addKeyListener(new NumberListener());//注册监听器
		bookPanel.add(keepMoney);//添加到书籍修改面板
		
		final JLabel zjLabel = new JLabel();//创建译者标签
		zjLabel.setHorizontalAlignment(SwingConstants.CENTER);//水平居中
		zjLabel.setText("证件类型：");//设置标签文本
		bookPanel.add(zjLabel);//添加到书籍修改面板
		zj = new JTextField();//译者文本框
		bookPanel.add(zj);//添加到书籍修改面板
		
		final JLabel zyLabel = new JLabel();//创建译者标签
		zyLabel.setHorizontalAlignment(SwingConstants.CENTER);//水平居中
		zyLabel.setText("电话：");//设置标签文本
		bookPanel.add(zyLabel);//添加到书籍修改面板
		zy = new JTextField();//译者文本框
		bookPanel.add(zy);//添加到书籍修改面板
		
		final JPanel bottomPanel = new JPanel();//创建底部面板
		bottomPanel.setBorder(new LineBorder(
				SystemColor.activeCaptionBorder, 1, false));//设置边框
		getContentPane().add(bottomPanel, BorderLayout.SOUTH);//添加到窗体底端
		final FlowLayout flowLayout = new FlowLayout();//流布局管理器
		flowLayout.setVgap(2);//设置组件之间垂直距离
		flowLayout.setHgap(30);//设置组件之间平行距离
		flowLayout.setAlignment(FlowLayout.RIGHT);//设置向右对齐
		bottomPanel.setLayout(flowLayout);//设置底部面板布局
		
		final JButton updateButton = new JButton();//创建修改按钮
		updateButton.addActionListener(new UpdateBookActionListener ());//注册监听器
		updateButton.setText("修改");//设置按钮文本
		bottomPanel.add(updateButton);//添加到底部面板
		
		final JButton closeButton= new JButton();//创建关闭按钮
		closeButton.addActionListener(new ActionListener() {//注册监听器
			public void actionPerformed(final ActionEvent e) {
				doDefaultCloseAction();//关闭窗体
			}
		});
		closeButton.setText("关闭");//设置按钮文本
		bottomPanel.add(closeButton);//添加到底部面板
		
		final JLabel headLogo = new JLabel();//图片标签
		ImageIcon bookModiAndDelIcon=CreatecdIcon.add("res/table2.jpg");//图片图标
		headLogo.setIcon(bookModiAndDelIcon);//设置标签显示图片
		headLogo.setOpaque(true);//设置图片标签不透明
		headLogo.setBackground(Color.CYAN);//设置标签背景颜色
		headLogo.setPreferredSize(new Dimension(400, 80));//设置标签的大小
		headLogo.setBorder(new LineBorder(
				SystemColor.activeCaptionBorder, 1, false));//设置标签边框
		getContentPane().add(headLogo, BorderLayout.NORTH);//添加到窗体上端
		
		setVisible(true);//显示窗体可见
}
	class TableListener extends MouseAdapter {
		public void mouseClicked(final MouseEvent e) {
			String ISBNs, names, sexs,ages,identitycards,
					dates,maxnums,tels,keepmoneys,zjs,zys,bztimes;//声明变量
			int selRow = table.getSelectedRow();//获得所选行号
			ISBNs = table.getValueAt(selRow, 0).toString().trim();//获得书号
			names= table.getValueAt(selRow, 1).toString().trim();//获得类别编号
			sexs = table.getValueAt(selRow, 2).toString().trim();//获得书名
			ages= table.getValueAt(selRow, 3).toString().trim();//获得作者
			identitycards = table.getValueAt(selRow, 4).toString().trim();//获得译者
			dates = table.getValueAt(selRow, 5).toString().trim();//获得出版社
			maxnums = table.getValueAt(selRow, 6).toString().trim();//获得出版日期
			tels = table.getValueAt(selRow, 7).toString().trim();//获得价格
			keepmoneys=table.getValueAt(selRow, 8).toString().trim();
			zjs=table.getValueAt(selRow, 9).toString().trim();
			zys=table.getValueAt(selRow, 10).toString().trim();
			bztimes=table.getValueAt(selRow, 11).toString().trim();
			
			
			ISBN.setText(ISBNs);//设置书号文本框为获得的书号信息
			keepMoney.setText(keepmoneys);//设置书名文本框为获得的书名信息
			date.setText(dates);//设置作者文本框为获得的作者信息
			bztime.setText(bztimes);//设置译者文本框为获得的译者信息
			name.setText(names);//设置出版社文本框为获得的出版社信息
			sex.setText(sexs);//设置出版日期文本框为获得的出版日期信息
			age.setText(ages);//设置价格文本框为获得的价格信息
			identityCard.setText(identitycards);
			maxNum.setText(maxnums);
			tel.setText(tels);
			zj.setText(zjs);
			zy.setText(zys);
		}
	}
	class UpdateBookActionListener  implements ActionListener {
		public void actionPerformed(final ActionEvent e) {
			if(ISBN.getText().length()==0){//判断是否输入了书籍编号
				JOptionPane.showMessageDialog(null, "书号文本框不可以为空");
				return;
			}
			String ISBN1=ISBN.getText().trim();//获得书籍编号
			String keepmoney1=keepMoney.getText().trim();//获得译者信息
			String date1=date.getText().trim();//获得书籍名称
			String bztime1=bztime.getText().trim();//获得作者信息
			String name1=name.getText().trim();//获得出版社信息
			String sex1=sex.getText().trim();//获得出版日期
			String age1=age.getText().trim();//获得价格信息
			String identityCard1=identityCard.getText().trim();
			String maxNum1=maxNum.getText().trim();
			String tel1=tel.getText().trim();
			String zj2=zj.getText().trim();
			int zj1=Integer.parseInt(zj2);
			String zy1=zy.getText().trim();
			int i=ReaderDao.Updatereader(name1,sex1,age1,identityCard1,Date.valueOf(date1),
					maxNum1,tel1, Double.parseDouble(keepmoney1),zj1,zy1,ISBN1, Date.valueOf(bztime1));
			if(i==1){//如果返回更新记录数为1，表示修改成功
				JOptionPane.showMessageDialog(null, "修改成功");
				Object[][] results=getFileStates(ReaderDao.selectreaderInfo());//重新获得书籍信息
				DefaultTableModel model=new DefaultTableModel();//获得表格模型
				table.setModel(model);//设置表格模型
				model.setDataVector(results, columnNames);//设置模型数据和列名
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