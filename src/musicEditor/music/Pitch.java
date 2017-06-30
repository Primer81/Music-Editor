package musicEditor.music;

/**
 * Represents a pitch in music. Contains a Note enum to represent this Pitch's pitch class and
 * an octave to represent the octave of this pitch.
 */
public class Pitch implements Comparable<Pitch> {
  private Note note;
  private int octave;

  /**
   * Constructs a Pitch. Initializes note and octave fields. Requires that octave is greater than
   * or equal to -1.
   * @param note this Pitch's note
   * @param octave this Pitch's octave
   * @throws IllegalArgumentException if octave is less than -1
   */
  public Pitch(Note note, int octave) {
    this.note = note;
    if (!(octave >= -1)) {
      throw new IllegalArgumentException("octave must be greater than or equal to -1");
    }
    this.octave = octave;
  }

  /**
   * Constructs a Pitch given the specified midiPitch to represent a pitch.
   * @param midiPitch the midiPitch value
   */
  public Pitch(int midiPitch) {
    if (midiPitch < 0) {
      throw new IllegalArgumentException("midiPitch cannot be negative");
    }
    if (midiPitch > 127) {
      throw new IllegalArgumentException("midiPitch cannot be greater than 127");
    }
    this.octave = (midiPitch / 12) - 1;
    this.note = Note.values()[midiPitch % 12];
  }

  /**
   * Sets this Pitch's note.
   * @param note new note
   */
  public void setNote(Note note) {
    this.note = note;
  }

  /**
   * Sets this Pitch's octave.
   * @param octave new octave
   */
  public void setOctave(int octave) {
    this.octave = octave;
  }

  /**
   * Gets this Pitch's note.
   */
  public Note getNote() {
    return this.note;
  }

  /**
   * Gets this Pitch's octave.
   */
  public int getOctave() {
    return this.octave;
  }

  /**
   * Returns the MIDI pitch representation of this pitch.
   * eg. C0 maps to 0, C#0 maps to 1, D0 maps to 2, ... C4 maps to 60, etc.
   * @return the MIDI pitch representation of this pitch
   */
  public int midiPitch() {
    return ((this.getOctave() + 1) * 12) + this.getNote().ordinal();
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || this.getClass() != o.getClass()) return false;

    Pitch pitch = (Pitch) o;

    if (this.getOctave() != pitch.getOctave()) return false;
    return this.getNote() == pitch.getNote();
  }

  @Override
  public int hashCode() {
    int result = this.getNote().hashCode();
    result = 31 * result + this.getOctave();
    return result;
  }

  @Override
  public String toString() {
    return this.getNote().toString() + this.getOctave();
  }

  @Override
  public Pitch clone() {
    return new Pitch(this.note, this.octave);
  }

  @Override
  public int compareTo(Pitch o) {
    if (this.getOctave() == o.getOctave()) {
      return this.note.compareTo(o.note);
    }
    else {
      return this.getOctave() - o.getOctave();
    }
  }
}
