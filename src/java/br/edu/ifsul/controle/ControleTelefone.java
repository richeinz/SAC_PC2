
package br.edu.ifsul.controle;

import br.edu.ifsul.dao.TelefoneDAO;
import br.edu.ifsul.modelo.Telefone;
import br.edu.ifsul.util.Util;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author Ricardo
 */
@ManagedBean(name = "controleTelefone")
@SessionScoped
public class ControleTelefone implements Serializable {

    private TelefoneDAO dao;
    private Telefone objeto;

    public ControleTelefone() {
        dao = new TelefoneDAO();
    }

    public String listar() {
        return "/privado/telefone/listar?faces-redirect=true";
    }

    public String novo() {
        objeto = new Telefone();
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
        objeto = (Telefone) dao.localizar(id);
    }

    public void remover(Integer id) {
        objeto = (Telefone) dao.localizar(id);
        if (dao.remover(objeto)) {
            Util.mensagemInformacao(dao.getMensagem());
        } else {
            Util.mensagemErro(dao.getMensagem());
        }
    }

    public TelefoneDAO getDao() {
        return dao;
    }

    public void setDao(TelefoneDAO dao) {
        this.dao = dao;
    }

    public Telefone getObjeto() {
        return objeto;
    }

    public void setObjeto(Telefone objeto) {
        this.objeto = objeto;
    }

}
