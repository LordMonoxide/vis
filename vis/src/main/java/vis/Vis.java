package vis;

import vis.components.constructs.For;
import vis.components.io.SimpleBinding;
import vis.components.triggers.Triggerable;

public class Vis {
  public static void main(String[] args) {
    For f = new For();
    f.start.bind(new SimpleBinding<>(Integer.valueOf(0)));
    f.end  .bind(new SimpleBinding<>(Integer.valueOf(10)));
    
    f.event.bind(new Triggerable() {
      @Override
      public void trigger() {
        System.out.println(f.index.get());
      }
    });
    
    f.trigger();
  }
}
