package main;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JToolBar;
import javax.swing.UIManager;
import javax.swing.WindowConstants;
import javax.swing.border.BevelBorder;

import iframe.BookLoginIFrame;
import util.CreatecdIcon;
import util.Iocnsize;

/**
 * ������
 * 
 */
public class Library extends JFrame {
	private static final JDesktopPane 
				DESKTOP_PANE = new JDesktopPane();//���洰��
	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel(UIManager
				.getSystemLookAndFeelClassName());//����ϵͳ�������
			new BookLoginIFrame();//��¼����
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	public static void addIFame(JInternalFrame iframe) { // ����Ӵ���ķ���
		DESKTOP_PANE.add(iframe);	//�����Ӵ���
	}
	public Library() {
		super();
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);////���ùرհ�ť�����¼�
		Toolkit tool = Toolkit.getDefaultToolkit();				//���Ĭ�ϵĹ�����
		Dimension screenSize = tool.getScreenSize();			//�����Ļ�Ĵ�С
		setSize(800, 600);										//���ô����С
		setLocation((screenSize.width - getWidth()) / 2,
				(screenSize.height - getHeight()) / 2);			//���ô���λ��
		setTitle("ͼ��ݹ���ϵͳ");								//���ô������
		/**
		 * ��Ӳ˵����͹�����
		 */
		ImageIcon frameIcon = CreatecdIcon.add("res/bbg2.jpg");
		final JLabel label = new JLabel();	//����һ����ǩ��������ʾͼƬ
		label.setBounds(0, 0, 800,600);		//���ô���Ĵ�С��λ��		
		label.setIcon(frameIcon); // ���屳��
		JMenuBar menuBar = createMenu(); 	//���ô����˵����ķ���
		setJMenuBar(menuBar);				//���ò˵���
		JToolBar toolBar = createToolBar(); // ���ô����������ķ���
//		DESKTOP_PANE.addComponentListener(new ComponentAdapter() {
//			public void componentResized(final ComponentEvent e) {
//				Dimension size = e.getComponent().getSize();//��������С
//				label.setSize(e.getComponent().getSize());//���ñ�ǩ��С
//				label.setText("<html><img width=" + size.width + " height="
//						+ size.height + " src='"
//						+ this.getClass().getResource("res/bgg.jpg")
//						+ "'></html>");//���ñ�ǩ�ı�
//			}
//		});
		DESKTOP_PANE.add(label,new Integer(Integer.MIN_VALUE));//����ǩ��ӵ����洰��
		getContentPane().add(DESKTOP_PANE);//�����洰����ӵ���������
		getContentPane().add(toolBar, BorderLayout.NORTH);//���ù�����
	}
	/**
	 * ����������
	 * 
	 * @return JToolBar
	 */
	private JToolBar createToolBar() { // �����������ķ���
		JToolBar toolBar = new JToolBar();		//��ʼ��������
		toolBar.setFloatable(false);			//�����Ƿ�����ƶ�������
		toolBar.setBorder(new BevelBorder(BevelBorder.RAISED));//���ñ߿�
		
		JButton bookAddButton=new JButton(MenuActions.BOOK_ADD);//ͼ����Ϣ��Ӱ�ť	
		bookAddButton.setIcon(CreatecdIcon.add("res/tool7.png"));//���ð�ťͼ��
		bookAddButton.setHideActionText(true);//��ʾ��ʾ�ı�
		toolBar.add(bookAddButton);//��ӵ���������
		
		JButton bookModiAndDelButton=new JButton(MenuActions.BOOK_MODIFY);//ͼ����Ϣ�޸İ�ť
		bookModiAndDelButton.setIcon(CreatecdIcon.add("res/tool6.png"));//���ð�ťͼ��
		bookModiAndDelButton.setHideActionText(true);//��ʾ��ʾ�ı�
		toolBar.add(bookModiAndDelButton);//��ӵ���������
		
		JButton ExitButton=new JButton(MenuActions.EXIT);//�˳�ϵͳ��ť
		ExitButton.setIcon(CreatecdIcon.add("res/tool4.png"));//���ð�ťͼ��
		ExitButton.setHideActionText(true);//��ʾ��ʾ�ı�
		toolBar.add(ExitButton);//��ӵ���������
		return toolBar;
	}
	/**
	 * �����˵���
	 */
	private JMenuBar createMenu() { // �����˵����ķ���
		JMenuBar menuBar = new JMenuBar();//����������
		JMenu baseMenu = new JMenu();// ��ʼ����������ά���˵�
		baseMenu.setIcon(CreatecdIcon.add("res/menu2.png"));//���ò˵�ͼ��
		JMenu readerManagerMItem = new JMenu("������Ϣ����");//����������Ϣ�����Ӳ˵�
		readerManagerMItem.add(MenuActions.READER_ADD);
		readerManagerMItem.add(MenuActions.REAADER_MODIFY);
		
		JMenu bookTypeManageMItem = new JMenu("ͼ��������");//����ͼ���������Ӳ˵�
		bookTypeManageMItem.add(MenuActions.BOOKTYPE_ADD);
		bookTypeManageMItem.add(MenuActions.BOOKTYPE_MODIFY);
		
		JMenu menu = new JMenu("ͼ����Ϣ����");//����ͼ����Ϣ�����Ӳ˵�
		menu.add(MenuActions.BOOK_ADD);//���ͼ����Ϣ��Ӳ˵���
		menu.add(MenuActions.BOOK_MODIFY);//���ͼ����Ϣ�޸Ĳ˵���
		baseMenu.add(readerManagerMItem);//��Ӷ�����Ϣ�����Ӳ˵�
		baseMenu.add(bookTypeManageMItem);//���ͼ���������Ӳ˵�
		baseMenu.add(menu);				//���ͼ����Ϣ�����Ӳ˵�
		baseMenu.addSeparator();		//��ӷָ���
		baseMenu.add(MenuActions.EXIT);//����˳�ϵͳ�˵���
		menuBar.add(baseMenu); // ��ӻ�������ά���˵����˵���
		
		return menuBar;
	}
}
