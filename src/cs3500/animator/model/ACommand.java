package cs3500.animator.model;

/**
 * Abstract skeleton of Command interface.
 */
public abstract class ACommand implements ICommand {
  protected int startTime;
  protected int endTime;
  protected AShape s;

  /**
   * Constructs an abstract Command.
   *
   * @param startTime time the animation starts
   * @param endTime   time the animation ends
   * @param s         shape that the animation is acting on
   */
  public ACommand(int startTime, int endTime, AShape s) {
    this.startTime = startTime;
    this.endTime = endTime;
    this.s = s;
  }

  @Override
  public AShape getShape() {
    return s;
  }
}
