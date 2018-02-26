package cs3500.animator.view;

import java.awt.event.ActionListener;
import java.util.ArrayList;

import cs3500.animator.model.AShape;

/**
 * Interface housing methods for the Hybrid View.
 */
public interface IHybridView extends IView {

  /**
   * Stops the current animation.
   */
  void pause();

  /**
   * Toggles whether or not the animation should loop.
   */
  void loopToggle();

  /**
   * Method to check if this hybrid view is paused or not.
   *
   * @return true if this view is paused, false if it is running
   */
  boolean isPaused();

  /**
   * Adds an ActionListener to be used by the hybrid view's buttons.
   *
   * @param listener designated action listener
   */
  void addActionListener(ActionListener listener);

  /**
   * Adds an ActionListener to be used by the hybrid view's text field.
   *
   * @param listener designated action listener
   */
  void addTextListener(ActionListener listener);

  /**
   * Adds the designated shape to the view's currently visible shapes.
   */
  void addShapeToVisible(String s);

  /**
   * Removes the designated shape from the view's currently visible shapes.
   */
  void removeShapeFromVisible(String s);

  /**
   * Adds action listeners to the HybridView's JComboBoxes.
   *
   * @param visibleBoxListener action listener for the list of visible shapes
   * @param allBoxListener     action listener for the list of removed shapes
   */
  void addBoxListener(ActionListener visibleBoxListener, ActionListener allBoxListener);

  /**
   * Disconnects the JComboBox listeners in this view.
   */
  void cutBoxListeners();

  /**
   * Method to find out if this view should loop.
   *
   * @return boolean true if looping, false if not.
   */
  boolean isLooping();

  /**
   * Retrievs text box input from this view.
   *
   * @return String of the input to the text box
   */
  String getInput();

  /**
   * Method to get the currently visible shapes from this view.
   *
   * @return arraylist of the currently visible shapes
   */
  ArrayList<AShape> getShapes();

  /**
   * Set's this views arraylist of shapes to the given list.
   *
   * @param shapes desired list of shapes
   */
  void setShapes(ArrayList<AShape> shapes);

  /**
   * Set's this view's arraylist of shapes to be displayed.
   *
   * @param shapes desired list of shapes
   */
  void setVisibleShapes(ArrayList<AShape> shapes);

  /**
   * Sets this view's current tick to t.
   *
   * @param t desired tick
   */
  void setTick(int t);
}
