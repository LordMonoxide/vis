package vis.components.constructs;

import vis.components.io.Input;

public class ConsoleOutput extends Construct {
  public final Input<Character> character = new Input<>();
  
  @Override
  public void trigger() {
    System.out.print(character.get());
  }
}
