package br.edu.ifsul.controle;

import br.edu.ifsul.dao.ChamadoDAO;
import br.edu.ifsul.dao.LinhaDAO;
import br.edu.ifsul.dao.OnibusDAO;
import br.edu.ifsul.dao.FuncionarioDAO;
import br.edu.ifsul.dao.PessoaDAO;
import br.edu.ifsul.dao.ReclamacaoDAO;
import br.edu.ifsul.dao.UsuarioDAO;
import br.edu.ifsul.modelo.Chamado;
import br.edu.ifsul.modelo.Funcionario;
import br.edu.ifsul.modelo.Movimento;
import br.edu.ifsul.util.Util;
import java.io.Serializable;
import java.util.Calendar;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Ricardo
 */
@ManagedBean(name = "controleChamado")
@SessionScoped
public class ControleChamado implements Serializable {

    private ChamadoDAO<Chamado> dao;
    private Chamado objeto;
    private FuncionarioDAO daoFuncionario;
    private PessoaDAO daoPessoa;
    private UsuarioDAO daoUsuario;
    private LinhaDAO daoLinha;
    private OnibusDAO daoOnibus;
    private ReclamacaoDAO daoReclamacao;
    private Movimento movimento;
    private Boolean novoMovimento;
    @ManagedProperty(value = "#{controleLogin}")
    private ControleLogin controleLogin;

    public ControleChamado() {
        dao = new ChamadoDAO<>();
        daoFuncionario = new FuncionarioDAO();
        daoPessoa = new PessoaDAO();
        daoLinha = new LinhaDAO();
        daoOnibus = new OnibusDAO();
        daoUsuario = new UsuarioDAO();
        daoReclamacao = new ReclamacaoDAO();
    }

    public String listar() {
        return "/privado/chamado/listar?faces-redirect=true";
    }

    public String novo() {
        objeto = new Chamado();
        Movimento m = new Movimento();
        m.setChamado(objeto);
        m.setData_hora(Calendar.getInstance());
        m.setFuncionario(controleLogin.getUsuarioLogado());
        m.setInformacao("Realizado abertura de chamado");
        objeto.getMovimentos().add(m);
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
        
        objeto = dao.localizar(id);
    }

    public void remover(Integer id) {
        objeto = dao.localizar(id);
        if (dao.remover(objeto)) {
            Util.mensagemInformacao(dao.getMensagem());
        } else {
            Util.mensagemErro(dao.getMensagem());
        }
    }

    public void novoMovimento() {
        movimento = new Movimento();
        movimento.setFuncionario(getControleLogin().getUsuarioLogado());
        novoMovimento = true;
    }

    public void salvarMovimento() {
        if (novoMovimento) {
            objeto.adicionarMovimento(movimento);
        }
        Util.mensagemInformacao("Movimento salvo com sucesso!");
    }

    public FacesContext getFacesContext() {
        return FacesContext.getCurrentInstance();
    }

    public HttpSession getSession() {
        return (HttpSession) getFacesContext().getExternalContext().getSession(false);
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

    public FuncionarioDAO getDaoFuncionario() {
        return daoFuncionario;
    }

    public void setDaoFuncionario(FuncionarioDAO daoFuncionario) {
        this.daoFuncionario = daoFuncionario;
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

    public ReclamacaoDAO getDaoReclamacao() {
        return daoReclamacao;
    }

    public void setDaoReclamacao(ReclamacaoDAO daoReclamacao) {
        this.daoReclamacao = daoReclamacao;
    }

    public PessoaDAO getDaoPessoa() {
        return daoPessoa;
    }

    public void setDaoPessoa(PessoaDAO daoPessoa) {
        this.daoPessoa = daoPessoa;
    }

    public Movimento getMovimento() {
        return movimento;
    }

    public void setMovimento(Movimento movimento) {
        this.movimento = movimento;
    }

    public Boolean getNovoMovimento() {
        return novoMovimento;
    }

    public void setNovoMovimento(Boolean novoMovimento) {
        this.novoMovimento = novoMovimento;
    }

    public ControleLogin getControleLogin() {
        return controleLogin;
    }

    public void setControleLogin(ControleLogin controleLogin) {
        this.controleLogin = controleLogin;
    }
}
