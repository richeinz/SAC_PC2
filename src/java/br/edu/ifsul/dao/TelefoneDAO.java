
package br.edu.ifsul.dao;

import br.edu.ifsul.modelo.Telefone;
import java.io.Serializable;

/**
 *
 * @author Ricardo Heinz
 * @email ricvicheinz@gmail.com
 * 
 */
public class TelefoneDAO<T> extends DAOGenerico<Telefone> implements Serializable {
    
    public TelefoneDAO(){
        super();
        super.setClassePersistente(Telefone.class);       
        super.setOrdem("id");
    }
   
}