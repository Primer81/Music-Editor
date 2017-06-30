package musicEditor.model;

import musicEditor.music.MusicComposition;
import musicEditor.music.MusicPlayer;
import musicEditor.music.MusicTracker;
import musicEditor.music.Pitch;
import musicEditor.music.Tone;

import java.util.SortedSet;

/**
 * Created by gwlar on 6/27/2017.
 */
public interface IMusicEditorModel {

  /**
   * Gets this MusicEditorModel's composition.
   * @return this MusicEditorModel's composition
   */
  MusicComposition getComposition();

  /**
   * Sets this MusicEditorModel's composition.
   * @param composition this MusicEditorModel's composition
   */
  void setComposition(MusicComposition composition);

  /**
   * Gets this model's MusicTracker.
   * @return the MusicTracker
   */
  MusicTracker getTracker();

  /**
   * Sets this model's MusicTracker.
   * @param musicTracker the MusicTracker
   */
  void setTracker(MusicTracker musicTracker);

  /**
   * Gets this model's MusicPlayer.
   * @return the music player
   */
  MusicPlayer getPlayer();

  /**
   * Sets this model's MusicPlayer.
   * @param player the music player
   */
  void setPlayer(MusicPlayer player);

  /**
   * Sequences the composition in the music player.
   */
  void sequenceComposition();

  /**
   * Sets the tempo of the music player
   */
  void setTempo(int tempo);

  /**
   * Sets the current timbre in the musicTracker.
   */
  void setTimbre(int timbre);

  /**
   * Gets the current beat from the player.
   * @return the current beat
   */
  int getBeat();

  /**
   * Sets the beat in the player.
   * @param beat the new beat
   */
  void setBeat(int beat);

  /**
   * Plays the composition.
   */
  void play();

  /**
   * Pauses the composition.
   */
  void pause();

  /**
   * Returns whether the composition is playing or not.
   */
  boolean isRunning();

  /**
   * Adds the given tone to the composition.
   * @param tone the tone to be added
   */
  void addTone(Tone tone);

  /**
   * Removes the tone of the specified pitch and returns it if it exists. Returns null otherwise.
   * Uses this model's current timbre and beat to find and remove the note.
   * @param pitch the pitch of the tone being removed
   * @return the tone removed
   */
  Tone removeTone(Pitch pitch);

  /**
   * Removes the given tone from the composition if it exists. Returns true if it could be found
   * and removed; false otherwise.
   * @param tone the tone to be removed
   * @return whether the tone was found and removed
   */
  boolean removedTone(Tone tone);

  /**
   * Returns true if this model contains no music.
   * @return whether this model contains music.
   */
  boolean isEmpty();

  /**
   * Returns the full range of pitches of the current sheet.
   * @return the full range of pitches of the sheet with the current timbre
   */
  SortedSet<Pitch> range();

  /**
   * The number of beats long the composition is.
   * @return the number of beats long this model's composition is
   */
  int length();
}
