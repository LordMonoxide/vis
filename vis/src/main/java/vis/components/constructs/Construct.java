package vis.components.constructs;

import vis.components.triggers.Event;
import vis.components.triggers.Triggerable;

public abstract class Construct extends Triggerable {
  public final Event event = new Event();
}
