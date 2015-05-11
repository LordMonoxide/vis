package vis.components.constructs;

import vis.components.io.Input;
import vis.components.io.Output;

public class For extends Construct {
  public final Input<Integer> start = new Input<>();
  public final Input<Integer> end   = new Input<>();
  
  private int _output;
  
  public final Output<Integer> index = () -> {
    return Integer.valueOf(_output);
  };
  
  @Override
  public void trigger() {
    for(_output = start.get().intValue(); _output < end.get().intValue(); _output++) {
      event.fire();
    }
  }
}
