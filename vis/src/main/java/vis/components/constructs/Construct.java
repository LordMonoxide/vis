package vis.components.constructs;

import vis.components.Component;
import vis.components.triggers.Event;
import vis.components.triggers.Triggerable;

public abstract class Construct extends Triggerable implements Component {
  public final Event event = new Event();
}
