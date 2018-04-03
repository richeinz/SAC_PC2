
package br.edu.ifsul.controle;

import br.edu.ifsul.dao.LinhaDAO;
import br.edu.ifsul.modelo.Linha;
import br.edu.ifsul.util.Util;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author Ricardo
 */
@ManagedBean(name = "controleLinha")
@SessionScoped
public class ControleLinha implements Serializable {

    private LinhaDAO dao;
    private Linha objeto;

    public ControleLinha() {
        dao = new LinhaDAO();
    }

    public String listar() {
        return "/privado/linha/listar?faces-redirect=true";
    }

    public String novo() {
        objeto = new Linha();
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
        objeto = (Linha) dao.localizar(id);
    }

    public void remover(Integer id) {
        objeto = (Linha) dao.localizar(id);
        if (dao.remover(objeto)) {
            Util.mensagemInformacao(dao.getMensagem());
        } else {
            Util.mensagemErro(dao.getMensagem());
        }
    }

    public LinhaDAO getDao() {
        return dao;
    }

    public void setDao(LinhaDAO dao) {
        this.dao = dao;
    }

    public Linha getObjeto() {
        return objeto;
    }

    public void setObjeto(Linha objeto) {
        this.objeto = objeto;
    }

}
