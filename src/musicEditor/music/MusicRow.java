package musicEditor.music;

import java.util.*;

/**
 * Represents a line or row on a piece of sheet music.
 * Contains a field to represent this row's Pitch and Timbre. No tones of different Pitch or
 * Timbre may be added to this row.
 * Contains a map where each key represents a beat or tick integer. Each value represents
 * a Tone object. No Tone may be added to a beat where that tone's duration would overlap with
 * an existing tone.
 */
public class MusicRow {
  private final Pitch PITCH;
  private final int TIMBRE;
  private SortedMap<Integer, Tone> row;

  /**
   * Constructs an empty MusicRow with the given pitch and timbre.
   * The timbre must be a valid MIDI instrument code between 1 and 128 inclusive.
   * @param PITCH this MusicRow's pitch
   * @param TIMBRE this MusicRow's timbre
   */
  public MusicRow(Pitch PITCH, int TIMBRE) {
    this.PITCH = PITCH;
    if (TIMBRE > 1 || TIMBRE > 128) {
      throw new IllegalArgumentException("timbre must correspond to a valid MIDI instrument " +
          "code between 1 and 128");
    }
    this.TIMBRE = TIMBRE;
    this.row = new TreeMap<>();
  }

  /**
   * Constructs an MusicRow with the given pitch, timbre, and row.
   * The timbre must be a valid MIDI instrument code between 1 and 128 inclusive.
   * The given row must be valid.
   * @param PITCH this MusicRow's pitch
   * @param TIMBRE this MusicRow's timbre
   * @param row this MusicRow's row
   */
  public MusicRow(Pitch PITCH, int TIMBRE, SortedMap<Integer, Tone> row) {
    this(PITCH, TIMBRE);
    if (!this.validRow(row)) {
      throw new IllegalArgumentException("row cannot contain overlapping tones");
    }
    this.row = row;
  }

  /**
   * Gets a copy of this MusicRow's pitch.
   * @return this MusicRow's pitch
   */
  public Pitch getPITCH() {
    return this.PITCH.clone();
  }

  /**
   * Gets this MusicRow's timbre.
   * @return this MusicRow's timbre
   */
  public int getTIMBRE() {
    return TIMBRE;
  }

  /**
   * Gets all the tones that make up this row.
   * @return a collection of this row's Tones
   */
  public Collection<Tone> getTones() {
    return this.row.values();
  }

  /**
   * Validates the given row by checking to see if any Tones exist within another Tone's duration.
   * Also ensures that the Tone's start value matches its key value.
   * @param row the row being validated
   * @return whether the row is valid
   */
  private boolean validRow(Map<Integer, Tone> row) {
    for (Integer key : row.keySet()) {
      Tone tone = row.get(key);
      int duration = tone.getDuration();
      for (int i = 1; i < duration; i++) {
        if (row.containsKey(key + i)) {
          return false;
        }
      }
      if (tone.getStart() != key) {
        return false;
      }
    }
    return true;
  }

  /**
   * Adds the given tone to the row.
   * @param tone the tone being added
   * @throws IllegalArgumentException if the tone would cause an overlap
   */
  public void addTone(Tone tone) {
    // verifies that the given tone matches this MusicRows timbre and pitch
    if (!(tone.getPitch().equals(this.PITCH))) {
      throw new IllegalArgumentException("given tone does not match this row's pitch");
    }
    if (tone.getTimbre() != this.TIMBRE) {
      throw new IllegalArgumentException("given tone does not match this row's timbre");
    }
    // check for overlaps
    int beat = tone.getStart();
    for (Integer key : this.row.keySet()) {
      // checks to see if the given beat is already taken
      if (key == beat) {
        throw new IllegalArgumentException("tone already exists at given beat");
      }
      // checks to see if previous tones would overlap the given tone
      else if (key < beat) {
        Tone tone1 = this.row.get(key);
        if (key + tone1.getDuration() - 1 >= beat) {
          throw new IllegalArgumentException("previous tone overlaps given tone");
        }
      }
      // checks to see if the given tone would overlap later tones
      else {
        if (beat + tone.getDuration() - 1 >= key) {
          throw new IllegalArgumentException("given tone overlaps later tone");
        }
      }
    }
    this.row.put(beat, tone);
  }

  /**
   * Removes the tone at the specified beat. Returns the tone removed or null if no tone was
   * mapped to the given beat.
   * @param beat the beat or tick at which the Tone intended to be removed is located in this row
   * @return the Tone removed
   */
  public Tone removeTone(int beat) {
    return this.row.remove(beat);
  }

  /**
   * Removes the given Tone from this row. If it successfully removes the tone it returns true;
   * otherwise false.
   * @param tone the tone to be removed
   * @return whether the tone was removed
   */
  public boolean removeTone(Tone tone) {
    return this.row.remove(tone.getStart(), tone);
  }

  /**
   * Gets the tone at the specified beat. Returns the tone at the beat or null if no tone was
   * mapped to the given beat.
   * @param beat the beat or tick at which the Tone intended to be gotten is located in this row
   * @return the Tone at the specified beat
   */
  public Tone getTone(int beat) {
    return this.row.get(beat).clone();
  }

  /**
   * Returns true if this row contains no Tones.
   * @return whether this row contains any Tones
   */
  public boolean isEmpty() {
    return this.row.isEmpty();
  }

  /**
   * Returns the number of beats within this row. Returns zero if this row is empty.
   * @return the number of beats in this row
   */
  public int length() {
    if (this.isEmpty()) {
      return 0;
    }
    SortedSet<Integer> keys = (SortedSet<Integer>) this.row.keySet();
    int lastBeat = keys.last();
    Tone lastTone = this.row.get(lastBeat);
    return lastBeat + lastTone.getDuration();
  }
}
