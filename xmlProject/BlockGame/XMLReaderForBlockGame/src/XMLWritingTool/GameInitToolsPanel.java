package XMLWritingTool;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.swing.*;
import javax.swing.event.*;
import org.w3c.dom.Node;
import XmlBlockGame.XMLReader;

/**
 * ���� ��Ʈ��ȭ�� ���� ���� Panel
 * @author ����
 *
 */
public class GameInitToolsPanel extends JPanel {
	private JTextField start = new JTextField();
	private JTextField win = new JTextField();
	private JTextField lose = new JTextField();
	
	private XmlString initBg = new XmlString("InitBg",this,0);
	private XmlString fontColor = new XmlString("fontColor",this);
	private JComboBox fontBox = null;
	private JSlider xSlider = new JSlider(JSlider.HORIZONTAL, 0, 100, 30);
	private Image bgImg = null;
	private JButton fontColorBtn = new JButton("font Color");
	private InitLabel fontLabel = new InitLabel("font");
	
	private JLabel startSentence = new JLabel("Start Sentence");
	private JLabel winSentence = new JLabel("Win Sentence");
	private JLabel loseSentence = new JLabel("Lose Sentence");
    
	/**
	 * GameInitToolsPanel ������
	 */
	public GameInitToolsPanel() {
		setLayout(null);
		JButton initBgBtn = new JButton("Init BackGround");
		initBgBtn.setSize(150,25);
		initBgBtn.setLocation(70,10);
		initBgBtn.addActionListener(new ImgActionListener(initBg,initBgBtn));
		add(initBgBtn);
		
		fontLabel.setSize(300,80);
		fontLabel.setLocation(-10,30);
		fontLabel.setHorizontalAlignment(JLabel.CENTER);
		add(fontLabel);
		GraphicsEnvironment ge = null;
        ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        Font[] fonts = ge.getAllFonts();
        String fontName[] = new String[fonts.length];
        for (int i = 0; i < fonts.length; i++)
        	fontName[i] = fonts[i].getFontName();
        fontBox = new JComboBox(fontName);
        
        fontBox.setSize(200,20);
        fontBox.setLocation(40,130);
        add(fontBox);
		fontColorBtn.setBackground(new Color(194,211,235));
		fontColorBtn.addActionListener(new ColorchooseListener(fontColor,fontLabel));
		fontColorBtn.setSize(100,25);
		fontColorBtn.setLocation(90,170);
		add(fontColorBtn);
		
		xSlider.setPaintLabels(false);
		xSlider.setPaintTicks(true);
		xSlider.setPaintTrack(true);
		xSlider.setOpaque(false);
		xSlider.setMajorTickSpacing(50);
		xSlider.setMinorTickSpacing(10);  
		
		xSlider.setSize(200,30);
		xSlider.setLocation(40,210);
	    add(xSlider);
	    
	    //GameSentence start="��Ÿ�� ���� ������ ��������" win="������ ������ �����߾�!" lose="������ ���ڶ�,,,"
	    
	    startSentence.setSize(200,20);
	    startSentence.setLocation(10,250);
	    add(startSentence);
	    start.setSize(200,20);
	    start.setLocation(40,270);
	    start.setOpaque(false);
	    add(start);
	    
	    winSentence.setSize(200,20);
	    winSentence.setLocation(10,300);
	    add(winSentence);
	    win.setSize(200,20);
	    win.setLocation(40,320);
	    win.setOpaque(false);
	    add(win);
	    
	    loseSentence.setSize(200,20);
	    loseSentence.setLocation(10,350);
	    add(loseSentence);
	    lose.setSize(200,20);
	    lose.setLocation(40,370);
	    lose.setOpaque(false);
	    add(lose);
	    
	    fontBox.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                fontLabel.setFont(new Font(fontBox.getSelectedItem().toString(),Font.BOLD,xSlider.getValue()));
           }
      });
		xSlider.addChangeListener(new ChangeListener(){
	    @Override
	    	public void stateChanged(ChangeEvent e) {
	    		fontLabel.setFont(new Font(fontBox.getSelectedItem().toString(),Font.BOLD,xSlider.getValue()));
	    	}
	    });
	}
	/**
	 * ����̹��� �ٲٴ� �Լ�
	 * @param bgName ��� �̹��� ���� �̸�
	 */	
	public void setBgImg(String bgName) {
		ImageIcon BgIcon = new ImageIcon(bgName);
		bgImg = BgIcon.getImage();
		repaint();
	}
	/**
	 * ���� ���ۿ� �ʿ��� �⺻ ������ �� �Ǿ����� Ȯ���ϴ� �Լ�
	 * @return �ʿ��� ������ ��� �Ǿ������� true �ƴϸ� false ����
	 */
	public boolean checkInitXml() {
		if(initBg.getObj()==null)
			return true;
		if(fontColor.getObj()==null)
			return true;
		return false;
	}
	/**
	 * xml�� �����ϴ� ��쿡 ���<br>
	 * xml���� ������ �о� ������ ������ �гο� ǥ���ϴ� �Լ�
	 * @param initGameNode ������ ��� ���� xml Node
	 */
	public void setXmlInit(Node initGameNode) {
		Node fontNode = XMLReader.getNode(initGameNode, XMLReader.E_FONT);
		Node sentenceNode = XMLReader.getNode(initGameNode, XMLReader.E_GAMESENTENCE);
		int r = Integer.parseInt(XMLReader.getAttr(fontNode, "r"));
		int g = Integer.parseInt(XMLReader.getAttr(fontNode, "g"));
		int b = Integer.parseInt(XMLReader.getAttr(fontNode, "b"));
		Color color = new Color(r,g,b);
		fontColor.setObj("r=\""+r+"\" g=\""+g+"\" b=\""+b+"\"");
		fontLabel.setForeground(color);
		
		String fontName = XMLReader.getAttr(fontNode, "font");
		int fontSize = Integer.parseInt(XMLReader.getAttr(fontNode, "fontSize"));
		xSlider.setValue(fontSize);
		int i=0;
		for(i=0;i<fontBox.getItemCount();i++)
			if(fontBox.getItemAt(i).equals(fontName))
				break;
		if(i!=fontBox.getItemCount())
			fontBox.setSelectedIndex(i);
		fontLabel.setFont(new Font(fontBox.getSelectedItem().toString(),Font.BOLD,xSlider.getValue()));

		start.setText(XMLReader.getAttr(sentenceNode, "start"));
		win.setText(XMLReader.getAttr(sentenceNode, "win"));
		lose.setText(XMLReader.getAttr(sentenceNode, "lose"));
		
		Node initBgNode = XMLReader.getNode(initGameNode, XMLReader.INIT_BG);
		initBg.setObj(initBgNode.getTextContent());
	}
	/**
	 * ������ ������ �� ���Ͽ� tool�� �̿��� ������ �͵��� �Է��ϴ� �Լ�
	 * @param file ������ ���� �̸�
	 */
	public void xmlInitWriting(File file) {
		try{
			FileOutputStream fw = new FileOutputStream(file, true); // true�� ���� �ִ� txt ���Ͽ� �̾���� ���� ���� 
			BufferedWriter bufferedWriter =new BufferedWriter(new OutputStreamWriter(fw, "utf-8"));
            if(file.isFile() && file.canWrite()){
                //����
                bufferedWriter.write(
                		  "    <InitPanel>\r\n"
                		+ "        <Font font=\""+fontBox.getSelectedItem().toString()+"\" fontSize=\""+xSlider.getValue()+"\"  "+ fontColor.writingObj() +"  />\r\n"
                		+ "        <GameSentence start=\""+start.getText()+"\" win=\""+win.getText()+"\" lose=\""+lose.getText()+"\" />\r\n"
                		+ "        "+initBg.getString()
                		+ "    </InitPanel>\r\n");
                bufferedWriter.flush();
                bufferedWriter.close();
            }
        }catch (IOException e) {
            return;
        }
	}
	@Override
	public void paintComponent(Graphics g) {
		if(bgImg != null)
			g.drawImage(bgImg, 0, 0, this.getWidth(), this.getHeight(), this);
		else
			super.paintComponent(g);
	}
	/**
	 * initLabel�� �� ���̺��� ����� �� �ٸ� ���̺� ��ȭ��Ű�� ���̺� (extends JLabel)
	 *
	 * @author ����
	 */
	public class InitLabel extends JLabel {
		/**
		 * InitLabel ������
		 * @param s ���̺� text
		 */
		public InitLabel(String s) {
			super(s);
		}
		@Override
		public void setFont(Font font) {
			super.setFont(font);
			Font newFont = new Font(font.getFontName(),Font.BOLD, 15);

			start.setFont(newFont);
			win.setFont(newFont);
			lose.setFont(newFont);
		}
		@Override
		public void setForeground(Color color) {
			super.setForeground(color);

			start.setForeground(color);
			win.setForeground(color);
			lose.setForeground(color);
		}
	}
}
