package cs3500.animator.model;

import java.awt.Color;
import java.util.ArrayList;

import cs3500.animator.util.TweenModelBuilder;

/**
 * Animation Model implementation. Represents an animation environment with various shapes that can
 * be manipulated in various ways.
 */
public class AnimationModel implements IAnimationModel {

  protected ArrayList<AShape> shapes;
  protected ArrayList<AShape> initialState;
  ArrayList<ICommand> commands;
  int time;

  /**
   * Constructs an AnimationModel.
   */
  public AnimationModel() {
    shapes = new ArrayList();
    commands = new ArrayList();
    initialState = new ArrayList();
    time = 0;
  }

  @Override
  public void moveShape(int startTime, int endTime, AShape s, int x1, int y1, int x2, int y2)
          throws IllegalArgumentException {
    if (startTime > endTime || !shapes.contains(s)
            || commandOverlap(new MoveCommand(startTime, endTime, s, x1, y1, x2, y2))) {
      throw new IllegalArgumentException("Invalid move command.");
    }

    commands.add(new MoveCommand(startTime, endTime, s, x1, y1, x2, y2));
  }

  @Override
  public void scale(int startTime, int endTime, AShape s, int width1, int height1,
                    int width2, int height2)
          throws IllegalArgumentException {
    if (startTime > endTime || !shapes.contains(s)
            || commandOverlap(new ScaleCommand(startTime, endTime, s, width1, height1
            , width2, height2))) {
      throw new IllegalArgumentException("Invalid scale command.");
    }

    commands.add(new ScaleCommand(startTime, endTime, s, width1, height1, width2, height2));
  }

  @Override
  public void gradient(int startTime, int endTime, AShape s, Color c1, Color c2)
          throws IllegalArgumentException {
    if (startTime > endTime || !shapes.contains(s)
            || commandOverlap(new GradientCommand(startTime, endTime, s, c1, c2))) {
      throw new IllegalArgumentException("Invalid gradient command.");
    }

    commands.add(new GradientCommand(startTime, endTime, s, c1, c2));
  }

  @Override
  public void addShape(AShape shape) throws IllegalArgumentException {
    if (shapes.contains(shape)) {
      throw new IllegalArgumentException("Cannot add given shape.");
    }

    shapes.add(shape);
    initialState.add(shape.copyShape());
  }

  private boolean commandOverlap(ICommand c) {
    for (ICommand other : this.commands) {
      if (other.overlaps(c)) {
        return true;
      }
    }
    return false;
  }

  @Override
  public ArrayList<AShape> getShapes() {
    return this.shapes;
  }

  @Override
  public int animationEnd() {
    int endTick = 0;
    for (AShape s : this.shapes) {
      if (s.t1 > endTick) {
        endTick = s.t1;
      }
    }
    return endTick;
  }

  @Override
  public void setToInitialState() {
    ArrayList<AShape> initialCopy = new ArrayList();
    for (AShape a : this.initialState) {
      initialCopy.add(a.copyShape());

    }

    for (AShape s : initialCopy) {
      for (AShape r : this.shapes) {
        if (r.getName().equals(s.getName())) {
          r.reset(s);

        }
      }
    }

  }

  @Override
  public ArrayList<AShape> getInitialState() {
    ArrayList<AShape> initialCopy = new ArrayList();
    for (AShape a : this.initialState) {
      initialCopy.add(a.copyShape());

    }
    return initialCopy;
  }

  @Override
  public ArrayList<ICommand> getCommands() {
    return commands;
  }

  @Override
  public void setToTime(int t) {
    for (ICommand c : this.commands) {
      c.runAnimation(t);
    }
  }

  @Override
  public ArrayList<AShape> copyShapes() {
    ArrayList<AShape> copy = new ArrayList<>();
    for (AShape a : this.shapes) {
      copy.add(a.copyShape());
    }
    return copy;
  }

  /**
   * Builder class that adapts output that the AnimationFileReader expects to the Animation Model.
   */
  public static final class Builder implements TweenModelBuilder<IAnimationModel> {
    IAnimationModel model = new AnimationModel();

    @Override
    public TweenModelBuilder<IAnimationModel> addOval(String name, float cx, float cy,
                                                      float xRadius, float yRadius, float red,
                                                      float green, float blue, int startOfLife,
                                                      int endOfLife) {
      Color c = new Color((int) (red * 255), (int) (green * 255), (int) (blue * 255));
      Oval o = new Oval(name, (int) cx, (int) cy, (int) xRadius, (int) yRadius,
              c, startOfLife, endOfLife);
      model.addShape(o);
      return this;
    }

    @Override
    public TweenModelBuilder<IAnimationModel> addRectangle(String name, float lx, float ly,
                                                           float width, float height, float red,
                                                           float green, float blue, int startOfLife,
                                                           int endOfLife) {
      Color c = new Color((int) (red * 255), (int) (green * 255), (int) (blue * 255));
      Rectangle r = new Rectangle(name, (int) lx, (int) ly, (int) width, (int) height,
              c, startOfLife, endOfLife);
      model.addShape(r);
      return this;
    }

    @Override
    public TweenModelBuilder<IAnimationModel> addMove(String name, float moveFromX, float moveFromY,
                                                      float moveToX, float moveToY, int startTime,
                                                      int endTime) {
      AShape aShape = new Rectangle("", 0, 0, 0, 0,
              Color.black, 0, 0);
      for (AShape a : this.model.getShapes()) {
        if (a.name.equals(name)) {
          aShape = a;
        }
      }
      model.moveShape(startTime, endTime, aShape, (int) moveFromX, (int) moveFromY,
              (int) moveToX, (int) moveToY);
      return this;
    }

    @Override
    public TweenModelBuilder<IAnimationModel> addColorChange(String name, float oldR, float oldG,
                                                             float oldB, float newR, float newG,
                                                             float newB, int startTime,
                                                             int endTime) {
      AShape aShape = new Rectangle("", 0, 0, 0, 0,
              Color.black, 0, 0);
      for (AShape a : this.model.getShapes()) {
        if (a.name.equals(name)) {
          aShape = a;
        }
      }
      Color c1 = new Color((int) (oldR * 255), (int) (oldG * 255), (int) (oldB * 255));
      Color c2 = new Color((int) (newR * 255), (int) (newG * 255), (int) (newB * 255));
      model.gradient(startTime, endTime, aShape, c1, c2);
      return this;
    }

    @Override
    public TweenModelBuilder<IAnimationModel> addScaleToChange(String name, float fromSx,
                                                               float fromSy, float toSx,
                                                               float toSy, int startTime,
                                                               int endTime) {
      AShape aShape = new Rectangle("", 0, 0, 0, 0,
              Color.black, 0, 0);
      for (AShape a : this.model.getShapes()) {
        if (a.name.equals(name)) {
          aShape = a;
        }
      }
      model.scale(startTime, endTime, aShape, (int) fromSx, (int) fromSy, (int) toSx, (int) toSy);
      return this;
    }

    @Override
    public IAnimationModel build() {
      return model;
    }
  }
}
