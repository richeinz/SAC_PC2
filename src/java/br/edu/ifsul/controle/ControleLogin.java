
package br.edu.ifsul.controle;

import br.edu.ifsul.dao.FuncionarioDAO;
import br.edu.ifsul.modelo.Funcionario;
import br.edu.ifsul.util.Util;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author Ricardo
 */
@ManagedBean(name = "controleLogin")
@SessionScoped
public class ControleLogin implements Serializable{
    private FuncionarioDAO<Funcionario> dao;
    private Funcionario usuarioLogado;
    private String usuario;
    private String senha;
    
    public ControleLogin(){
        dao = new FuncionarioDAO<>();
    }

    public FuncionarioDAO<Funcionario> getDao() {
        return dao;
    }
    
    public String efetuarLogin(){
        if(dao.login(usuario, senha)){
            usuarioLogado = dao.localizaPorNomeUsuario(usuario);
            Util.mensagemInformacao("Login realizado com sucesso!");
            usuario = "";
            senha = "";
            return "/index?faces-redirect=true";
        }else{
            Util.mensagemErro("Usuário ou senha inválidos!");
            return "/login?facews-redirect=true";
        }
    } 
    
    public String efetuarLogout(){
        usuarioLogado = null;
        Util.mensagemInformacao("Logout realizado com sucesso!");
        return "/index?faces-redirect=true";
    }
    
    public String paginaLogin(){
        return "/login?faces-redirect=true";
    }
    
    public void setDao(FuncionarioDAO<Funcionario> dao) {
        this.dao = dao;
    }

    public Funcionario getUsuarioLogado() {
        return usuarioLogado;
    }

    public void setUsuarioLogado(Funcionario usuarioLogado) {
        this.usuarioLogado = usuarioLogado;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
    
}
