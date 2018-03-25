
package br.edu.ifsul.dao;

import br.edu.ifsul.modelo.Usuario;
import java.io.Serializable;

/**
 *
 * @author Ricardo Heinz
 * @email ricvicheinz@gmail.com
 * 
 */
public class UsuarioDAO<T> extends DAOGenerico<Usuario> implements Serializable {
    
    public UsuarioDAO(){
        super();
        super.setClassePersistente(Usuario.class);       
        super.setOrdem("nome");
    }
   
}