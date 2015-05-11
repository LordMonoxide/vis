package vis.components.triggers;

public class Event {
  protected Triggerable _triggerable;
  
  public void bind(Triggerable triggerable) {
    _triggerable = triggerable;
  }
  
  public void fire() {
    if(_triggerable != null) {
      _triggerable.trigger();
    }
  }
}
