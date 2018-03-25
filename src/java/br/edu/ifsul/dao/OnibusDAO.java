
package br.edu.ifsul.dao;

import br.edu.ifsul.modelo.Onibus;
import java.io.Serializable;

/**
 *
 * @author Ricardo Heinz
 * @email ricvicheinz@gmail.com
 * 
 */
public class OnibusDAO<T> extends DAOGenerico<Onibus> implements Serializable {
    
    public OnibusDAO(){
        super();
        super.setClassePersistente(Onibus.class);       
        super.setOrdem("prefixo");
    }
   
}