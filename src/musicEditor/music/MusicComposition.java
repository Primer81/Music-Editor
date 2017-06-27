package musicEditor.music;

import java.util.*;

/**
 * Represents a composition of music. Contains a Map with integer keys representing timbre which
 * map to MusicSheet objects. These MusicSheet Objects will always have a timbre matching
 * their corresponding key in the map.
 * Also contains a map with integer keys representing
 * beats that correspond to Feature objects so that the composition can hold data on where this
 * composition should repeat or have varied endings.
 */
public class MusicComposition {
  private SortedMap<Integer, MusicSheet> composition;
  private SortedMap<Integer, Feature> features;

  /**
   * Constructs a new MusicComposition. Initializes the composition.
   */
  public MusicComposition() {
    this.composition = new TreeMap<>();
    this.features = new TreeMap<>();
  }

  /**
   * Constructs a new MusicComposition. Initializes the composition to the given composition
   * @param composition the composition
   * @throws IllegalArgumentException if the given composition is invalid
   */
  public MusicComposition(SortedMap<Integer, MusicSheet> composition) {
    this();
    if (!this.validComposition(composition)) {
      throw new IllegalArgumentException("the given composition is invalid");
    }
    this.composition = composition;
  }

  /**
   * Constructs a new MusicComposition. Allows the construction of this composition with a given
   * composition as well as a given map of features.
   * @param composition the composition
   * @param features the features for this composition
   * @throws IllegalArgumentException if the given composition is invalid
   */
  public MusicComposition(SortedMap<Integer, MusicSheet> composition,
                          SortedMap<Integer, Feature> features) {
    this(composition);
    this.features = features;
  }

  /**
   * Gets the music sheets that make up this composition.
   * @return a collection of the music sheets in this composition
   */
  public Collection<MusicSheet> getSheets() {
    return this.composition.values();
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
   */
  public void addTone(Tone tone) {
    MusicSheet sheet = this.composition.get(tone.getTimbre());
    sheet.addTone(tone);
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
   * Removes the given Tone from this composition. If it successfully removes the
   * tone it returns true; otherwise false.
   * @param tone the tone to be removed
   * @return whether the tone was removed
   */
  public boolean removeTone(Tone tone) {
    return this.composition.get(tone.getTimbre()).removeTone(tone);
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
    return result.clone();
  }

  /**
   * Returns true if this composition contains no MusicSheets.
   * @return whether this composition contains MusicSheets
   */
  public boolean isEmpty() {
    return this.composition.isEmpty() && this.features.isEmpty();
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
    // changes result to the last feature beat if it is larger
    if (!this.features.isEmpty()) {
      int lastFeatureBeat = ((SortedSet<Integer>) this.features.keySet()).last();
      if (lastFeatureBeat > result) {
        result = lastFeatureBeat;
      }
    }
    return result;
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
   * Adds the given feature to this composition's feature map. Maps the given beat to the
   * feature. Does no allow features to overlap.
   * @param feature the feature being added
   * @param beat the beat at which the feature will occur
   */
  public void addFeature(Feature feature, int beat) {
    if (this.features.containsKey(beat)) {
      throw new IllegalArgumentException("feature already exists at given beat");
    }
    this.features.put(beat, feature);
  }

  /**
   * Removes the feature at the specified beat if there is any and returns it.
   * Returns null if no feature exists at that beat.
   */
  public Feature removeFeature(int beat) {
    return this.features.remove(beat);
  }

  /**
   * Gets the feature at the specified beat if there is any and returns it.
   * Returns null if no feature exists at that beat.
   */
  public Feature getFeature(int beat) {
    return this.features.get(beat);
  }
}