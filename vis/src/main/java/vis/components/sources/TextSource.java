package vis.components.sources;

import vis.components.io.Input;
import vis.components.io.Output;

public class TextSource extends Source {
  public final Input<Integer> index = new Input<>();
  
  private final String _text;
  
  public final Output<Integer>   length;
  public final Output<Character> character;
  
  public TextSource(String text) {
    _text = text;
    
    length    = () -> { return _text.length(); };
    character = () -> { return _text.charAt(index.get()); };
  }
}
