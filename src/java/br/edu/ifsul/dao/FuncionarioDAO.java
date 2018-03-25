
package br.edu.ifsul.dao;

import br.edu.ifsul.modelo.Funcionario;
import java.io.Serializable;

/**
 *
 * @author Ricardo Heinz
 * @email ricvicheinz@gmail.com
 * 
 */
public class FuncionarioDAO<T> extends DAOGenerico<Funcionario> implements Serializable {
    
    public FuncionarioDAO(){
        super();
        super.setClassePersistente(Funcionario.class);       
        super.setOrdem("nome");
    }
   
}