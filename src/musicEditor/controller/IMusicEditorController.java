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
}
