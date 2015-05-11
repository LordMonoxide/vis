package vis.components.io;

public class SimpleBinding<T> extends Binding<T> {
  private T _val;
  
  public SimpleBinding(T val) {
    _val = val;
  }
  
  @Override
  public T get() {
    return _val;
  }
}
