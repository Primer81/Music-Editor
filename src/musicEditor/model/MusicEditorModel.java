package musicEditor.model;

import musicEditor.music.*;
import musicEditor.util.CompositionBuilder;

import javax.sound.midi.Sequencer;
import java.util.ArrayList;
import java.util.List;
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
  public MusicEditorModel(MusicComposition composition, MusicTracker tracker, MusicPlayer player) {
    this();
    this.composition = composition;
    this.tracker = tracker;
    this.player = player;
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
  public int getTempo() {
    return this.player.getTempo();
  }

  @Override
  public void setTempo(int tempo) {
    this.player.setTempo(tempo);
  }

  @Override
  public int getBeat() {
    return this.player.getBeat();
  }

  @Override
  public void setBeat(int beat) {
    this.player.setBeat(beat);
  }

  @Override
  public int getTimbre() {
    return this.tracker.getTimbre();
  }

  @Override
  public void setTimbre(int timbre) {
    this.tracker.setTimbre(timbre);
  }

  @Override
  public void sequenceComposition() {
    this.player.sequenceComposition(this.composition);
  }

  @Override
  public void play() {
    this.player.play();
  }

  @Override
  public void pause() {
    this.player.pause();
  }

  @Override
  public boolean isRunning() {
    return this.player.isRunning();
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


  /**
   * Builder class that constructs a music composition.
   */
  public static final class Builder implements CompositionBuilder<MusicEditorModel> {
    private MusicComposition composition;
    private MusicTracker tracker;
    private MusicPlayer player;
    private IMusicEditorModel model;

    public Builder() {
      this.composition = new MusicComposition();
      this.tracker = new MusicTracker();
      this.player = new MusicPlayer();
      this.model = new MusicEditorModel();
    }

    /**
     * Builds the MusicEditorModel.
     */
    @Override
    public MusicEditorModel build() {
      return new MusicEditorModel(this.composition, this.tracker, this.player);
    }

    @Override
    public CompositionBuilder<MusicEditorModel> setTempo(int tempo) {
      this.player.setTempo(tempo);
      return this;
    }

    @Override
    public CompositionBuilder<MusicEditorModel> addNote(
        int start, int end, int timbre, int midiPitch, int volume) {
      Tone tone = new Tone(new Pitch((midiPitch)), start, start + (end - start), volume, timbre);
      this.composition.addTone(tone);
      return this;
    }
  }
}
