
package br.edu.ifsul.dao;

import br.edu.ifsul.modelo.Reclamacao;
import java.io.Serializable;

/**
 *
 * @author Ricardo Heinz
 * @email ricvicheinz@gmail.com
 * 
 */
public class ReclamacaoDAO<T> extends DAOGenerico<Reclamacao> implements Serializable {
    
    public ReclamacaoDAO(){
        super();
        super.setClassePersistente(Reclamacao.class);       
        super.setOrdem("descricao");
    }
   
}