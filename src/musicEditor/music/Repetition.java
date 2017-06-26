package musicEditor.music;

/**
 * Represents a repetition in a piece of music. Contains information such as the number of
 * repetitions desired and how many beats back this repetition should send the music.
 */
public class Repetition implements Feature {
  private int rewind;
  private int loops;

  /**
   * Constructs a new Repetition with the given rewind value. Requires that rewind be
   * non-negative. Defaults loops to one so that the repetition occurs once.
   * @param rewind how many beats or ticks this repetitions will rewind the music
   */
  public Repetition(int rewind) {
    if (rewind < 0) {
      throw new IllegalArgumentException("to cannot be less than zero");
    }
    this.rewind = rewind;
  }

  /**
   * Constructs a new Repetition with the given rewind value. Requires that rewind be
   * non-negative. Requires that loops be greater than one.
   * @param rewind how many beats or ticks this repetitions will rewind the music
   * @param loops number of loops this Repetition will make
   */
  public Repetition(int rewind, int loops) {
    this(rewind);
    if (loops < 1) {
      throw new IllegalArgumentException("loops cannot be less than one");
    }
    this.loops = loops;
  }

  /**
   * Gets this Repetition's rewind value.
   * @return this Repetition's rewind value
   */
  public int getRewind() {
    return rewind;
  }

  /**
   * Sets this Repetition's rewind value.
   */
  public void setRewind(int rewind) {
    this.rewind = rewind;
  }

  /**
   * Gets this Repetition's loops value.
   * @return this Repetition's loops value
   */
  public int getLoops() {
    return loops;
  }

  /**
   * Sets this Repetition's loops value.
   */
  public void setLoops(int loops) {
    this.loops = loops;
  }
}
