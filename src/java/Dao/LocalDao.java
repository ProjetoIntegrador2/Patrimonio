package Dao;

import Model.LocalModelo;
import java.util.List;
import org.hibernate.Session;
import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.mapping.PersistentClass;

/**
 *
 * @author Paulo
 */
public class LocalDao<P> extends GenericDao<Object> {
    
    private static final Logger logger = Logger.getLogger(GenericDao.class);
    private final Class persistentClassLocal;
    private final Session sessionLocal;
    
    public LocalDao(Session session, Class persistentClass) {
        super(session, persistentClass);
        this.persistentClassLocal = persistentClass;
        this.sessionLocal = session;
    }
    
    public List<P> buscarPorDescricao(String descricao){
        logger.info("getting " + persistentClassLocal + " with descricao " + descricao);
        Criteria c = sessionLocal.createCriteria(persistentClassLocal);
        return c.list();
    }
    
//    public List<LocalModelo> buscarTodos(){
//        List<Object> List = selectAll();
//        List<LocalModelo> modelo = null;
//        for(Object listaAgora: List ){
//        modelo.add((LocalModelo) listaAgora);
//        }
//        return modelo;
//    }
    
    public static <P> LocalDao<P> instanciar(Session session, Class<P> c) {
        //instantiating(session, c);
        return new LocalDao<>(session,c);
    }
    
}
