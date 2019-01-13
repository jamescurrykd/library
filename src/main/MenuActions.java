package main;

import java.awt.event.ActionEvent;
import java.util.HashMap;
import java.util.Map;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JInternalFrame;
import iframe.BookAddIFrame;
import iframe.BookModiAndDelIFrame;
import iframe.BooktypeAddIFrame;
import iframe.BooktypeModiAndDelIFrame;
import iframe.ReaderAddIFrame;
import iframe.ReaderModiAndDelIFrame;

/**
 * �˵��Ͱ�ť��Action����
 * 
 */
public class MenuActions {
	private static Map<String, JInternalFrame> frames; // �Ӵ��弯��
	public static BookModiAction BOOK_MODIFY; // ͼ����Ϣ�޸Ĵ��嶯��
	public static BookAddAction BOOK_ADD; // ͼ����Ϣ��Ӵ��嶯��
	public static BooktypeModiAction BOOKTYPE_MODIFY;
	public static BooktypeAddAction BOOKTYPE_ADD;
	public static ReaderAddAction READER_ADD;
	public static ReaderModiAction REAADER_MODIFY;
	public static ExitAction EXIT; // ϵͳ�˳�����

	static {
		frames = new HashMap<String, JInternalFrame>();
		BOOK_MODIFY = new BookModiAction();
		BOOK_ADD = new BookAddAction();
		BOOKTYPE_ADD=new BooktypeAddAction();
		BOOKTYPE_MODIFY=new BooktypeModiAction();
		READER_ADD=new ReaderAddAction();
		REAADER_MODIFY=new ReaderModiAction();
		EXIT = new ExitAction();
	}
	//ͼ���޸���ɾ��
	private static class BookModiAction extends AbstractAction {
		BookModiAction() {
			super("ͼ���޸�", null);
			putValue(Action.LONG_DESCRIPTION, "�޸ĺ�ɾ��ͼ����Ϣ");
			putValue(Action.SHORT_DESCRIPTION, "ͼ���޸�");
		}
		public void actionPerformed(ActionEvent e) {
			if (!frames.containsKey("ͼ���޸�")||frames.get("ͼ���޸�").isClosed()) {
				BookModiAndDelIFrame iframe=new BookModiAndDelIFrame();
				frames.put("ͼ���޸�", iframe);
				Library.addIFame(frames.get("ͼ���޸�"));
			}
		}
	}
	private static class BookAddAction extends AbstractAction {				// ͼ����Ϣ��ӣ������Ѿ�ʵ�֣������
		BookAddAction() {

			super("ͼ����Ϣ���", null);
			//super();
			putValue(Action.LONG_DESCRIPTION, "Ϊͼ�������µ�ͼ����Ϣ");
			putValue(Action.SHORT_DESCRIPTION, "ͼ����Ϣ���");
		}
		public void actionPerformed(ActionEvent e) {
			if (!frames.containsKey("ͼ����Ϣ���")||frames.get("ͼ����Ϣ���").isClosed()) {
				BookAddIFrame iframe = new BookAddIFrame();
				frames.put("ͼ����Ϣ���", iframe);
				Library.addIFame(frames.get("ͼ����Ϣ���"));
			}
		}
	}
	private static class BooktypeAddAction extends AbstractAction {				// ͼ����Ϣ��ӣ������Ѿ�ʵ�֣������
		BooktypeAddAction() {

			super("ͼ��������", null);
			//super();
			putValue(Action.LONG_DESCRIPTION, "Ϊͼ�������µ�ͼ�����");
			putValue(Action.SHORT_DESCRIPTION, "ͼ��������");
		}
		public void actionPerformed(ActionEvent e) {
			if (!frames.containsKey("ͼ��������")||frames.get("ͼ��������").isClosed()) {
				BooktypeAddIFrame iframe = new BooktypeAddIFrame();
				frames.put("ͼ��������", iframe);
				Library.addIFame(frames.get("ͼ��������"));
			}
		}
	}
	private static class BooktypeModiAction extends AbstractAction {
		BooktypeModiAction() {
			super("ͼ������޸�", null);
			putValue(Action.LONG_DESCRIPTION, "�޸ĺ�ɾ��ͼ�����");
			putValue(Action.SHORT_DESCRIPTION, "ͼ������޸�");
		}
		public void actionPerformed(ActionEvent e) {
			if (!frames.containsKey("ͼ������޸�")||frames.get("ͼ������޸�").isClosed()) {
				BooktypeModiAndDelIFrame iframe=new BooktypeModiAndDelIFrame();
				frames.put("ͼ������޸�", iframe);
				Library.addIFame(frames.get("ͼ������޸�"));
			}
		}
	}
	private static class ReaderAddAction extends AbstractAction {				// ͼ����Ϣ��ӣ������Ѿ�ʵ�֣������
		ReaderAddAction() {

			super("������Ϣ���", null);
			//super();
			putValue(Action.LONG_DESCRIPTION, "Ϊͼ�������µĶ�����Ϣ");
			putValue(Action.SHORT_DESCRIPTION, "������Ϣ���");
		}
		public void actionPerformed(ActionEvent e) {
			if (!frames.containsKey("������Ϣ���")||frames.get("������Ϣ���").isClosed()) {
				ReaderAddIFrame iframe = new ReaderAddIFrame();
				frames.put("������Ϣ���", iframe);
				Library.addIFame(frames.get("������Ϣ���"));
			}
		}
	}
	private static class ReaderModiAction extends AbstractAction {
		ReaderModiAction() {
			super("������Ϣ�޸�", null);
			putValue(Action.LONG_DESCRIPTION, "�޸ĺ�ɾ��������Ϣ");
			putValue(Action.SHORT_DESCRIPTION, "������Ϣ�޸�");
		}
		public void actionPerformed(ActionEvent e) {
			if (!frames.containsKey("������Ϣ�޸�")||frames.get("������Ϣ�޸�").isClosed()) {
				ReaderModiAndDelIFrame iframe=new ReaderModiAndDelIFrame();
				frames.put("������Ϣ�޸�", iframe);
				Library.addIFame(frames.get("������Ϣ�޸�"));
			}
		}
	}
	private static class ExitAction extends AbstractAction { // �˳�ϵͳ����
		public ExitAction() {
			super("�˳�ϵͳ", null);
			putValue(Action.LONG_DESCRIPTION, "�˳�ͼ��ݹ���ϵͳ");
			putValue(Action.SHORT_DESCRIPTION, "�˳�ϵͳ");
		}
		public void actionPerformed(final ActionEvent e) {
			System.exit(0);
		}
	}

	private MenuActions() {
		super();
	}

}
