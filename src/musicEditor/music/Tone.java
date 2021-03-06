package musicEditor.music;

/**
 * Represents a musical tone. Has fields representing its pitch, start, duration, volume,
 * and timbre.
 * Pitch is represented by the Pitch class.
 * Start is an int representing the tick or beat at which this tone is played.
 * Duration is an int measured in MIDI ticks.
 * Volume is an int representing the tones loudness.
 * Timbre is an int representing MIDI instrument codes between 1 and 128 inclusive.
 */
public class Tone {
  private Pitch pitch;
  private int start;
  private int duration;
  private int volume;
  private int timbre;

  /**
   * Constructs a Tone.
   * Ensures that start is non-negative.
   * Ensures that duration is non-negative.
   * Ensures that volume is non-negative.
   * Ensures that timbre is greater than or equal to one and less than or equal to 128
   * @param pitch this Tone's pitch
   */
  public Tone(Pitch pitch, int start, int duration, int volume, int timbre) {
    this.pitch = pitch;
    if (start < 0) {
      throw new IllegalArgumentException("start cannot be negative");
    }
    this.start = start;
    if (duration < 0) {
      throw new IllegalArgumentException("duration must be non-negative");
    }
    this.duration = duration;
    if (volume < 0) {
      throw new IllegalArgumentException("volume must be non-negative");
    }
    this.volume = volume;
    if (timbre < 1 || timbre > 128) {
      throw new IllegalArgumentException("timbre must correspond to a valid MIDI instrument " +
          "code between 1 and 128");
    }
    this.timbre = timbre;
  }

  /**
   * Sets this Tone's pitch.
   * @param pitch this Tone's pitch
   */
  public void setPitch(Pitch pitch) {
    this.pitch = pitch;
  }

  /**
   * Sets this Tone's start
   * @param start this Tone's start
   */
  public void setStart(int start) {
    this.start = start;
  }

  /**
   * Sets this Tone's duration.
   * @param duration this Tone's duration
   */
  public void setDuration(int duration) {
    this.duration = duration;
  }

  /**
   * Sets this Tone's volume.
   * @param volume this Tone's volume
   */
  public void setVolume(int volume) {
    this.volume = volume;
  }

  /**
   * Sets this Tone's timbre.
   * @param timbre this Tone's timbre
   */
  public void setTimbre(int timbre) {
    this.timbre = timbre;
  }

  /**
   * Gets this Tone's pitch.
   */
  public Pitch getPitch() {
    return this.pitch;
  }

  /**
   * Gets this Tone's start.
   * @return this Tone's start
   */
  public int getStart() {
    return start;
  }

  /**
   * Gets this Tone's duration.
   */
  public int getDuration() {
    return this.duration;
  }

  /**
   * Gets this Tone's volume.
   */
  public int getVolume() {
    return this.volume;
  }

  /**
   * Gets this Tone's timbre.
   */
  public int getTimbre() {
    return this.timbre;
  }

  /**
   * Returns the MIDI pitch representation of this tone's pitch.
   * eg. C0 maps to 0, C#0 maps to 1, D0 maps to 2, ... C4 maps to 60, etc.
   * @return the MIDI pitch representation of this pitch
   */
  public int midiPitch() {
    return this.getPitch().midiPitch();
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || this.getClass() != o.getClass()) return false;

    Tone tone = (Tone) o;

    if (this.getDuration() != tone.getDuration()) return false;
    if (this.getStart() != tone.getStart()) return false;
    if (this.getVolume() != tone.getVolume()) return false;
    if (this.getTimbre() != tone.getTimbre()) return false;
    return this.getPitch().equals(tone.getPitch());
  }

  @Override
  public int hashCode() {
    int result = this.getPitch().hashCode();
    result = 31 * result + this.getDuration();
    result = 31 * result + this.getStart();
    result = 31 * result + this.getVolume();
    result = 31 * result + this.getTimbre();
    return result;
  }

  @Override
  public String toString() {
    return "Tone{" +
        "pitch=" + pitch +
        ", start=" + start +
        ", duration=" + duration +
        ", volume=" + volume +
        ", timbre=" + timbre +
        '}';
  }

  @Override
  public Tone clone() {
    return new Tone(this.pitch.clone(), this.start, this.duration, this.volume, this.timbre);
  }
}