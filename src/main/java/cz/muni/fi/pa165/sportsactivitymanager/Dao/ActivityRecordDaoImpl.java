package cz.muni.fi.pa165.sportsactivitymanager.Dao;

import cz.muni.fi.pa165.sportsactivitymanager.Entity.Activity;
import cz.muni.fi.pa165.sportsactivitymanager.Entity.ActivityRecord;
import cz.muni.fi.pa165.sportsactivitymanager.Entity.User;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;


/**
 * @author Michal Stefanik 422237
 */
@Repository
public class ActivityRecordDaoImpl implements ActivityRecordDao {

    @PersistenceContext
    private EntityManager em;

    @Override
    public void create(ActivityRecord activityRecord) {
        em.persist(activityRecord);
    }

    @Override
    public ActivityRecord getRecordById(Long id) {
        return em.find(ActivityRecord.class, id);
    }

    @Override
    public List<ActivityRecord> getRecordsByUser(User user) {
        return em.createQuery("SELECT ar FROM ActivityRecord ar WHERE user.email LIKE :email", ActivityRecord.class)
                .setParameter("email", user.getEmail())
                .getResultList();
    }

    @Override
    public List<ActivityRecord> getRecordsByActivity(Activity activity) {
        return em.createQuery("SELECT ar FROM ActivityRecord ar WHERE activity.name LIKE :name", ActivityRecord.class)
                .setParameter("name", activity.getName())
                .getResultList();
    }

    @Override
    public void update(ActivityRecord activityRecord) {
        em.merge(activityRecord);
    }

    @Override
    public void delete(ActivityRecord activityRecord) {
        em.remove(activityRecord);
    }
}
