package br.edu.ifsul.controle;

import br.edu.ifsul.dao.FuncionarioDAO;
import br.edu.ifsul.dao.SetorDAO;
import br.edu.ifsul.modelo.Funcionario;
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
    private SetorDAO daoSetor;
    FuncionarioDAO daoPessoa;

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

}
