package XMLWritingTool;

import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JPanel;

/**
 * ������ ������ ���� xmlString
 * 
 * @author ����
 */
public class XmlString {
	private String obj=null, objName;
	private int type = -1;
	private JPanel panel = null;
	private JButton btn = null;
	/**
	 * XmlString ������
	 * @param objName obj�� ���� �̸�
	 * @param groundPanel ����ȭ�� �г�
	 * @param type xml�� getString�Լ����� ���� �ɶ� ���ڿ� ����
	 */
	public XmlString(String objName, JPanel groundPanel, int type) {
		this.objName = objName;
		this.panel = groundPanel;
		this.type = type;
	}
	/**
	 * XmlString ������
	 * @param objName obj�� ���� �̸�
	 * @param groundPanel ����ȭ�� �г�
	 */
	public XmlString(String objName, JPanel groundPanel) {
		this.objName = objName;
		this.panel = groundPanel;
	}
	/**
	 * xml�� ���� ������ �� ��ư �� �������ֱ� ���� ��ư ����ϴ� �Լ�
	 * @param btn button
	 */
	public void setButton(JButton btn) {
		this.btn = btn;
	}
	/**
	 * xml ���ڿ��� �ٲ㼭 �����ϴ� �Լ�
	 * @return xml�� �ۼ��Ǵ� Ÿ��(�����ڿ��� ��ϵ� Ÿ��)�� ���� �����ؼ� ����
	 */
	public String getString() {
		String obj = this.obj;
		if(obj==null)
			obj="";
		if(type == 0)
			return "<"+objName+">"+obj+"</"+objName+">\r\n";
		else if(type == 1)
			return "<"+objName+" "+obj+"/>\r\n";
		//return obj;
		return objName+"=\""+obj+"\"";
	}
	/**
	 * xmlString�� ��(obj) �����ϴ� �Լ�
	 * @param obj obj ���� ��
	 */
	public void setObj(String obj) { 
		this.obj = obj; 
		if(panel instanceof GameGroundPanel)
			((GameGroundPanel) panel).setGroundPanel(this);
		if(panel instanceof GameInitToolsPanel)
			if(objName.equals("InitBg"))
				((GameInitToolsPanel) panel).setBgImg(obj);
		if(btn!=null)
			btn.setBackground(Color.LIGHT_GRAY);
	}
	/**
	 * xml�� ���� ���� obj �����ϴ� �Լ�
	 * @return obj
	 */
	public String writingObj() {
		if(obj==null)
			return " ";
		return obj;
	}
	/**
	 * xmlString�� objName �����ϴ� �Լ�
	 * @return objName
	 */
	public String getObjName() { return objName; }
	/**
	 * xmlString�� obj �����ϴ� �Լ�
	 * @return obj
	 */
	public String getObj() { return obj; }
	/**
	 * objName�� s ���� ������ Ȯ���ϴ� �Լ�
	 * @param s objName�� ���� ���ڿ�
	 * @return ������ �� �� ���ڿ��� ���� �� true, �ƴ� �� false
	 */
	public boolean equalsObjName(String s) { return (s.equals(objName)); }
}

