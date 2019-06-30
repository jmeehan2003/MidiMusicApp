import javax.sound.midi.*;

public class MiniMusicPlayer implements ControllerEventListener {
	
	public static void main(String[] args) {
		MiniMusicPlayer mini = new MiniMusicPlayer();
		mini.play();
	}
	
	public void play() {
		try {
			Sequencer player = MidiSystem.getSequencer();
			player.open();
			
			int[] eventsIWant = {127};
			player.addControllerEventListener(this, eventsIWant);
			
			Sequence seq = new Sequence(Sequence.PPQ, 4);
			Track track = seq.createTrack();
			
			for (int i = 5; i < 60; i+= 4) {
				// NoteOn event
				track.add(makeEvent(144, 1, i, 100, i));
				// Controller event to fire when NoteON occurs
				track.add(makeEvent(176, 1, 127, 0, i));
				// NoteOff event
				track.add(makeEvent(128,1,i,100,i+2));
			}
			
			player.setSequence(seq);
			player.setTempoInBPM(220);
			player.start();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
	public void controlChange(ShortMessage event) {
		System.out.println("la");
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
}
