package musicEditor.model;

import musicEditor.music.*;

import java.util.SortedSet;

/**
 * Represents a model for a music editor.
 * Keeps track of the current beat, the tempo, the current timbre, and the composition.
 */
public class MusicEditorModel implements IMusicEditorModel {
  private MusicComposition composition;
  private MusicTracker musicTracker;
  private MusicPlayer musicPlayer;

  /**
   * Default constructor for a MusicEditorModel.
   * Defaults composition to an empty composition, tempo to 1, timbre to 1, and beat to 0.
   */
  public MusicEditorModel() {
    this.composition = new MusicComposition();
    this.musicTracker = new MusicTracker();
    this.musicPlayer = new MusicPlayer();
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

  /**
   * Gets this MusicEditorModel's composition.
   * @return this MusicEditorModel's composition
   */
  public MusicComposition getComposition() {
    return composition;
  }

  /**
   * Sets this MusicEditorModel's composition.
   * @param composition this MusicEditorModel's composition
   */
  public void setComposition(MusicComposition composition) {
    this.composition = composition;
  }

  /**
   * Gets this model's MusicTracker.
   * @return the MusicTracker
   */
  public MusicTracker getMusicTracker() {
    return musicTracker;
  }

  /**
   * Sets this model's MusicTracker.
   * @param musicTracker the MusicTracker
   */
  public void setMusicTracker(MusicTracker musicTracker) {
    this.musicTracker = musicTracker;
  }

  public MusicPlayer getMusicPlayer() {
    return musicPlayer;
  }

  public void setMusicPlayer(MusicPlayer musicPlayer) {
    this.musicPlayer = musicPlayer;
  }

  /**
   * Adds the given tone to the composition.
   * @param tone the tone to be added
   */
  public void addTone(Tone tone) {
    this.composition.addTone(tone);
  }

  /**
   * Removes the tone of the specified pitch and returns it if it exists. Returns null otherwise.
   * Uses this model's current timbre and beat to find and remove the note.
   * @param pitch the pitch of the tone being removed
   * @return the tone removed
   */
  public Tone removeTone(Pitch pitch) {
    return this.composition.removeTone(
        this.musicTracker.getTimbre(), pitch, this.musicTracker.getBeat());
  }

  /**
   * Removes the given tone from the composition if it exists. Returns true if it could be found
   * and removed; false otherwise.
   * @param tone the tone to be removed
   * @return whether the tone was found and removed
   */
  public boolean removedTone(Tone tone) {
    return this.composition.removeTone(tone);
  }

  /**
   * Gets the tone with the specified pitch from the composition if it exists. Returns null
   * if it could not be found.
   * @param pitch the pitch of the tone being returned
   * @return the tone with the specified pitch
   */
  public Tone getTone(Pitch pitch) {
    return this.composition.getTone(
        this.musicTracker.getTimbre(), pitch, this.musicTracker.getBeat());
  }

  /**
   * Returns true if this model contains no music.
   * @return whether this model contains music.
   */
  public boolean isEmpty() {
    return this.composition.isEmpty();
  }

  /**
   * Returns the full range of pitches of the current sheet.
   * @return the full range of pitches of the sheet with the current timbre
   */
  public SortedSet<Pitch> range() {
    return this.composition.range(this.musicTracker.getTimbre());
  }

  /**
   * The number of beats long the composition is.
   * @return the number of beats long this model's composition is
   */
  public int length() {
    return this.composition.length();
  }

  /* TODO: Add Feature methods. */
}