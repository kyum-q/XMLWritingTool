package XMLWritingTool;
import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

/**
 * ������ ������ ���� Block �̹��� ���̺� (extends JLabel)
 * @author ����
 */
public class WritingBlock extends JLabel {
	private String type = "dontGone";
	private String imgName = null;
	private int w=80, h=80, x, y, score=0, hitCount=0, moveDelay=0, moveDirection=0, blockDown=-1;
	private int goneCheck=0, moveSideCheck=0;
	private Image img;
	private SetSideMove setSideMove = null;
	private SetGone setGone = null;
	/**
	 * block�� �����ؼ� ���ο� block �����ϴ� �Լ�
	 * @return ������ block ����
	 */
	public WritingBlock blockCopy() {
		WritingBlock copy = new WritingBlock();
		
		copy.setSize(this.w,this.h);
		copy.setImg(imgName);
		if(goneCheck==1)
			setGone.finalSetBlock(copy);
		if(moveSideCheck==1)
			setSideMove.finalSetBlock(copy);
		copy.setMoveDownBlock(blockDown);
		
		return copy;
	}
	/**
	 * ����� ������� ������� üũ�ϴ� �Լ�<br>
	 * @return goneCheck�� 1�� ��(������� ����� ��) true
	 */
	public boolean goneCheck() { 
		if(goneCheck == 1)
			return true;
		return false;
	}
	/**
	 * ����� �¿�� �����̴� ������� üũ�ϴ� �Լ�<br>
	 * @return moveSideCheck�� 1�� ��(�¿�� �����̴� ����� ��) true
	 */
	public boolean moveSideCheck() { 
		if(moveSideCheck == 1)
			return true;
		return false;
	}
	/**
	 * blockDown�� �����ϴ� �Լ�
	 * @return blockDown (1�̸� �������� block -1�̸� �ȳ������� block)
	 */
	public int getBlockDown() { return blockDown; }
	/**
	 * hitCount�� �����ϴ� �Լ�
	 * @return hitCount
	 */
	public int getHitCount() { return hitCount; }
	/**
	 * score�� �����ϴ� �Լ�
	 * @return score
	 */
	public int getScore() { return score; }
	/**
	 * moveDelay�� �����ϴ� �Լ�
	 * @return moveDelay
	 */
	public int getMoveDelay() { return moveDelay; }
	/**
	 * moveDirection�� �����ϴ� �Լ�
	 * @return moveDirection
	 */
	public int getMoveDirection() { return moveDirection; }
	/**
	 * ������� ����� �� ������� ��Ͽ� �ʿ��� �� �����ϴ� �Լ�
	 * @param hitCount ����� �¾ƾ��ϴ� ������ ����
	 * @param score ����� �¾� ������� �� ��� ���� ������ ����
	 */
	public void setGoneBlock(int hitCount, int score) {
		this.hitCount = hitCount;
		this.score = score;
		goneCheck=1;
	}
	/**
	 * ������� ������� �ƴ��� �����ϴ� �Լ�
	 * @param goneCheck ������� ����� �� 1, �ƴ� �� 0
	 * @param setGone ������� ����� �� ���� ������ �ִ� SetGone
	 */
	public void setGoneCheck(int goneCheck, SetGone setGone) { 
		this.goneCheck = goneCheck; 
		if(goneCheck==1)
			this.setGone = setGone;
	}
	/**
	 * �¿�� �����̴� ����� �� ������� ��Ͽ� �ʿ��� �� �����ϴ� �Լ�
	 * @param moveDelay �¿�� �����̴� Delay ������ ����
	 * @param moveDirection �����̴� ����� �����̴� �Ÿ� ������ ����
	 */
	public void setMoveSideBlock(int moveDelay, int moveDirection) {
		this.moveDelay = moveDelay;
		this.moveDirection = moveDirection;
		 moveSideCheck=1;
	}
	/**
	 * �����̴� ������� �ƴ��� �����ϴ� �Լ�
	 * @param moveSideCheck �����̴� ����� �� 1, �ƴ� �� 0
	 * @param setSideMove �����̴� ����� �� ���� ������ �ִ� SetSideMove
	 */
	public void setMoveCheck(int moveSideCheck,SetSideMove setSideMove) { 
		this.moveSideCheck = moveSideCheck; 
		if(moveSideCheck==1)
			this.setSideMove = setSideMove;
	}
	/**
	 * blockDown �����ϴ� �Լ�
	 * @param blockDown blockDown�� �����ϴ� ��
	 */
	public void setMoveDownBlock(int blockDown) {
		this.blockDown = blockDown;
	}
	@Override
	public void setSize(int w,int h) {
		super.setSize(w, h);
		this.w = w;
		this.h = h;
	}
	@Override
	public void setLocation(int x,int y) {
		this.x = x;
		this.y = y;
		super.setLocation(this.x, this.y);	
	}
	/**
	 * �̹��� �����ϴ� �Լ�
	 * @param imgName �̹��� ���� �̸�
	 */
	public void setImg(String imgName) {
		this.imgName = imgName;
		ImageIcon icon = new ImageIcon(imgName);
		img = icon.getImage();
		if(this.getParent() != null)
			this.getParent().repaint();
	}
	/**
	 * �̹����� ���� �ִ��� üũ�ϴ� �Լ�
	 * @return �̹����� null�� �� false, �ƴ� �� true 
	 */
	public boolean checkImg() {
		if(this.imgName == null)
			return false;
		return true;
	}
	/**
	 * xml ���ڿ��� �ٲ㼭 �����ϴ� �Լ�
	 * @return xml�� block type�� ���� �����ؼ� ���� 
	 */
	public String getString() {
		if(imgName==null)
			imgName = "";
		String basic = "x=\""+x+"\" y=\""+y+"\" w=\""+w+"\" h=\""+h+"\" blockDown=\""+blockDown+"\" img=\""+imgName+"\"";
		if(moveSideCheck == 0) {
			if(goneCheck == 0)
				return "<Obj type=\"dontGone\" "+basic+" />\r\n";
			else
				return "<Obj type=\"gone\" "+basic+" hitCount=\""+hitCount+"\" score=\""+score+"\" />\r\n";
		}
		else {
			if(goneCheck == 0)
				return "<Obj type=\"move\" "+basic+" moveDelay=\""+moveDelay+"\" moveDirection=\""+moveDirection+"\" />\r\n";
			else
				return "<Obj type=\"moveAndGone\" "+basic+" moveDelay=\""+moveDelay+"\" moveDirection=\""+moveDirection+"\" hitCount=\""+hitCount+"\" score=\""+score+"\" />\r\n";
		}
	}
	@Override
	public void paintComponent(Graphics g) {
			g.drawImage(img, 0, 0, this.getWidth(), this.getHeight(), this);
	}	
}
