
package br.edu.ifsul.controle;

import br.edu.ifsul.dao.SetorDAO;
import br.edu.ifsul.modelo.Setor;
import br.edu.ifsul.util.Util;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author Ricardo
 */
@ManagedBean(name = "controleSetor")
@SessionScoped
public class ControleSetor implements Serializable {

    private SetorDAO dao;
    private Setor objeto;

    public ControleSetor() {
        dao = new SetorDAO();
    }

    public String listar() {
        return "/privado/setor/listar?faces-redirect=true";
    }

    public String novo() {
        objeto = new Setor();
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
        objeto = (Setor) dao.localizar(id);
    }

    public void remover(Integer id) {
        objeto = (Setor) dao.localizar(id);
        if (dao.remover(objeto)) {
            Util.mensagemInformacao(dao.getMensagem());
        } else {
            Util.mensagemErro(dao.getMensagem());
        }
    }

    public SetorDAO getDao() {
        return dao;
    }

    public void setDao(SetorDAO dao) {
        this.dao = dao;
    }

    public Setor getObjeto() {
        return objeto;
    }

    public void setObjeto(Setor objeto) {
        this.objeto = objeto;
    }

}
