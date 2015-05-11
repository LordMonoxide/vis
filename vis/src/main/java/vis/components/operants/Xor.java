package vis.components.operants;

import vis.components.io.Input;
import vis.components.io.Output;

public class Xor<T> extends Operant {
  public final Input<T> a = new Input<>();
  public final Input<T> b = new Input<>();
  
  @SuppressWarnings("unchecked")
  public final Output<T> out = () -> {
    T val1 = a.get();
    T val2 = b.get();
    
    if(val1 instanceof Integer  ) { return (T)(Number)((int  )val1 ^ (int  )val2); }
    if(val1 instanceof Short    ) { return (T)(Number)((short)val1 ^ (short)val2); }
    if(val1 instanceof Byte     ) { return (T)(Number)((byte )val1 ^ (byte )val2); }
    if(val1 instanceof Character) { return (T)(Character)(char)((char)val1 ^ (char)val2); }
    
    //TODO: what am I doing with my life
    
    throw new IllegalArgumentException("This shouldn't happen: " + val1 + " was not a number.");
  };
}
