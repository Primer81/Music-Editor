package musicEditor;

import musicEditor.controller.IMusicEditorController;
import musicEditor.controller.MusicEditorController;
import musicEditor.model.IMusicEditorModel;
import musicEditor.model.MusicEditorModel;
import musicEditor.util.MusicReader;
import musicEditor.view.MusicEditorView;

import javax.sound.midi.InvalidMidiDataException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

/**
 * Application that runs the music editor.
 */
public class MusicEditor {
  /**
   * Main method for application running.
   *
   * @param args The arguments
   * @throws IOException Exception
   * @throws InvalidMidiDataException Exception
   */
  public static void main(String[] args) throws IOException, InvalidMidiDataException {
    MusicEditorModel.Builder builder = new MusicEditorModel.Builder();
    Readable fileReader = null;
    Scanner in = new Scanner(System.in);
    String fileName;

    while (fileReader == null) {
      System.out.print("Input a file: ");
      try {
        fileName = in.next();
        fileReader = new FileReader(new File(fileName));
      } catch (FileNotFoundException e) {
        e.printStackTrace();
      }
    }
    IMusicEditorModel model = MusicReader.parseFile(fileReader, builder);

    IMusicEditorController controller = new MusicEditorController(model);

    controller.setView(new MusicEditorView());
  }
}
