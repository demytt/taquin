package ui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;

import javax.swing.DefaultListCellRenderer;
import javax.swing.Icon;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

class ColorListRenderer implements ListCellRenderer<Color> {
	
  protected DefaultListCellRenderer defaultRenderer = new DefaultListCellRenderer();

  public Component getListCellRendererComponent(JList<? extends Color> list, Color color, int index,
      boolean isSelected, boolean cellHasFocus) {

    JLabel renderer = (JLabel) defaultRenderer.getListCellRendererComponent(list, color, index,
        isSelected, cellHasFocus);

    renderer.setText("");
    renderer.setIcon(new MyIcon(color, 60, 30));
    
    return renderer;
  }

}

class MyIcon implements Icon {

	Color color;
	int height;
	int widht;
	
  public MyIcon(Color _color, int _widht,  int _heigth) {
	  color = _color;
	  height = _heigth;
	  widht = _widht;
  }

  public int getIconHeight() {
    return height;
  }

  public int getIconWidth() {
    return widht;
  }

  public void paintIcon(Component c, Graphics g, int x, int y) {
	g.setColor(color);
    g.fillRect(widht/10, height/10, 9*widht/10, 9*height/10);
    
  }
}