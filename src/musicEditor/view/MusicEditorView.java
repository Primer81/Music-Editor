package musicEditor.view;

import musicEditor.music.MusicSheet;

/**
 * Represents the view for the music editor.
 */
public class MusicEditorView implements IMusicEditorView {
  private JPanel editorPanel;
  private JPanel pianoPanel;
  
  private MusicComposition composition;
  private MusicTracker tracker;
  private MusicPlayer player;
  
  /**
   * Constructs new MusicEditorView with default fields.
   */
  public MusicEditorView() {
    this.editorPanel = new EditorPanel();
    this.pianoPanel = new PianoPanel();
    
    this.composition = new MusicComposition();
    this.tracker = new MusicTracker();
    this.player = new MusicPlayer();
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
