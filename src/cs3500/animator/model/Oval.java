package cs3500.animator.model;

import java.awt.Color;
import java.awt.Graphics2D;

/**
 * Represents an oval.
 */
public class Oval extends AShape {

  /**
   * Constructs an Oval.
   *
   * @param name   the name of the shape
   * @param x      the position on the x axis
   * @param y      the position on the y axis
   * @param c      the Color
   * @param width  the width of the shape
   * @param height the height of the shape
   * @param t0     time oval appears
   * @param t1     time oval disappears
   */
  public Oval(String name, int x, int y, int width, int height, Color c, int t0, int t1) {
    super(name, x, y, width, height, c, t0, t1);
  }

  @Override
  public String shapeToString(int speed) {
    double start = (double) t0 / (double) speed;
    double end = (double) t1 / (double) speed;
    return String.format("Name: %s%nType: oval%nCenter: (%d,%d)" +
                    ", %s, Color: (%d,%d,%d)%n" +
                    "Appears at t=%.1fs%nDisappears at t=%.1fs%n", super.name, super.x, super.y
            , "X radius: " + width + ", Y radius: " + height, super.c.getRed(), super.c.getGreen(),
            super.c.getBlue(), start, end);
  }

  @Override
  public String shapeToSVG(int speed) {
    int start = t0 / speed;
    int end = t1 / speed;
    String out = "";
    String color = "" + c.getRed() + "," + c.getGreen() + "," + c.getBlue();
    out += "<ellipse cx=\"" + this.x + "\" cy=\"" + this.y +
            "\" ry=\"" + this.height + "\" rx=\"" + this.width + "\" stroke=\"black\" " +
            "stroke-width=\"0\" fill=\"rgb(" + color + ")\" fill-opacity=\"0\">\n";
    out += "<set attributeName=\"fill-opacity\" attributeType=\"XML\"\n" +
            "         to=\"1\"\n" +
            "         begin=\"" + start + "s\"  />\n";
    out += "<set attributeName=\"fill-opacity\" attributeType=\"XML\"\n" +
            "         to=\"0\"\n" +
            "         begin=\"" + end + "s\"  />\n";
    return out;
  }

  @Override
  public String endLineSVG() {
    return "</ellipse>\n";
  }

  @Override
  public boolean equals(Object other) {
    if (other instanceof Oval) {
      return this.name.equals(((Oval) other).name);
    } else {
      return false;
    }
  }

  @Override
  public int hashCode() {
    return this.name.hashCode();
  }

  @Override
  public void drawShapeOnPanel(Graphics2D g2d, int time) {
    if (t0 <= time && time <= t1) {
      g2d.setColor(super.c);
      g2d.fillOval(super.x, super.y, this.width * 2, this.height * 2);
    } else {
      //do nothing
    }

  }

  @Override
  public String formatDimensions(int width, int height) {
    return String.format("X radius: %d, Y radius: %d", width, height);
  }

  @Override
  public String xFieldSVG() {
    return "cx";
  }

  @Override
  public String yFieldSVG() {
    return "cy";
  }

  @Override
  public String scaleX() {
    return "rx";
  }

  @Override
  public String scaleY() {
    return "ry";
  }

  @Override
  public AShape copyShape() {
    AShape copy = new Oval(this.name, this.x, this.y, this.width, this.height,
            this.c, this.t0, this.t1);
    return copy;
  }

  @Override
  public String shapeToSVGCopy(int speed) {
    String start = t0 / speed + ";base.begin+" + t0 / speed;
    String end = t1 / speed + ";base.begin+" + t1 / speed;
    String out = "";
    String color = "" + c.getRed() + "," + c.getGreen() + "," + c.getBlue();
    out += "<ellipse cx=\"" + this.x + "\" cy=\"" + this.y +
            "\" ry=\"" + this.height + "\" rx=\"" + this.width + "\" stroke=\"black\" " +
            "stroke-width=\"0\" fill=\"rgb(" + color + ")\" fill-opacity=\"0\">\n";
    out += "<set attributeName=\"fill-opacity\" attributeType=\"XML\"\n" +
            "         to=\"1\"\n" +
            "         begin=\"" + start + "s\"  />\n";
    out += "<set attributeName=\"fill-opacity\" attributeType=\"XML\"\n" +
            "         to=\"0\"\n" +
            "         begin=\"" + end + "s\"  />\n";
    return out;
  }
}
