import org.junit.Before;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class DatabaseIT {

    private EntityTransaction transaction;
    private EntityManager em;

    @Before
    public void initializeDependencies(){
        this.em = Persistence.createEntityManagerFactory("integration").createEntityManager();
        this.transaction = this.em.getTransaction();
    }

    @Test
    public void retrieving_a_person(){
        transaction.begin();
        List resultList = this.em.createNativeQuery("SELECT * FROM PERSON").getResultList();
        transaction.commit();
        assertThat(resultList.size(), is(3));
    }
}
