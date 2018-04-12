package br.edu.ifsul.controle;

import br.edu.ifsul.dao.FuncionarioDAO;
import br.edu.ifsul.dao.SetorDAO;
import br.edu.ifsul.modelo.Funcionario;
import br.edu.ifsul.modelo.Telefone;
import br.edu.ifsul.util.Util;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author Ricardo
 */
@ManagedBean(name = "controleFuncionario")
@SessionScoped
public class ControleFuncionario implements Serializable {

    private FuncionarioDAO dao;
    private Funcionario objeto;
    private Telefone telefone;
    private Boolean novoTelefone;
    private SetorDAO daoSetor;
    private FuncionarioDAO daoPessoa;

    public ControleFuncionario() {
        dao = new FuncionarioDAO();
        daoSetor = new SetorDAO();
    }

    public String listar() {
        return "/privado/funcionario/listar?faces-redirect=true";
    }

    public String novo() {
        objeto = new Funcionario();
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
        objeto = (Funcionario) dao.localizar(id);
    }

    public void remover(Integer id) {
        objeto = (Funcionario) dao.localizar(id);
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

    public FuncionarioDAO getDao() {
        return dao;
    }

    public void setDao(FuncionarioDAO dao) {
        this.dao = dao;
    }

    public Funcionario getObjeto() {
        return objeto;
    }

    public void setObjeto(Funcionario objeto) {
        this.objeto = objeto;
    }

    public SetorDAO getDaoSetor() {
        return daoSetor;
    }

    public void setDaoSetor(SetorDAO daoSetor) {
        this.daoSetor = daoSetor;
    }

    public Telefone getTelefone() {
        return telefone;
    }

    public void setTelefone(Telefone telefone) {
        this.telefone = telefone;
    }

    public FuncionarioDAO getDaoPessoa() {
        return daoPessoa;
    }

    public void setDaoPessoa(FuncionarioDAO daoPessoa) {
        this.daoPessoa = daoPessoa;
    }

    public Boolean getNovoTelefone() {
        return novoTelefone;
    }

    public void setNovoTelefone(Boolean novoTelefone) {
        this.novoTelefone = novoTelefone;
    }

}
