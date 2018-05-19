
package br.edu.ifsul.dao;

import br.edu.ifsul.modelo.Funcionario;
import java.io.Serializable;
import javax.persistence.Query;

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
    
    public boolean login(String usuario, String senha){
        Query query = em.createQuery("from Funcionario where upper(nomeUsuario) = :usuario and upper(senha) = :senha");
        query.setParameter("usuario", usuario.toUpperCase());
        query.setParameter("senha", senha.toUpperCase());
        if(!query.getResultList().isEmpty()){
            return true;
        }else{
            return false;
        }
    }
    
    public Funcionario localizaPorNomeUsuario(String usuario){
        Query query = em.createQuery("from Funcionario where upper(nomeUsuario) = :usuario");
        query.setParameter("usuario", usuario.toUpperCase());
        return (Funcionario) query.getSingleResult();
    }
}