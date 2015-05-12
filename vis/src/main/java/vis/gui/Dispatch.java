package vis.gui;

import static org.lwjgl.glfw.Callbacks.errorCallbackPrint;
import static org.lwjgl.glfw.GLFW.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.lwjgl.glfw.GLFWErrorCallback;
import org.lwjgl.opengl.GL11;

public class Dispatch {
  public static final Dispatch instance = new Dispatch();
  
  public static void join() {
    while(instance._thread.isAlive()) {
      try {
        instance._thread.join(100);
      } catch(InterruptedException e) {
        e.printStackTrace();
      }
    }
  }
  
  static {
    GLFWErrorCallback errorCallback = errorCallbackPrint(System.err);
    
    glfwSetErrorCallback(errorCallback);
    
    Runtime.getRuntime().addShutdownHook(new Thread() {
      @Override
      public void run() {
        errorCallback.release();
        glfwTerminate();
      }
    });
    
    if(glfwInit() != GL11.GL_TRUE) {
      throw new IllegalStateException("Unable to initialize GLFW.");
    }
  }
  
  private final List<Context> _contexts = new ArrayList<>();
  
  private int _windowCount;
  
  private Thread  _thread;
  private boolean _running;
  
  private Dispatch() { }
  
  void windowOpened() {
    _windowCount++;
    
    if(!_running) {
      start();
    }
  }
  
  void windowClosed(Window window) {
    removeContextForWindow(window);
    
    _windowCount--;
    
    if(_windowCount == 0) {
      stop();
    }
  }
  
  void registerContext(Context context) {
    _contexts.add(context);
  }
  
  private void removeContextForWindow(Window window) {
    for(Iterator<Context> it = _contexts.iterator(); it.hasNext(); ) {
      if(it.next()._window == window) {
        it.remove();
      }
    }
  }
  
  private void start() {
    _running = true;
    
    _thread = new Thread(() -> {
      loop();
    });
    
    _thread.start();
  }
  
  private void stop() {
    _running = false;
  }
  
  private void loop() {
    while(_running) {
      for(Context ctx : _contexts) {
        ctx.makeCurrent();
        ctx.clear();
        ctx.draw();
        ctx.present();
      }
      
      glfwPollEvents();
    }
  }
}
