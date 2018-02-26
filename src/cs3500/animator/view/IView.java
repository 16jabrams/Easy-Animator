package cs3500.animator.view;

import cs3500.animator.controller.IController;

/**
 * Collection of methods used for the different types of views that the model can be viewed from.
 */
public interface IView {

  /**
   * Runs the view, animating the model.
   */
  void run();

  /**
   * Dispatches this view from the controller (needs to dispatch back to the controller if it's a
   * hybrid or visual view so the controller can run the animation loop).
   */
  void dispatchRun(IController c);
}
