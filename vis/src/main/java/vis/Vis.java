package vis;

import java.awt.Frame;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.SwingUtilities;

import vis.components.constructs.ConsoleOutput;
import vis.components.constructs.For;
import vis.components.io.OutputBinding;
import vis.components.io.SimpleBinding;
import vis.components.operants.Mod;
import vis.components.operants.Xor;
import vis.components.sources.TextSource;
import vis.gui.GraphRenderer;

public class Vis {
  public static void main(String[] args) {
    /*TextSource text = new TextSource("This is a test");
    TextSource key  = new TextSource("My key 123");
    
    For f = new For();
    f.start.bind(new SimpleBinding<>(Integer.valueOf(0)));
    f.end  .bind(new OutputBinding<>(text.length));
    
    Mod<Integer> mod = new Mod<>();
    mod.numerator  .bind(new OutputBinding<>(f.index));
    mod.denominator.bind(new OutputBinding<>(key.length));
    
    text.index.bind(new OutputBinding<>(f.index));
    key .index.bind(new OutputBinding<>(mod.out));
    
    Xor<Character> xor = new Xor<>();
    xor.a.bind(new OutputBinding<>(text.character));
    xor.b.bind(new OutputBinding<>(key .character));
    
    ConsoleOutput cout = new ConsoleOutput();
    cout.character.bind(new OutputBinding<>(xor.out));
    f.event.bind(cout);
    
    f.trigger();*/
    
    SwingUtilities.invokeLater(() -> {
      new Vis();
    });
  }
  
  private final Frame _window;
  
  public Vis() {
    _window = new Frame("Vis");
    _window.setSize(1280, 720);
    _window.setVisible(true);
    _window.addWindowListener(new WindowAdapter() {
      @Override
      public void windowClosing(WindowEvent e) {
        _window.dispose();
        super.windowClosing(e);
      }
    });
    
    _window.add(new GraphRenderer());
  }
}
