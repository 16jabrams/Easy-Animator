import org.junit.Test;

import java.awt.Color;

import cs3500.animator.model.Rectangle;
import cs3500.animator.model.Oval;

import static org.junit.Assert.assertEquals;

/**
 * Used for testing methods in the shape interface.
 */
public class ShapeTest {
  /**
   * Tests that the shape descriptions are drawn as desired in text view.
   */
  @Test
  public void shapeDescription() {
    Rectangle r1 = new Rectangle("R",5, 5, 5, 5, Color.cyan, 1, 2);
    Oval o1 = new Oval("O", 1, 2, 6, 6, Color.cyan, 1, 2);
    String rOut = "Name: R\n" +
            "Type: rectangle\n" +
            "Lower-left corner: (5,5), Width: 5, Height: 5, Color: (0,255,255)\n" +
            "Appears at t=0.5s\n" +
            "Disappears at t=1.0s\n";
    String oOut = "Name: O\n" +
            "Type: oval\n" +
            "Center: (1,2), X radius: 6, Y radius: 6, Color: (0,255,255)\n" +
            "Appears at t=0.5s\n" +
            "Disappears at t=1.0s\n";
    assertEquals(r1.shapeToString(2), rOut);
    assertEquals(o1.shapeToString(2), oOut);
  }
}
