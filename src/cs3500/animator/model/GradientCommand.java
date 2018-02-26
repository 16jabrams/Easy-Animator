package cs3500.animator.model;

import java.awt.Color;

/**
 * Represents a Color Change command.
 */
public class GradientCommand extends ACommand {
  Color c1;
  Color c2;

  /**
   * Constructs a Gradient Animation.
   *
   * @param startTime time the animation starts
   * @param endTime   time the animation ends
   * @param s         shape that the animation is acting on
   * @param c1        original color of the shape
   * @param c2        final color of the shape
   */
  public GradientCommand(int startTime, int endTime, AShape s, Color c1, Color c2) {
    super(startTime, endTime, s);
    this.c1 = c1;
    this.c2 = c2;
  }

  @Override
  public void runAnimation(int time) {
    if (super.startTime <= time && time <= super.endTime) {
      int timeDiff = super.endTime - super.startTime;
      int r1 = c1.getRed();
      int g1 = c1.getGreen();
      int b1 = c1.getBlue();
      int r2 = c2.getRed();
      int g2 = c2.getGreen();
      int b2 = c2.getBlue();
      int rDiff = r2 - r1;
      int gDiff = g2 - g1;
      int bDiff = b2 - b1;
      double rChange = (double) rDiff / (double) timeDiff;
      double gChange = (double) gDiff / (double) timeDiff;
      double bChange = (double) bDiff / (double) timeDiff;
      double rChangeSoFar = rChange * (time - startTime);
      double gChangeSoFar = gChange * (time - startTime);
      double bChangeSoFar = bChange * (time - startTime);
      int red = c1.getRed() + ((Double) Math.floor(rChangeSoFar)).intValue();
      int green = c1.getGreen() + ((Double) Math.floor(gChangeSoFar)).intValue();
      int blue = c1.getBlue() + ((Double) Math.floor(bChangeSoFar)).intValue();
      Color newC = new Color((int) red, (int) green, (int) blue);
      super.s.c = newC;
    } else {
      //do nothing
    }
  }

  @Override
  public String commandToString(int speed) {
    double start = (double) startTime / (double) speed;
    double end = (double) endTime / (double) speed;
    return String.format("Shape %s changes color from (%d,%d,%d) to (%d,%d,%d) " +
                    "from t=%.1fs to t=%.1fs"
            , super.s.name, this.c1.getRed(), this.c1.getGreen(), this.c1.getBlue(),
            this.c2.getRed(), this.c2.getGreen()
            , this.c2.getBlue(), start, end);
  }

  @Override
  public String commandToSVG(int speed) {
    int start = startTime / speed;
    int dur = (super.endTime - super.startTime) / speed;
    String out = "";
    String color1 = c1.getRed() + "," + c1.getGreen() + "," + c1.getBlue();
    String color2 = c2.getRed() + "," + c2.getGreen() + "," + c2.getBlue();
    out += "<animate attributeName=\"fill\"\n" +
            "            attributeType=\"XML\"\n" +
            "            from=\"rgb(" + color1 + ")\" to=\"rgb(" + color2 + ")\"\n" +
            "            begin=\"" + start + "s\" dur=\"" + dur + "s\"\n" +
            "            repeatCount=\"0\"\n" +
            "            fill=\"freeze\"" +
            "           />\n";
    return out;
  }

  @Override
  public boolean overlaps(ICommand other) {
    if (other instanceof GradientCommand) {
      if (((GradientCommand) other).s.equals(this.s)) {
        if (((GradientCommand) other).startTime > this.startTime) {
          return ((GradientCommand) other).startTime < this.endTime;
        } else {
          return ((GradientCommand) other).endTime > this.startTime;
        }
      } else {
        return false;
      }
    } else {
      return false;
    }
  }

  @Override
  public String commandToSVGCopy(int speed) {
    String start = startTime / speed + ";base.begin+" + startTime / speed;
    int dur = (super.endTime - super.startTime) / speed;
    String out = "";
    String color1 = c1.getRed() + "," + c1.getGreen() + "," + c1.getBlue();
    String color2 = c2.getRed() + "," + c2.getGreen() + "," + c2.getBlue();
    out += "<animate attributeName=\"fill\"\n" +
            "            attributeType=\"XML\"\n" +
            "            from=\"rgb(" + color1 + ")\" to=\"rgb(" + color2 + ")\"\n" +
            "            begin=\"" + start + "s\" dur=\"" + dur + "s\"\n" +
            "            repeatCount=\"0\"\n" +
            "            fill=\"freeze\"" +
            "           />\n";
    return out;
  }
}
