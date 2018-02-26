package cs3500.animator.view;

import java.util.ArrayList;
import java.io.IOException;

import cs3500.animator.controller.IController;

import cs3500.animator.model.AShape;
import cs3500.animator.model.ICommand;

/**
 * Represents the text view of the animation.
 */
public class TextView implements ITextView {
  ArrayList<AShape> shapes;
  ArrayList<ICommand> commands;
  Appendable ap;
  int speed;

  /**
   * Constructs a TextView.
   *
   * @param shapes   the shapes in the model
   * @param commands the commands from the model acting on the shapes
   * @param ap       appendable object to store output for various formats
   * @param speed    the number of ticks per second the animation will run at
   */
  public TextView(ArrayList<AShape> shapes, ArrayList<ICommand> commands, Appendable ap,
                  int speed) {
    this.shapes = shapes;
    this.commands = commands;
    this.ap = ap;
    this.speed = speed;
  }

  @Override
  public void dispatchRun(IController c) {
    this.run();
  }

  @Override
  public void run() {
    runTextAnimation();
  }

  /**
   * Represents the text view of the animation.
   */
  public void runTextAnimation() {
    try {
      ap.append("Shapes:\n");

      for (AShape s : this.shapes) {
        ap.append(s.shapeToString(speed));
      }

      ap.append("\n");

      for (ICommand c : this.commands) {
        ap.append(c.commandToString(speed)).append("\n");
      }

    } catch (IOException e) {
      return;
    }
    System.out.print(ap);
    //return ap;
  }


}
