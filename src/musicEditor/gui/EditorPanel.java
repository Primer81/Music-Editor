package musicEditor.gui;

import musicEditor.music.*;

import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.SortedSet;

/**
 * Represents the panel that will display all Tones within the given MusicSheet.
 */
public class EditorPanel extends JPanel {
  private final int CELL_WIDTH = 20;
  private final int CELL_HEIGHT = 20;
  private MusicComposition composition;
  private MusicTracker musicTracker;

  /**
   * Creates a new EditorPanel with a double buffer and a flow layout.
   */
  public EditorPanel(MusicComposition composition, MusicTracker musicTracker) {
    super();
    this.composition = composition;
    this.musicTracker = musicTracker;
  }

  @Override
  public void paintComponent(Graphics g) {
    super.paintComponent(g);
    this.paintSheet(g);
  }

  @Override
  public Dimension getPreferredSize() {
    return new Dimension(
        (this.composition.length() + 1) * this.CELL_WIDTH,
        (this.composition.range(
            this.musicTracker.getTimbre()).size() + 1) * this.CELL_HEIGHT);
  }

  /**
   * Paints this EditorPanel's sheet onto the given graphics. Only draws the sheet within
   * this panel's current clipping area so as to improve performance.
   * @param g this panel's graphics
   */
  private void paintSheet(Graphics g) {
    Rectangle drawHere = g.getClipBounds();

    int timbre = this.musicTracker.getTimbre();
    SortedSet<Pitch> range = this.composition.range(timbre);
    int length = this.composition.length();

    int col = length;
    int row = range.size();

    // draws the notes
    int curRow = 0;
    for (Pitch p : range) {
      for (int curCol = 0; curCol < length; curCol++) {
        Tone tone = this.composition.getTone(timbre, p, curCol);
        if (tone != null) {
          // creates a rectangle to represent the start of the tone and draws it if it
          // intersects the clipping area
          Rectangle blackRect = new Rectangle(
              this.CELL_WIDTH, this.CELL_HEIGHT);
          blackRect.setLocation(
              this.CELL_WIDTH * curCol, this.CELL_HEIGHT * curRow);
          if (drawHere.intersects(blackRect)) {
            g.setColor(Color.BLACK);
            g.drawRect(blackRect.x, blackRect.y, blackRect.width, blackRect.height);
          }
          // creates a rectangle to represent the continuation of the tone and draws it if it
          // intersects the clipping area
          Rectangle greenRect = new Rectangle(
              this.CELL_WIDTH * tone.getDuration(), this.CELL_HEIGHT);
          greenRect.setLocation(
              this.CELL_WIDTH * (curCol + 1), this.CELL_HEIGHT * (curRow + 1));
          if (drawHere.intersects(greenRect)) {
            g.setColor(Color.GREEN);
            g.drawRect(greenRect.x, greenRect.y, greenRect.width, greenRect.height);
          }
        }
      }
      curRow++;
    }

    // draws the measure lines
    for (int i = 0; i < col; i += 4) {
      int x = i * this.CELL_WIDTH;
      int y2 = row * this.CELL_HEIGHT;
      if (drawHere.intersectsLine(x, 0, x, y2)) {
        g.setColor(Color.BLACK);
        g.drawLine(x, 0, x, y2);
      }
    }

    // draws the pitch lines
    for (int i = 0; i < row + 1; i++) {
      int y = i * this.CELL_HEIGHT;
      int x2 = col * this.CELL_WIDTH;
      if (drawHere.intersectsLine(0, y, x2, y)) {
        g.setColor(Color.BLACK);
        g.drawLine(0, y, x2, y);
      }
    }

    // draws the red line
    int x = this.musicTracker.getBeat() * this.CELL_WIDTH;
    int y = range.size() * this.CELL_HEIGHT;
    if (drawHere.intersectsLine(x, 0, x, y)) {
      g.drawLine(x, 0, x, y);
    }
  }
}
