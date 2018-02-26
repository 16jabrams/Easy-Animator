package cs3500.animator.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JComboBox;

import cs3500.animator.model.IAnimationModel;
import cs3500.animator.model.AShape;
import cs3500.animator.view.HybridView;
import cs3500.animator.view.IGUIView;
import cs3500.animator.view.ISVGView;
import cs3500.animator.view.IView;
import cs3500.animator.view.SVGView;

/**
 * Controller in the MVC interface, facilitates communcation between the user, model, and view.
 */
public class Controller implements IController {
  private IAnimationModel model;
  private IView view;
  private int speed;
  private int t;

  /**
   * Creates Controller with a model and a view.
   */
  public Controller(IAnimationModel model, IView view) {
    this.model = model;
    this.view = view;
    this.t = 0;
  }

  @Override
  public void runView() {
    this.view.dispatchRun(this);
  }

  @Override
  public void hybridAnimationLoop() {
    this.configureButtonListener();
    this.configureTextListener();
    this.t = 0;

    model.setToTime(t);
    ((HybridView) view).setShapes(this.model.getShapes());
    ((HybridView) view).setVisibleShapes(this.model.getShapes());
    ((HybridView) view).setTick(t);

    while (t < (model.animationEnd() + 10)) {
      model.setToTime(t);
      ((HybridView) view).setTick(t);
      view.run();
      if ((((HybridView) view).isPaused())) {
        this.configureComboBoxListener();
      } else {
        ((HybridView) view).cutBoxListeners();
        t += 1;
        view.run();
      }
      try {
        Thread.sleep(((Double) Math.floor((1 / (double) speed) * 1000)).intValue());
      } catch (InterruptedException e) {
        return;
      }
      if (t >= model.animationEnd() && ((HybridView) view).isLooping()) {
        t = 0;
        model.setToInitialState();
        model.setToTime(0);
        ((HybridView) view).setShapes(this.model.getShapes());
        ((HybridView) view).setVisibleShapes(this.model.getShapes());
        ((HybridView) view).setTick(t);
      }
    }
  }

  @Override
  public void setSpeed(int speed) {
    this.speed = speed;
  }

  @Override
  public void animationLoop() {
    int t = 0;

    while (t < 10000) {
      model.setToTime(t);
      ((IGUIView) view).setTick(t);
      ((IGUIView) view).setShapes(this.model.copyShapes());
      view.run();
      try {
        Thread.sleep(((Double) Math.floor((1 / (double) speed) * 1000)).intValue());
      } catch (InterruptedException e) {
        return;
      }
      t += 1;
    }
  }

  /**
   * Configures button listeners.
   */
  private void configureButtonListener() {
    Map<String, Runnable> buttonClickedMap = new HashMap<String, Runnable>();
    ButtonListener buttonListener = new ButtonListener();

    buttonClickedMap.put("pause", new PauseButtonAction());
    buttonClickedMap.put("speed up", new SpeedUpButtonAction());
    buttonClickedMap.put("speed down", new SpeedDownButtonAction());
    buttonClickedMap.put("loop", new LoopButtonAction());
    buttonClickedMap.put("reset", new ResetButtonAction());


    buttonListener.setButtonClickedActionMap(buttonClickedMap);
    ((HybridView) view).addActionListener(buttonListener);
  }

  /**
   * Configures combo box listener with hybrid view.
   */
  private void configureComboBoxListener() {
    ((HybridView) view).addBoxListener(new VisibleBoxListener(true), new AllBoxListener(true));
  }

  /**
   * Represents a listener for box representing visible shapes.
   */
  class VisibleBoxListener implements ActionListener {
    boolean paused;

    /**
     * Creates a listener for box representing visible shapes.
     */
    public VisibleBoxListener(boolean paused) {
      this.paused = paused;
    }

    /**
     * Sets paused boolean field.
     */
    public void setPaused(boolean paused) {
      this.paused = paused;
    }

    public void actionPerformed(ActionEvent e) {
      if (this.paused) {
        JComboBox box = (JComboBox) e.getSource();
        String selectedShape = (String) box.getSelectedItem();
        new RemoveShapeFromView(selectedShape).run();
        view.run();
      } else {
        //do nothing
      }
    }
  }

  /**
   * Represents a listener for box representing removed shapes.
   */
  class AllBoxListener implements ActionListener {
    boolean paused;

    /**
     * Creates a listener for box representing removed shapes.
     */
    public AllBoxListener(boolean paused) {
      this.paused = paused;
    }

    public void setPaused(boolean paused) {
      this.paused = paused;
    }

    public void actionPerformed(ActionEvent e) {
      if (this.paused) {
        JComboBox box = (JComboBox) e.getSource();
        String selectedShape = (String) box.getSelectedItem();
        new AddShapeToView(selectedShape).run();
        view.run();
      } else {
        //do nothing
      }
    }
  }

  /**
   * Represents a pause button action.
   */
  class PauseButtonAction implements Runnable {
    public void run() {
      ((HybridView) view).pause();
    }
  }

  /**
   * Represents a speed up button action.
   */
  class SpeedUpButtonAction implements Runnable {
    public void run() {
      setSpeed(speed + 2);
    }
  }

  /**
   * Represents a loop button action.
   */
  class LoopButtonAction implements Runnable {
    public void run() {
      ((HybridView) view).loopToggle();
    }
  }

  /**
   * Represents a speed down button action.
   */
  class SpeedDownButtonAction implements Runnable {
    public void run() {
      if (speed > 2) {
        setSpeed(speed - 2);
      }
    }
  }

  /**
   * Represents a reset button action.
   */
  class ResetButtonAction implements Runnable {
    public void run() {
      t = 0;
      model.setToInitialState();
      model.setToTime(0);
      ((HybridView) view).setShapes(model.getShapes());
      ((HybridView) view).setVisibleShapes(model.getShapes());
      ((HybridView) view).setTick(t);
    }
  }

  /**
   * Used by remove button listener to remove shapes.
   */
  class RemoveShapeFromView implements Runnable {
    String shape;

    protected RemoveShapeFromView(String shape) {
      this.shape = shape;
    }

    public void run() {
      ((HybridView) view).removeShapeFromVisible(this.shape);
    }
  }

  /**
   * Used by remove button listener to add shapes.
   */
  class AddShapeToView implements Runnable {
    String shape;

    protected AddShapeToView(String shape) {
      this.shape = shape;
    }

    public void run() {
      ((HybridView) view).addShapeToVisible(this.shape);
    }
  }

  /**
   * Configures text listener.
   */
  private void configureTextListener() {
    TextListener textListener = new TextListener();
    TextAction action = new TextAction();
    textListener.setTextAction(action);
    ((HybridView) view).addTextListener(textListener);
  }

  /**
   * Represents action on text field.
   */
  public class TextAction implements Runnable {
    /**
     * What happens when action happens.
     */
    public void run() {
      String inputString = ((HybridView) view).getInput();
      Appendable output;
      BufferedWriter outputFile = null;
      try {
        outputFile = new BufferedWriter(new FileWriter(inputString));
        output = outputFile;
      } catch (IOException e) {
        return;
      }
      ArrayList<AShape> visibleShapes = ((HybridView) view).getShapes();
      ArrayList<AShape> finalShapes = new ArrayList<>();
      for (AShape a : visibleShapes) {
        for (AShape s : model.getInitialState()) {
          if (a.getName().equals(s.getName())) {
            finalShapes.add(s);
          }
        }
      }
      ISVGView svg = new SVGView(finalShapes, model.getCommands(), output, speed,
              1000, 1000);
      if (((HybridView) view).isLooping()) {
        svg.loopRun();
      } else {
        svg.run();
      }
      try {
        outputFile.close();
      } catch (IOException e) {
        return;
      }
    }
  }

}
