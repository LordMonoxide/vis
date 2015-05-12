package vis.gui;

import org.lwjgl.opengl.GLContext;

import static org.lwjgl.opengl.GL11.*;

import org.lwjgl.opengl.GL;

import static org.lwjgl.glfw.GLFW.*;

public class Context {
  public static Context fromWindow(Window window) {
    if(!window.isContextCurrent()) {
      window.makeContextCurrent();
    }
    
    return new Context(window, GLContext.createFromCurrent());
  }
  
  final Window _window;
  
  private final GLContext _context;
  
  private Context(Window window, GLContext context) {
    _window  = window;
    _context = context;
    
    glClearColor(1, 0, 1, 0);
    
    Dispatch.instance.registerContext(this);
  }
  
  void makeCurrent() {
    if(!(GL.getCurrent() == _context)) {
      GL.setCurrent(_context);
    }
  }
  
  void clear() {
    glClear(GL_COLOR_BUFFER_BIT);
  }
  
  void present() {
    glfwSwapBuffers(_window._handle);
  }
  
  void draw() {
    
  }
}
