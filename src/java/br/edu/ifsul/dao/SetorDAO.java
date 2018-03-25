
package br.edu.ifsul.dao;

import br.edu.ifsul.modelo.Setor;
import java.io.Serializable;

/**
 *
 * @author Ricardo Heinz
 * @email ricvicheinz@gmail.com
 * 
 */
public class SetorDAO<T> extends DAOGenerico<Setor> implements Serializable {
    
    public SetorDAO(){
        super();
        super.setClassePersistente(Setor.class);       
        super.setOrdem("descricao");
    }
   
}