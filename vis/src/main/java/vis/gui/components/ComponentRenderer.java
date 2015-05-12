package vis.gui.components;

import java.awt.Component;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.geom.Rectangle2D;
import java.beans.Transient;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.text.BreakIterator;

import javax.accessibility.Accessible;
import javax.accessibility.AccessibleContext;
import javax.accessibility.AccessibleExtendedComponent;
import javax.accessibility.AccessibleIcon;
import javax.accessibility.AccessibleKeyBinding;
import javax.accessibility.AccessibleRelation;
import javax.accessibility.AccessibleRelationSet;
import javax.accessibility.AccessibleRole;
import javax.accessibility.AccessibleText;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.KeyStroke;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.UIDefaults;
import javax.swing.UIManager;
import javax.swing.JComponent.AccessibleJComponent;
import javax.swing.plaf.LabelUI;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.Element;
import javax.swing.text.Position;
import javax.swing.text.StyledDocument;
import javax.swing.text.View;

@SuppressWarnings("serial")
public class ComponentRenderer extends JComponent {
  private static final String uiClassID = "ComponentRendererUI";
  
  private Component _component;
  
  public ComponentRenderer(Component component) {
      setComponent(component);
      updateUI();
  }
  
  public LabelUI getUI() {
      return (LabelUI)ui;
  }
  
  public void setUI(ComponentRendererUI ui) {
      super.setUI(ui);
  }
  
  public void updateUI() {
      setUI((ComponentRendererUI)UIManager.getUI(this));
  }
  
  public String getUIClassID() {
      return uiClassID;
  }

  public Component getComponent() {
      return _component;
  }
  
  public void setComponent(Component component) {
      Component oldValue = _component;
      _component = component;
      firePropertyChange("component", oldValue, component);
      
      if (text == null || oldValue == null || !text.equals(oldValue)) {
          revalidate();
          repaint();
      }
  }
  
  /**
   * This is overridden to return false if the current Icon's Image is
   * not equal to the passed in Image <code>img</code>.
   *
   * @see     java.awt.image.ImageObserver
   * @see     java.awt.Component#imageUpdate(java.awt.Image, int, int, int, int, int)
   */
  public boolean imageUpdate(Image img, int infoflags,
                             int x, int y, int w, int h) {
      // Don't use getDisabledIcon, will trigger creation of icon if icon
      // not set.
      if (!isShowing() ||
          !SwingUtilities.doesIconReferenceImage(getIcon(), img) &&
          !SwingUtilities.doesIconReferenceImage(disabledIcon, img)) {

          return false;
      }
      return super.imageUpdate(img, infoflags, x, y, w, h);
  }


  /**
   * See readObject() and writeObject() in JComponent for more
   * information about serialization in Swing.
   */
  private void writeObject(ObjectOutputStream s) throws IOException {
      s.defaultWriteObject();
      if (getUIClassID().equals(uiClassID)) {
          byte count = JComponent.getWriteObjCounter(this);
          JComponent.setWriteObjCounter(this, --count);
          if (count == 0 && ui != null) {
              ui.installUI(this);
          }
      }
  }


  /**
   * Returns a string representation of this JLabel. This method
   * is intended to be used only for debugging purposes, and the
   * content and format of the returned string may vary between
   * implementations. The returned string may be empty but may not
   * be <code>null</code>.
   *
   * @return  a string representation of this JLabel.
   */
  protected String paramString() {
      String textString = (text != null ?
                           text : "");
      String defaultIconString = ((defaultIcon != null)
                                  && (defaultIcon != this)  ?
                                  defaultIcon.toString() : "");
      String disabledIconString = ((disabledIcon != null)
                                   && (disabledIcon != this) ?
                                   disabledIcon.toString() : "");
      String labelForString = (labelFor  != null ?
                               labelFor.toString() : "");
      String verticalAlignmentString;
      if (verticalAlignment == TOP) {
          verticalAlignmentString = "TOP";
      } else if (verticalAlignment == CENTER) {
          verticalAlignmentString = "CENTER";
      } else if (verticalAlignment == BOTTOM) {
          verticalAlignmentString = "BOTTOM";
      } else verticalAlignmentString = "";
      String horizontalAlignmentString;
      if (horizontalAlignment == LEFT) {
          horizontalAlignmentString = "LEFT";
      } else if (horizontalAlignment == CENTER) {
          horizontalAlignmentString = "CENTER";
      } else if (horizontalAlignment == RIGHT) {
          horizontalAlignmentString = "RIGHT";
      } else if (horizontalAlignment == LEADING) {
          horizontalAlignmentString = "LEADING";
      } else if (horizontalAlignment == TRAILING) {
          horizontalAlignmentString = "TRAILING";
      } else horizontalAlignmentString = "";
      String verticalTextPositionString;
      if (verticalTextPosition == TOP) {
          verticalTextPositionString = "TOP";
      } else if (verticalTextPosition == CENTER) {
          verticalTextPositionString = "CENTER";
      } else if (verticalTextPosition == BOTTOM) {
          verticalTextPositionString = "BOTTOM";
      } else verticalTextPositionString = "";
      String horizontalTextPositionString;
      if (horizontalTextPosition == LEFT) {
          horizontalTextPositionString = "LEFT";
      } else if (horizontalTextPosition == CENTER) {
          horizontalTextPositionString = "CENTER";
      } else if (horizontalTextPosition == RIGHT) {
          horizontalTextPositionString = "RIGHT";
      } else if (horizontalTextPosition == LEADING) {
          horizontalTextPositionString = "LEADING";
      } else if (horizontalTextPosition == TRAILING) {
          horizontalTextPositionString = "TRAILING";
      } else horizontalTextPositionString = "";

      return super.paramString() +
      ",defaultIcon=" + defaultIconString +
      ",disabledIcon=" + disabledIconString +
      ",horizontalAlignment=" + horizontalAlignmentString +
      ",horizontalTextPosition=" + horizontalTextPositionString +
      ",iconTextGap=" + iconTextGap +
      ",labelFor=" + labelForString +
      ",text=" + textString +
      ",verticalAlignment=" + verticalAlignmentString +
      ",verticalTextPosition=" + verticalTextPositionString;
  }
}
