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

  public void setComposition(MusicComposition composition);

  public void setTracker(MusicTracker tracker);

  public void setPlayer(MusicPlayer player);

  public void addKeyListener(KeyListener listener);

  public void addMouseListener(MouseListener listener);

  public void addMetaEventListener(MetaEventListener listener);

  public void initialize();

  public void update();
}
