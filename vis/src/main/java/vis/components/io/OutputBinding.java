package vis.components.io;

public class OutputBinding<T> extends Binding<T> {
  private final Output<? extends T> _output;
  
  public OutputBinding(Output<? extends T> output) {
    _output = output;
  }
  
  @Override
  public T get() {
    return _output.get();
  }
}
