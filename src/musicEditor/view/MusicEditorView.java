package musicEditor.view;

import musicEditor.music.MusicSheet;

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
    
    this.editorPanel = new EditorPanel(composition, player, tracker);
    this.pianoPanel = new PianoPanel(composition, player, tracker);
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
    JFrame frame = new Jframe();
  }
  
  public void update() {
    
  }
}
