package musicEditor.model;

import musicEditor.music.*;
  
import javax.sound.midi.Sequencer;
import java.util.SortedSet;

/**
 * Represents a model for a music editor.
 * Keeps track of the current beat, the tempo, the current timbre, and the composition.
 */
public class MusicEditorModel implements IMusicEditorModel {
  private MusicComposition composition;
  private MusicTracker tracker;
  private MusicPlayer player;

  /**
   * Default constructor for a MusicEditorModel.
   * Defaults composition to an empty composition, tempo to 1, timbre to 1, and beat to 0.
   */
  public MusicEditorModel() {
    this.composition = new MusicComposition();
    this.tracker = new MusicTracker();
    this.player = new MusicPlayer();
  }

  /**
   * Constructs a MusicEditorModel with the given composition.
   * Defaults beat to 0, tempo to 1, and timbre to 1.
   * @param composition this editor's composition
   */
  public MusicEditorModel(MusicComposition composition) {
    this();
    this.composition = composition;
  }

  @Override
  public MusicComposition getComposition() {
    return composition;
  }

  @Override
  public void setComposition(MusicComposition composition) {
    this.composition = composition;
  }

  @Override
  public MusicTracker getTracker() {
    return tracker;
  }

  @Override
  public void setTracker(MusicTracker musicTracker) {
    this.tracker = musicTracker;
  }

  @Override
  public MusicPlayer getPlayer() {
    return player;
  }

  @Override
  public void setPlayer(MusicPlayer player) {
    this.player = player;
  }

  @Override
  public void sequenceComposition() {
    this.player.sequenceComposition(this.composition);
  }

  @Override
  public void setTempo(int tempo) {
    this.player.setTempo(tempo);
  }

  @Override
  public void setTimbre(int timbre) {
    this.tracker.setTimbre(timbre);
  }

  @Override
  public void addTone(Tone tone) {
    this.composition.addTone(tone);
  }

  @Override
  public Tone removeTone(Pitch pitch) {
    Sequencer sequencer = this.player.getSequencer();
    return this.composition.removeTone(
        this.tracker.getTimbre(), pitch, (int) sequencer.getTickPosition());
  }

  @Override
  public boolean removedTone(Tone tone) {
    return this.composition.removeTone(tone);
  }

  @Override
  public boolean isEmpty() {
    return this.composition.isEmpty();
  }

  @Override
  public SortedSet<Pitch> range() {
    return this.composition.range(this.tracker.getTimbre());
  }

  @Override
  public int length() {
    return this.composition.length();
  }

  /* TODO: Add Feature methods. */
}
