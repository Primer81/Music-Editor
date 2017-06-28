package musicEditor.gui;

import musicEditor.music.*;

import javax.sound.midi.Sequencer;
import javax.swing.*;
import java.awt.*;
import java.util.SortedSet;

/**
 * Created by gwlar on 6/28/2017.
 */
public class PitchesComponent extends JComponent {
  private final int CELL_WIDTH = 20;
  private final int CELL_HEIGHT = 20;
  private MusicComposition composition;
  private MusicTracker musicTracker;
  private MusicPlayer musicPlayer;

  /**
   * Creates a new EditorPanel with a double buffer and a flow layout.
   */
  public PitchesComponent(MusicComposition composition,
                          MusicTracker musicTracker, MusicPlayer musicPlayer) {
    super();
    this.composition = composition;
    this.musicTracker = musicTracker;
    this.musicPlayer = musicPlayer;
  }

  @Override
  public void paintComponent(Graphics g) {
    super.paintComponent(g);
    this.paintPitches(g);
  }

  @Override
  public Dimension getPreferredSize() {
    return new Dimension(
        this.CELL_WIDTH,
        (this.composition.range(
            this.musicTracker.getTimbre()).size() + 1) * this.CELL_HEIGHT);
  }

  /**
   * Paints the pitches of the current sheet from the composition onto the given Graphics.
   * @param g the given graphics
   */
  private void paintPitches(Graphics g) {
    Rectangle drawHere = g.getClipBounds();
    SortedSet<Pitch> range = this.composition.range(this.musicTracker.getTimbre());

    int y = 0;
    for (Pitch p : range) {
      y += this.CELL_HEIGHT;
      if (drawHere.contains(0, y)) {
        g.drawString(p.toString(), 0, y);
      }
    }
  }
}
