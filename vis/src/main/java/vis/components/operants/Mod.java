package vis.components.operants;

import vis.components.io.Input;
import vis.components.io.Output;

public class Mod<T extends Number> extends Operant {
  public final Input<T> numerator   = new Input<>();
  public final Input<T> denominator = new Input<>();
  
  @SuppressWarnings("unchecked")
  public final Output<T> out = () -> {
    T val1 = numerator  .get();
    T val2 = denominator.get();
    
    if(val1 instanceof Integer) { return (T)(Number)(val1.   intValue() % val2.   intValue()); }
    if(val1 instanceof Short  ) { return (T)(Number)(val1. shortValue() % val2. shortValue()); }
    if(val1 instanceof Byte   ) { return (T)(Number)(val1.  byteValue() % val2.  byteValue()); }
    if(val1 instanceof Double ) { return (T)(Number)(val1.doubleValue() % val2.doubleValue()); }
    if(val1 instanceof Float  ) { return (T)(Number)(val1. floatValue() % val2. floatValue()); }
    
    throw new IllegalArgumentException("This shouldn't happen: " + val1 + " was not a number.");
  };
}
