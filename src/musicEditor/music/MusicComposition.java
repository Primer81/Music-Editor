package musicEditor.data;

import java.util.*;

/**
 * Represents a composition of music. Contains a Map with integer keys representing timbre which
 * map to MusicSheet objects. These MusicSheet Objects will always have a timbre matching
 * their corresponding key in the map.
 */
public class MusicComposition {
  private SortedMap<Integer, MusicSheet> composition;

  /**
   * Constructs a new MusicComposition. Initializes the composition.
   */
  public MusicComposition() {
    this.composition = new TreeMap<>();
  }
  /**
   * Constructs a new MusicComposition. Initializes the composition to the given composition
   * @param composition the composition
   * @throws IllegalArgumentException if the given composition is invalid
   */
  public MusicComposition(SortedMap<Integer, MusicSheet> composition) {
    if (!this.validComposition(composition)) {
      throw new IllegalArgumentException("the given composition is invalid");
    }
    this.composition = composition;
  }

  /**
   * Validates a composition by checking to see if each MusicSheet is mapped by a key with a
   * matching timbre.
   * @param composition the composition being checked
   * @return whether the composition is valid
   */
  private boolean validComposition(Map<Integer, MusicSheet> composition) {
    for (Map.Entry<Integer, MusicSheet> e : composition.entrySet()) {
      if (e.getKey() != e.getValue().getTIMBRE()) {
        return false;
      }
    }
    return true;
  }

  /**
   * Adds the given tone to the composition.
   * @param tone the tone being added
   * @param beat the beat at which the tone will be added
   */
  public void addTone(Tone tone, int beat) {
    MusicSheet sheet = this.composition.get(tone.getTimbre());
    sheet.addTone(tone, beat);
  }

  /**
   * Removes the Tone of the given timbre and pitch at the specified beat. Returns the removed Tone
   * or null if no Tone was found.
   * @param timbre the timbre of the Tone
   * @param pitch the pitch of the Tone
   * @param beat the beat of the Tone
   * @return the Tone removed or null if no Tone was found
   */
  public Tone removeTone(int timbre, Pitch pitch, int beat) {
    if (!this.composition.containsKey(timbre)) {
      return null;
    }
    MusicSheet sheet = this.composition.get(timbre);
    Tone result = sheet.removeTone(pitch, beat);
    if (sheet.isEmpty()) {
      this.composition.remove(timbre);
    }
    return result;
  }

  /**
   * Gets the Tone of the given timbre and pitch at the specified beat. Returns null if no Tone
   * was found.
   * @param timbre the timbre of the Tone
   * @param pitch the pitch of the Tone
   * @param beat the beat of the Tone
   * @return the specified Tone or null if no Tone was found
   */
  public Tone getTone(int timbre, Pitch pitch, int beat) {
    if (!this.composition.containsKey(timbre)) {
      return null;
    }
    MusicSheet sheet = this.composition.get(timbre);
    Tone result = sheet.getTone(pitch, beat);
    return result;
  }

  /**
   * Returns true if this composition contains no MusicSheets.
   * @return whether this composition contains MusicSheets
   */
  public boolean isEmpty() {
    return this.composition.isEmpty();
  }

  /**
   * Returns the full sorted range of Pitches in from the sheet with the specified timbre
   * from lowest pitch to the highest pitch. Returns an empty set if the sheet does not exist.
   * highest pitch.
   * @return a full sorted range of Pitches in this sheet
   */
  public SortedSet<Pitch> range(int timbre) {
    if (!this.composition.containsKey(timbre)) {
      return new TreeSet<>();
    }
    MusicSheet sheet = this.composition.get(timbre);
    return sheet.range();
  }

  /**
   * Returns the number of beats within this composition. Returns zero if this
   * composition is empty.
   * @return the number of beats in this composition
   */
  public int length() {
    if (this.isEmpty()) {
      return 0;
    }
    List<MusicSheet> values = new ArrayList<>(this.composition.values());
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