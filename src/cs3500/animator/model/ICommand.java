package cs3500.animator.model;

/**
 * Specifies operations for Command objects. Commands are performed on the shapes to create the full
 * animation the user desires.
 */
public interface ICommand {

  /**
   * Manipulates the shape data as the animation details.
   *
   * @param time the int of the game state
   */
  void runAnimation(int time);

  /**
   * Returns the string of the command to be printed in the text view.
   *
   * @return String
   */
  String commandToString(int speed);

  /**
   * Returns the string of the animation to be printed in the svg view.
   *
   * @return String
   */
  String commandToSVG(int speed);

  /**
   * Returns boolean determining if this move overlaps with that move.
   *
   * @return boolean
   */
  boolean overlaps(ICommand other);

  /**
   * Returns the the abstract shape the command is acting on.
   *
   * @return AShape
   */
  AShape getShape();

  /**
   * Returns the string of the animation to be printed in the svg view when it loops.
   *
   * @return String
   */
  String commandToSVGCopy(int speed);
}
