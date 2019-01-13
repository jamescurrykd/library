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

	private Object[][] getFileStates(List list) {// ȡ���ݿ���ͼ�������Ϣ��������
		String[] columnNames = { "�����", "�������", "�ɽ�����", "�ٻ�����" };
		Object[][] results = new Object[list.size()][columnNames.length];// ��ά���������������м�¼

		for (int i = 0; i < list.size(); i++) {// ����list
			BookType booktype = (BookType) list.get(i);// ȡ��ͼ���¼
			results[i][0] = booktype.getId();// ����ͼ����
			results[i][1] = booktype.getTypeName();// ����ͼ���������
			results[i][2] = booktype.getDays();// ����ͼ������
			results[i][3] = booktype.getFk(); // ����ͼ������
		}
		return results;// ��Χ��ά������鼮��¼

	}

	public BooktypeModiAndDelIFrame() {
		super();
		final BorderLayout borderLayout = new BorderLayout();// �߿򲼾ֹ�����
		getContentPane().setLayout(borderLayout);// ʹ�ñ߿򲼾ֹ�����
		setIconifiable(true);// ���ô������С��
		setClosable(true);// ���ô���ɹر�
		setTitle("ͼ����Ϣ�޸�");// ���ô������
		setBounds(100, 100, 593, 406);// ���ô���λ�úʹ�С

		final JPanel mainPanel = new JPanel();// �����
		final BorderLayout borderLayout_1 = new BorderLayout();// �߿򲼾ֹ�����
		borderLayout_1.setVgap(5);// �������֮�䴹ֱ����
		mainPanel.setLayout(borderLayout_1);// ʹ�ñ߿򲼾ֹ�����
		mainPanel.setBorder(new EmptyBorder(5, 10, 5, 10));// ���ñ߿�
		getContentPane().add(mainPanel);// ���������ӵ�������

		final JScrollPane scrollPane = new JScrollPane();// �������
		mainPanel.add(scrollPane);// �����������ӵ��������

		Object[][] results = getFileStates(BooktypeDao.selectBooktype());// ����鼮��¼
		columnNames = new String[] { "�����", "�������", "�ɽ�����", "�ٻ�����" };// �����б�
		table = new JTable(results, columnNames);// �������
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);// ����Ӧ����
		// ��굥������е����ݲ����¼�,������е����ݷ����ı�����
		table.addMouseListener(new TableListener());
		scrollPane.setViewportView(table);// �������ӵ����������

		final JPanel bookPanel = new JPanel();// �鼮�޸����
		mainPanel.add(bookPanel, BorderLayout.SOUTH);// ��ӵ������׶�
		final GridLayout gridLayout = new GridLayout(0, 6);// ���񲼾�
		gridLayout.setVgap(5);// �������֮�䴹ֱ����
		gridLayout.setHgap(5);// �������֮��ƽ�о���
		bookPanel.setLayout(gridLayout);// �����鼮�����岼��

		final JLabel idLabel = new JLabel();// ����ͼ���ű�ǩ
		idLabel.setHorizontalAlignment(SwingConstants.CENTER);// ˮƽ����
		idLabel.setText("����ţ�");// ���ñ�ǩ�ı�
		bookPanel.add(idLabel);// ��ӵ��鼮�޸����
		id = new JTextField();// ��������ı���
		bookPanel.add(id);// ��ӵ��鼮�޸����

		final JLabel typeNameLabel = new JLabel();// �����鼮����ǩ
		typeNameLabel.setHorizontalAlignment(SwingConstants.CENTER);// ˮƽ����
		typeNameLabel.setText("������ƣ�");// ���ñ�ǩ�ı�
		bookPanel.add(typeNameLabel);// ��ӵ��鼮�޸����
		typeName = new JTextField();// ��������ı���
		bookPanel.add(typeName);// ��ӵ��鼮�޸����

		final JLabel daysLabel = new JLabel();// ����������ǩ
		daysLabel.setHorizontalAlignment(SwingConstants.CENTER);// ˮƽ����
		daysLabel.setText("�ɽ�����");// ���ñ�ǩ�ı�
		bookPanel.add(daysLabel);// ��ӵ��鼮�޸����
		days = new JTextField();// �����ı���
		bookPanel.add(days);// ��ӵ��鼮�޸����

		final JLabel fkLabel = new JLabel();// �������۱�ǩ
		fkLabel.setHorizontalAlignment(SwingConstants.CENTER);// ˮƽ����
		fkLabel.setText("��      �ۣ�");// ���ñ�ǩ�ı�
		bookPanel.add(fkLabel);// ��ӵ��鼮�޸����
		fk = new JFormattedTextField();// �۸��ı���
		fk.addKeyListener(new NumberListener());// ע�������
		bookPanel.add(fk);// ��ӵ��鼮�޸����
		final JPanel bottomPanel = new JPanel();// �����ײ����
		bottomPanel.setBorder(new LineBorder(SystemColor.activeCaptionBorder, 1, false));// ���ñ߿�
		getContentPane().add(bottomPanel, BorderLayout.SOUTH);// ��ӵ�����׶�
		final FlowLayout flowLayout = new FlowLayout();// �����ֹ�����
		flowLayout.setVgap(2);// �������֮�䴹ֱ����
		flowLayout.setHgap(30);// �������֮��ƽ�о���
		flowLayout.setAlignment(FlowLayout.RIGHT);// �������Ҷ���
		bottomPanel.setLayout(flowLayout);// ���õײ���岼��

		final JButton updateButton = new JButton();// �����޸İ�ť
		updateButton.addActionListener(new UpdateBookActionListener());// ע�������
		updateButton.setText("�޸�");// ���ð�ť�ı�
		bottomPanel.add(updateButton);// ��ӵ��ײ����

		final JButton closeButton = new JButton();// �����رհ�ť
		closeButton.addActionListener(new ActionListener() {// ע�������
			public void actionPerformed(final ActionEvent e) {
				doDefaultCloseAction();// �رմ���
			}
		});
		closeButton.setText("�ر�");// ���ð�ť�ı�
		bottomPanel.add(closeButton);// ��ӵ��ײ����

		final JLabel headLogo = new JLabel();// ͼƬ��ǩ
		ImageIcon bookModiAndDelIcon = CreatecdIcon.add("res/table2.jpg");// ͼƬͼ��
		headLogo.setIcon(bookModiAndDelIcon);// ���ñ�ǩ��ʾͼƬ
		headLogo.setOpaque(true);// ����ͼƬ��ǩ��͸��
		headLogo.setBackground(Color.CYAN);// ���ñ�ǩ������ɫ
		headLogo.setPreferredSize(new Dimension(400, 80));// ���ñ�ǩ�Ĵ�С
		headLogo.setBorder(new LineBorder(SystemColor.activeCaptionBorder, 1, false));// ���ñ�ǩ�߿�
		getContentPane().add(headLogo, BorderLayout.NORTH);// ��ӵ������϶�

		setVisible(true);// ��ʾ����ɼ�
	}

	class TableListener extends MouseAdapter {
		public void mouseClicked(final MouseEvent e) {
			String ids, typeNames, dayss, fks;// ��������
			int selRow = table.getSelectedRow();// �����ѡ�к�
			ids = table.getValueAt(selRow, 0).toString().trim();// ������
			typeNames = table.getValueAt(selRow, 1).toString().trim();// ��������
			dayss = table.getValueAt(selRow, 2).toString().trim();// �������
			fks = table.getValueAt(selRow, 3).toString().trim();// �������
			id.setText(ids);// ��������ı���Ϊ��õ������Ϣ
			typeName.setText(typeNames);// ���������ı���Ϊ��õ�������Ϣ
			days.setText(dayss);// ���������ı���Ϊ��õ�������Ϣ
			fk.setText(fks);// ���������ı���Ϊ��õ�������Ϣ
		}
	}

	class UpdateBookActionListener implements ActionListener {
		public void actionPerformed(final ActionEvent e) {
			if (id.getText().length() == 0) {// �ж��Ƿ��������鼮���
				JOptionPane.showMessageDialog(null, "����ı��򲻿���Ϊ��");
				return;
			}
			if (typeName.getText().length() == 0) {// �ж��Ƿ��������鼮����
				JOptionPane.showMessageDialog(null, "ͼ�������ı��򲻿���Ϊ��");
				return;
			}
			if (days.getText().length() == 0) {// �ж��Ƿ�����������
				JOptionPane.showMessageDialog(null, "�����ı��򲻿���Ϊ��");
				return;
			}
			if (fk.getText().length() == 0) {// �ж��Ƿ������˳�����
				JOptionPane.showMessageDialog(null, "�������ı��򲻿���Ϊ��");
				return;
			}
			String ids = id.getText().trim();// ����鼮���
			String typeNames = typeName.getText().trim();// ���������Ϣ
			String dayss = days.getText().trim();// ����鼮����
			String fks = fk.getText().trim();// ���������Ϣ
			int i = BooktypeDao.Updatebook(ids, typeNames, dayss,Double.parseDouble(fks));
			if (i == 1) {// ������ظ��¼�¼��Ϊ1����ʾ�޸ĳɹ�
				JOptionPane.showMessageDialog(null, "�޸ĳɹ�");
				Object[][] results = getFileStates(BooktypeDao.selectBooktype());// ���»���鼮��Ϣ
				DefaultTableModel model = new DefaultTableModel();// ��ñ��ģ��
				table.setModel(model);// ���ñ��ģ��
				model.setDataVector(results, columnNames);// ����ģ�����ݺ�����
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
