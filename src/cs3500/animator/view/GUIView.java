package cs3500.animator.view;

import javax.swing.JFrame;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.util.ArrayList;

import cs3500.animator.controller.IController;
import cs3500.animator.model.AShape;

/**
 * Represents GUI view of model animation.
 */
public class GUIView extends JFrame implements IGUIView {
  private AnimationPanel panel;
  private int t;
  private ArrayList<AShape> shapes;
  private int speed;

  /**
   * Constructs GUIView.
   */
  public GUIView(int speed) {
    this.t = 0;
    this.shapes = new ArrayList();
    this.speed = speed;

    this.setSize(1000, 1000);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setLayout(new BorderLayout());
    panel = new AnimationPanel();
    panel.setPreferredSize(new Dimension(1000, 1000));
    this.add(panel);

    this.pack();
  }

  public void setShapes(ArrayList<AShape> shapes) {
    this.shapes = shapes;
  }

  public void setTick(int t) {
    this.t = t;
  }

  @Override
  public void dispatchRun(IController c) {
    c.setSpeed(this.speed);
    c.animationLoop();
  }

  @Override
  public void run() {
    this.setVisible(true);
    panel.setShapes(this.shapes);
    panel.setTime(this.t);
    panel.paintComponent(this.getGraphics());
  }
}
