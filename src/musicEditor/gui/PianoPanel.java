package musicEditor.gui;

import musicEditor.music.MusicComposition;
import musicEditor.music.MusicPlayer;
import musicEditor.music.MusicTracker;

import javax.swing.*;

/**
 * Represents the panel that will display the piano on the MusicEditorFrame
 */
public class PianoPanel extends JPanel {
  private MusicComposition composition;
  private MusicTracker tracker;
  private MusicPlayer player;

  public PianoPanel(MusicComposition composition, MusicTracker tracker, MusicPlayer player) {
    this.composition = composition;
    this.tracker = tracker;
    this.player = player;
  }
}
