
package br.edu.ifsul.controle;

import br.edu.ifsul.dao.UsuarioDAO;
import br.edu.ifsul.modelo.Telefone;
import br.edu.ifsul.modelo.Usuario;
import br.edu.ifsul.util.Util;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author Ricardo
 */
@ManagedBean(name = "controleUsuario")
@ViewScoped
public class ControleUsuario implements Serializable {

    private UsuarioDAO dao;
    private Usuario objeto;
    private Telefone telefone;
    private Boolean novoTelefone;

    public ControleUsuario() {
        dao = new UsuarioDAO();
    }

    public String listar() {
        return "/privado/usuario/listar?faces-redirect=true";
    }

    public String novo() {
        objeto = new Usuario();
        return "formulario";
    }

    public void salvar() {
        boolean persistiu;
        if (objeto.getId() == null) {
            persistiu = dao.persist(objeto);
        } else {
            persistiu = dao.merge(objeto);
        }
        if (persistiu) {
            Util.mensagemInformacao(dao.getMensagem());
        } else {
            Util.mensagemErro(dao.getMensagem());
        }
    }

    public void editar(Integer id) {
        objeto = (Usuario) dao.localizar(id);
    }

    public void remover(Integer id) {
        objeto = (Usuario) dao.localizar(id);
        if (dao.remover(objeto)) {
            Util.mensagemInformacao(dao.getMensagem());
        } else {
            Util.mensagemErro(dao.getMensagem());
        }
    }
    
    public void novoTelefone(){
        telefone = new Telefone();
        novoTelefone = true;
    }
    
    public void alterarTelefone(int index){
        telefone = objeto.getTelefones().get(index);
        novoTelefone = false;
    }
    
    public void salvarTelefone(){
        if(novoTelefone){
            objeto.adicionarTelefone(telefone);
        }
        Util.mensagemInformacao("Telefone salvo com sucesso!");
    }
    
    public void removerTelefone(int index){
        objeto.removerTelefone(index);
        Util.mensagemInformacao("Telefone removido com sucesso!");
    }

    public UsuarioDAO getDao() {
        return dao;
    }

    public void setDao(UsuarioDAO dao) {
        this.dao = dao;
    }

    public Usuario getObjeto() {
        return objeto;
    }

    public void setObjeto(Usuario objeto) {
        this.objeto = objeto;
    }

    public Telefone getTelefone() {
        return telefone;
    }

    public void setTelefone(Telefone telefone) {
        this.telefone = telefone;
    }

    public Boolean getNovoTelefone() {
        return novoTelefone;
    }

    public void setNovoTelefone(Boolean novoTelefone) {
        this.novoTelefone = novoTelefone;
    }

}
