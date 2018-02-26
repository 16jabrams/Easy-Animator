package cs3500.animator.view;

import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JComboBox;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JLabel;

import java.awt.event.ActionListener;
import java.awt.FlowLayout;
import java.awt.Dimension;

import cs3500.animator.controller.IController;
import cs3500.animator.model.AShape;

/**
 * Class representing a view that can be interacted with by a user.
 */
public class HybridView extends JFrame implements IHybridView {
  private AnimationPanel panel;

  private int t;
  private boolean paused;
  private boolean loop;
  private JButton pauseButton;
  private JButton loopButton;
  private JButton speedUpButton;
  private JButton speedDownButton;
  private JButton resetButton;
  private JTextField input;
  private JComboBox visibleBox;
  private JComboBox removedBox;
  private ArrayList<AShape> visibleShapes;
  private ArrayList<AShape> allShapes;
  private ArrayList<AShape> removedShapes;
  int speed;

  /**
   * Constructs a HybridView.
   */
  public HybridView(int speed) {
    this.paused = true;
    this.loop = false;
    this.t = 0;
    this.visibleShapes = new ArrayList();
    this.allShapes = new ArrayList();
    this.removedShapes = new ArrayList();
    this.speed = speed;

    ControlPanel cPanel = new ControlPanel();

    visibleBox = new JComboBox(this.getAShapeNames(this.visibleShapes));
    removedBox = new JComboBox(this.getAShapeNames(this.removedShapes));

    this.setSize(800, 800);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setLayout(new FlowLayout());

    pauseButton = new JButton("play");
    pauseButton.setActionCommand("pause");
    loopButton = new JButton("currently not looping");
    loopButton.setActionCommand("loop");
    speedUpButton = new JButton("speed up");
    speedUpButton.setActionCommand("speed up");
    speedDownButton = new JButton("slow down");
    speedDownButton.setActionCommand("speed down");
    resetButton = new JButton("reset");
    resetButton.setActionCommand("reset");

    input = new JTextField(20);

    panel = new AnimationPanel();
    panel.setPreferredSize(new Dimension(800, 600));
    cPanel.setPreferredSize(new Dimension(800, 200));
    this.add(panel);
    this.add(cPanel);

    JLabel visibleLabel = new JLabel("Currently visible shapes: ");
    JLabel invisLabel = new JLabel("Currently non-visible shapes: ");

    cPanel.add(visibleLabel);
    cPanel.add(visibleBox);
    cPanel.add(pauseButton);
    cPanel.add(invisLabel);
    cPanel.add(removedBox);
    cPanel.add(loopButton);
    cPanel.add(speedUpButton);
    cPanel.add(speedDownButton);
    cPanel.add(input);
    cPanel.add(resetButton);

    this.pack();
    this.setVisible(true);
  }

  /**
   * Returns the given list of shapes as an array of strings containing the shapes' names (packaged
   * in a comboboxmodel to be used by the comboboxes).
   *
   * @param shapes list of shapes to convert to an array of names
   * @return comboboxmodel with all the shapes' names.
   */
  private ComboBoxModel<String> getAShapeNames(ArrayList<AShape> shapes) {
    String[] names = new String[shapes.size()];
    int i = 0;
    for (AShape s : shapes) {
      names[i] = s.getName();
      i += 1;
    }
    return new DefaultComboBoxModel(names);
  }

  @Override
  public void setShapes(ArrayList<AShape> shapes) {
    this.allShapes = shapes;
  }

  @Override
  public void setVisibleShapes(ArrayList<AShape> shapes) {
    this.visibleShapes = shapes;
    this.removedBox.setModel(getAShapeNames(this.removedShapes));
    this.visibleBox.setModel(getAShapeNames(this.visibleShapes));
  }

  @Override
  public void setTick(int t) {
    this.t = t;
  }

  @Override
  public void dispatchRun(IController c) {
    c.setSpeed(this.speed);
    c.hybridAnimationLoop();
  }

  @Override
  public void run() {
    panel.setShapes(this.visibleShapes);
    panel.setTime(this.t);
    panel.paintComponent(this.getGraphics());
  }

  @Override
  public boolean isPaused() {
    return this.paused;
  }

  @Override
  public void pause() {
    this.paused = !this.paused;
    if (this.paused) {
      this.pauseButton.setText("play");
    } else {
      this.pauseButton.setText("pause");
    }
  }

  @Override
  public void loopToggle() {
    if (!this.loop) {
      this.loop = true;
      this.loopButton.setText("currently looping");
    } else {
      this.loop = false;
      this.loopButton.setText("currently not looping");
    }
  }

  @Override
  public void addShapeToVisible(String name) {
    for (AShape s : this.allShapes) {
      if (s.getName().equals(name)) {
        this.visibleShapes.add(s);
        this.removedShapes.remove(s);
      }
    }
    this.removedBox.setModel(this.getAShapeNames(this.removedShapes));
    this.visibleBox.setModel(this.getAShapeNames(this.visibleShapes));
  }

  @Override
  public void removeShapeFromVisible(String name) {
    ArrayList<AShape> visibleShapesCopy = new ArrayList();
    for (AShape s : this.visibleShapes) {
      if (s.getName().equals(name)) {
        this.removedShapes.add(s);
      } else {
        visibleShapesCopy.add(s);
      }
    }
    this.visibleShapes = visibleShapesCopy;
    this.removedBox.setModel(this.getAShapeNames(this.removedShapes));
    this.visibleBox.setModel(this.getAShapeNames(this.visibleShapes));
  }

  @Override
  public void addActionListener(ActionListener actionListener) {
    pauseButton.addActionListener(actionListener);
    speedUpButton.addActionListener(actionListener);
    speedDownButton.addActionListener(actionListener);
    loopButton.addActionListener(actionListener);
    resetButton.addActionListener(actionListener);
  }

  @Override
  public void addBoxListener(ActionListener visibleBoxListener, ActionListener allBoxListener) {
    if (visibleBox.getActionListeners().length == 0) {
      visibleBox.addActionListener(visibleBoxListener);
    }
    if (removedBox.getActionListeners().length == 0) {
      removedBox.addActionListener(allBoxListener);
    }
  }

  @Override
  public void cutBoxListeners() {
    if (visibleBox.getActionListeners().length > 0) {
      visibleBox.removeActionListener(visibleBox.getActionListeners()[0]);
    }
    if (removedBox.getActionListeners().length > 0) {
      removedBox.removeActionListener(removedBox.getActionListeners()[0]);
    }
  }

  @Override
  public boolean isLooping() {
    return this.loop;
  }

  @Override
  public void addTextListener(ActionListener listener) {
    input.addActionListener(listener);
  }

  @Override
  public String getInput() {
    String out = input.getText();
    input.setText("");
    return out;
  }

  @Override
  public ArrayList<AShape> getShapes() {
    return visibleShapes;
  }

}
