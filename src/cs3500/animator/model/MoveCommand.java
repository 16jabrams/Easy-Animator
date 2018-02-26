package cs3500.animator.model;

/**
 * Represents the Move command.
 */
public class MoveCommand extends ACommand {
  int x1;
  int y1;
  int x2;
  int y2;

  /**
   * Constructs a Move Command.
   *
   * @param startTime time the animation starts
   * @param endTime   time the animation ends
   * @param s         shape that the animation is acting on
   * @param x1        x pos starting point of shape
   * @param y1        y pos starting point of shape
   * @param x2        x pos ending point of shape
   * @param y2        y pos ending point of shape
   */
  public MoveCommand(int startTime, int endTime, AShape s, int x1, int y1, int x2, int y2) {
    super(startTime, endTime, s);
    this.x1 = x1;
    this.y1 = y1;
    this.x2 = x2;
    this.y2 = y2;
  }

  @Override
  public void runAnimation(int time) {
    if (super.startTime <= time && super.endTime >= time) {
      int timeDiff = super.endTime - super.startTime;
      int xDiff = this.x2 - this.x1;
      int yDiff = this.y2 - this.y1;
      double xChange = (double) xDiff / (double) timeDiff;
      double yChange = (double) yDiff / (double) timeDiff;
      double xChangeSoFar = xChange * (time - startTime);
      double yChangeSoFar = yChange * (time - startTime);
      int newX = this.x1 + ((Double) Math.floor(xChangeSoFar)).intValue();
      int newY = this.y1 + ((Double) Math.floor(yChangeSoFar)).intValue();
      //System.out.println("Shape: " + this.s.getName() + ", newX: " + newX);
      super.s.x = newX;
      super.s.y = newY;
    } else {
      //do nothing
    }
  }

  @Override
  public String commandToString(int speed) {
    double start = (double) startTime / (double) speed;
    double end = (double) endTime / (double) speed;
    return String.format("Shape %s moves from (%d,%d) to (%d,%d) from t=%.1fs to t=%.1fs"
            , super.s.name, this.x1, this.y1, this.x2, this.y2, start, end);
  }

  @Override
  public String commandToSVG(int speed) {
    int start = startTime / speed;
    int dur = (super.endTime - super.startTime) / speed;
    String out = "";
    String xF = s.xFieldSVG();
    String yF = s.yFieldSVG();
    out += "<animate attributeName=\"" + xF + "\"\n" +
            "            attributeType=\"XML\"\n" +
            "            from=\"" + x1 + "\" to=\"" + x2 + "\"\n" +
            "            begin=\"" + start + "s\" dur=\"" + dur + "s\"\n" +
            "            fill=\"freeze\"\n" +
            "            repeatCount=\"0\"\n" +
            "           />\n" +
            "   <animate attributeName=\"" + yF + "\"\n" +
            "            attributeType=\"XML\"\n" +
            "            from=\"" + y1 + "\" to=\"" + y2 + "\"\n" +
            "            begin=\"" + start + "s\" dur=\"" + dur + "s\"\n" +
            "            fill=\"freeze\"\n" +
            "            repeatCount=\"0\"\n" +
            "           />\n";
    return out;
  }

  @Override
  public boolean overlaps(ICommand other) {
    if (other instanceof MoveCommand) {
      if (((MoveCommand) other).s.equals(this.s)) {
        if (((MoveCommand) other).startTime > this.startTime) {
          return ((MoveCommand) other).startTime < this.endTime;
        } else {
          return ((MoveCommand) other).endTime > this.startTime;
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
    String xF = s.xFieldSVG();
    String yF = s.yFieldSVG();
    out += "<animate attributeName=\"" + xF + "\"\n" +
            "            attributeType=\"XML\"\n" +
            "            from=\"" + x1 + "\" to=\"" + x2 + "\"\n" +
            "            begin=\"" + start + "s\" dur=\"" + dur + "s\"\n" +
            "            fill=\"freeze\"\n" +
            "            repeatCount=\"0\"\n" +
            "           />\n" +
            "   <animate attributeName=\"" + yF + "\"\n" +
            "            attributeType=\"XML\"\n" +
            "            from=\"" + y1 + "\" to=\"" + y2 + "\"\n" +
            "            begin=\"" + start + "s\" dur=\"" + dur + "s\"\n" +
            "            fill=\"freeze\"\n" +
            "            repeatCount=\"0\"\n" +
            "           />\n";
    return out;
  }
}
