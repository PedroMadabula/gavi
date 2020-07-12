package Controler;
import View.*;
import java.awt.BorderLayout;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import Model.Dao.DAO_Estatisticas;
/**
 *
 * @author Manuel
 */
public class EstatisticasGraficos {
    private final View_DesenhandoGraficos desenhando;
    private DAO_Estatisticas daoE;
    private JFrame frame;
    public EstatisticasGraficos(){
        desenhando = new View_DesenhandoGraficos();
        daoE = new DAO_Estatisticas();
        criar();
    }
    public void criar() {
        // cria painel para desenhar grafico
        
        desenhando.setTitulos("Estatistica Geral", "Total",
                " ");
        desenhando.setParametros(daoE.getTotalUsuarios(),  "Usuarios");
        desenhando.setParametros(daoE.getTotalAnuncios(), "Anuncios");
        desenhando.setParametros(daoE.getTotalParaAluguel(),  "Para Aluguel");
        desenhando.setParametros(daoE.getTotalParaVenda(),  "Para Venda");
        desenhando.setParametros(daoE.getTotalDesativados(), "Desativados");
        desenhando.setParametros(daoE.getTotalApagados(), "Apagados");
        desenhando.setParametros(daoE.getTotalVendidos(), "Vendidos");
        desenhando.setParametros(daoE.getTotalAlugados(), "Alugados");
       
        desenhando.setPerAlugados(daoE.getPerAlugados());
        desenhando.setPerAlugar(daoE.getPerParaAluguel());
        desenhando.setPerVendidos(daoE.getPerVendidos());
        desenhando.setPerVender(daoE.getPerParaVenda());

        // cria comboBox
        final JComboBox jComboBoxGrafico = new JComboBox();
        jComboBoxGrafico.setModel(new javax.swing.DefaultComboBoxModel(
                new String[]{"Grafico em Colunas", "Grafico em Linha"}));
        jComboBoxGrafico.addActionListener(new java.awt.event.ActionListener() {

            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                desenhando.setTipoGrafico(jComboBoxGrafico.getSelectedIndex());
            }
        });

        frame = new JFrame();
        frame.add(jComboBoxGrafico, BorderLayout.NORTH);
        frame.add(desenhando);
         frame.setLocation(Configs.posFX - 20,Configs.posFY + 150);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(desenhando.getTamanhoFrameX(), 420);
        
        
        frame.setVisible(true);
    }

    public JFrame getFrame() {
        return frame;
    }
    
} 