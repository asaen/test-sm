package de.smava.recrt.persistence.repository;

import de.smava.recrt.persistence.config.PersistenceConfig;
import de.smava.recrt.persistence.model.AppUserEntity;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(loader = AnnotationConfigContextLoader.class, classes = PersistenceConfig.class)
public class AppUserRepositoryTest {

    @Autowired
    private AppUserRepository repository;

    @Test
    public void testSave() throws Exception {
        final String user = "userA";
        final String mail = "usera@email.com";
        final String pswd = "userapswd";
        final AppUserEntity entity = new AppUserEntity();
        entity.setUsername(user);
        entity.setEmail(mail);
        entity.setPassword(pswd);
        final AppUserEntity result = repository.save(entity);
        Assert.assertNotNull(result);
        Assert.assertEquals(user, result.getUsername());
        Assert.assertEquals(mail, result.getEmail());
        Assert.assertEquals(pswd, result.getPassword());
    }

    @Test
    public void testFindOne() throws Exception {
        final String user = "userB";
        final String mail = "userb@email.com";
        final String pswd = "userbpswd";
        final AppUserEntity entity = new AppUserEntity();
        entity.setUsername(user);
        entity.setEmail(mail);
        entity.setPassword(pswd);
        repository.save(entity);
        final AppUserEntity result = repository.findOne(user);
        Assert.assertNotNull(result);
        Assert.assertEquals(user, result.getUsername());
        Assert.assertEquals(mail, result.getEmail());
        Assert.assertEquals(pswd, result.getPassword());
    }

    @Test
    public void testDelete() throws Exception {
        final String user = "userC";
        final String mail = "userc@email.com";
        final String pswd = "usercpswd";
        final AppUserEntity entity = new AppUserEntity();
        entity.setUsername(user);
        entity.setEmail(mail);
        entity.setPassword(pswd);
        Assert.assertNotNull(repository.save(entity));
        Assert.assertNotNull(repository.findOne(user));
        repository.delete(user);
        Assert.assertNull(repository.findOne(user));
    }

}