import org.junit.Test;
import static org.junit.Assert.assertEquals;

import cs3500.animator.view.HybridView;

public class HybridViewTest {

  @Test
  public void testPauseMethod() {
    HybridView v = new HybridView(10);

    assertEquals(true, v.isPaused());

    v.pause();

    assertEquals(false, v.isPaused());


  }

  @Test
  public void testLoopToggleMethod() {
    HybridView v = new HybridView(10);

    assertEquals(false, v.isLooping());

    v.loopToggle();

    assertEquals(true, v.isLooping());


  }
}
