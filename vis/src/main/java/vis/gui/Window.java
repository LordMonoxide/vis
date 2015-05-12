package vis.gui;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.system.MemoryUtil.*;

import org.lwjgl.glfw.GLFWWindowCloseCallback;

public class Window {
  private final Dispatch _dispatch;
  
  final long _handle;
  
  private final GLFWWindowCloseCallback _cbClose;
  
  public Window() {
    _dispatch = Dispatch.instance;
    _handle = glfwCreateWindow(800, 600, "Vis", NULL, NULL);
    
    glfwSetWindowCloseCallback(_handle, _cbClose = new GLFWWindowCloseCallback() {
      @Override
      public void invoke(long window) {
        Window.this.destroy();
      }
    });
    
    makeContextCurrent();
    glfwSwapInterval(1);
    
    _dispatch.windowOpened();
    
    show();
  }
  
  boolean isContextCurrent() {
    return _handle == glfwGetCurrentContext();
  }
  
  void makeContextCurrent() {
    glfwMakeContextCurrent(_handle);
  }
  
  private void destroy() {
    _cbClose.release();
    
    _dispatch.windowClosed(this);
  }
  
  public void show() {
    glfwShowWindow(_handle);
  }
}
