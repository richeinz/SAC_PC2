package br.edu.ifsul.controle;

import br.edu.ifsul.dao.ChamadoDAO;
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

    public ControleChamado() {
        dao = new ChamadoDAO();
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

}
