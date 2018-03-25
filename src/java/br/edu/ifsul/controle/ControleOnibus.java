
package br.edu.ifsul.controle;

import br.edu.ifsul.dao.OnibusDAO;
import br.edu.ifsul.modelo.Onibus;
import br.edu.ifsul.util.Util;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author Ricardo
 */
@ManagedBean(name = "controleOnibus")
@SessionScoped
public class ControleOnibus implements Serializable {

    private OnibusDAO dao;
    private Onibus objeto;

    public ControleOnibus() {
        dao = new OnibusDAO();
    }

    public String listar() {
        return "/privado/onibus/listar?faces-redirect=true";
    }

    public String novo() {
        objeto = new Onibus();
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
        objeto = (Onibus) dao.localizar(id);
    }

    public void remover(Integer id) {
        objeto = (Onibus) dao.localizar(id);
        if (dao.remover(objeto)) {
            Util.mensagemInformacao(dao.getMensagem());
        } else {
            Util.mensagemErro(dao.getMensagem());
        }
    }

    public OnibusDAO getDao() {
        return dao;
    }

    public void setDao(OnibusDAO dao) {
        this.dao = dao;
    }

    public Onibus getObjeto() {
        return objeto;
    }

    public void setObjeto(Onibus objeto) {
        this.objeto = objeto;
    }

}
