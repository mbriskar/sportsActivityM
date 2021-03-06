import cz.muni.fi.pa165.sportsactivitymanager.Dao.CaloriesDAO;
import cz.muni.fi.pa165.sportsactivitymanager.Dao.CaloriesDAOImpl;
import cz.muni.fi.pa165.sportsactivitymanager.Entity.Calories;
import junit.framework.Assert;
import junit.framework.TestCase;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.BeforeMethod;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceUnit;

public class CaloriesDAOTest extends AbstractTestNGSpringContextTests {

    private Calories c1;
    private Calories c2;
    private Calories c3;
    private Calories c4;

    private CaloriesDAO dao;

    @PersistenceUnit
    private EntityManagerFactory emf;

    private EntityManager em;

    @BeforeMethod
    public void setUp() throws Exception {

        c1 = new Calories((long)1);
        c2 = new Calories((long)2);

        c1.setIndex(8.5);
        c2.setIndex(7.4);

        emf = Persistence.createEntityManagerFactory("ActivityManagerPersistence");
        em = emf.createEntityManager();

        dao = new CaloriesDAOImpl(em);
        em.getTransaction().begin();
        em.persist(c1);
        em.persist(c2);
    }

    public void testCreate() throws Exception {
        c4 = new Calories((long)4);

        c4.setIndex(5.2);

        dao.create(c4);

        Assert.assertEquals(c4, em.find(Calories.class, c4.getId()));
    }

    public void testUpdate() throws Exception {
        c2.setIndex(9.6);

        dao.update(c2);

        Assert.assertEquals(9.6, em.find(Calories.class, c2).getIndex());
    }

    public void testDelete() throws Exception {
        c3 = new Calories((long)3);

        c3.setIndex(6.3);

        em.persist(c3);

        Assert.assertFalse(dao.findById(c3.getId()).equals(null));
        dao.delete(c3);
        Assert.assertTrue(dao.findById(c3.getId()).equals(null));
    }

    public void testFindById() throws Exception {
        Assert.assertEquals(c1, dao.findById((long) 1));
        Assert.assertEquals(null, dao.findById((long) 5));
    }

    public void testFindAll() throws Exception {
        Assert.assertEquals(2, dao.findAll().size());
        for(Calories c: dao.findAll()){
            Assert.assertTrue(c.equals(c1) || c.equals(c2));
        }

    }
}