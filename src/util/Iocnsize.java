package util;

import java.awt.Image;

import javax.swing.ImageIcon;

public class Iocnsize {
	public static ImageIcon setIconsize(String url,int height,int width) {
		ImageIcon imageIcon = new ImageIcon(url);    // Icon��ͼƬ�ļ��γ�
		Image image = imageIcon.getImage();                         // �����ͼƬ̫���ʺ���Icon
//		    Ϊ������С�㣬��Ҫȡ�����Icon��image ,Ȼ�����ŵ����ʵĴ�С
		Image smallImage = image.getScaledInstance(height,width,Image.SCALE_FAST);
//		    �����޸ĺ��Image�����ɺ��ʵ�Icon
		ImageIcon smallIcon = new ImageIcon(smallImage);
		//   ���������Ϊ��ť��ͼƬ
		return smallIcon;
	}
}
