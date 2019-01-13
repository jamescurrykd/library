package util;

import java.awt.Image;

import javax.swing.ImageIcon;

public class Iocnsize {
	public static ImageIcon setIconsize(String url,int height,int width) {
		ImageIcon imageIcon = new ImageIcon(url);    // Icon由图片文件形成
		Image image = imageIcon.getImage();                         // 但这个图片太大不适合做Icon
//		    为把它缩小点，先要取出这个Icon的image ,然后缩放到合适的大小
		Image smallImage = image.getScaledInstance(height,width,Image.SCALE_FAST);
//		    再由修改后的Image来生成合适的Icon
		ImageIcon smallIcon = new ImageIcon(smallImage);
		//   最后设置它为按钮的图片
		return smallIcon;
	}
}
