import javax.sound.midi.*;
import java.io.*;
import javax.swing.*;
import java.awt.*;

public class MiniMusicPlayer {
	static JFrame f = new JFrame("Muy Bueno Music Video");
	static MyDrawPanel ml;
	
	public static void main(String[] args) {
		MiniMusicPlayer mini = new MiniMusicPlayer();
		mini.play();
	}
	
	public void setUpGui() {
		ml = new MyDrawPanel();
		f.setContentPane(ml);
		f.setBounds(30, 30, 300, 300);
		f.setVisible(true);
	}
	
	public void play() {
		setUpGui();
		
		try {
			Sequencer player = MidiSystem.getSequencer();
			player.open();
			
			player.addControllerEventListener(ml, new int[] {127});
			Sequence seq = new Sequence(Sequence.PPQ, 4);
			Track track = seq.createTrack();
			
			int r = 0;
			for (int i = 5; i < 60; i+= 4) {
				//Get random note
				r = (int) ((Math.random() * 50) + 1);
				// NoteOn event
				track.add(makeEvent(144, 1, r, 100, i));
				// Controller event to fire when NoteON occurs
				track.add(makeEvent(176, 1, 127, 0, i));
				// NoteOff event
				track.add(makeEvent(128,1,r,100,i+2));
			}
			
			player.setSequence(seq);
			player.setTempoInBPM(120);
			player.start();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
	public MidiEvent makeEvent(int comd, int chan, int one, int two, int tick) {
		MidiEvent event = null;
		try {
			ShortMessage a = new ShortMessage();
			a.setMessage(comd, chan, one, two);
			event = new MidiEvent(a, tick);
		} catch (Exception e) { }
		
		return event;
	}
	
	class MyDrawPanel extends JPanel implements ControllerEventListener {
		boolean msg = false;
		
		public void controlChange(ShortMessage event) {
			msg = true;
			repaint();
		}
		
		public void paintComponent(Graphics g) {
			if (msg) {
				int red = (int) (Math.random() * 256);
				int green = (int) (Math.random() * 256);
				int blue = (int) (Math.random() * 256);
				
				g.setColor(new Color(red, green, blue));
				
				int height = (int) ((Math.random() * 120) + 10);
				int width = (int) ((Math.random() * 120) + 10);
				
				int x = (int) ((Math.random() * 40) + 10);
				int y = (int) ((Math.random() * 40) + 10);
				
				g.fillRect(x, y, width, height);
				msg = false;
			}
		}
	}
}