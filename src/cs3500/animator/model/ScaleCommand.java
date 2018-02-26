package cs3500.animator.model;

/**
 * Represents a Scale command.
 */
public class ScaleCommand extends ACommand {
  int width1;
  int height1;
  int width2;
  int height2;

  /**
   * Constructs a Scale command.
   *
   * @param startTime time the animation starts
   * @param endTime   time the animation ends
   * @param s         shape that the animation is acting on
   * @param width1    starting width of shape
   * @param height1   starting height of shape
   * @param width2    ending width of shape
   * @param height2   ending height of shape
   */
  public ScaleCommand(int startTime, int endTime, AShape s, int width1, int height1, int width2,
                      int height2) {
    super(startTime, endTime, s);
    this.width1 = width1;
    this.height1 = height1;
    this.width2 = width2;
    this.height2 = height2;
  }

  @Override
  public void runAnimation(int time) {
    if (super.startTime <= time && super.endTime >= time) {
      int timeDiff = super.endTime - super.startTime;
      int wDiff = this.width2 - this.width1;
      int hDiff = this.height2 - this.height1;
      double wChange = (double) wDiff / (double) timeDiff;
      double hChange = (double) hDiff / (double) timeDiff;
      double wChangeSoFar = wChange * (time - startTime);
      double hChangeSoFar = hChange * (time - startTime);
      super.s.width = this.width1 + ((Double) Math.floor(wChangeSoFar)).intValue();
      super.s.height = this.height1 + ((Double) Math.floor(hChangeSoFar)).intValue();
    } else {
      //do nothing
    }
  }

  @Override
  public String commandToString(int speed) {
    double start = (double) startTime / (double) speed;
    double end = (double) endTime / (double) speed;
    return String.format("Shape %s scales from %s to %s from t=%.1fs to t=%.1fs"
            , super.s.name, s.formatDimensions(this.width1, this.height1)
            , s.formatDimensions(this.width2, this.height2), start, end);
  }

  @Override
  public String commandToSVG(int speed) {
    int start = startTime / speed;
    int dur = ((Double) (Math.ceil((double) (endTime - startTime)) / ((double) speed))).intValue();
    if (dur == 0) {
      dur = 1;
    }
    String out = "";
    String w = this.s.scaleX();
    String h = this.s.scaleY();
    out += "<animate attributeName=\"" + w + "\"\n" +
            "            attributeType=\"XML\"\n" +
            "            from=\"" + this.width1 + "\" to=\"" + this.width2 + "\"\n" +
            "            begin=\"" + start + "s\" dur=\"" + dur + "s\"\n" +
            "            fill=\"freeze\"\n" +
            "            repeatCount=\"0\"\n" +
            "           />\n" +
            "   <animate attributeName=\"" + h + "\"\n" +
            "            attributeType=\"XML\"\n" +
            "            from=\"" + this.height1 + "\" to=\"" + this.height2 + "\"\n" +
            "            begin=\"" + start + "s\" dur=\"" + dur + "s\"\n" +
            "            fill=\"freeze\"\n" +
            "            repeatCount=\"0\"\n" +
            "           />\n";
    return out;
  }

  @Override
  public boolean overlaps(ICommand other) {
    if (other instanceof ScaleCommand) {
      if (((ScaleCommand) other).s.equals(this.s)) {
        if (((ScaleCommand) other).startTime > this.startTime) {
          return ((ScaleCommand) other).startTime < this.endTime;
        } else {
          return ((ScaleCommand) other).endTime > this.startTime;
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
    int dur = ((Double) (Math.ceil((double) (endTime - startTime)) / ((double) speed))).intValue();
    if (dur == 0) {
      dur = 1;
    }
    String out = "";
    String w = this.s.scaleX();
    String h = this.s.scaleY();
    out += "<animate attributeName=\"" + w + "\"\n" +
            "            attributeType=\"XML\"\n" +
            "            from=\"" + this.width1 + "\" to=\"" + this.width2 + "\"\n" +
            "            begin=\"" + start + "s\" dur=\"" + dur + "s\"\n" +
            "            fill=\"freeze\"\n" +
            "            repeatCount=\"0\"\n" +
            "           />\n" +
            "   <animate attributeName=\"" + h + "\"\n" +
            "            attributeType=\"XML\"\n" +
            "            from=\"" + this.height1 + "\" to=\"" + this.height2 + "\"\n" +
            "            begin=\"" + start + "s\" dur=\"" + dur + "s\"\n" +
            "            fill=\"freeze\"\n" +
            "            repeatCount=\"0\"\n" +
            "           />\n";
    return out;
  }
}
