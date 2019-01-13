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

	private Object[][] getFileStates(List list){//ȡ���ݿ���ͼ�������Ϣ��������
		String[] columnNames = { "���߱��", "��������", "�����Ա�", "��������", "֤������", "��Ա֤��Ч����",
				"��������", "�绰����","Ѻ��","֤������","ְҵ","��֤����" };
		Object[][] results=new Object[list.size()][columnNames.length];//��ά���������������м�¼
		
		for(int i=0;i<list.size();i++){//����list
			Reader reader=(Reader)list.get(i);//ȡ��ͼ���¼
			results[i][0]=reader.getISBN();//����ͼ����
			results[i][1]=reader.getName();//����ͼ���������
			results[i][2]=reader.getSex();//����ͼ������
			results[i][3]=reader.getAge();	//����ͼ������
			results[i][4]=reader.getIdentityCard();//����ͼ������
			results[i][5]=reader.getDate();//���ó�����
			results[i][6]=reader.getMaxNum();//���ó�������
			results[i][7]=reader.getTel();//�����鼮�۸�
			results[i][8]=reader.getKeepMoney();
			results[i][9]=reader.getZj();
			results[i][10]=reader.getZy();
			results[i][11]=reader.getBztime();
		}
		return results;//��Χ��ά������鼮��¼
	         		
	}
	public ReaderModiAndDelIFrame() {
		super();
		final BorderLayout borderLayout = new BorderLayout();//�߿򲼾ֹ�����
		getContentPane().setLayout(borderLayout);//ʹ�ñ߿򲼾ֹ�����
		setIconifiable(true);// ���ô������С��
		setClosable(true);// ���ô���ɹر�
		setTitle("������Ϣ�޸�");// ���ô������
		setBounds(100, 100, 593, 406);// ���ô���λ�úʹ�С

		

		final JPanel mainPanel = new JPanel();//�����
		final BorderLayout borderLayout_1 = new BorderLayout();//�߿򲼾ֹ�����
		borderLayout_1.setVgap(5);//�������֮�䴹ֱ����
		mainPanel.setLayout(borderLayout_1);//ʹ�ñ߿򲼾ֹ�����
		mainPanel.setBorder(new EmptyBorder(5, 10, 5, 10));//���ñ߿�
		getContentPane().add(mainPanel);//���������ӵ�������

		final JScrollPane scrollPane = new JScrollPane();//�������
		mainPanel.add(scrollPane);//�����������ӵ��������

		Object[][] results=getFileStates(ReaderDao.selectreaderInfo());//����鼮��¼
		columnNames = new String[]{"���߱��", "��������", "�����Ա�", "��������", "֤������", "��Ա֤��Ч����",
				"��������", "�绰����","Ѻ��","֤������","ְҵ","��֤����" };//�����б�
		table = new JTable(results,columnNames);//�������
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);//����Ӧ����
		//��굥������е����ݲ����¼�,������е����ݷ����ı�����
		table.addMouseListener(new TableListener());
		scrollPane.setViewportView(table);//�������ӵ����������

		final JPanel bookPanel = new JPanel();//�鼮�޸����
		mainPanel.add(bookPanel, BorderLayout.SOUTH);//��ӵ������׶�
		final GridLayout gridLayout = new GridLayout(0, 6);//���񲼾�
		gridLayout.setVgap(5);//�������֮�䴹ֱ����
		gridLayout.setHgap(5);//�������֮��ƽ�о���
		bookPanel.setLayout(gridLayout);//�����鼮�����岼��
		
		final JLabel ISBNLabel = new JLabel();//����ͼ���ű�ǩ
		ISBNLabel.setHorizontalAlignment(SwingConstants.CENTER);//ˮƽ����
		ISBNLabel.setText("���߱�ţ�");//���ñ�ǩ�ı�
		bookPanel.add(ISBNLabel);//��ӵ��鼮�޸����
		ISBN = new JTextField();//��������ı���
		ISBN.setDocument(new MyDocument(13)); //����ı����������ֵΪ13
		bookPanel.add(ISBN);//��ӵ��鼮�޸����
		
		final JLabel nameLabel = new JLabel();//�����鼮����ǩ
		nameLabel.setHorizontalAlignment(SwingConstants.CENTER);//ˮƽ����
		nameLabel.setText("����������");//���ñ�ǩ�ı�
		bookPanel.add(nameLabel);//��ӵ��鼮�޸����
		name = new JTextField();//��������ı���
		bookPanel.add(name);
		
		
		final JLabel sexLabel = new JLabel();//����������ǩ
		sexLabel.setHorizontalAlignment(SwingConstants.CENTER);//ˮƽ����
		sexLabel.setText("�����Ա�");//���ñ�ǩ�ı�
		bookPanel.add(sexLabel);//��ӵ��鼮�޸����
		sex = new JTextField();//�����ı���
		bookPanel.add(sex);//��ӵ��鼮�޸����
		
		final JLabel ageLabel = new JLabel();//�������߱�ǩ
		ageLabel.setHorizontalAlignment(SwingConstants.CENTER);//ˮƽ����
		ageLabel.setText("�������䣺");//���ñ�ǩ�ı�
		bookPanel.add(ageLabel);//��ӵ��鼮�޸����
		age = new JTextField();//�����ı���
		bookPanel.add(age);
		
		final JLabel idenLabel = new JLabel();//�����������ǩ
		idenLabel.setHorizontalAlignment(SwingConstants.CENTER);//ˮƽ����
		idenLabel.setText("֤�����룺");//���ñ�ǩ�ı�
		bookPanel.add(idenLabel);//��ӵ��鼮�޸����
		identityCard = new JTextField();//�������ı���
		bookPanel.add(identityCard);//��ӵ��鼮�޸����
		
		final JLabel dateLabel = new JLabel();//�����������ڱ�ǩ
		dateLabel.setHorizontalAlignment(SwingConstants.CENTER);//ˮƽ����
		dateLabel.setText("��Ч���ڣ�");//���ñ�ǩ�ı�
		bookPanel.add(dateLabel);//��ӵ��鼮�޸����
		SimpleDateFormat myfmt=new SimpleDateFormat("yyyy-MM-dd");//�������ڸ�ʽ
		date= new JFormattedTextField();//�������������
		date.setValue(myfmt.format(new java.util.Date()));//��������Ϊ��ǰϵͳʱ��
		bookPanel.add(date);//��ӵ��鼮�޸����
		
		final JLabel maxLabel = new JLabel();//�������߱�ǩ
		maxLabel.setHorizontalAlignment(SwingConstants.CENTER);//ˮƽ����
		maxLabel.setText("����������");//���ñ�ǩ�ı�
		bookPanel.add(maxLabel);//��ӵ��鼮�޸����
		maxNum = new JTextField();//�����ı���
		bookPanel.add(maxNum);//��ӵ��鼮�޸����
		
		final JLabel telLabel = new JLabel();//�������߱�ǩ
		telLabel.setHorizontalAlignment(SwingConstants.CENTER);//ˮƽ����
		telLabel.setText("�绰��");//���ñ�ǩ�ı�
		bookPanel.add(telLabel);//��ӵ��鼮�޸����
		tel = new JTextField();//�����ı���
		bookPanel.add(tel);//��ӵ��鼮�޸����
		
		final JLabel pubDateLabel = new JLabel();//�����������ڱ�ǩ
		pubDateLabel.setHorizontalAlignment(SwingConstants.CENTER);//ˮƽ����
		pubDateLabel.setText("��֤���ڣ�");//���ñ�ǩ�ı�
		bookPanel.add(pubDateLabel);//��ӵ��鼮�޸����
		bztime= new JFormattedTextField();//�������������
		bztime.setValue(myfmt.format(new java.util.Date()));//��������Ϊ��ǰϵͳʱ��
		bookPanel.add(bztime);//��ӵ��鼮�޸����
		
		final JLabel priceLabel = new JLabel();//�������۱�ǩ
		priceLabel.setHorizontalAlignment(SwingConstants.CENTER);//ˮƽ����
		priceLabel.setText("Ѻ��");//���ñ�ǩ�ı�
		bookPanel.add(priceLabel);//��ӵ��鼮�޸����
		keepMoney= new JFormattedTextField();//�۸��ı���
		keepMoney.addKeyListener(new NumberListener());//ע�������
		bookPanel.add(keepMoney);//��ӵ��鼮�޸����
		
		final JLabel zjLabel = new JLabel();//�������߱�ǩ
		zjLabel.setHorizontalAlignment(SwingConstants.CENTER);//ˮƽ����
		zjLabel.setText("֤�����ͣ�");//���ñ�ǩ�ı�
		bookPanel.add(zjLabel);//��ӵ��鼮�޸����
		zj = new JTextField();//�����ı���
		bookPanel.add(zj);//��ӵ��鼮�޸����
		
		final JLabel zyLabel = new JLabel();//�������߱�ǩ
		zyLabel.setHorizontalAlignment(SwingConstants.CENTER);//ˮƽ����
		zyLabel.setText("�绰��");//���ñ�ǩ�ı�
		bookPanel.add(zyLabel);//��ӵ��鼮�޸����
		zy = new JTextField();//�����ı���
		bookPanel.add(zy);//��ӵ��鼮�޸����
		
		final JPanel bottomPanel = new JPanel();//�����ײ����
		bottomPanel.setBorder(new LineBorder(
				SystemColor.activeCaptionBorder, 1, false));//���ñ߿�
		getContentPane().add(bottomPanel, BorderLayout.SOUTH);//��ӵ�����׶�
		final FlowLayout flowLayout = new FlowLayout();//�����ֹ�����
		flowLayout.setVgap(2);//�������֮�䴹ֱ����
		flowLayout.setHgap(30);//�������֮��ƽ�о���
		flowLayout.setAlignment(FlowLayout.RIGHT);//�������Ҷ���
		bottomPanel.setLayout(flowLayout);//���õײ���岼��
		
		final JButton updateButton = new JButton();//�����޸İ�ť
		updateButton.addActionListener(new UpdateBookActionListener ());//ע�������
		updateButton.setText("�޸�");//���ð�ť�ı�
		bottomPanel.add(updateButton);//��ӵ��ײ����
		
		final JButton closeButton= new JButton();//�����رհ�ť
		closeButton.addActionListener(new ActionListener() {//ע�������
			public void actionPerformed(final ActionEvent e) {
				doDefaultCloseAction();//�رմ���
			}
		});
		closeButton.setText("�ر�");//���ð�ť�ı�
		bottomPanel.add(closeButton);//��ӵ��ײ����
		
		final JLabel headLogo = new JLabel();//ͼƬ��ǩ
		ImageIcon bookModiAndDelIcon=CreatecdIcon.add("res/table2.jpg");//ͼƬͼ��
		headLogo.setIcon(bookModiAndDelIcon);//���ñ�ǩ��ʾͼƬ
		headLogo.setOpaque(true);//����ͼƬ��ǩ��͸��
		headLogo.setBackground(Color.CYAN);//���ñ�ǩ������ɫ
		headLogo.setPreferredSize(new Dimension(400, 80));//���ñ�ǩ�Ĵ�С
		headLogo.setBorder(new LineBorder(
				SystemColor.activeCaptionBorder, 1, false));//���ñ�ǩ�߿�
		getContentPane().add(headLogo, BorderLayout.NORTH);//��ӵ������϶�
		
		setVisible(true);//��ʾ����ɼ�
}
	class TableListener extends MouseAdapter {
		public void mouseClicked(final MouseEvent e) {
			String ISBNs, names, sexs,ages,identitycards,
					dates,maxnums,tels,keepmoneys,zjs,zys,bztimes;//��������
			int selRow = table.getSelectedRow();//�����ѡ�к�
			ISBNs = table.getValueAt(selRow, 0).toString().trim();//������
			names= table.getValueAt(selRow, 1).toString().trim();//��������
			sexs = table.getValueAt(selRow, 2).toString().trim();//�������
			ages= table.getValueAt(selRow, 3).toString().trim();//�������
			identitycards = table.getValueAt(selRow, 4).toString().trim();//�������
			dates = table.getValueAt(selRow, 5).toString().trim();//��ó�����
			maxnums = table.getValueAt(selRow, 6).toString().trim();//��ó�������
			tels = table.getValueAt(selRow, 7).toString().trim();//��ü۸�
			keepmoneys=table.getValueAt(selRow, 8).toString().trim();
			zjs=table.getValueAt(selRow, 9).toString().trim();
			zys=table.getValueAt(selRow, 10).toString().trim();
			bztimes=table.getValueAt(selRow, 11).toString().trim();
			
			
			ISBN.setText(ISBNs);//��������ı���Ϊ��õ������Ϣ
			keepMoney.setText(keepmoneys);//���������ı���Ϊ��õ�������Ϣ
			date.setText(dates);//���������ı���Ϊ��õ�������Ϣ
			bztime.setText(bztimes);//���������ı���Ϊ��õ�������Ϣ
			name.setText(names);//���ó������ı���Ϊ��õĳ�������Ϣ
			sex.setText(sexs);//���ó��������ı���Ϊ��õĳ���������Ϣ
			age.setText(ages);//���ü۸��ı���Ϊ��õļ۸���Ϣ
			identityCard.setText(identitycards);
			maxNum.setText(maxnums);
			tel.setText(tels);
			zj.setText(zjs);
			zy.setText(zys);
		}
	}
	class UpdateBookActionListener  implements ActionListener {
		public void actionPerformed(final ActionEvent e) {
			if(ISBN.getText().length()==0){//�ж��Ƿ��������鼮���
				JOptionPane.showMessageDialog(null, "����ı��򲻿���Ϊ��");
				return;
			}
			String ISBN1=ISBN.getText().trim();//����鼮���
			String keepmoney1=keepMoney.getText().trim();//���������Ϣ
			String date1=date.getText().trim();//����鼮����
			String bztime1=bztime.getText().trim();//���������Ϣ
			String name1=name.getText().trim();//��ó�������Ϣ
			String sex1=sex.getText().trim();//��ó�������
			String age1=age.getText().trim();//��ü۸���Ϣ
			String identityCard1=identityCard.getText().trim();
			String maxNum1=maxNum.getText().trim();
			String tel1=tel.getText().trim();
			String zj2=zj.getText().trim();
			int zj1=Integer.parseInt(zj2);
			String zy1=zy.getText().trim();
			int i=ReaderDao.Updatereader(name1,sex1,age1,identityCard1,Date.valueOf(date1),
					maxNum1,tel1, Double.parseDouble(keepmoney1),zj1,zy1,ISBN1, Date.valueOf(bztime1));
			if(i==1){//������ظ��¼�¼��Ϊ1����ʾ�޸ĳɹ�
				JOptionPane.showMessageDialog(null, "�޸ĳɹ�");
				Object[][] results=getFileStates(ReaderDao.selectreaderInfo());//���»���鼮��Ϣ
				DefaultTableModel model=new DefaultTableModel();//��ñ��ģ��
				table.setModel(model);//���ñ��ģ��
				model.setDataVector(results, columnNames);//����ģ�����ݺ�����
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