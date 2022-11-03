package XMLWritingTool;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.swing.*;
import org.w3c.dom.Node;
import XmlBlockGame.XMLReader;

/**
 * ���� ���Ǽ��� ���� Panel
 * 
 * @author ����
 */
public class MusicToolsPanel extends JPanel {
	private GameGroundPanel groundPanel;
	private XmlString hitSoundObj, removeSoundObj, dieSoundObj;
	private XmlString winEndSoundObj, loseEndSoundObj, bgmObj;
	private JTabbedPane pane = new JTabbedPane(JTabbedPane.SCROLL_TAB_LAYOUT);
	private JScrollPane scrollPane;
	private int tabCount = 0;
	/**
	 * MusicToolsPanel ������
	 * @param groundPanel ����ȭ�� �г�
	 */
	public MusicToolsPanel(GameGroundPanel groundPanel) {
		this.groundPanel = groundPanel;
		
		setLayout(null);
		
		hitSoundObj = new XmlString("hitSound", groundPanel); 
		removeSoundObj = new XmlString("removeSound", groundPanel);
		dieSoundObj = new XmlString("dieSound", groundPanel);
		winEndSoundObj = new XmlString("winEndSound", groundPanel); 
		loseEndSoundObj = new XmlString("loseEndSound", groundPanel);
		bgmObj = new XmlString("backGroundSound", groundPanel);
		
		JButton hitSoundBtn = new JButton("Hit");
		hitSoundBtn.setSize(80,20);
		hitSoundBtn.setLocation(5,10);
		hitSoundBtn.setToolTipText("����� ���� �¾��� �� ���� �Ҹ�");
		hitSoundBtn.addActionListener(new MusicActionListener(hitSoundObj,hitSoundBtn));
		add(hitSoundBtn);
		
		JButton removeSoundBtn = new JButton("Remove");
		removeSoundBtn.setSize(80,20);
		removeSoundBtn.setLocation(95,10);
		removeSoundBtn.setToolTipText("����� ���� ������ ȹ������ �� ���� �Ҹ�");
		removeSoundBtn.addActionListener(new MusicActionListener(removeSoundObj,removeSoundBtn));
		add(removeSoundBtn);
		
		JButton dieSoundBtn = new JButton("Die");
		dieSoundBtn.setSize(80,20);
		dieSoundBtn.setLocation(185,10);
		dieSoundBtn.setToolTipText("������ ����� �پ��� �Ҹ�");
		dieSoundBtn.addActionListener(new MusicActionListener(dieSoundObj,dieSoundBtn));
		add(dieSoundBtn);
		
		JButton winEndSoundBtn = new JButton("Win");
		winEndSoundBtn.setSize(80,20);
		winEndSoundBtn.setLocation(5,40);
		winEndSoundBtn.setToolTipText("������ �¸��� ������ �� ���� ���� ����");
		winEndSoundBtn.addActionListener(new MusicActionListener(winEndSoundObj,winEndSoundBtn));
		add(winEndSoundBtn);
		
		JButton loseEndSoundBtn = new JButton("Lose");
		loseEndSoundBtn.setSize(80,20);
		loseEndSoundBtn.setLocation(95,40);
		loseEndSoundBtn.setToolTipText("������ �й�� ������ �� ���� ���� ����");
		loseEndSoundBtn.addActionListener(new MusicActionListener(loseEndSoundObj,loseEndSoundBtn));
		add(loseEndSoundBtn);
		
		JButton backGroundSoundBtn = new JButton("BGM");
		backGroundSoundBtn.setSize(80,20);
		backGroundSoundBtn.setLocation(185,40);
		backGroundSoundBtn.setToolTipText("���� ��� ����");
		backGroundSoundBtn.addActionListener(new MusicActionListener(bgmObj,backGroundSoundBtn));
		add(backGroundSoundBtn);
		
		JSplitPane hPane = new JSplitPane();
		hPane.setOrientation(JSplitPane.VERTICAL_SPLIT);
		hPane.setEnabled(false); // splitPane ��ġ ����
		hPane.setLocation(0,80);
		hPane.setSize(280,300);
		hPane.setTopComponent(pane);
		hPane.setBottomComponent(null);
		add(hPane);
	}
	/**
	 * ���� ���ۿ� �ʿ��� �⺻ ������ �� �Ǿ����� Ȯ���ϴ� �Լ�
	 * @return �ʿ��� ������ ��� �Ǿ������� true �ƴϸ� false ����
	 */
	public boolean checkMusicXml() {
		//XmlString hitSoundObj, removeSoundObj, dieSoundObj;
		//XmlString winEndSoundObj, loseEndSoundObj, bgmObj;
		if(bgmObj.getObj() ==  null)
			return true;
		return false;
	}
	/**
	 * xml�� �����ϴ� ��쿡 ���<br>
	 * xml���� ������ �о� ������ ������ �гο� ǥ���ϴ� �Լ�
	 * @param musicPanelNode ������ ��� ���� xml Node
	 */
	public void setXmlMusic(Node musicPanelNode) {
		Node gameSoundNode = XMLReader.getNode(musicPanelNode, XMLReader.E_GAMESOUND);
		String bgm, loseEndBgm, winEndBgm, hit, remove, die;
		bgm = XMLReader.getAttr(gameSoundNode, "backGroundSound");
		bgmObj.setObj(bgm);
		loseEndBgm = XMLReader.getAttr(gameSoundNode, "loseEndSound");
		loseEndSoundObj.setObj(loseEndBgm);
		winEndBgm = XMLReader.getAttr(gameSoundNode, "winEndSound");
		winEndSoundObj.setObj(winEndBgm);
		Node ballNode = XMLReader.getNode(musicPanelNode, XMLReader.E_BALLSOUND);
		hit = XMLReader.getAttr(ballNode, "hitSound");
		hitSoundObj.setObj(hit);
		remove = XMLReader.getAttr(ballNode, "removeSound");
		removeSoundObj.setObj(remove);
		die = XMLReader.getAttr(ballNode, "dieSound");
		dieSoundObj.setObj(die);
	}
	/**
	 * ������ ������ �� ���Ͽ� tool�� �̿��� ������ �͵��� �Է��ϴ� �Լ�
	 *
	 * @param file ������ ���� �̸�
	 */
	public void xmlMusicWriting(File file) {
		try{
			FileOutputStream fw = new FileOutputStream(file, true); // true�� ���� �ִ� txt ���Ͽ� �̾���� ���� ���� 
			BufferedWriter bufferedWriter =new BufferedWriter(new OutputStreamWriter(fw, "utf-8"));
            if(file.isFile() && file.canWrite()){
                //����
            	//XmlString hitSoundObj, removeSoundObj, dieSoundObj;
        		//XmlString winEndSoundObj, loseEndSoundObj, bgmObj;
                bufferedWriter.write(
                		  "    <GamePanel>\r\n"
                		+ "        <Sound> \r\n"
                		+ "            <BallSound "+hitSoundObj.getString()+" \r\n"
                		+ "                "+removeSoundObj.getString()+" \r\n"
                		+ "                "+dieSoundObj.getString()+" />\r\n"
                		+ "            <GameSound "+winEndSoundObj.getString()+" \r\n"
                		+ "                "+loseEndSoundObj.getString()+" \r\n"
                		+ "                "+bgmObj.getString()+" />\r\n"
                		+ "        </Sound>\r\n");
                bufferedWriter.flush();
                bufferedWriter.close();
            }
        }catch (IOException e) {
            return;
        }
	}
	/**
	 * music ������ ���� JButton�� ActionListener<br>
	 *  : Ŭ���� ��� SelectImageDialog ����
	 * 
	 * @author ����
	 */
	private class MusicActionListener implements ActionListener {
		private XmlString xmlObj;
		private boolean clickCheck = false;
		private JScrollPane scroll = null;
		/**
		 * MusicActionListener�� ������
		 * @param xmlObj ������ �̹��� ���� xmlString
		 * @param btn ActionListener�� �߰��� btn
		 */
		public MusicActionListener(XmlString xmlObj, JButton btn) {
			this.xmlObj = xmlObj;
			xmlObj.setButton(btn);
			btn.setBackground(new Color(194,211,235));
		}
		@Override
		public void actionPerformed(ActionEvent e) {
			if(!clickCheck) {
				SelectMusicPanel selectMusicPanel =new SelectMusicPanel(xmlObj);
				scroll = new JScrollPane(selectMusicPanel);
				scroll.setPreferredSize(new Dimension(100, 100));
				pane.add(scroll, xmlObj.getObjName());
				pane.setSelectedIndex(tabCount++);
				clickCheck = true;
			}
			else {
				pane.remove(scroll);
				clickCheck = false;
				tabCount--;
			}
		}			
	}
}
