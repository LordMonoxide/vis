package vis.components.operants;

import vis.components.io.Input;
import vis.components.io.Output;

public class Or extends Operant {
  public final Input<Boolean> a = new Input<>();
  public final Input<Boolean> b = new Input<>();
  
  public final Output<Boolean> out = () -> {
    return Boolean.valueOf(a.get().booleanValue() || b.get().booleanValue());
  };
}
