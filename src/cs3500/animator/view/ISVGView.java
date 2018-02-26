package cs3500.animator.view;

/**
 * Interface housing methods for the SVG View.
 */
public interface ISVGView extends IView {

  /**
   * Returns ending of animation.
   */
  int getEndTime();

  /**
   * Runs view when animation is to be looped.
   */
  void loopRun();
}
