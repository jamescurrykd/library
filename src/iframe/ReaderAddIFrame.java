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
		final BorderLayout borderLayout = new BorderLayout();//�����߿򲼾ֹ�����
		getContentPane().setLayout(borderLayout);			//���ò���
		setIconifiable(true);							// ���ô������С��
		setClosable(true);								// ���ô���ɹر�
		setTitle("������Ϣ���");						// ���ô������
		setBounds(100, 100, 396,380);					// ���ô���λ�úʹ�С

		final JPanel mainPanel = new JPanel();			//�����������
		mainPanel.setBorder(new EmptyBorder(5, 10, 5, 10));//���ñ߿�
		final GridLayout gridLayout = new GridLayout(0, 4);//������񲼾ֹ�����
		gridLayout.setVgap(5);					//�������֮�䴹ֱ����
		gridLayout.setHgap(5);					//�������֮��ƽ�о���
		mainPanel.setLayout(gridLayout);		//���ò���
		getContentPane().add(mainPanel);		//�����������뵽����

		final JLabel ISBNLabel = new JLabel();	//����ͼ���ű�ǩ
		ISBNLabel.setText("���߱�ţ�");			//���ñ�ǩ�ı�
		mainPanel.add(ISBNLabel);				//��ӵ��������

		ISBN = new JTextField("��������߱��",13);//��������ı���
		ISBN.setDocument(new MyDocument(13)); //��������ı����������ֵΪ13
		ISBN.setColumns(13);//�����ı��򳤶�
		ISBN.addFocusListener(new ISBNFocusListener());//ע�������
		mainPanel.add(ISBN);

		final JLabel bookTypeLabel = new JLabel();//�����鼮����ǩ
		bookTypeLabel.setHorizontalAlignment(SwingConstants.CENTER);//����ƽ�ж��뷽ʽ
		bookTypeLabel.setText("������");//���ñ�ǩ�ı�
		mainPanel.add(bookTypeLabel);//��ӵ��������
		name = new JTextField();//�����鼮���������
		mainPanel.add(name);//��ӵ��������

		final JLabel bookNameLabel = new JLabel();//����������ǩ
		bookNameLabel.setText("�Ա�");//���ñ�ǩ�ı�
		mainPanel.add(bookNameLabel);//��ӵ��������
		sex = new JTextField();//���������ı���
		mainPanel.add(sex);	//��ӵ��������

		final JLabel writerLabel = new JLabel();//�������߱�ǩ
		writerLabel.setHorizontalAlignment(SwingConstants.CENTER);//����ƽ�ж��뷽ʽ
		writerLabel.setText("���䣺");//���ñ�ǩ�ı�
		mainPanel.add(writerLabel);//��ӵ��������
		age = new JTextField();//���������ı���
		mainPanel.add(age);//��ӵ��������
		
		final JLabel publisherLabel = new JLabel();//�����������ǩ
		publisherLabel.setText("֤�����룺");//���ñ�ǩ�ı�
		mainPanel.add(publisherLabel);//��ӵ��������
		identitycard = new JTextField();//����������������
		mainPanel.add(identitycard);//��ӵ��������

		final JLabel translatorLabel = new JLabel();//�������߱�ǩ
		translatorLabel.setHorizontalAlignment(SwingConstants.CENTER);//����ƽ�ж��뷽ʽ
		translatorLabel.setText("��Ч���ڣ�");//���ñ�ǩ�ı�
		mainPanel.add(translatorLabel);//��ӵ��������
		SimpleDateFormat myfmt=new SimpleDateFormat("yyyy-MM-dd");//�������ڸ�ʽ
		date= new JFormattedTextField(myfmt);//�������������
		date.setValue(new java.util.Date());		//��������Ϊ��ǰϵͳʱ��
		mainPanel.add(date);//��ӵ��������

		final JLabel pubDateLabel = new JLabel();//�����������ڱ�ǩ
		pubDateLabel.setText("����������");//���ñ�ǩ�ı�
		mainPanel.add(pubDateLabel);//��ӵ��������
		maxnum = new JTextField();//���������ı���
		mainPanel.add(maxnum);//��ӵ��������

		
		final JLabel priceLabel = new JLabel();//�����۸��ǩ
		priceLabel.setHorizontalAlignment(SwingConstants.CENTER);//����ƽ�ж��뷽ʽ
		priceLabel.setText("�绰���룺");//���ñ�ǩ�ı�
		mainPanel.add(priceLabel);//��ӵ��������
		tel = new JTextField();//���������ı���
		mainPanel.add(tel);//��ӵ��������
		
		
		final JLabel moneyLabel = new JLabel();//�����������ڱ�ǩ
		moneyLabel.setText("Ѻ��");//���ñ�ǩ�ı�
		mainPanel.add(moneyLabel);//��ӵ��������
		keepmoney=   new   JTextField();//�����۸��ı���
		keepmoney.setDocument(new MyDocument(5));//���ü۸��ı����������ֵΪ5
		keepmoney.addKeyListener(new NumberListener());//ע�������
		mainPanel.add(keepmoney);//��ӵ��������
		
		
		final JLabel zjLabel = new JLabel();//�����۸��ǩ
		zjLabel.setHorizontalAlignment(SwingConstants.CENTER);//����ƽ�ж��뷽ʽ
		zjLabel.setText("֤�����ͣ�");//���ñ�ǩ�ı�
		mainPanel.add(zjLabel);//��ӵ��������
		zj = new JTextField();//���������ı���
		mainPanel.add(zj);//��ӵ��������
		
		
		final JLabel zyLabel = new JLabel();//�����۸��ǩ
		zyLabel.setText("ְҵ��");//���ñ�ǩ�ı�
		mainPanel.add(zyLabel);//��ӵ��������
		zy = new JTextField();//���������ı���
		mainPanel.add(zy);//��ӵ��������
		
		
		final JLabel bzLabel = new JLabel();//�������߱�ǩ
		bzLabel.setHorizontalAlignment(SwingConstants.CENTER);//����ƽ�ж��뷽ʽ
		bzLabel.setText("��֤���ڣ�");//���ñ�ǩ�ı�
		mainPanel.add(bzLabel);//��ӵ��������
		bztime= new JFormattedTextField(myfmt);//�������������
		bztime.setValue(new java.util.Date());		//��������Ϊ��ǰϵͳʱ��
		mainPanel.add(bztime);//��ӵ��������

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
		imageLabel.setText("������Ϣ���(LOGOͼƬ)");//���ñ�ǩ�ı�
		
		setVisible(true);											// ��ʾ����ɼ�
	}
	class ISBNFocusListener extends FocusAdapter {
		public void focusLost(FocusEvent e){
			if(!ReaderDao.selectreader(ISBN.getText().trim()).isEmpty()){
				JOptionPane.showMessageDialog(null, "�������ظ���");
				return;
			}
		}
	}
	class CloseActionListener implements ActionListener {			// ��ӹرհ�ť���¼�������
		public void actionPerformed(final ActionEvent e) {
			doDefaultCloseAction();
		}
	}
	class AddBookActionListener implements ActionListener {		// ��Ӱ�ť�ĵ����¼�������
		public void actionPerformed(final ActionEvent e) {
			if(ISBN.getText().length()==0){//�ж��Ƿ��������鼮���
				JOptionPane.showMessageDialog(null, "��Ų�����Ϊ��");
				return;
			}
			String ISBNs=ISBN.getText().trim();//����鼮���
			String Name=name.getText().trim();//���������Ϣ
			String Sex=sex.getText().trim();//����鼮����
			String Age=age.getText().trim();//���������Ϣ
			String Identitycard=identitycard.getText().trim();//��ó�������Ϣ
			String Date=date.getText().trim();//��ó�������
			String Bztime=bztime.getText().trim();//��ó�������
			String Maxnum=maxnum.getText().trim();//��ü۸���Ϣ
			String Tel=tel.getText().trim();
			String Keepmoney=keepmoney.getText().trim();
			String Zj=zj.getText().trim();
			int ZJ=Integer.parseInt(Zj);
			String Zy=zy.getText().trim();
			int i=ReaderDao.Insertreader(Name,Sex, Age,Identitycard,java.sql.Date.valueOf(Date), 
					Maxnum,Tel,Double.parseDouble(Keepmoney),ZJ,Zy,ISBNs, java.sql.Date.valueOf(Bztime));
			if(i==1){	//������ظ��¼�¼��Ϊ1����ʾ��ӳɹ�
				JOptionPane.showMessageDialog(null, "��ӳɹ�");
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
