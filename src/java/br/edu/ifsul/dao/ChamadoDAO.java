
package br.edu.ifsul.dao;

import br.edu.ifsul.modelo.Chamado;
import java.io.Serializable;

/**
 *
 * @author Ricardo Heinz
 * @email ricvicheinz@gmail.com
 * 
 */
public class ChamadoDAO<T> extends DAOGenerico<Chamado> implements Serializable {
    
    public ChamadoDAO(){
        super();
        super.setClassePersistente(Chamado.class);       
        super.setOrdem("id");
    }
   
}