package musicEditor.music;

import java.util.*;

/**
 * A class to represent a piece of sheet music.
 * <p>Contains a field to represent this sheet music's intended timbre or instrument. It is
 * represented by an int between 1 and 128 inclusive to represent MIDI instrument codes.</p>
 * <p>Contains map where the key is a pitch and value is a MusicRow
 * that has the same pitch as specified by the key and same timbre as this MusicSheet.</p>
 * <p>Allows for the adding of tones by passing in the tone and then their intended
 * index or beat location in the corresponding tone list. Tones added must have the same timbre as
 * this MusicSheet. Allows for the removal of notes by specifying their pitch and then their index
 * in the corresponding array.</p>
 */
public class MusicSheet {
  private final int TIMBRE;
  private SortedMap<Pitch, MusicRow> sheet;

  /**
   * Constructs a MusicSheet with the given timbre and initializes the Map of Tone objects.
   * The given timbre must be a valid MIDI instrument code between 1 and 128 inclusive.
   * @param TIMBRE this MusicSheet's timbre (MIDI instrument code).
   */
  public MusicSheet(int TIMBRE) {
    if (TIMBRE < 1 || TIMBRE > 128) {
      throw new IllegalArgumentException("timbre must correspond to a valid MIDI instrument " +
          "code between 1 and 128");
    }
    this.TIMBRE = TIMBRE;
    this.sheet = new TreeMap<>();
  }

  /**
   * Constructs a MusicSheet with the given timbre and the given Map of Tone objects.
   * The given timbre must be a valid MIDI instrument code between 1 and 128 inclusive.
   * The given sheet must be valid (no overlapping tones).
   * @param TIMBRE this MusicSheet's timbre (MIDI instrument code)
   * @param sheet this MusicSheet's sheet of musical tones
   */
  public MusicSheet(int TIMBRE, SortedMap<Pitch, MusicRow> sheet) {
    this(TIMBRE);
    if (!this.validSheet(sheet)) {
      throw new IllegalArgumentException("given sheet is invalid");
    }
    this.sheet = sheet;
  }

  /**
   * Gets this MusicSheet's timbre.
   * @return this MusicSheet's timbre
   */
  public int getTIMBRE() {
    return this.TIMBRE;
  }

  /**
   * Gets all the music rows that make up this sheet.
   * @return a collection of this sheet's music rows
   */
  public Collection<MusicRow> getRows() {
    return this.sheet.values();
  }

  /**
   * Validates a sheet by checking that each key is mapped to a MusicRow that has a matching pitch
   * and that each MusicRow has a matching timbre.
   * @param sheet the sheet being checked
   * @return whether the sheet is valid
   */
  private boolean validSheet(Map<Pitch, MusicRow> sheet) {
    for (Map.Entry<Pitch, MusicRow> e : sheet.entrySet()) {
      MusicRow row = e.getValue();
      if (!e.getKey().equals(row.getPITCH())
          || this.TIMBRE != row.getTIMBRE()) {
        return false;
      }
    }
    return true;
  }

  /**
   * Adds the given tone to the sheet. Adds another MusicRow to the sheet
   * if no row exists for the Tone's pitch.
   * @param tone the tone being added
   * @throws IllegalArgumentException if tone's timbre doesn't match this sheet's timbre
   */
  public void addTone(Tone tone) {
    if (tone.getTimbre() != this.TIMBRE) {
      throw new IllegalArgumentException("tone's timbre does not match this sheet's timbre");
    }
    Pitch pitch = tone.getPitch();
    if (!this.sheet.containsKey(pitch)) {
      this.sheet.put(pitch, new MusicRow(pitch, this.TIMBRE));
    }
    this.sheet.get(pitch).addTone(tone);
  }

  /**
   * Removes the Tone of the given pitch at the given beat from the sheet. Returns the removed tone
   * or null if no tone was found. Removes the MusicRow as well if it becomes empty.
   * @param pitch pitch of the tone being removed
   * @param beat beat at which the tone will be found
   * @return the tone removed or null if no tone was found
   */
  public Tone removeTone(Pitch pitch, int beat) {
    if (!this.sheet.containsKey(pitch)) {
      return null;
    }
    MusicRow row = this.sheet.get(pitch);
    Tone result = row.removeTone(beat);
    if (row.isEmpty()) {
      this.sheet.remove(pitch);
    }
    return result;
  }

  /**
   * Removes the given Tone from this sheet. If it successfully removes the tone it returns true;
   * otherwise false.
   * @param tone the tone to be removed
   * @return whether the tone was removed
   */
  public boolean removeTone(Tone tone) {
    return this.sheet.get(tone.getPitch()).removeTone(tone);
  }

  /**
   * Gets the Tone of the given pitch at the given beat from the sheet. Returns null if no
   * tone was found.
   * @param pitch pitch of the tone being removed
   * @param beat beat at which the tone will be found
   * @return the tone removed or null if no tone was found
   */
  public Tone getTone(Pitch pitch, int beat) {
    if (!this.sheet.containsKey(pitch)) {
      return null;
    }
    MusicRow row = this.sheet.get(pitch);
    Tone result = row.getTone(beat);
    return result;
  }

  /**
   * Gets the list of tones at the specified beat from the sheet. Returns an empty list if no
   * beats exist at that beat.
   * @param beat where the Tones are being obtained
   * @return the list of tones at the given beat
   */
  public List<Tone> getTonesAtBeat(int beat) {
    if (this.isEmpty()) {
      return new ArrayList<>();
    }
    List<Tone> result = new ArrayList<>();
    for (Pitch p : this.sheet.keySet()) {
      result.add(this.getTone(p, beat));
    }
    return result;
  }

  /**
   * Returns true is this sheet contains no rows.
   * @return whether or not this sheet contains rows
   */
  public boolean isEmpty() {
    return this.sheet.isEmpty();
  }

  /**
   * Returns the full sorted range of Pitches in this sheet from lowest pitch to highest pitch.
   * @return a full sorted range of Pitches in this sheet
   */
  public SortedSet<Pitch> range() {
    if (this.isEmpty()) {
      return new TreeSet<>();
    }
    // gets the sorted set of keys from the sheet. keys are already sorted in ascending order
    // so they may be cast to a SortedSet
    SortedSet<Pitch> keys = (SortedSet<Pitch>) this.sheet.keySet();
    // gets the lowest and highest pitches from the set of keys
    Pitch first = keys.first().clone();
    Pitch last = keys.last().clone();
    // gets a list of all the possible notes from the note enum
    Note[] notes = Note.values();
    // creates an empty sorted set to store the full range of notes
    SortedSet<Pitch> range = new TreeSet<>();
    // loop until the lowest pitch is equal to the highest pitch
    // each loop, add the lowest pitch to the set and then increment to the next pitch
    while (!first.equals(last)) {
      range.add(first);
      int newNoteIndex = (first.getNote().ordinal() + 1) % 12;
      first.setNote(notes[newNoteIndex]);
      if (first.getNote().equals(Note.C)) {
        first.setOctave(first.getOctave() + 1);
      }
    }
    // add the last pitch which was not added during the loop
    range.add(last);
    return range;
  }

  /**
   * Returns the number of beats within this sheet. Returns zero if this sheet is empty.
   * @return the number of beats in this sheet
   */
  public int length() {
    if (this.isEmpty()) {
      return 0;
    }
    List<MusicRow> values = new ArrayList<>(this.sheet.values());
    int result = values.get(0).length();
    for (int i = 1; i < values.size(); i++) {
      int length = values.get(i).length();
      if (result < length) {
        result = length;
      }
    }
    return result;
  }
}