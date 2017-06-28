package musicEditor.controller;

import musicEditor.view.IMusicEditorView;

import javax.sound.midi.InvalidMidiDataException;

/**
 * Created by gwlar on 6/27/2017.
 */
public interface IMusicEditorController {

  /**
   * Sets the view for the controller and displays it.
   * @param view The view
   * @throws InvalidMidiDataException If there is any invalid MIDI data
   */
  void setView(IMusicEditorView view) throws InvalidMidiDataException;

  /**
   * Creates and sets a keyboard listener for the view
   * In effect it creates snippets of code as Runnable object, one for each time a key
   * is typed, pressed and released, only for those that the program needs.
   * Last we create our KeyboardListener object, set all its maps and then give it to
   * the view.
   */
  void configureKeyBoardListener();

  /**
   * Creates and set a mouse listener for the view.
   * In effect it creates snippets of code as Runnable object, one for each time a mouse is clicked,
   * pressed, and released, only for those that the program needs.
   * Last we create our MouseKeyListener object, set all its maps and then give it to
   * the view.
   */
  void configureMouseKeyListener();

  /**
   * Creates and set a meta listener for the view.
   * In effect it creates snippets of code as Runnable object, one for each time a meta event is
   * read by the sequencer.
   * Last we create our MetaEventsListener object, set all its maps and then give it to
   * the view.
   */
  void configureMetaEventsListener();
}
