package Model.Table;

import Model.Value.Anuncios;
import java.util.List;
import javax.swing.table.AbstractTableModel;

public class AnunciosTableModel extends AbstractTableModel {

    private String colunas[] = {"id","Bairro","Preco","Titulo","Tipo","Descrisao","Activo","Tipo Anuncio"};
    private List<Anuncios> dados;

    @Override
    public int getRowCount() {
        if(dados == null){
            return 0;
        }
        return dados.size();
    }

    @Override
    public int getColumnCount() {
        return colunas.length;
    }

    @Override
    public Object getValueAt(int l, int c) {
        Anuncios anuncios = dados.get(l);
        switch (c) {
            case 0:
                return anuncios.getId();
            case 1:
                return anuncios.getBairro();
            case 2:
                return anuncios.getPreco();
            case 3:
                return anuncios.getTitulo();
            case 4:
                return anuncios.getTipo();
            case 5:
                return anuncios.getDescricao();
            case 6:
                return anuncios.isAtivo();
            case 7:
                return anuncios.getTipoAnuncio();
//            case 8:
//                return anuncios.getURL2();
//            case 9:
//                return anuncios.getURL3();
//               
            default:
                throw new IndexOutOfBoundsException("Coluna inexistente!");
        }
    }

    @Override
    public String getColumnName(int c) {
        return colunas[c];
    }

    @Override
    public boolean isCellEditable(int l, int c) {
        return false;
    }

    public void setDados(List<Anuncios> dados) {
        this.dados = dados;
        fireTableDataChanged();
    }

    public Anuncios getRowValue(int l) {
        return dados.get(l);
    }
}
