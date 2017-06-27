package musicEditor.music;

/**
 * Created by gwlar on 6/26/2017.
 */
public class MusicTracker {
  private int beat;
  private int timbre;

  public MusicTracker() {
    this.beat = 0;
    this.timbre = 1;
  }

  public int getBeat() {
    return beat;
  }

  public void setBeat(int beat) {
    this.beat = beat;
  }

  public int getTimbre() {
    return timbre;
  }

  public void setTimbre(int timbre) {
    this.timbre = timbre;
  }
}
