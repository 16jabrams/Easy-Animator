package cs3500.animator.model;

import java.awt.Color;
import java.awt.Graphics2D;

/**
 * Represents a rectangle.
 */
public class Rectangle extends AShape {

  /**
   * Constructs a Rectangle.
   *
   * @param name   the name of the shape
   * @param x      the position on the x axis
   * @param y      the position on the y axis
   * @param c      the Color
   * @param width  the width of the shape
   * @param height the height of the shape
   * @param t0     time shape appears
   * @param t1     time shape disappears
   */
  public Rectangle(String name, int x, int y, int width, int height, Color c, int t0, int t1) {
    super(name, x, y, width, height, c, t0, t1);
  }

  @Override
  public String shapeToString(int speed) {
    double start = (double) t0 / (double) speed;
    double end = (double) t1 / (double) speed;
    return String.format("Name: %s%nType: rectangle%nLower-left corner: (%d,%d)" +
                    ", %s, Color: (%d,%d,%d)%n" +
                    "Appears at t=%.1fs%nDisappears at t=%.1fs%n", super.name, super.x, super.y
            , "Width: " + this.width + ", Height: " + this.height, super.c.getRed(),
            super.c.getGreen(), super.c.getBlue()
            , start, end);
  }

  @Override
  public String shapeToSVG(int speed) {
    int start = t0 / speed;
    int end = t1 / speed;
    String out;
    String color = super.c.getRed() + "," + super.c.getGreen() + "," + super.c.getBlue();
    out = "<rect x=\"" + super.x + "\" y=\"" + super.y + "\" " +
            "width=\"" + this.width + "\" height=\"" + this.height +
            "\" fill=\"rgb(" + color + ")\" stroke-width=\"0\" fill-opacity=\"0\">\n";
    out = out + "<set attributeName=\"fill-opacity\" attributeType=\"XML\"\n" +
            "         to=\"1\"\n" +
            "         begin=\"" + start + "s\"  /> \n";
    out = out + "<set attributeName=\"fill-opacity\" attributeType=\"XML\"\n" +
            "         to=\"0\"\n" +
            "         begin=\"" + end + "s\"  />\n";
    return out;
  }

  @Override
  public String endLineSVG() {
    return "</rect>\n";
  }

  @Override
  public boolean equals(Object other) {
    if (other instanceof Rectangle) {
      return this.name.equals(((Rectangle) other).name);
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
    if (super.t0 <= time && super.t1 >= time) {
      g2d.setColor(super.c);
      g2d.fillRect(this.x, this.y, this.width, this.height);
    } else {
      //do nothing
    }
  }

  @Override
  public String formatDimensions(int width, int height) {
    return String.format("Width: %d, Height: %d", width, height);
  }

  @Override
  public String xFieldSVG() {
    return "x";
  }

  @Override
  public String yFieldSVG() {
    return "y";
  }

  @Override
  public String scaleX() {
    return "width";
  }

  @Override
  public String scaleY() {
    return "height";
  }

  @Override
  public AShape copyShape() {
    Rectangle copy = new Rectangle(this.name, this.x, this.y, this.width, this.height,
            this.c, this.t0, this.t1);
    return copy;
  }

  @Override
  public String shapeToSVGCopy(int speed) {
    String start = t0 / speed + ";base.begin+" + t0 / speed;
    String end = t1 / speed + ";base.begin+" + t1 / speed;
    String out;
    String color = super.c.getRed() + "," + super.c.getGreen() + "," + super.c.getBlue();
    out = "<rect x=\"" + super.x + "\" y=\"" + super.y + "\" " +
            "width=\"" + this.width + "\" height=\"" + this.height +
            "\" fill=\"rgb(" + color + ")\" stroke-width=\"0\" fill-opacity=\"0\">\n";
    out = out + "<set attributeName=\"fill-opacity\" attributeType=\"XML\"\n" +
            "         to=\"1\"\n" +
            "         begin=\"" + start + "s\"  /> \n";
    out = out + "<set attributeName=\"fill-opacity\" attributeType=\"XML\"\n" +
            "         to=\"0\"\n" +
            "         begin=\"" + end + "s\"  />\n";
    return out;
  }
}
