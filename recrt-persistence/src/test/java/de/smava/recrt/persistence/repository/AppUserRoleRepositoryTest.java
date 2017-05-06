package de.smava.recrt.persistence.repository;

import de.smava.recrt.model.UserRole;
import de.smava.recrt.persistence.config.PersistenceConfig;
import de.smava.recrt.persistence.model.AppUserEntity;
import de.smava.recrt.persistence.model.AppUserRoleEntity;
import java.util.List;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(loader = AnnotationConfigContextLoader.class, classes = PersistenceConfig.class)
public class AppUserRoleRepositoryTest {

    @Autowired
    private AppUserRoleRepository repository;

    @Test
    public void testFindByKeyAppUser() throws Exception {
        final AppUserRoleEntity roleA = new AppUserRoleEntity();
        final AppUserEntity userA = new AppUserEntity("userA");
        roleA.setAppUser(userA);
        roleA.setRole(UserRole.ROLE_USER);
        repository.save(roleA);
        final List<AppUserRoleEntity> foundA = repository.findByKeyAppUser(userA);
        Assert.assertNotNull(foundA);
        Assert.assertEquals(1, foundA.size());
        Assert.assertEquals(UserRole.ROLE_USER, foundA.get(0).getRole());
    }
}