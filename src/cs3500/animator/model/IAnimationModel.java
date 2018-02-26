package cs3500.animator.model;

import java.awt.Color;
import java.util.ArrayList;

/**
 * Interface for an easy animation model for a specific shape object.
 */
public interface IAnimationModel {

  /**
   * Moves the target shape from the given starting coordinate (x1, y1) to the given ending
   * coordinate (x2, y2), with the move starting at time t0 and arriving at the destination at time
   * t1.
   *
   * @param s         the shape to move
   * @param x1        starting location x coordinate
   * @param y1        starting location y coordinate
   * @param x2        desired location x coordinate
   * @param y2        desired location y coordinate
   * @param endTime   start time of move animation
   * @param startTime end time of move animation
   * @throws IllegalArgumentException if the given target isn't in the animation model, or if t0 is
   *                                  less than t1
   */
  void moveShape(int startTime, int endTime, AShape s, int x1, int y1, int x2, int y2)
          throws IllegalArgumentException;

  /**
   * Changes the dimensions of the target shape from d1 to d2, starting at time t0 and ending at
   * time t1.
   *
   * @param s         the shape to scale
   * @param width1    the starting width of target
   * @param height1   the starting height of target
   * @param width2    the desired ending width of target
   * @param height2   the desired ending height of target
   * @param startTime start time of the scale
   * @param endTime   end time of the scale
   * @throws IllegalArgumentException if the given target isn't in the animation model or if either
   *                                  of the given dimensions aren't a valid dimension for target,
   *                                  or if t0 is less than t1.
   */
  void scale(int startTime, int endTime, AShape s, int width1, int height1, int width2, int height2)
          throws IllegalArgumentException;

  /**
   * Fades the target shape's color from c1 to c2, starting at time t0 and ending at time t1.
   *
   * @param s         the shape to change colors
   * @param c1        starting color
   * @param c2        ending color
   * @param startTime start time of color change
   * @param endTime   ending time of color change
   * @throws IllegalArgumentException if the given target isn't in the animation model, or if t0 is
   *                                  less than t1
   */
  void gradient(int startTime, int endTime, AShape s, Color c1, Color c2)
          throws IllegalArgumentException;

  /**
   * Adds shape to the AnimationModel.
   *
   * @param shape the shape to be added to the model
   * @throws IllegalArgumentException if the given shape doesn't have a unique name in relation to
   *                                  the shapes already in the AnimationModel, or if t0 is less
   *                                  than t1.
   */
  void addShape(AShape shape) throws IllegalArgumentException;

  /**
   * Returns the shapes in the animation model.
   *
   * @return the list of abstract shapes
   */
  ArrayList<AShape> getShapes();

  /**
   * Returns the commands in the animation model.
   *
   * @return the list of commands
   */
  ArrayList<ICommand> getCommands();

  /**
   * Updates all fields in the model to where they should be at a given time.
   *
   * @param t the current time
   */
  void setToTime(int t);

  /**
   * Returns a copy of the shapes in the animation model.
   *
   * @return the list of abstract shapes
   */
  ArrayList<AShape> copyShapes();

  /**
   * Returns the time that the last shape disappears.
   *
   * @return the int
   */
  int animationEnd();

  /**
   * Sets the model to its initial state.
   */
  void setToInitialState();

  /**
   * Returns a copy of the shapes in the animation model at the initial state.
   *
   * @return the list of abstract shapes
   */
  ArrayList<AShape> getInitialState();
}
