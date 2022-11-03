package XMLWritingTool;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.*;

/**
 * ���� Block Type���� ���� Panel
 * 
 * @author ����
 */
public class BlockTypeSetPanel extends JPanel {
	private JCheckBox gone = null;
	private JCheckBox moveSide = null;
	private JCheckBox moveDown = null;
	private JTabbedPane pane = new JTabbedPane();
	private SetGone setGone = new SetGone();
	private SetSideMove setSideMove = new SetSideMove();
	private int tabCount = 0, width = 0;
	private WritingBlock block;
	/**
	 * BlockTypeSetPanel ������
	 * 
	 * @param block type�� �����ϰ��� �ϴ� block
	 * @param w JTabbedPane�� ����
	 * @param h JTabbedPane�� ����
	 */
	public BlockTypeSetPanel(WritingBlock block, int w, int h) {
		super();
		setLayout(null);
		this.width = w;
		this.block = block;
		splitPane(w, h);
		setTab();
	}
	/**
	 * BlockToolsPanel�� �ִ� JTabbedPane�� ��ġ �����ϴ� �Լ�<br>
	 *  : Tab���� Bloc Type ����
	 */
	private void splitPane(int w, int h) {
		JSplitPane hPane = new JSplitPane();
		hPane.setOrientation(JSplitPane.VERTICAL_SPLIT);
		hPane.setEnabled(false); // splitPane ��ġ ����
		hPane.setLocation(0,30);
		hPane.setSize(w,h);
		
		hPane.setTopComponent(pane);
		hPane.setBottomComponent(null);
		add(hPane);
	}
	/**
	 * JTabbedPane�� block�� ���ǿ� ���� �����ϴ� �Լ�<br>
	 *  : block�� ������ ���� Ȯ���� �� �ְ� ����
	 */
	private void setTab() {
		if(block.goneCheck()) {
			gone = new JCheckBox("Gone",true);
			setGone.setGoneType(block);
    		 pane.add("Gone",setGone);
    		 pane.setSelectedIndex(tabCount++);
		}
		else
			gone = new JCheckBox("Gone",false);
		if(block.moveSideCheck()) {
			moveSide = new JCheckBox("Side",true);
			setSideMove.setSideMoveType(block);
			pane.add("SideMove",setSideMove);
			pane.setSelectedIndex(tabCount++);
		}
		else
			moveSide = new JCheckBox("Side",false);
		if(block.getBlockDown() == 1)
			 moveDown = new JCheckBox("Down",true);
		else
			 moveDown = new JCheckBox("Down",false);
		
		gone.setSize(60,20);
		gone.setLocation(0,5);
		gone.setToolTipText("������� ����� �� üũ");
		gone.addItemListener(new ItemListener() {
	         public void itemStateChanged(ItemEvent e) {
	        	 if(e.getStateChange()==1) {
	        		 block.setGoneCheck(1, setGone);
	        		 pane.add("Gone",setGone);
	        		 pane.setSelectedIndex(tabCount++);
	        	 }
	        	 else {
	        		 block.setGoneCheck(0, setGone);
	        		 pane.remove(setGone);
	        		 tabCount--;
	        	 }
	         }
	    });
		add(gone);
		moveSide.setSize(60,20);
		moveSide.setLocation(60,5);
		moveSide.setToolTipText("������ �����̴� ����� �� üũ");
		moveSide.addItemListener(new ItemListener() {
	         public void itemStateChanged(ItemEvent e) {
	        	 if(e.getStateChange()==1) {
	        		 block.setMoveCheck(1,setSideMove);
	        		 pane.add("SideMove",setSideMove);
	        		 pane.setSelectedIndex(tabCount++);
	        	 }
	        	 else {
	        		 block.setMoveCheck(0,setSideMove);
	        		 pane.remove(setSideMove);
	        		 tabCount--;
	        	 }
	         }
	    });
		add(moveSide);
		moveDown.setSize(60,20);
		moveDown.setLocation(120,5);
		moveDown.setToolTipText("������ �����Ҷ����� �Ʒ��� �������� ����� �� üũ");
		moveDown.addItemListener(new ItemListener() {
	         public void itemStateChanged(ItemEvent e) {
	        	 if(e.getStateChange()==1)
	        		 block.setMoveDownBlock(1);
	        	 else
	        		 block.setMoveDownBlock(0);
	         }
	    });
		add(moveDown);
	}
	/**
	 * ���� blockType �����ϱ�
	 */
	public void finalSetBlocktype() {
		setGone.finalSetBlock(block);
		setSideMove.finalSetBlock(block);
	}
}
/**
 * ���� Block Type �� ������� ��� ���� Panel
 * 
 * @author ����
 */
class SetGone extends JPanel {
	private NumberField hitCount = new NumberField(3);
	private NumberField score = new NumberField(3);
	/**
	 * SetGone ������
	 */
	public SetGone() {
		add(new JLabel("HitCount"));
		hitCount.setToolTipText("����� ���(��� �¾ƾ��� ������� ��)");
		add(hitCount);
		add(new JLabel("Score"));
		score.setToolTipText("����� ������ �� ȹ�� ����");
		add(score);
	}
	/**
	 * setGonePanel�� block�� ���缭 �ٽ� �����ϴ� �Լ�
	 * @param block �������� ����ִ� block
	 */
	public void setGoneType(WritingBlock block) {
		hitCount.setText(block.getHitCount());
		score.setText(block.getScore());
	}
	/**
	 * block�� ������� ����� ��� �ʿ��� �� �����ϱ�
	 * @param block ������ block
	 */
	public void finalSetBlock(WritingBlock block) {
		block.setGoneBlock(hitCount.getNumber(), score.getNumber());
	}
}
/**
 * ���� Block Type �� �¿�� �����̴� ��� ���� Panel
 * 
 * @author ����
 */
class SetSideMove extends JPanel {
	private int moveDirection = 1;
	private NumberField moveDelay = new NumberField(3);
	private NumberField moveX = new NumberField(3);
	private JRadioButton rd1 = new JRadioButton("��");
	private JRadioButton rd2 = new JRadioButton("��");
	/**
	 * SetSideMove ������
	 */
	public SetSideMove() {
		setSize(300,200);
		
		add(new JLabel("       "));
		add(new JLabel("Delay"));
		moveDelay.setToolTipText("����� �����̴� �ӵ�");
		add(moveDelay);
		add(new JLabel("       "));
		add(new JLabel("   "));
		add(new JLabel("Distance"));
        moveX.setToolTipText("����� �����̴� �Ÿ�");
		add(moveX);
		add(new JLabel("       "));
		
		JLabel direction = new JLabel("Direction");
		direction.setToolTipText("����� ó�� �����̴� ���� ����");
		add(direction);
		
        rd1.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				 if(e.getStateChange()==1)
					 moveDirection = 1;
			}
        });
        rd2.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				 if(e.getStateChange()==1)
					 moveDirection = -1;
			}
        });
        // 1�� ���� ��ư �������ֵ���
        rd1.setSelected(true);
        
        ButtonGroup groupRd = new ButtonGroup();

        groupRd.add(rd1);
        groupRd.add(rd2);

        add(rd1);
        add(rd2);
	}
	/**
	 * setSideMovePanel�� block�� ���缭 �ٽ� �����ϴ� �Լ�
	 * @param block �������� ����ִ� block
	 */
	public void setSideMoveType(WritingBlock block) {
		moveDelay.setText(block.getMoveDelay());
		if(block.getMoveDirection()>0) {
			moveX.setText(block.getMoveDirection());
			rd1.setSelected(true);
		}
		else {
			moveX.setText(block.getMoveDirection()*-1);
			rd2.setSelected(true);
		}
	}
	/**
	 * block�� ������ �����̴� ����� ��� �ʿ��� �� �����ϱ�
	 * @param block ������ block
	 */
	public void finalSetBlock(WritingBlock block) {
		block.setMoveSideBlock(moveDelay.getNumber(), moveDirection*moveX.getNumber());
	}
}