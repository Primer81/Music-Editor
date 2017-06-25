package musicEditor.data;

/**
 * An enumeration to represent the 12 different pitch classes or notes that exist in music.
 */
public enum Note {
  C("C"),
  C_S("C#"),
  D("D"),
  D_S("D#"),
  E("E"),
  F("F"),
  F_S("F#"),
  G("G"),
  G_S("G#"),
  A("A"),
  A_S("A#"),
  B("B");

  String rep;

  Note(String rep) {
    this.rep = rep;
  }

  @Override
  public String toString() {
    return this.rep;
  }
}
