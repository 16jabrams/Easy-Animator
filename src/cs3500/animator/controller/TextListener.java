package cs3500.animator.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Represents a listener for text box.
 */
public class TextListener implements ActionListener {
  Runnable textAction;

  /**
   * Empty default constructor.
   */
  public TextListener() {
    // default
  }

  public void setTextAction(Runnable action) {
    textAction = action;
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    textAction.run();
  }
}
