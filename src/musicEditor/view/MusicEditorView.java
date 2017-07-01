package musicEditor.view;

import musicEditor.MusicEditor;
import musicEditor.gui.EditorPanel;
import musicEditor.gui.MeasuresComponent;
import musicEditor.gui.PianoPanel;
import musicEditor.gui.PitchesComponent;
import musicEditor.music.MusicComposition;
import musicEditor.music.MusicPlayer;
import musicEditor.music.MusicTracker;

import javax.sound.midi.MetaEventListener;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;

/**
 * Represents the view for the music editor.
 */
public class MusicEditorView implements IMusicEditorView {
  private final int CELL_WIDTH = 20;
  private final int CELL_HEIGHT = 20;

  private MusicComposition composition;
  private MusicTracker tracker;
  private MusicPlayer player;

  private JFrame frame;
  private JScrollPane upperScrollPane;
  private JComponent measuresComponent;
  private JComponent pitchesComponent;
  private JPanel editorPanel;

  private JPanel pianoPanel;

  /**
   * Constructs new MusicEditorView with the given composition, tracker, and player
   */
  public MusicEditorView(MusicComposition composition,
                         MusicTracker tracker, MusicPlayer player) {
    this.composition = composition;
    this.tracker = tracker;
    this.player = player;

    this.frame = new JFrame();

    this.measuresComponent = new MeasuresComponent(composition, tracker, player);
    this.pitchesComponent = new PitchesComponent(composition, tracker, player);
    this.editorPanel = new EditorPanel(composition, tracker, player);
    this.pianoPanel = new PianoPanel(composition, tracker, player);

    this.upperScrollPane = new JScrollPane(this.editorPanel);
    this.upperScrollPane.setColumnHeaderView(this.measuresComponent);
    this.upperScrollPane.setRowHeaderView(this.pitchesComponent);
  }

  @Override
  public void addKeyListener(KeyListener listener) {
    this.frame.addKeyListener(listener);
    this.upperScrollPane.addKeyListener(listener);
    this.editorPanel.addKeyListener(listener);
  }

  @Override
  public void addMouseListener(MouseListener listener) {
    this.pianoPanel.addMouseListener(listener);
  }

  @Override
  public void addMetaEventListener(MetaEventListener listener) {
    this.player.getSequencer().addMetaEventListener(listener);
  }

  @Override
  public void initialize() {
    frame.setFocusable(true);
    frame.requestFocus();
    frame.setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
    frame.setTitle("Music Editor");
    frame.setPreferredSize(new Dimension(20 * 71, 1000));
    frame.setResizable(true);
    frame.setLayout(new BorderLayout());
    frame.add(this.upperScrollPane, BorderLayout.CENTER);
    frame.add(this.pianoPanel, BorderLayout.SOUTH);
    frame.pack();
    frame.setVisible(true);
  }

  @Override
  public void update() {
    // moves the view to where the beat is
    int beat = (int) this.player.getSequencer().getTickPosition();
    int viewWidth = this.upperScrollPane.getViewport().getWidth();
    int beatPerView = viewWidth / this.CELL_WIDTH;
    int page = beat - (beat % beatPerView);
    int x = page * this.CELL_WIDTH;
    JViewport viewport = this.upperScrollPane.getViewport();
    viewport.setViewPosition(new Point(x, viewport.getViewPosition().y));
    // repaints the gui
    this.frame.repaint();
  }
}
