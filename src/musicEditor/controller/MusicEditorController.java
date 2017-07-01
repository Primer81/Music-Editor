package musicEditor.controller;

import musicEditor.model.IMusicEditorModel;
import musicEditor.view.IMusicEditorView;

import javax.sound.midi.InvalidMidiDataException;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * Controller for the Music Editor program. Allows key inputs to navigate the visual views as well
 * as play/pause the audio portions of the views. Can add new notes to the compositions through
 * mouse selection.
 */
public class MusicEditorController implements IMusicEditorController {
  private IMusicEditorModel model;
  private IMusicEditorView view;

  /**
   * Constructs a MusicEditorController.
   *
   * @param model The model
   */
  public MusicEditorController(IMusicEditorModel model) {
    this.model = model;
  }

  /**
   * Sets the view for the controller and displays it.
   * @param view The view
   * @throws InvalidMidiDataException If there is any invalid MIDI data
   */
  public void setView(IMusicEditorView view) throws InvalidMidiDataException {
    this.view = view;
    //create and set the listeners
    this.configureKeyBoardListener();
    this.configureMouseKeyListener();
    this.configureMetaEventsListener();
    view.initialize();
  }

  /**
   * Creates and sets a keyboard listener for the view
   * In effect it creates snippets of code as Runnable object, one for each time a key
   * is typed, pressed and released, only for those that the program needs.
   * Last we create our KeyboardListener object, set all its maps and then give it to
   * the view.
   */
  private void configureKeyBoardListener()
  {
    Map<Character,Runnable> keyTypes = new HashMap<Character,Runnable>();
    Map<Integer,Runnable> keyPresses = new HashMap<Integer,Runnable>();
    Map<Integer,Runnable> keyReleases = new HashMap<Integer,Runnable>();

    keyPresses.put(KeyEvent.VK_LEFT, () -> {
      if (!this.model.isRunning()) {
        this.model.setBeat(this.model.getBeat() - 1);
        this.view.update();
      }
    });
    keyPresses.put(KeyEvent.VK_RIGHT, () -> {
      if (!this.model.isRunning()) {
        this.model.setBeat(this.model.getBeat() + 1);
        this.view.update();
      }
    });
    keyPresses.put(KeyEvent.VK_SPACE, () -> {
      if (this.model.isRunning()) {
        this.model.pause();
        this.view.update();
      }
      else {
        this.model.sequenceComposition();
        this.model.play();
        this.view.update();
      }
    });
    keyPresses.put(KeyEvent.VK_HOME, () -> {
      if (!this.model.isRunning()) {
        this.model.setBeat(0);
        this.view.update();
      }
    });
    keyPresses.put(KeyEvent.VK_END, () -> {
      if (!this.model.isRunning()) {
        this.model.setBeat(this.model.length() - 1);
        this.view.update();
      }
    });

    KeyboardListener listener = new KeyboardListener();
    listener.setKeyTypedMap(keyTypes);
    listener.setKeyPressedMap(keyPresses);
    listener.setKeyReleasedMap(keyReleases);

    this.view.addKeyListener(listener);
  }

  /**
   * Creates and set a mouse listener for the view.
   * In effect it creates snippets of code as Runnable object, one for each time a mouse is clicked,
   * pressed, and released, only for those that the program needs.
   * Last we create our MouseKeyListener object, set all its maps and then give it to
   * the view.
   */
  private void configureMouseKeyListener() {
    Map<Integer,Runnable> mouseClicks = new HashMap<Integer,Runnable>();
    Stack<Point> points = new Stack<>();

    MouseEventListener listener = new MouseEventListener();
    listener.setMouseClickedMap(mouseClicks);
    listener.setPoints(points);

    this.view.addMouseListener(listener);
  }

  /**
   * Creates and set a meta listener for the view.
   * In effect it creates snippets of code as Runnable object, one for each time a meta event is
   * read by the sequencer.
   * Last we create our MetaEventsListener object, set all its maps and then give it to
   * the view.
   */
  private void configureMetaEventsListener() {
    Map<String, Runnable> metaRead = new HashMap<>();

    metaRead.put("beat", () -> {
      this.model.setBeat(this.model.getBeat() + 1);
      this.view.update();
    });
    metaRead.put("end", () -> {
      this.model.pause();
    });

    MetaMessageListener listener = new MetaMessageListener();
    listener.setMetaReadMap(metaRead);

    this.view.addMetaEventListener(listener);
  }
}
