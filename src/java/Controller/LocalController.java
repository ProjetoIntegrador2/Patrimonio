package Controller;

import Dao.HibernateUtil;
import Dao.LocalDao;
import Model.LocalModelo;
import java.io.Serializable;
import java.util.List;
import javax.annotation.ManagedBean;
import javax.faces.bean.SessionScoped;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author Paulo
 */

@ManagedBean
@SessionScoped
public class LocalController implements Serializable{
    private List<LocalModelo> locais;
    private LocalModelo local;
    private final Session sessao;
    
    
    public LocalController(){
        sessao = HibernateUtil.getSessionFactory().openSession();
        atualizaLocal(sessao);
        local = new LocalModelo();
    }
    
    private void atualizaLocal(Session hibernateSession) {
        LocalDao<LocalModelo> daoLocal = LocalDao.instanciar(hibernateSession, LocalModelo.class);
        locais = (List<LocalModelo>) (LocalModelo) daoLocal.selectAll();
    }

    public List<LocalModelo> getLocais() {
        return locais;
    }

    public LocalModelo getLocalAtual() {
        return local;
    }

    public void salvar(){
        LocalDao<LocalModelo> daoLocal = LocalDao.instanciar(sessao, LocalModelo.class);
        Transaction t = sessao.getTransaction();
        t.begin();
        daoLocal.insert(local);
        t.commit();
        atualizaLocal(sessao);
    }
    
//    public void salvaAlteracao() {
//        Dao<Cliente> daoCliente = Dao.criarInstancia(hibernateSession, Cliente.class);
//        Transaction t = hibernateSession.getTransaction();
//        t.begin();
//        daoCliente.atualizar(clienteCorrente);
//        t.commit();
//        atualizaClientes(hibernateSession);
//        
//    }
//    public void excluirCliente(Cliente cliente) {
//        Dao<Cliente> daoCliente = Dao.criarInstancia(hibernateSession, Cliente.class);
//        Transaction t = hibernateSession.getTransaction();
//        t.begin();
//        daoCliente.excluir(cliente);
//        t.commit();
//        atualizaClientes(hibernateSession);
//    }
    
}
