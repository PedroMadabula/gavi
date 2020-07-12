package Model.Dao;

import java.util.List;

/**
 * Interface para classes de acesso a dados
 *
 * @author Manuel
 */
public interface IDAO<Tipo> {

    public void inserir(Tipo objeto);

    public void alterar(Tipo objeto);

    public void excluir(Tipo objeto);

    public List<Tipo> listarTodos();

    public Tipo recuperar(int codigo);
}
