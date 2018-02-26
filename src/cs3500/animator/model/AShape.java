package cs3500.animator.model;

import java.awt.Color;

/**
 * Abstract skeleton of shape interface.
 */
public abstract class AShape implements IShape {
  String name;
  int x;
  int y;
  int width;
  int height;
  Color c;
  int t0;
  int t1;

  /**
   * Constructs an abstract Shape.
   *
   * @param name   name of the Shape
   * @param x      the position on the x axis
   * @param y      the position on the y axis
   * @param c      the Color
   * @param width  the width of the shape
   * @param height the height of the shape
   * @param t0     when the shape appears
   * @param t1     when the shape disappears
   */
  public AShape(String name, int x, int y, int width, int height, Color c, int t0, int t1) {
    this.name = name;
    this.x = x;
    this.y = y;
    this.width = width;
    this.height = height;
    this.c = c;
    this.t0 = t0;
    this.t1 = t1;
  }

  @Override
  public String getName() {
    return this.name;
  }

  @Override
  public void reset(AShape other) {
    this.x = other.x;
    this.y = other.y;
    this.width = other.width;
    this.height = other.height;
    this.c = other.c;
    this.t0 = other.t0;
    this.t1 = other.t1;
  }

  @Override
  public int endTime() {
    return this.t1;
  }
}
