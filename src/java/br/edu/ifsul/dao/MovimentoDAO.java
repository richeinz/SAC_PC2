
package br.edu.ifsul.dao;

import br.edu.ifsul.modelo.Movimento;
import java.io.Serializable;

/**
 *
 * @author Ricardo Heinz
 * @email ricvicheinz@gmail.com
 * 
 */
public class MovimentoDAO<T> extends DAOGenerico<Movimento> implements Serializable {
    
    public MovimentoDAO(){
        super();
        super.setClassePersistente(Movimento.class);       
        super.setOrdem("nome");
    }
   
}