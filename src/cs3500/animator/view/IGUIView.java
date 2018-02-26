package cs3500.animator.view;

import cs3500.animator.model.AShape;

import java.util.ArrayList;

/**
 * Interface housing methods for the GUI View.
 */
public interface IGUIView extends IView {

  /**
   * Updates this view's tick count.
   *
   * @param t desired tick
   */
  void setTick(int t);

  /**
   * Updates this view's shapes list.
   *
   * @param shapes the desired shapes
   */
  public void setShapes(ArrayList<AShape> shapes);
}
