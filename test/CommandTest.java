import org.junit.Test;

import java.awt.Color;

import cs3500.animator.model.ScaleCommand;
import cs3500.animator.model.Rectangle;
import cs3500.animator.model.MoveCommand;
import cs3500.animator.model.GradientCommand;


import static org.junit.Assert.assertEquals;

/**
 * Tests the methods for the Command interface.
 */
public class CommandTest {

  /**
   * Runs tests for the description method.
   */
  @Test
  public void commandToString() {
    Rectangle r1 = new Rectangle("R", 5, 5, 1, 2, Color.cyan, 1, 2);
    MoveCommand m = new MoveCommand(0, 50, r1, 1, 1, 10, 10);
    GradientCommand g = new GradientCommand(10, 20, r1, Color.blue, Color.black);
    ScaleCommand s = new ScaleCommand(5, 10, r1, 1, 2, 5, 10);
    String mOut = "Shape R moves from (1,1) to (10,10) from t=0 to t=50";
    String gOut = "Shape R changes color from (0,0,255) to (0,0,0) from t=10 to t=20";
    String sOut = "Shape R scales from Width: 1, Height: 2 to Width: 5, " +
            "Height: 10 from t=5 to t=10";
    assertEquals(m.commandToString(5), mOut);
    assertEquals(g.commandToString(5), gOut);
    assertEquals(s.commandToString(5), sOut);
  }

  /**
   * Runs tests for the runAnimation method.
   */
  @Test
  public void runAnimationMoveLinear() {
    Rectangle r1 = new Rectangle("R", 1, 1, 1, 2, Color.cyan, 1, 2);
    MoveCommand m = new MoveCommand(0, 10, r1, 1, 1, 11, 11);
    m.runAnimation(1);
    assertEquals(true, r1.shapeToString(5).contains("Lower-left corner: (2,2)"));
    m.runAnimation(1);
    assertEquals(true, r1.shapeToString(5).contains("Lower-left corner: (3,3)"));
  }

  /**
   * Runs tests for the runAnimation method.
   */
  @Test
  public void runAnimationMoveSlow() {
    Rectangle r1 = new Rectangle("R", 1, 1, 1, 2, Color.cyan, 1, 2);
    MoveCommand m = new MoveCommand(0, 20, r1, 1, 1, 11, 11);
    m.runAnimation(1);
    System.out.println(r1.shapeToString(5));
    //assertEquals(true, r1.shapeToString().contains("Lower-left corner: (2,2)"));
    m.runAnimation(1);
    System.out.println(r1.shapeToString(5));
    assertEquals(true, r1.shapeToString(5).contains("Lower-left corner: (3,3)"));
  }

  /**
   * Runs tests for the runAnimation method.
   */
  @Test
  public void runAnimationGradient() {
    Rectangle r1 = new Rectangle("R", 1, 1, 1, 2, Color.black, 1, 2);
    GradientCommand g = new GradientCommand(0, 10, r1, Color.black, Color.blue);
    g.runAnimation(1);
    assertEquals(true, r1.shapeToString(5).contains("Color: (0,0,25)"));
    g.runAnimation(1);
    assertEquals(true, r1.shapeToString(5).contains("Color: (0,0,50)"));
  }

  /**
   * Runs tests for the runAnimation method.
   */
  @Test
  public void runAnimationScale() {
    Rectangle r1 = new Rectangle("R", 1, 1, 1, 2, Color.cyan, 1, 2);
    ScaleCommand s = new ScaleCommand(0, 5, r1, 1, 2, 6, 12);
    s.runAnimation(1);
    assertEquals(true, r1.shapeToString(5).contains("Width: 2, Height: 4"));
    s.runAnimation(1);
    assertEquals(true, r1.shapeToString(5).contains("Width: 3, Height: 6"));
  }

}
