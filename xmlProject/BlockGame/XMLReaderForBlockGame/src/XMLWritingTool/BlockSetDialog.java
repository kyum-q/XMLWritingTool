package XMLWritingTool;
import java.awt.event.*;
import javax.swing.*;
/**
 * Block ������ �����ִ� Dialog
 * 
 * @author ����
 */
public class BlockSetDialog extends JDialog {
	
	private int x,y,w,h,tabCount = 0;
	private WritingBlock block = null;
	private BlockTypeSetPanel blockTypePanel = null;
	/**
	 * BlockSetDialog ������
	 * @param block �����ϰ����ϴ� block
	 */
	public BlockSetDialog(final WritingBlock block) {
		super(new JFrame(),"block set",true);
		setLayout(null);
		setSize(200,350);
		this.block = block;
		
		blockTypePanel = new BlockTypeSetPanel(block, 180, 120);
		splitPane();
		setDialog();
		
		setLocationRelativeTo(null);
		setResizable(false); // âũ�� ���� (���� �Ұ�)
	}
	/**
	 * dialog�� �ִ� JTabbedPane�� ��ġ �����ϴ� �Լ�
	 * : Tab���� Bloc Type ����
	 */
	private void splitPane() {
		JSplitPane hPane = new JSplitPane();
		hPane.setOrientation(JSplitPane.VERTICAL_SPLIT);
		hPane.setEnabled(false); // splitPane ��ġ ����
		hPane.setDividerSize(0);
		hPane.setBorder(null);
		hPane.setLocation(5,130);
		hPane.setSize(180,150);
		
		hPane.setTopComponent(blockTypePanel);
		hPane.setBottomComponent(null);
		add(hPane);
	}
	/**
	 * Dialog�� �⺻ ���� component ���� �Լ�
	 */
	private void setDialog() {
		NumberField xInput = new NumberField(), yInput = new NumberField();
		NumberField widthInput = new NumberField(), heightInput = new NumberField();
		
		xInput.setText(block.getX());
		yInput.setText(block.getY());
		widthInput.setText(block.getWidth());
		heightInput.setText(block.getHeight());
		
		JLabel xLabel = new JLabel("X"), yLabel = new JLabel("Y");
		JLabel width = new JLabel("WIDTH"), height = new JLabel("HEIGHT");
		
		xLabel.setSize(100,20);
		xLabel.setLocation(40,10);
		add(xLabel);
		xInput.setSize(30,20);
		xInput.setLocation(100,10);
		xInput.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				block.setLocation(xInput.getNumber(),yInput.getNumber());
			}
		});
		add(xInput);
		
		yLabel.setSize(100,20);
		yLabel.setLocation(40,40);
		add(yLabel);
		yInput.setSize(30,20);
		yInput.setLocation(100,40);
		yInput.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				block.setLocation(xInput.getNumber(),yInput.getNumber());
			}
		});
		add(yInput);
		
		width.setSize(100,20);
		width.setLocation(40,70);
		add(width);
		widthInput.setSize(30,20);
		widthInput.setLocation(100,70);
		widthInput.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				block.setSize(widthInput.getNumber(),heightInput.getNumber());
			}
		});
		add(widthInput);
		
		height.setSize(100,20);
		height.setLocation(40,100);
		add(height);
		heightInput.setSize(30,20);
		heightInput.setLocation(100,100);
		heightInput.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				block.setSize(widthInput.getNumber(),heightInput.getNumber());
			}
		});
		add(heightInput);
		
		JButton select = new JButton("����");
		select.setSize(100,20);
		select.setLocation(40,280);
		select.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) { 
				block.setSize(widthInput.getNumber(),heightInput.getNumber());
				block.setLocation(xInput.getNumber(),yInput.getNumber());
				blockTypePanel.finalSetBlocktype();
				setVisible(false);
			}	
		});
		add(select);
	}
}
