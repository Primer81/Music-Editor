package musicEditor.music;

import javax.sound.midi.*;
import java.util.Collection;

/**
 * Created by gwlar on 6/26/2017.
 */
public class MusicPlayer {
  private int tempo;
  private Sequencer sequencer;

  public MusicPlayer() {
    this.tempo = 1;
    try {
      this.sequencer = MidiSystem.getSequencer();
    } catch (MidiUnavailableException e) {
      e.printStackTrace();
    }
  }

  public int getTempo() {
    return tempo;
  }

  public void setTempo(int tempo) {
    this.tempo = tempo;
  }

  public void play() {
    this.sequencer.start();
    this.sequencer.setTempoInMPQ(this.tempo);
  }

  public void pause() {
    this.sequencer.stop();
  }

  public void sequenceComposition(MusicComposition composition) {
    try {
      Sequence sequence = new Sequence(Sequence.PPQ, 1);
      Track track = sequence.createTrack();
      Collection<MusicSheet> sheets = composition.getSheets();
      for (MusicSheet sheet : sheets) {
        Collection<MusicRow> rows = sheet.getRows();
        for (MusicRow row : rows) {
          Collection<Tone> tones = row.getTones();
          for (Tone tone : tones) {
            MidiMessage start = new ShortMessage(
                ShortMessage.NOTE_ON, tone.getTimbre(), tone.midiPitch(), tone.getVolume());
            MidiMessage stop = new ShortMessage(
                ShortMessage.NOTE_OFF, tone.getTimbre(), tone.midiPitch(), tone.getVolume());
            track.add(new MidiEvent(start, tone.getStart()));
            track.add(new MidiEvent(stop, (tone.getStart() + tone.getDuration())));
          }
        }
      }
      this.sequencer.setSequence(sequence);
    } catch (InvalidMidiDataException e) {
      e.printStackTrace();
    }
  }

  public void sequenceSheet(MusicSheet sheet) {
    try {
      Sequence sequence = new Sequence(Sequence.PPQ, 1);
      Track track = sequence.createTrack();
      Collection<MusicRow> rows = sheet.getRows();
      for (MusicRow row : rows) {
        Collection<Tone> tones = row.getTones();
        for (Tone tone : tones) {
          MidiMessage start = new ShortMessage(
              ShortMessage.NOTE_ON, tone.getTimbre(), tone.midiPitch(), tone.getVolume());
          MidiMessage stop = new ShortMessage(
              ShortMessage.NOTE_OFF, tone.getTimbre(), tone.midiPitch(), tone.getVolume());
          track.add(new MidiEvent(start, tone.getStart()));
          track.add(new MidiEvent(stop, (tone.getStart() + tone.getDuration())));
        }
      }
      this.sequencer.setSequence(sequence);
    } catch (InvalidMidiDataException e) {
      e.printStackTrace();
    }
  }

  public void sequenceComposition(MusicRow row) {
    try {
      Sequence sequence = new Sequence(Sequence.PPQ, 1);
      Track track = sequence.createTrack();
      Collection<Tone> tones = row.getTones();
      for (Tone tone : tones) {
        MidiMessage start = new ShortMessage(
            ShortMessage.NOTE_ON, tone.getTimbre(), tone.midiPitch(), tone.getVolume());
        MidiMessage stop = new ShortMessage(
            ShortMessage.NOTE_OFF, tone.getTimbre(), tone.midiPitch(), tone.getVolume());
        track.add(new MidiEvent(start, tone.getStart()));
        track.add(new MidiEvent(stop, (tone.getStart() + tone.getDuration())));
      }
      this.sequencer.setSequence(sequence);
    } catch (InvalidMidiDataException e) {
      e.printStackTrace();
    }
  }
}
