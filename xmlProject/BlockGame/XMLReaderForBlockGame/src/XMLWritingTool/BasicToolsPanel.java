package XMLWritingTool;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import org.w3c.dom.Node;
import XmlBlockGame.XMLReader;

/**
 * ���� �⺻���� ���� Panel
 * 
 * @author ����
 */
public class BasicToolsPanel extends JPanel {
	private GameGroundPanel groundPanel = null;
	
	private XmlString aimObj, attackImgObj, attackingImgObj, userImgObj;
	
	private NumberField ballNumber = new NumberField(3);
	private NumberField attackDelay = new NumberField(3);
	private NumberField ballCountDelay = new NumberField(3);
	private NumberField userLife = new NumberField(3);
	private NumberField winScore = new NumberField(3);
	
	private JSlider xSlider = null;
	private JSlider ySlider = null;
	
	JButton aimBtn = new JButton("AimColor");
	
	/**
	 * BasicToolsPanel ������
	 * @param groundPanel ����ȭ�� �г�
	 */
	public BasicToolsPanel(GameGroundPanel groundPanel) {
		this.groundPanel = groundPanel;
		setLayout(new FlowLayout());
		
		attackImgObj = new XmlString("AttackImg", groundPanel);
		aimObj = new XmlString("Aim",groundPanel,1);
		attackingImgObj = new XmlString("attackImg",groundPanel);
		userImgObj = new XmlString("UserImg", groundPanel);
		
		setTools();
		
	}
	/**
	 * BasicToolsPanel �⺻ ���� component ���� �Լ�
	 */
	private void setTools() {
		add(new JLabel("User Img                                                          "));

		JButton attackingImgBtn = new JButton("attack User Image");
		attackingImgBtn.setToolTipText("������ �� ������ �̹���");
		attackingImgBtn.addActionListener(new ImgActionListener(attackingImgObj,attackingImgBtn));
		add(attackingImgBtn);
		JButton userImgBtn = new JButton("User Image");
		userImgBtn.setToolTipText("�������� ���� �� ������ �̹���");
		userImgBtn.addActionListener(new ImgActionListener(userImgObj,userImgBtn));
		add(userImgBtn);
		
		xSlider = new JSlider(JSlider.HORIZONTAL, 0, 150, 60);
		xSlider.setPaintLabels(false);
		xSlider.setPaintTicks(false);
		xSlider.setPaintTrack(true);
		xSlider.setMajorTickSpacing(50);
		xSlider.setMinorTickSpacing(10);  
		xSlider.setToolTipText("���� �̹����� �ʺ� ��");
		xSlider.addChangeListener(new ChangeListener(){
	    @Override
	    	public void stateChanged(ChangeEvent e) {
	    		JSlider source=(JSlider)e.getSource();
	    		int val = (int) source.getValue();
	    		groundPanel.setUserSize(val,-1);
	    	}
	    });
	    add(xSlider);
	    
	    ySlider = new JSlider(JSlider.HORIZONTAL, 0, 150, 60);
	    ySlider.setPaintLabels(true);
	    ySlider.setPaintTicks(true);
	    ySlider.setPaintTrack(true);
	    ySlider.setMajorTickSpacing(50);
	    ySlider.setMinorTickSpacing(10); 
	    ySlider.setToolTipText("���� �̹����� ���� ��");
	    ySlider.addChangeListener(new ChangeListener(){
	    @Override
	    	public void stateChanged(ChangeEvent e) {
	    		JSlider source=(JSlider)e.getSource();
	    		int val = (int) source.getValue();
	    		groundPanel.setUserSize(-1,val);
	    	}
	    });
	    add(ySlider);
	    
	    add(new JLabel("                                                                         "));
	    add(new JLabel("Attack Img                                                        "));
	    JButton attackImgBtn = new JButton("attack Image");
	    attackImgBtn.setToolTipText("���� ���� �̹���");
		attackImgBtn.addActionListener(new ImgActionListener(attackImgObj,attackImgBtn));
		add(attackImgBtn);
		
		aimBtn.setBackground(new Color(194,211,235));
		aimBtn.setToolTipText("���� �������� ���� ��� ����");
		aimBtn.addActionListener(new ColorchooseListener(aimObj));
		add(aimBtn);
		add(new JLabel("                                                                         "));
		add(new JLabel("Attack Delay"));
		attackDelay.setToolTipText("���� ���� �ӵ�");
		add(attackDelay);
		add(new JLabel("Ball Delay"));
		ballCountDelay.setToolTipText("���� ������ ���� �ӵ� ����");
		add(ballCountDelay);
		add(new JLabel("Attack Number"));
		ballNumber.setToolTipText("���� ����");
		add(ballNumber);
		
		add(new JLabel("                                                                              "));
		add(new JLabel("                     "));
		
		add(new JLabel("User Life"));
		userLife.setToolTipText("������ ���� ��");
		add(userLife);
		add(new JLabel("                  "));
		add(new JLabel("                 "));
		add(new JLabel("Win Score"));
		winScore.setToolTipText("���� �¸� ����");
		add(winScore);
		add(new JLabel("               "));
	}
	/**
	 * ���� ���ۿ� �ʿ��� �⺻ ������ �� �Ǿ����� Ȯ���ϴ� �Լ�
	 * @return �ʿ��� ������ ��� �Ǿ������� true �ƴϸ� false ����
	 */
	public boolean checkBasicXml() {
		//aimObj, attackImgObj, attackingImgObj, userImgObj
		if(userLife.getNumber()<1 || winScore.getNumber()<1)
			return true;
		if(attackImgObj.getObj() == null)
			return true;
		if(attackingImgObj.getObj() == null)
			return true;
		if(userImgObj.getObj() ==  null)
			return true;
		if(aimObj.getObj() ==  null)
			return true;
		if(ballNumber.getNumber()<1 || attackDelay.getNumber()<1)
			return true;
		return false;
	}
	/**
	 * xml�� �����ϴ� ��쿡 ���<br>
	 * xml���� ������ �о� ������ ������ �гο� ǥ���ϴ� �Լ�
	 * @param gamePanelNode ������ ��� ���� xml Node
	 */
	public void setXmlBasic(Node gamePanelNode) {
		Node UserNode = XMLReader.getNode(gamePanelNode, XMLReader.E_USER);
		attackingImgObj.setObj(XMLReader.getAttr(UserNode, "attackImg"));
		userImgObj.setObj(XMLReader.getAttr(UserNode, "img"));
		xSlider.setValue(Integer.parseInt(XMLReader.getAttr(UserNode, "w")));
		ySlider.setValue(Integer.parseInt(XMLReader.getAttr(UserNode, "h")));
		userLife.setText(XMLReader.getAttr(UserNode, "life"));
		
		Node aimNode = XMLReader.getNode(gamePanelNode, XMLReader.E_AIM);
		int r = Integer.parseInt(XMLReader.getAttr(aimNode, "r"));
		int g = Integer.parseInt(XMLReader.getAttr(aimNode, "g"));
		int b = Integer.parseInt(XMLReader.getAttr(aimNode, "b"));
		aimBtn.setBackground(new Color(r,g,b));
		aimObj.setObj("r=\""+r+"\" g=\""+g+"\" b=\""+b+"\"");
		// ���� �̹��� ����
		Node attackNode = XMLReader.getNode(gamePanelNode, XMLReader.E_ATTACK);
		attackImgObj.setObj(XMLReader.getAttr(attackNode, "img"));
		
		ballNumber.setText(XMLReader.getAttr(attackNode, "count"));
		attackDelay.setText(XMLReader.getAttr(attackNode, "delay"));
		ballCountDelay.setText(XMLReader.getAttr(attackNode, "ballCountDelay"));
		Node score = XMLReader.getNode(gamePanelNode, XMLReader.E_FINALSCORE);
		winScore.setText(XMLReader.getAttr(score, "winScore"));
	}
	/**
	 * ������ ������ �� ���Ͽ� tool�� �̿��� ������ �͵��� �Է��ϴ� �Լ�
	 * @param gameBg ���� ���ȭ���� �˾Ƴ��� ���� xmlString ����
	 * @param file ������ ���� �̸�
	 */
	public void xmlBasicWriting(XmlString gameBg, File file) {
		try{
			FileOutputStream fw = new FileOutputStream(file, true); // true�� ���� �ִ� txt ���Ͽ� �̾���� ���� ���� 
			BufferedWriter bufferedWriter =new BufferedWriter(new OutputStreamWriter(fw, "utf-8")); // �ѱ��� �����ϴ� utf-8�� ����
            if(file.isFile() && file.canWrite()){
                //����
                bufferedWriter.write(
                		  "        "+gameBg.getString() +"\r\n"
                		+ "        <Attack count=\""+ballNumber.getNumber()+"\" delay=\""+attackDelay.getNumber()+"\" ballCountDelay=\""+ballCountDelay.getNumber()+"\" img=\""+attackImgObj.getObj()+"\"/>\r\n"
                		+ "        "+aimObj.getString()
                		+ "        <User w=\""+xSlider.getValue()+"\" h=\""+ySlider.getValue()+"\" life=\""+userLife.getNumber()+"\" \r\n"
                		+ "            "+attackingImgObj.getString()+" \r\n"
                		+ "            img=\""+userImgObj.writingObj()+"\" />\r\n"
                		+ "        <FinalScore winScore=\""+ winScore.getNumber()+"\" />\r\n");
                bufferedWriter.flush();
                bufferedWriter.close();
            }
        }catch (IOException e) { return; }
	}
}
/**
 * img ������ ���� JButton�� ActionListener<br>
 *  : Ŭ���� ��� SelectImageDialog ����
 * 
 * @author ����
 */
class ImgActionListener implements ActionListener {
	private XmlString xmlObj;
	/**
	 * ImgActionListener�� ������
	 * @param xmlObj ������ �̹��� ���� xmlString
	 * @param btn ActionListener�� �߰��� btn
	 */
	public ImgActionListener(XmlString xmlObj, JButton btn) {
		this.xmlObj = xmlObj;
		xmlObj.setButton(btn);
		btn.setBackground(new Color(194,211,235));
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		SelectImageDialog selectFileFrame = new SelectImageDialog(xmlObj, ((JButton)e.getSource()).getParent());
	}			
}
/**
 * color ������ ���� JButton�� ActionListener<br>
 *  : Ŭ���� ��� JColorChooser �����Ͽ� �÷� ����
 * 
 * @author ����
 */
class ColorchooseListener implements ActionListener {
	private XmlString xmlObj;
	private JLabel label = null;
	/**
	 * ColorchooseListener�� ������
	 * @param xmlObj ������ �÷� ���� xmlString
	 */
	public ColorchooseListener(XmlString xmlObj) {
		this.xmlObj = xmlObj;
	}
	/**
	 * ColorchooseListener�� ������
	 * @param xmlObj ������ �÷� ���� xmlString
	 * @param label ������ �÷� ������ �����Ű�� ���� label
	 */
	public ColorchooseListener(XmlString xmlObj, JLabel label) {
		this.xmlObj = xmlObj;
		this.label = label;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		Color color = JColorChooser.showDialog(null, "Color", Color.WHITE);
		
		if(color == null) {
			return;
		}
		int r = color.getRed();
		int g = color.getGreen();
		int b = color.getBlue();
		((JButton)e.getSource()).setBackground(color);
		if(label!=null)
			label.setForeground(color);
		xmlObj.setObj("r=\""+r+"\" g=\""+g+"\" b=\""+b+"\"");
	}
}