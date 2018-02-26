package cs3500.animator.view;

import java.io.IOException;
import java.util.ArrayList;

import cs3500.animator.controller.IController;

import cs3500.animator.model.AShape;
import cs3500.animator.model.ICommand;

/**
 * Represents the SVG view of the animation.
 */
public class SVGView implements ISVGView {
  ArrayList<AShape> shapes;
  ArrayList<ICommand> commands;
  Appendable ap;
  int speed;
  int sceneX;
  int sceneY;

  /**
   * Constructs an SVGView.
   *
   * @param shapes   the shapes in the model
   * @param commands the commands from the model acting on the shapes
   * @param ap       appendable object to store output for various formats
   * @param speed    the number of ticks per second the animation will run at
   * @param sceneX   width of the animation canvas
   * @param sceneY   height of the animation canvas
   */
  public SVGView(ArrayList<AShape> shapes, ArrayList<ICommand> commands, Appendable ap,
                 int speed, int sceneX, int sceneY) {
    this.shapes = shapes;
    this.commands = commands;
    this.ap = ap;
    this.speed = speed;
    this.sceneX = sceneX;
    this.sceneY = sceneY;
  }

  @Override
  public void dispatchRun(IController c) {
    this.run();
  }

  @Override
  public void run() {
    runSVGAnimation();
  }

  /**
   * Runs the SVG animation.
   */
  public void runSVGAnimation() {
    try {
      ap.append("<svg width=\"2000\" height=\"2000\" version=\"1.1\"\n" +
              "     xmlns=\"http://www.w3.org/2000/svg\">\n\n");
      ArrayList<ICommand> comms;
      for (AShape s : this.shapes) {
        comms = new ArrayList();
        ap.append(s.shapeToSVG(speed));
        for (ICommand c : this.commands) {
          if (c.getShape().getName().equals(s.getName())) {
            comms.add(c);
          }
        }
        for (ICommand c : comms) {
          ap.append(c.commandToSVG(speed));
        }
        ap.append(s.endLineSVG() + "\n");
      }
      ap.append("</svg>");
    } catch (IOException e) {
      e.printStackTrace();
    }
    System.out.print(ap);
  }

  @Override
  public int getEndTime() {
    int last = 0;
    for (AShape a : shapes) {
      if (a.endTime() > last) {
        last = a.endTime();
      }
    }
    return last;
  }

  @Override
  public void loopRun() {
    int end = this.getEndTime();
    try {
      ap.append("<svg width=\"2000\" height=\"2000\" version=\"1.1\"\n" +
              "     xmlns=\"http://www.w3.org/2000/svg\">\n\n");
      ap.append("<rect>\n" +
              "   <animate id=\"base\" begin=\"0;base.end\" dur=\"20s\" " +
              "attributeName=\"visibility\" from=\"hide\" to=\"hide\"/>\n" +
              "</rect>");
      ArrayList<ICommand> comms;
      for (AShape s : this.shapes) {
        comms = new ArrayList();
        ap.append(s.shapeToSVGCopy(speed));
        for (ICommand c : this.commands) {
          if (c.getShape().getName().equals(s.getName())) {
            comms.add(c);
          }
        }
        for (ICommand c : comms) {
          ap.append(c.commandToSVGCopy(speed));
        }
        ap.append(s.endLineSVG() + "\n");
      }
      ap.append("</svg>");
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
