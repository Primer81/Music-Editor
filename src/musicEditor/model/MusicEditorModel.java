package musicEditor.model;

import musicEditor.music.MusicComposition;
import musicEditor.music.MusicSheet;
import musicEditor.music.Pitch;
import musicEditor.music.Tone;

import java.util.List;
import java.util.SortedSet;

/**
 * Represents a model for a music editor.
 * Keeps track of the current beat, the tempo, the current timbre, and the composition.
 */
public class MusicEditorModel {
  private MusicComposition composition;
  private int beat;
  private int tempo;
  private int timbre;

  /**
   * Default constructor for a MusicEditorModel.
   * Defaults composition to an empty composition, tempo to 1, timbre to 1, and beat to 0.
   */
  public MusicEditorModel() {
    this.composition = new MusicComposition();
    this.tempo = 1;
    this.timbre = 1;
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
   * Gets this MusicEditorModel's beat.
   * @return this MusicEditorModel's beat
   */
  public int getBeat() {
    return beat;
  }

  /**
   * Sets this MusicEditorModel's beat.
   * @param beat this MusicEditorModel's beat
   */
  public void setBeat(int beat) {
    this.beat = beat;
  }

  /**
   * Gets this MusicEditorModel's tempo.
   * @return this MusicEditorModel's tempo
   */
  public int getTempo() {
    return tempo;
  }

  /**
   * Sets this MusicEditorModel's tempo.
   * @param tempo this MusicEditorModel's tempo
   */
  public void setTempo(int tempo) {
    this.tempo = tempo;
  }

  /**
   * Gets this MusicEditorModel's timbre.
   * @return this MusicEditorModel's timbre
   */
  public int getTimbre() {
    return timbre;
  }

  /**
   * Sets this MusicEditorModel's timbre.
   * @param timbre this MusicEditorModel's timbre
   */
  public void setTimbre(int timbre) {
    this.timbre = timbre;
  }

  /**
   * Returns a copy of the music sheet that this model is currently on.
   * @return a copy of this model's current music sheet
   */
  public MusicSheet getSheet() {
    return this.composition.getSheet(this.timbre);
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
    return this.composition.removeTone(this.timbre, pitch, this.beat);
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
    return this.composition.getTone(this.timbre, pitch, this.beat);
  }

  /**
   * Gets the all the tones from the composition at this model's current beat from the
   * sheet with the model's current timbre. Returns an empty list if no tones exists.
   * @return the list of tones at the the current beat from the sheet with the current timbre
   */
  public List<Tone> getTonesAtBeat() {
    return this.composition.getTonesAtBeat(this.timbre, this.beat);
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
    return this.composition.range(this.timbre);
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