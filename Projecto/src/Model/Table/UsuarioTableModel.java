package Model.Table;

import Model.Value.Usuario;
import java.util.List;
import javax.swing.table.AbstractTableModel;

public class UsuarioTableModel extends AbstractTableModel {

    private String colunas[] = {"BI","Nome","Apelido","Anuncios","Bairro","n.Usuario","email","celular"};
    private List<Usuario> dados;

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
        Usuario usuario = dados.get(l);
        switch (c) {
            case 0:
                return usuario.getBi();
            case 1:
                return usuario.getNome();
            case 2:
                return usuario.getApelido();
            case 3:
                return usuario.getTotalAd();
            case 4:
                return usuario.getBairro();
            case 5:
                return usuario.getNomeUsuario();
            case 6:
                return usuario.getEmail();
            case 7:
                return usuario.getCelular();
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

    public void setDados(List<Usuario> dados) {
        this.dados = dados;
        fireTableDataChanged();
    }

    public Usuario getRowValue(int l) {
        return dados.get(l);
    }
}
