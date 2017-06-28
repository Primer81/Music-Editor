package musicEditor.view;

import musicEditor.gui.EditorPanel;
import musicEditor.gui.PianoPanel;
import musicEditor.music.MusicComposition;
import musicEditor.music.MusicPlayer;
import musicEditor.music.MusicSheet;
import musicEditor.music.MusicTracker;

import javax.swing.*;

/**
 * Represents the view for the music editor.
 */
public class MusicEditorView implements IMusicEditorView {
  private MusicComposition composition;
  private MusicTracker tracker;
  private MusicPlayer player;
  
  private JPanel editorPanel;
  private JPanel pianoPanel;
  
  /**
   * Constructs new MusicEditorView with default fields.
   */
  public MusicEditorView(MusicComposition composition,
                         MusicTracker tracker, MusicPlayer player) {
    this.composition = composition;
    this.tracker = tracker;
    this.player = player;
    
    this.editorPanel = new EditorPanel(composition, tracker, player);
    this.pianoPanel = new PianoPanel(composition, tracker, player);
  }
  
  public void setComposition(MusicComposition composition) {
    this.composition = composition;
  }

  public void setTracker(MusicTracker tracker) {
    this.tracker = tracker;
  }

  public void setPlayer(MusicPlayer player) {
    this.player = player;
  }

  public void initialize() {
    JFrame frame = new JFrame();
  }
  
  public void update() {
    
  }
}
