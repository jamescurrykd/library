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
		final BorderLayout borderLayout = new BorderLayout();//�����߿򲼾ֹ�����
		getContentPane().setLayout(borderLayout);			//���ò���
		setIconifiable(true);							// ���ô������С��
		setClosable(true);								// ���ô���ɹر�
		setTitle("ͼ��������");						// ���ô������
		setBounds(100, 100, 396, 260);					// ���ô���λ�úʹ�С

		final JPanel mainPanel = new JPanel();			//�����������
		mainPanel.setBorder(new EmptyBorder(5, 10, 5, 10));//���ñ߿�
		final GridLayout gridLayout = new GridLayout(0, 4);//������񲼾ֹ�����
		gridLayout.setVgap(5);					//�������֮�䴹ֱ����
		gridLayout.setHgap(5);					//�������֮��ƽ�о���
		mainPanel.setLayout(gridLayout);		//���ò���
		getContentPane().add(mainPanel);		//�����������뵽����

		final JLabel idLabel = new JLabel();	//����ͼ���ű�ǩ
		idLabel.setText("����ţ�");			//���ñ�ǩ�ı�
		mainPanel.add(idLabel);				//��ӵ��������

		id = new JTextField();//��������ı���
		id.addFocusListener(new idFocusListener());//ע�������
		mainPanel.add(id);

		final JLabel typeNameLabel = new JLabel();//�����鼮����ǩ
		typeNameLabel.setHorizontalAlignment(SwingConstants.CENTER);//����ƽ�ж��뷽ʽ
		typeNameLabel.setText("������ƣ�");//���ñ�ǩ�ı�
		mainPanel.add(typeNameLabel);//��ӵ��������

		typeName = new JTextField();//���������ı���
		mainPanel.add(typeName);	//��ӵ��������

		final JLabel daysLabel = new JLabel();//����������ǩ
		daysLabel.setText("�ɽ�������");//���ñ�ǩ�ı�
		mainPanel.add(daysLabel);//��ӵ��������

		days = new JTextField();//���������ı���
		mainPanel.add(days);	//��ӵ��������

		final JLabel fkLabel = new JLabel();//�������߱�ǩ
		fkLabel.setHorizontalAlignment(SwingConstants.CENTER);//����ƽ�ж��뷽ʽ
		fkLabel.setText("�ٻ�һ��ķ��");//���ñ�ǩ�ı�
		mainPanel.add(fkLabel);//��ӵ��������

		fk = new JTextField();//���������ı���
		fk.addKeyListener(new NumberListener());//ע�������
		mainPanel.add(fk);//��ӵ��������


		final JPanel bottomPanel = new JPanel();//�����ײ����
		bottomPanel.setBorder(new LineBorder(SystemColor.
							activeCaptionBorder, 1, false));//���ñ߿�
		getContentPane().add(bottomPanel, BorderLayout.SOUTH);//��ӵ�������
		final FlowLayout flowLayout = new FlowLayout();//�����ֹ�����
		flowLayout.setVgap(2);	//�������֮�䴹ֱ����
		flowLayout.setHgap(30);//�������֮��ƽ�о���
		flowLayout.setAlignment(FlowLayout.RIGHT);//���ö��뷽ʽ
		bottomPanel.setLayout(flowLayout);//���õײ���岼��
		
		buttonadd= new JButton();//������Ӱ�ť
		buttonadd.addActionListener(new AddBookActionListener());//ע�������
		buttonadd.setText("���");//���ð�ť�ı�
		bottomPanel.add(buttonadd);//��ӵ��ײ����
		
		buttonclose = new JButton();//�����رհ�ť
		buttonclose.addActionListener(new CloseActionListener());//ע�������
		buttonclose.setText("�ر�");//���ð�ť�ı�
		bottomPanel.add(buttonclose);//��ӵ��ײ����

		final JLabel imageLabel = new JLabel();//ͼƬ��ǩ
		ImageIcon bookAddIcon=CreatecdIcon.add("res/table1.jpg");//ͼƬͼ��
		imageLabel.setIcon(bookAddIcon);//���ñ�ǩ��ʾͼƬ
		imageLabel.setPreferredSize(new Dimension(400, 80));//���ñ�ǩ�Ĵ�С
		imageLabel.setBorder(new LineBorder(SystemColor.
						activeCaptionBorder, 1, false));//���ñ߿�
		getContentPane().add(imageLabel, BorderLayout.NORTH);//��ӵ�������
		imageLabel.setText("ͼ����Ϣ���(LOGOͼƬ)");//���ñ�ǩ�ı�
		
		setVisible(true);											// ��ʾ����ɼ�
	}

	class idFocusListener extends FocusAdapter {
		public void focusLost(FocusEvent e) {
			if (!BooktypeDao.selectBooktype(id.getText().trim()).isEmpty()) {
				JOptionPane.showMessageDialog(null, "���������ظ���");
				return;
			}
		}
	}


	class CloseActionListener implements ActionListener { // ��ӹرհ�ť���¼�������
		public void actionPerformed(final ActionEvent e) {
			doDefaultCloseAction();
		}
	}

	class AddBookActionListener implements ActionListener { // ��Ӱ�ť�ĵ����¼�������
		public void actionPerformed(final ActionEvent e) {
			if (id.getText().length() == 0) {// �ж��Ƿ��������鼮���
				JOptionPane.showMessageDialog(null, "������ı��򲻿���Ϊ��");
				return;
			}
			if (typeName.getText().length() == 0) {// �ж��Ƿ��������鼮����
				JOptionPane.showMessageDialog(null, "��������ı��򲻿���Ϊ��");
				return;
			}
			if (days.getText().length() == 0) {// �ж��Ƿ�����������
				JOptionPane.showMessageDialog(null, "�ɽ������ı��򲻿���Ϊ��");
				return;
			}
			if (fk.getText().length() == 0) {// �ж��Ƿ������˳�������
				JOptionPane.showMessageDialog(null, "�����ı��򲻿���Ϊ��");
				return;
			}

			String ids = id.getText().trim();// ����鼮���
			String typeNames = typeName.getText().trim();// ���������Ϣ
			String dayss = days.getText().trim();// ����鼮����
			String fks = fk.getText().trim();// ���������Ϣ
			int i = BooktypeDao.Insertbook(ids, typeNames, dayss, Double.parseDouble(fks));
			if (i == 1) { // ������ظ��¼�¼��Ϊ1����ʾ��ӳɹ�
				JOptionPane.showMessageDialog(null, "��ӳɹ�");
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
