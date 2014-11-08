package Dao;

import java.util.List;
import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Session;

/**
 *
 * @author Paulo
 */
public class GenericDao<P>{
    
    private static final Logger logger = Logger.getLogger(GenericDao.class);
    private final Class persistentClass;
    private final Session session;
    
    protected GenericDao(Session session, Class persistentClass) {
        this.session = session;
        this.persistentClass = persistentClass;
    }
    
    public void insert(P p) {
        logger.info("saving " + p);
        session.save(p);
    }
    
    public void update(P p) {
        logger.info("updating " + p);
        session.update(p);
    }
    
    public void delete(P p) {
        logger.info("deleting " + p);
        session.delete(p);
    }
    
    public P selectForCode(int codigo) {
        logger.info("getting " + persistentClass + " with codigo " + codigo);
        return (P) session.get(persistentClass, codigo);
    }
    
    public List<P> selectAll() {
        logger.info("getting all " + persistentClass);
        Criteria c = session.createCriteria(persistentClass);        
        return c.list();
    }
    
    public static <P> GenericDao<P> instantiating(Session session, Class<P> c) {
        return new GenericDao<>(session, c);
    }

}
