package XMLWritingTool;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.swing.*;
import javax.swing.border.BevelBorder;
/**
 * image ������ �����ִ� Dialog
 * 
 * @author ����
 */
public class SelectImageDialog extends JDialog {
	private ImageIcon icon[] = null;
	private XmlString xmlObj = null;
	private WritingBlock block = null;
	/**
	 * SelectImageDialog ������
	 * @param block �̹��� �����ϰ����ϴ� block
	 * @param container dialog�� ��ġ�� container
	 */
	public SelectImageDialog(WritingBlock block,Container container) {
		super(new JFrame(),"select image",true);
		this.block = block;
		setDialog();
		setLocationRelativeTo(container);
		setVisible(true);
	}
	/**
	 * SelectImageDialog ������
	 * @param xmlObj �̹��� �����ϰ����ϴ� xmlObj
	 * @param container dialog�� ��ġ�� container
	 */
	public SelectImageDialog(XmlString xmlObj,Container container) {
		super(new JFrame(),"select image",true);
		this.xmlObj = xmlObj;
		setDialog();
		setLocationRelativeTo(container);
		setVisible(true);
	}
	/**
	 * SelectImageDialog ������
	 * @param xmlObj �̹��� �����ϰ����ϴ� xmlObj
	 * @param x dialog�� ��ġ�� x��ǥ
	 * @param y dialog�� ��ġ�� y��ǥ
	 */
	public SelectImageDialog(XmlString xmlObj, int x, int y) {
		setLocation(x,y);
		this.xmlObj = xmlObj;
		setDialog();
		setVisible(true);
	}
	/**
	 * dialog �⺻ ���� component ���� �Լ�
	 */
	private void setDialog() {
		setSize(300,300);
		File dir = null;
		FileFilter filter = null;
		setLayout(new GridLayout(5,5));
		dir = new File("src/image");
		filter = new FileFilter() { 
			public boolean accept(File f) { 
				if(f.getName().endsWith("png") || f.getName().endsWith("jpg"))
					return true;
				return false;
			} 
		};
		File files[] = dir.listFiles(filter); 
	
		for(int i=0;i<files.length;i++) {
			ImageIcon icon = null;
			ImgFileLabel label = null;
			icon = new ImageIcon(files[i].toString());
			label = new ImgFileLabel(icon);
			label.addMouseListener(new SelectMouseListener(files[i].toString()));
			add(label);
		}
	}
	/**
	 * �������̺��� Ŭ������ �� ������ ���õǰ� â�� �������� MouseAdapter
	 * 
	 * @author ����
	 */
	class SelectMouseListener extends MouseAdapter {
		private BevelBorder border = new BevelBorder(BevelBorder.RAISED);
		private String fileName;
		/**
		 * SelectMouseListener ������
		 * @param fileName �̹������� �̸�
		 */
		public SelectMouseListener(String fileName) {
			this.fileName = fileName;
		}
		@Override
		public void mouseClicked(MouseEvent e) { 
			if(xmlObj!=null)
				xmlObj.setObj(fileName);
			else
				block.setImg(fileName);
			setVisible(false);
		}
		@Override
		public void mouseEntered(MouseEvent e) {//���콺�� �ش� ������Ʈ ���� �ȿ� ���� ��
			((ImgFileLabel)e.getSource()).setBorder(border);
		}
		@Override
		public void mouseExited(MouseEvent e) {
			((ImgFileLabel)e.getSource()).setBorder(null);
		}
	}
}
/**
 * �̹������̺� (extends JLabel)
 * 
 * @author ����
 */
class ImgFileLabel extends JLabel {
	Image img = null;
	/**
	 * ImgFileLabel ������
	 * @param icon �̹��� icon
	 */
	public ImgFileLabel(ImageIcon icon) {
		img = icon.getImage();
	}
	/**
	 * ImgFileLabel ������
	 * @param icon �̹��� icon
	 * @param x �̹��� x ��ǥ
	 * @param y �̹��� y ��ǥ
	 */
	public ImgFileLabel(ImageIcon icon,int x,int y) {
		//super();
		img = icon.getImage();
		this.setBounds(x,y,30,30);
	}
	@Override
	public void paintComponent(Graphics g) {
		g.drawImage(img, 0, 0, this.getWidth(), this.getHeight(), this);
	}		
}