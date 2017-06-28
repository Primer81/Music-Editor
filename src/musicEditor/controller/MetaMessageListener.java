package musicEditor.controller;

import javax.sound.midi.MetaEventListener;
import javax.sound.midi.MetaMessage;
import java.util.HashMap;
import java.util.Map;

/**
 * This class represents a Meta event listener. It receives MetaMessages each time the
 * sequencer reads in a metaEvent message in it sequence. Each MetaMessage has its own data
 * which can be read to determine the meaning of the message. Each set of data will be mapped
 * to the metaRead<String, Runnable> map which will then run its corresponding runnable object.
 */
public class MetaMessageListener implements MetaEventListener {
  private Map<String, Runnable> metaRead;

  public MetaMessageListener() {
    this.metaRead = new HashMap<>();
  }

  /**
   * Sets the metaRead map to the specified map so that the map will be able to read and respond
   * to commands within the given map.
   * @param map the new map
   */
  public void setMetaReadMap(Map<String, Runnable> map) {
    this.metaRead = map;
  }

  @Override
  public void meta(MetaMessage meta) {
    String msg = new String(meta.getData());
    if (this.metaRead.containsKey(msg)) {
      this.metaRead.get(msg).run();
    }
  }
}
