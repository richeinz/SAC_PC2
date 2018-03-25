
package br.edu.ifsul.dao;

import br.edu.ifsul.modelo.Linha;
import java.io.Serializable;

/**
 *
 * @author Ricardo Heinz
 * @email ricvicheinz@gmail.com
 * 
 */
public class LinhaDAO<T> extends DAOGenerico<Linha> implements Serializable {
    
    public LinhaDAO(){
        super();
        super.setClassePersistente(Linha.class);       
        super.setOrdem("prefixo");
    }
   
}