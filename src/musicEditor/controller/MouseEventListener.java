package musicEditor.controller;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Map;
import java.util.Stack;

/**
 * This class represents a mouse listener. It is configurable by the controller that
 * instantiates it.
 * This class implements the MouseListener interface, so that its object can be used as a
 * valid mouseListener for Java Swing.
 */
public class MouseEventListener implements MouseListener {
  Map<Integer, Runnable> mouseClickedMap;
  Stack<Point> points;

  /**
   * Empty default constructor.
   */
  public MouseEventListener() {
  }

  /**
   * Sets this MouseEventListener to the given map.
   * @param map the new map
   */
  public void setMouseClickedMap(Map<Integer, Runnable> map) {
    this.mouseClickedMap = map;
  }

  /**
   * Sets this MouseEventListener's set of points to the given arrayList
   * @param points the new set of points
   */
  public void setPoints(Stack<Point> points) {
    this.points = points;
  }

  @Override
  public void mouseClicked(MouseEvent e) {
    if (mouseClickedMap.containsKey(e.getButton())) {
      points.push(e.getPoint());
      mouseClickedMap.get(e.getButton()).run();
    }
  }

  @Override
  public void mousePressed(MouseEvent e) {
    // do nothing
  }

  @Override
  public void mouseReleased(MouseEvent e) {
    // do nothing
  }

  @Override
  public void mouseEntered(MouseEvent e) {
    // do nothing
  }

  @Override
  public void mouseExited(MouseEvent e) {
    // do nothing
  }
}
