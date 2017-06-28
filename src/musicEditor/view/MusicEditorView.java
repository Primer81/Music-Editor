package musicEditor.view;

import musicEditor.gui.EditorPanel;
import musicEditor.gui.MeasuresComponent;
import musicEditor.gui.PianoPanel;
import musicEditor.gui.PitchesComponent;
import musicEditor.music.MusicComposition;
import musicEditor.music.MusicPlayer;
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

  private JScrollPane upperScrollPane;
  private JComponent measuresComponent;
  private JComponent pitchesComponent;
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

    this.measuresComponent = new MeasuresComponent(composition, tracker, player);
    this.pitchesComponent = new PitchesComponent(composition, tracker, player);
    this.editorPanel = new EditorPanel(composition, tracker, player);
    this.pianoPanel = new PianoPanel(composition, tracker, player);

    this.upperScrollPane = new JScrollPane(this.editorPanel);
    this.upperScrollPane.setColumnHeaderView(this.pitchesComponent);
    this.upperScrollPane.setRowHeaderView(this.measuresComponent);
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
    frame.add(this.upperScrollPane);
    frame.setVisible(true);
  }

  @Override
  public void update() {
    this.upperScrollPane.repaint();
  }
}
