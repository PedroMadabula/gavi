/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import javax.swing.Icon;
import javax.swing.ImageIcon;

/**
 *
 * @author Administrator
 */
public class View_BoxIconADS implements Icon {
  private Color color;
  private ImageIcon imagem;
  private int borderWidth;
  
  View_BoxIconADS(String img){
      imagem = new ImageIcon(img);
  }
  View_BoxIconADS(Color color, int borderWidth) {
    this.color = color;
    this.borderWidth = borderWidth;
  }

  public int getIconWidth() {
    return 20;
  }

  public int getIconHeight() {
    return 20;
  }

  public void paintIcon(Component c, Graphics g, int x, int y) {
    //g.setColor(Color.black);
    //g.fillRect(x, y, getIconWidth(), getIconHeight());
    //g.setColor(color);
    g.drawImage(imagem.getImage(),0,0,getIconWidth(), getIconHeight(), null);
    
    //g.fillRect(x + borderWidth, y + borderWidth, getIconWidth() - 2 * borderWidth,
       // getIconHeight() - 2 * borderWidth);
  }

}