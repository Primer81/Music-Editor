package musicEditor.gui;

import musicEditor.music.MusicComposition;
import musicEditor.music.MusicPlayer;
import musicEditor.music.MusicTracker;

import javax.swing.*;
import java.awt.*;

/**
 * Created by gwlar on 6/28/2017.
 */
public class MeasuresComponent extends JComponent {
  private final int CELL_WIDTH = 20;
  private final int CELL_HEIGHT = 20;
  private MusicComposition composition;
  private MusicTracker musicTracker;
  private MusicPlayer musicPlayer;

  /**
   * Creates a new EditorPanel with a double buffer and a flow layout.
   */
  public MeasuresComponent(MusicComposition composition,
                           MusicTracker musicTracker, MusicPlayer musicPlayer) {
    super();
    this.composition = composition;
    this.musicTracker = musicTracker;
    this.musicPlayer = musicPlayer;
  }

  @Override
  public void paintComponent(Graphics g) {
    super.paintComponent(g);
    this.paintMeasures(g);
  }

  @Override
  public Dimension getPreferredSize() {
    return new Dimension(
        (this.composition.length() + 1) * this.CELL_WIDTH,
        (this.CELL_HEIGHT));
  }

  /**
   * Paints the measure labels of this component's composition onto the given graphics.
   * @param g the given graphics
   */
  private void paintMeasures(Graphics g) {
    Rectangle drawHere = g.getClipBounds();
    int length = this.composition.length();

    for (int i = 0; i < length; i += 4) {
      int x = i * this.CELL_WIDTH;
      if (drawHere.contains(x, this.CELL_HEIGHT)) {
        g.drawString(Integer.toString(i), x, this.CELL_HEIGHT);
      }
    }
  }
}
