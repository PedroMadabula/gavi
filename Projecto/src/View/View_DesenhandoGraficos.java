package View;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JPanel;

/**
 *
 * @author Manuel
 */
public class View_DesenhandoGraficos extends JPanel {

    private int altura = 200; // altura do grafico
    private int espaco = 100; // espaco entre valores
    private int margem = 30,tamanhoFrameX=355;
    private List<String> rotulos = new ArrayList<String>();
    private List<Integer> valores = new ArrayList<Integer>();
    private int tipoGrafico; // 0 - Colunas; 1 - Linha
    private String tituloGrafico;
    private String tituloHorizontal;
    private String tituloVertical;
    private float perVender,perVendidos,perAligados,perAlugar;

    public View_DesenhandoGraficos() {
        
    }
    
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        this.setBackground(Color.WHITE);

        int tamanhoLinha = rotulos.size() * espaco + 50; // comprimento da linha horizontal
          
        tamanhoFrameX = tamanhoLinha;
      //  this.setSize(tamanhoLinha,355);
        // desenha linha horizontal
        g.setColor(Color.BLACK);
        g.drawLine(margem, altura + margem, tamanhoLinha, altura + margem);

        for (int i = 0; i < rotulos.size(); i++) {
            // desenha valores
            g.setColor(Color.BLACK);
            g.drawString(rotulos.get(i), i * espaco + 40, altura + 55);

            // desenha tracos da linha horizontal
            g.drawLine((i + 1) * espaco + 25, altura + margem,
                    (i + 1) * espaco + 25, altura + 40);

            // desenha valores
            int valor = valores.get(i);
            g.drawString("" + valores.get(i), (i + 1) * espaco -45,
                    (altura + margem) - valor - 5);
            
            // desenha graficos
            g.setColor(Color.BLUE);
            switch (tipoGrafico) {
                case 0: // grafico em colunas
                    g.fillRect((i + 1) * espaco - 50,  (altura + margem) - valor,
                            50, valor);
                   
                    break;
                case 1: // grafico em linha
                    if (i > 0) {
                        g.drawLine(i * espaco -43,
                                  (altura + margem)- valores.get(i - 1),
                                  (i + 1) * espaco -43,
                                  (altura + margem) - valor);
                        
                    }

                    g.fillOval((i + 1) * espaco -45, (altura + margem)
                            - valor - 3, 7, 7);
                    break;
            } // fim do switch
        } // fim do for


        // titulo do grafico
        g.setColor(Color.BLACK);
        g.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 14));
        g.drawString(tituloGrafico, margem, margem);

        // titulo horizontal
        g.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 12));
        g.drawString(tituloHorizontal, margem, altura + margem + 45);
        
        g.drawString("Percentagem de imoveis Vendidos : "+perVendidos+" %", margem, altura + margem + 60);
        g.drawString("Percentagem de imoveis Alugados : "+perAligados+" %", margem, altura + margem + 80);
        g.drawString("Percentagem para Venda : "+perVender+" %", (int)tamanhoLinha/2, altura + margem + 60);
        g.drawString("Percentagem para imoveis Aluguel : "+perAlugar+" %",(int) tamanhoLinha/2, altura + margem + 80);
        
        Graphics2D g2d = (Graphics2D) g; // faz conversão de g para Graphics2D

        // titulo vertical
        g2d.setColor(Color.BLACK);
        // rotaciona o sistema de coordenadas (gira -90 graus)
        g2d.rotate(Math.PI / -2.0); // PI equivale a 180 graus
        g2d.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 12));
        g2d.drawString(tituloVertical, -30 - altura, margem - 5);

    } // fim do metodo paintComponent

    public void setTitulos(String tituloGrafico, String tituloHor,
            String tituloVer) {
        this.tituloGrafico = tituloGrafico;
        tituloHorizontal = tituloHor;
        tituloVertical = tituloVer;
        
    }

    public void setParametros(int valor, String rotulo) {
        valores.add(valor);
        rotulos.add(rotulo);
    } // fim do metodo setParametros

    public void setTipoGrafico(int indice) {
        tipoGrafico = indice;

        repaint();
    } // fim do metodo setTipoGrafico

    public int getTamanhoFrameX() {
        return (rotulos.size() * espaco + 50)+25; 
    }

    public void setPerVender(float perVender) {
        this.perVender = perVender;
    }

    public void setPerVendidos(float perVendidos) {
        this.perVendidos = perVendidos;
    }

    public void setPerAlugados(float perAligados) {
        this.perAligados = perAligados;
    }

    public void setPerAlugar(float perAlugar) {
        this.perAlugar = perAlugar;
    }
    
} // fim da classe View_DesenhandoGraficos
