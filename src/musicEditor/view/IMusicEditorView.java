package musicEditor.view;

import musicEditor.music.MusicComposition;
import musicEditor.music.MusicPlayer;
import musicEditor.music.MusicTracker;

import javax.sound.midi.MetaEventListener;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;

/**
 * Created by gwlar on 6/27/2017.
 */
public interface IMusicEditorView {

  void addKeyListener(KeyListener listener);

  void addMouseListener(MouseListener listener);

  void addMetaEventListener(MetaEventListener listener);

  void initialize();

  void update();
}
