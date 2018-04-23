package br.edu.ifsul.controle;

import br.edu.ifsul.dao.ChamadoDAO;
import br.edu.ifsul.dao.FuncionarioDAO;
import br.edu.ifsul.dao.MovimentoDAO;
import br.edu.ifsul.modelo.Movimento;
import br.edu.ifsul.util.Util;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author Ricardo
 */
@ManagedBean(name = "controleMovimento")
@SessionScoped
public class ControleMovimento implements Serializable {

    private MovimentoDAO dao;
    private Movimento objeto;
    private ChamadoDAO daoChamado;
    private FuncionarioDAO daoFuncionario;

    public ControleMovimento() {
        dao = new MovimentoDAO();
        daoChamado = new ChamadoDAO();
        daoFuncionario = new FuncionarioDAO();
    }

    public String listar() {
        return "/privado/movimento/listar?faces-redirect=true";
    }

    public String novo() {
        setObjeto(new Movimento());
        return "formulario";
    }

    public void salvar() {
        boolean persistiu;
        if (getObjeto().getId() == null) {
            persistiu = getDao().persist(getObjeto());
        } else {
            persistiu = getDao().merge(getObjeto());
        }
        if (persistiu) {
            Util.mensagemInformacao(getDao().getMensagem());
        } else {
            Util.mensagemErro(getDao().getMensagem());
        }
    }

    public void editar(Integer id) {
        setObjeto((Movimento) getDao().localizar(id));
    }

    public void remover(Integer id) {
        setObjeto((Movimento) getDao().localizar(id));
        if (getDao().remover(getObjeto())) {
            Util.mensagemInformacao(getDao().getMensagem());
        } else {
            Util.mensagemErro(getDao().getMensagem());
        }
    }

    public MovimentoDAO getDao() {
        return dao;
    }

    public void setDao(MovimentoDAO dao) {
        this.dao = dao;
    }

    public Movimento getObjeto() {
        return objeto;
    }

    public void setObjeto(Movimento objeto) {
        this.objeto = objeto;
    }

    public ChamadoDAO getDaoChamado() {
        return daoChamado;
    }

    public void setDaoChamado(ChamadoDAO daoChamado) {
        this.daoChamado = daoChamado;
    }

    public FuncionarioDAO getDaoFuncionario() {
        return daoFuncionario;
    }

    public void setDaoFuncionario(FuncionarioDAO daoFuncionario) {
        this.daoFuncionario = daoFuncionario;
    }

    

}
