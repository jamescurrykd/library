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

import JComPz.MapPz;
import dao.BookInfoDao;
import dao.BooktypeDao;
import iframe.BookModiAndDelIFrame.NumberListener;
import iframe.BookModiAndDelIFrame.TableListener;
import iframe.BookModiAndDelIFrame.UpdateBookActionListener;
import model.BookInfo;
import model.BookType;
import util.CreatecdIcon;
import util.MyDocument;

public class BooktypeModiAndDelIFrame extends JInternalFrame {
	private JTable table;
	private JFormattedTextField fk;
	private JTextField id;
	private JTextField typeName;
	private JTextField days;
	Map map = new HashMap();
	private String[] columnNames;
	private Map m = MapPz.getMap();

	private Object[][] getFileStates(List list) {// 取数据库中图书相关信息放入表格中
		String[] columnNames = { "类别编号", "类别名称", "可借天数", "迟还罚款" };
		Object[][] results = new Object[list.size()][columnNames.length];// 二维数组用来保存所有记录

		for (int i = 0; i < list.size(); i++) {// 遍历list
			BookType booktype = (BookType) list.get(i);// 取出图书记录
			results[i][0] = booktype.getId();// 设置图书编号
			results[i][1] = booktype.getTypeName();// 设置图书类别名称
			results[i][2] = booktype.getDays();// 设置图书名称
			results[i][3] = booktype.getFk(); // 设置图书作者
		}
		return results;// 范围二维数组的书籍记录

	}

	public BooktypeModiAndDelIFrame() {
		super();
		final BorderLayout borderLayout = new BorderLayout();// 边框布局管理器
		getContentPane().setLayout(borderLayout);// 使用边框布局管理器
		setIconifiable(true);// 设置窗体可最小化
		setClosable(true);// 设置窗体可关闭
		setTitle("图书信息修改");// 设置窗体标题
		setBounds(100, 100, 593, 406);// 设置窗体位置和大小

		final JPanel mainPanel = new JPanel();// 主面板
		final BorderLayout borderLayout_1 = new BorderLayout();// 边框布局管理器
		borderLayout_1.setVgap(5);// 设置组件之间垂直距离
		mainPanel.setLayout(borderLayout_1);// 使用边框布局管理器
		mainPanel.setBorder(new EmptyBorder(5, 10, 5, 10));// 设置边框
		getContentPane().add(mainPanel);// 将主面板添加到窗体中

		final JScrollPane scrollPane = new JScrollPane();// 滚动面板
		mainPanel.add(scrollPane);// 将滚动面板添加到主面板中

		Object[][] results = getFileStates(BooktypeDao.selectBooktype());// 获得书籍记录
		columnNames = new String[] { "类别编号", "类别名称", "可借天数", "迟还罚款" };// 列名列表
		table = new JTable(results, columnNames);// 创建表格
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);// 自适应窗体
		// 鼠标单击表格中的内容产生事件,将表格中的内容放入文本框中
		table.addMouseListener(new TableListener());
		scrollPane.setViewportView(table);// 将表格添加到滚动面板中

		final JPanel bookPanel = new JPanel();// 书籍修改面板
		mainPanel.add(bookPanel, BorderLayout.SOUTH);// 添加到主面板底端
		final GridLayout gridLayout = new GridLayout(0, 6);// 网格布局
		gridLayout.setVgap(5);// 设置组件之间垂直距离
		gridLayout.setHgap(5);// 设置组件之间平行距离
		bookPanel.setLayout(gridLayout);// 设置书籍添加面板布局

		final JLabel idLabel = new JLabel();// 创建图书编号标签
		idLabel.setHorizontalAlignment(SwingConstants.CENTER);// 水平居中
		idLabel.setText("类别编号：");// 设置标签文本
		bookPanel.add(idLabel);// 添加到书籍修改面板
		id = new JTextField();// 创建书号文本框
		bookPanel.add(id);// 添加到书籍修改面板

		final JLabel typeNameLabel = new JLabel();// 创建书籍类别标签
		typeNameLabel.setHorizontalAlignment(SwingConstants.CENTER);// 水平居中
		typeNameLabel.setText("类别名称：");// 设置标签文本
		bookPanel.add(typeNameLabel);// 添加到书籍修改面板
		typeName = new JTextField();// 创建书号文本框
		bookPanel.add(typeName);// 添加到书籍修改面板

		final JLabel daysLabel = new JLabel();// 创建书名标签
		daysLabel.setHorizontalAlignment(SwingConstants.CENTER);// 水平居中
		daysLabel.setText("可借天数");// 设置标签文本
		bookPanel.add(daysLabel);// 添加到书籍修改面板
		days = new JTextField();// 书名文本框
		bookPanel.add(days);// 添加到书籍修改面板

		final JLabel fkLabel = new JLabel();// 创建单价标签
		fkLabel.setHorizontalAlignment(SwingConstants.CENTER);// 水平居中
		fkLabel.setText("单      价：");// 设置标签文本
		bookPanel.add(fkLabel);// 添加到书籍修改面板
		fk = new JFormattedTextField();// 价格文本框
		fk.addKeyListener(new NumberListener());// 注册监听器
		bookPanel.add(fk);// 添加到书籍修改面板
		final JPanel bottomPanel = new JPanel();// 创建底部面板
		bottomPanel.setBorder(new LineBorder(SystemColor.activeCaptionBorder, 1, false));// 设置边框
		getContentPane().add(bottomPanel, BorderLayout.SOUTH);// 添加到窗体底端
		final FlowLayout flowLayout = new FlowLayout();// 流布局管理器
		flowLayout.setVgap(2);// 设置组件之间垂直距离
		flowLayout.setHgap(30);// 设置组件之间平行距离
		flowLayout.setAlignment(FlowLayout.RIGHT);// 设置向右对齐
		bottomPanel.setLayout(flowLayout);// 设置底部面板布局

		final JButton updateButton = new JButton();// 创建修改按钮
		updateButton.addActionListener(new UpdateBookActionListener());// 注册监听器
		updateButton.setText("修改");// 设置按钮文本
		bottomPanel.add(updateButton);// 添加到底部面板

		final JButton closeButton = new JButton();// 创建关闭按钮
		closeButton.addActionListener(new ActionListener() {// 注册监听器
			public void actionPerformed(final ActionEvent e) {
				doDefaultCloseAction();// 关闭窗体
			}
		});
		closeButton.setText("关闭");// 设置按钮文本
		bottomPanel.add(closeButton);// 添加到底部面板

		final JLabel headLogo = new JLabel();// 图片标签
		ImageIcon bookModiAndDelIcon = CreatecdIcon.add("res/table2.jpg");// 图片图标
		headLogo.setIcon(bookModiAndDelIcon);// 设置标签显示图片
		headLogo.setOpaque(true);// 设置图片标签不透明
		headLogo.setBackground(Color.CYAN);// 设置标签背景颜色
		headLogo.setPreferredSize(new Dimension(400, 80));// 设置标签的大小
		headLogo.setBorder(new LineBorder(SystemColor.activeCaptionBorder, 1, false));// 设置标签边框
		getContentPane().add(headLogo, BorderLayout.NORTH);// 添加到窗体上端

		setVisible(true);// 显示窗体可见
	}

	class TableListener extends MouseAdapter {
		public void mouseClicked(final MouseEvent e) {
			String ids, typeNames, dayss, fks;// 声明变量
			int selRow = table.getSelectedRow();// 获得所选行号
			ids = table.getValueAt(selRow, 0).toString().trim();// 获得书号
			typeNames = table.getValueAt(selRow, 1).toString().trim();// 获得类别编号
			dayss = table.getValueAt(selRow, 2).toString().trim();// 获得书名
			fks = table.getValueAt(selRow, 3).toString().trim();// 获得作者
			id.setText(ids);// 设置书号文本框为获得的书号信息
			typeName.setText(typeNames);// 设置书名文本框为获得的书名信息
			days.setText(dayss);// 设置作者文本框为获得的作者信息
			fk.setText(fks);// 设置译者文本框为获得的译者信息
		}
	}

	class UpdateBookActionListener implements ActionListener {
		public void actionPerformed(final ActionEvent e) {
			if (id.getText().length() == 0) {// 判断是否输入了书籍编号
				JOptionPane.showMessageDialog(null, "书号文本框不可以为空");
				return;
			}
			if (typeName.getText().length() == 0) {// 判断是否输入了书籍名称
				JOptionPane.showMessageDialog(null, "图书名称文本框不可以为空");
				return;
			}
			if (days.getText().length() == 0) {// 判断是否输入了作者
				JOptionPane.showMessageDialog(null, "作者文本框不可以为空");
				return;
			}
			if (fk.getText().length() == 0) {// 判断是否输入了出版社
				JOptionPane.showMessageDialog(null, "出版人文本框不可以为空");
				return;
			}
			String ids = id.getText().trim();// 获得书籍编号
			String typeNames = typeName.getText().trim();// 获得译者信息
			String dayss = days.getText().trim();// 获得书籍名称
			String fks = fk.getText().trim();// 获得作者信息
			int i = BooktypeDao.Updatebook(ids, typeNames, dayss,Double.parseDouble(fks));
			if (i == 1) {// 如果返回更新记录数为1，表示修改成功
				JOptionPane.showMessageDialog(null, "修改成功");
				Object[][] results = getFileStates(BooktypeDao.selectBooktype());// 重新获得书籍信息
				DefaultTableModel model = new DefaultTableModel();// 获得表格模型
				table.setModel(model);// 设置表格模型
				model.setDataVector(results, columnNames);// 设置模型数据和列名
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
