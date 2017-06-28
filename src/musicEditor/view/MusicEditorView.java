package musicEditor.view;

import musicEditor.gui.EditorPanel;
import musicEditor.gui.PianoPanel;
import musicEditor.music.MusicComposition;
import musicEditor.music.MusicPlayer;
import musicEditor.music.MusicSheet;
import musicEditor.music.MusicTracker;

import javax.sound.midi.MetaEventListener;
import javax.swing.*;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;

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

  @Override
  public void setComposition(MusicComposition composition) {
    this.composition = composition;
  }

  @Override
  public void setTracker(MusicTracker tracker) {
    this.tracker = tracker;
  }

  @Override
  public void setPlayer(MusicPlayer player) {
    this.player = player;
  }

  @Override
  public void addKeyListener(KeyListener listener) {

  }

  @Override
  public void addMouseListener(MouseListener listener) {

  }

  @Override
  public void addMetaEventListener(MetaEventListener listener) {

  }

  @Override
  public void initialize() {
    JFrame frame = new JFrame();
  }

  @Override
  public void update() {
    
  }
}
