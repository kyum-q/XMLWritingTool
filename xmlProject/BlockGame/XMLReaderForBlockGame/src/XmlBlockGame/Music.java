package XmlBlockGame;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.sound.sampled.*;
import javax.swing.*;

/**
 * ������ ����ϴ� class
 * 
 * @author ����
 */
public class Music {
	private AudioInputStream ais;
	private Clip clip;
	private FloatControl gainControl;
	private float volume;
	private boolean musicState = true;
	/**
	 * Music ������
	 * 
	 * @param fileName ������ �ִ� ���� ��ġ
	 * @param i ������ �ݺ������ ������ �˾Ƴ��� (i�� 1�̸� ���� ����)
	 */
	public Music(String fileName, int i) {
		try {
			ais = AudioSystem.getAudioInputStream(new File(fileName));
			clip = AudioSystem.getClip();
			clip.open(ais);
			if(i==1)
				clip.loop(Clip.LOOP_CONTINUOUSLY);
			gainControl = 
				    (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
			volume = gainControl.getValue(); // Reduce volume by 10 decibels.
		} catch (Exception e) { return; }
	}
	/**
	 * Music ������
	 */
	public Music() {  }
	/**
	 * music�� ���� �����ϴ� �Լ� (�ڵ���� �Ƚ�Ű����� ��� false)
	 * 
	 * @return musicState  music ���� (false�� ����, true�� ���)
	 */
	public boolean checkMusicState() { return musicState; }
	/**
	 * musicState �� �����ϴ� �Լ�
	 * 
	 * @param i musicState �����ϰ��� �ϴ� ��
	 */
	public void setMusicState(boolean i) { musicState = i; }
	/**
	 * ������ �����Ű�� �Լ�
	 */
	public void play() { 
		if(clip!=null)
			clip.start();
		musicState = true;
	}
	/**
	 * ������ �����ϰ� �����Ű�� �Լ�<br>
	 * ( musicState�� false�� ��� ��� x)
	 * @param fileName ������ ������ �ִ� ���� ��ġ
	 */
	public void play(String fileName) { 
		try {
			if(clip!=null)
				clip.stop();
			ais = AudioSystem.getAudioInputStream(new File(fileName));
			clip = AudioSystem.getClip();
			clip.open(ais);
			gainControl = 
				    (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
			volume = gainControl.getValue(); // Reduce volume by 10 decibels.
		} catch (Exception e) {
			return;
		}
		if(musicState == false)
			return;
		musicState = true;
		clip.start();
	}
	/**
	 * ������ ������Ű�� �Լ�
	 */
	public void stop() { 
		if(clip!=null)
		clip.stop(); 
	}
	/**
	 * ������ ���ߴ� �Լ�
	 */
	public void volumeDown() { 
		FloatControl gainControl = 
			    (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
		
		if(volume-5>=-80) volume -= 5;
		else volume = -80;
		gainControl.setValue(volume); // Reduce volume by 10 decibels.
		clip.start();
	}
	/**
	 * ������ ���̴� �Լ�
	 */
	public void volumeUp() { 
		FloatControl gainControl = 
			    (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
		
		if(volume+5<=80) volume += 5;
		else volume = 80;
		gainControl.setValue(volume); // Reduce volume by 10 decibels.
		clip.start();
	}
}
