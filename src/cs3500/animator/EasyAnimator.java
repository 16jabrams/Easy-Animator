package cs3500.animator;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

import cs3500.animator.controller.Controller;
import cs3500.animator.controller.IController;
import cs3500.animator.model.AnimationModel;
import cs3500.animator.model.IAnimationModel;
import cs3500.animator.util.AnimationFileReader;
import cs3500.animator.util.TweenModelBuilder;
import cs3500.animator.view.GUIView;
import cs3500.animator.view.HybridView;
import cs3500.animator.view.IView;
import cs3500.animator.view.SVGView;
import cs3500.animator.view.TextView;

/**
 * Used to run main method to interact with user.
 */
public final class EasyAnimator {
  /**
   * Runs main.
   */
  public static void main(String args[]) {

    String input = null;
    int speed = 1;
    String viewType = null;
    BufferedWriter outputFile = null;
    Appendable output = System.out;
    for (int i = 0; i < args.length; i += 1) {
      switch (args[i]) {
        case "-if":
          input = args[i + 1];
          i += 1;
          break;
        case "-iv":
          viewType = args[i + 1];
          i += 1;
          break;
        case "-o":
          try {
            outputFile = new BufferedWriter(new FileWriter(args[i + 1]));
            output = outputFile;
          } catch (IOException e) {
            throw new IllegalArgumentException();
          }
          i += 1;
          break;
        case "-speed":
          speed = (Integer.valueOf(args[i + 1]));
          i += 1;
          break;
        default:
          throw new IllegalArgumentException();
      }
    }

    if (input == null || viewType == null) {
      throw new IllegalArgumentException();
    }

    TweenModelBuilder<IAnimationModel> builderModel = new AnimationModel.Builder();
    AnimationFileReader fileReader = new AnimationFileReader();
    try {
      fileReader.readFile(input, builderModel);
    } catch (FileNotFoundException e) {
      return;
    }

    IAnimationModel model = builderModel.build();
    IView view = null;

    switch (viewType) {
      case "text":
        view = new TextView(model.getShapes(), model.getCommands(), output, speed);
        break;
      case "visual":
        view = new GUIView(speed);
        break;
      case "svg":
        view = new SVGView(model.getShapes(), model.getCommands(), output, speed, 2000,
                2000);
        break;
      case "interactive":
        view = new HybridView(speed);
        break;
      default:
        throw new IllegalArgumentException();

    }

    IController controller = new Controller(model, view);
    controller.runView();

    if (outputFile == null) {
      //do nothing
    } else {
      try {
        outputFile.close();
      } catch (IOException e) {
        return;
      }
    }
  }
}