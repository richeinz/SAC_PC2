
package br.edu.ifsul.controle;

import br.edu.ifsul.dao.ReclamacaoDAO;
import br.edu.ifsul.modelo.Reclamacao;
import br.edu.ifsul.util.Util;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author Ricardo
 */
@ManagedBean(name = "controleReclamacao")
@SessionScoped
public class ControleReclamacao implements Serializable {

    private ReclamacaoDAO dao;
    private Reclamacao objeto;

    public ControleReclamacao() {
        dao = new ReclamacaoDAO();
    }

    public String listar() {
        return "/privado/reclamacao/listar?faces-redirect=true";
    }

    public String novo() {
        objeto = new Reclamacao();
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
        objeto = (Reclamacao) dao.localizar(id);
    }

    public void remover(Integer id) {
        objeto = (Reclamacao) dao.localizar(id);
        if (dao.remover(objeto)) {
            Util.mensagemInformacao(dao.getMensagem());
        } else {
            Util.mensagemErro(dao.getMensagem());
        }
    }

    public ReclamacaoDAO getDao() {
        return dao;
    }

    public void setDao(ReclamacaoDAO dao) {
        this.dao = dao;
    }

    public Reclamacao getObjeto() {
        return objeto;
    }

    public void setObjeto(Reclamacao objeto) {
        this.objeto = objeto;
    }

}
