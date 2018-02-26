package cs3500.animator.view;

import java.awt.Graphics;
import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;

import javax.swing.JPanel;

import cs3500.animator.model.AShape;

/**
 * JPanel to draw shapes onto.
 */
public class AnimationPanel extends JPanel {
  private ArrayList<AShape> shapes;
  private int time;

  /**
   * Default constructor.
   */
  public AnimationPanel() {
    super();
    this.setBackground(Color.CYAN);
    this.time = 0;
    this.shapes = new ArrayList();
  }

  /**
   * Sets this panel's shapes to the given arraylist of shapes.
   *
   * @param shapes the desired list of shapes.
   */
  protected void setShapes(ArrayList<AShape> shapes) {
    this.shapes = shapes;
  }

  /**
   * Sets this panel's time counter to the given int.
   *
   * @param t desired time
   */
  protected void setTime(int t) {
    this.time = t;
  }

  @Override
  protected void paintComponent(Graphics g) {
    super.paintComponent(g);
    Graphics2D g2d = (Graphics2D) g;
    for (AShape s : shapes) {

      s.drawShapeOnPanel(g2d, this.time);
    }
  }

}
