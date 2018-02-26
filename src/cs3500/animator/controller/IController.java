package cs3500.animator.controller;

/**
 * Interface that houses methods for Controllers.
 */
public interface IController {

  /**
   * Dispatch to view to see what needs to be run.
   */
  void runView();

  /**
   * Controls model and a visual or hybrid view to show the animation graphically.
   */
  void animationLoop();

  /**
   * Controls model and a visual or hybrid view to show the animation graphically.
   */
  void hybridAnimationLoop();

  /**
   * Sets this controller's speed (only used for visual and interactive views).
   *
   * @param speed the desired ticks per second
   */
  void setSpeed(int speed);

}
