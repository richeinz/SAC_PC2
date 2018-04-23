package br.edu.ifsul.controle;

import br.edu.ifsul.dao.ChamadoDAO;
import br.edu.ifsul.dao.LinhaDAO;
import br.edu.ifsul.dao.OnibusDAO;
import br.edu.ifsul.dao.PessoaDAO;
import br.edu.ifsul.dao.UsuarioDAO;
import br.edu.ifsul.modelo.Chamado;
import br.edu.ifsul.util.Util;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author Ricardo
 */
@ManagedBean(name = "controleChamado")
@SessionScoped
public class ControleChamado implements Serializable {

    private ChamadoDAO dao;
    private Chamado objeto;
    private PessoaDAO daoPessoa;
    private UsuarioDAO daoUsuario;
    private LinhaDAO daoLinha;
    private OnibusDAO daoOnibus;

    public ControleChamado() {
        dao = new ChamadoDAO();
        daoPessoa = new PessoaDAO();
        daoLinha = new LinhaDAO();
        daoOnibus = new OnibusDAO();
        daoUsuario = new UsuarioDAO();
    }

    public String listar() {
        return "/privado/chamado/listar?faces-redirect=true";
    }

    public String novo() {
        objeto = new Chamado();
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
        objeto = (Chamado) dao.localizar(id);
    }

    public void remover(Integer id) {
        objeto = (Chamado) dao.localizar(id);
        if (dao.remover(objeto)) {
            Util.mensagemInformacao(dao.getMensagem());
        } else {
            Util.mensagemErro(dao.getMensagem());
        }
    }

    public ChamadoDAO getDao() {
        return dao;
    }

    public void setDao(ChamadoDAO dao) {
        this.dao = dao;
    }

    public Chamado getObjeto() {
        return objeto;
    }

    public void setObjeto(Chamado objeto) {
        this.objeto = objeto;
    }

    public PessoaDAO getDaoPessoa() {
        return daoPessoa;
    }

    public void setDaoPessoa(PessoaDAO daoPessoa) {
        this.daoPessoa = daoPessoa;
    }

    public LinhaDAO getDaoLinha() {
        return daoLinha;
    }

    public void setDaoLinha(LinhaDAO daoLinha) {
        this.daoLinha = daoLinha;
    }

    public OnibusDAO getDaoOnibus() {
        return daoOnibus;
    }

    public void setDaoOnibus(OnibusDAO daoOnibus) {
        this.daoOnibus = daoOnibus;
    }

    public UsuarioDAO getDaoUsuario() {
        return daoUsuario;
    }

    public void setDaoUsuario(UsuarioDAO daoUsuario) {
        this.daoUsuario = daoUsuario;
    }

}
