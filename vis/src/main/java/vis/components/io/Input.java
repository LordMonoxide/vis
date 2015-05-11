package vis.components.io;

public class Input<T> {
  private Binding<T> _binding;
  
  public void bind(Binding<T> binding) {
    _binding = binding;
  }
  
  public T get() {
    return _binding.get();
  }
}
