package cs3500.animator.model;

import java.awt.Graphics2D;

/**
 * Interface for shape objects. Will be used in the implementation of shape classes that need to be
 * able to be animated. This includes movement, dimension resizing, and color changes.
 */
public interface IShape {
  /**
   * Method to return a string representation of this shape.
   *
   * @return String describing this shape.
   */
  String shapeToString(int speed);

  /**
   * Method to return an SVG text representation of this shape.
   *
   * @return String.
   */
  String shapeToSVG(int speed);

  /**
   * Method to return an SVG tag to close the shape.
   *
   * @return String.
   */
  String endLineSVG();

  /**
   * Method that places the shape on the swing panel.
   */
  void drawShapeOnPanel(Graphics2D g2d, int time);

  /**
   * Method to format the width and height fields for each shape for textual representation.
   *
   * @return String.
   */
  String formatDimensions(int width, int height);

  /**
   * Method to return an SVG rep of the x position.
   *
   * @return String.
   */
  String xFieldSVG();

  /**
   * Method to return an SVG rep of the y position.
   *
   * @return String.
   */
  String yFieldSVG();

  /**
   * Method to return an SVG rep of the width.
   *
   * @return String.
   */
  String scaleX();

  /**
   * Method to return an SVG rep of the height.
   *
   * @return String.
   */
  String scaleY();

  /**
   * Method to return the name of the shape.
   *
   * @return String.
   */
  String getName();

  /**
   * Method that returns a copy of the shape.
   *
   * @return AShape.
   */
  AShape copyShape();

  /**
   * Method that changes this shape to have fields of other shape.
   *
   * @param other AShape to be set to
   */
  void reset(AShape other);

  /**
   * Method that returns the time the shape disappears.
   *
   * @return int.
   */
  int endTime();

  /**
   * Method to return an SVG text representation of this shape when it is looped.
   *
   * @return String.
   */
  String shapeToSVGCopy(int speed);
}
