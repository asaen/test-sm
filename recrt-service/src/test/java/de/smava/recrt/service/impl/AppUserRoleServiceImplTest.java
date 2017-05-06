package de.smava.recrt.service.impl;

import de.smava.recrt.model.AppUserRole;
import de.smava.recrt.persistence.model.AppUserEntity;
import de.smava.recrt.persistence.model.AppUserRoleEntity;
import de.smava.recrt.persistence.repository.AppUserRoleRepository;
import de.smava.recrt.service.AppUserRoleService;
import de.smava.recrt.service.config.CachingConfig;
import java.util.Collections;
import java.util.List;
import org.easymock.EasyMock;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(loader = AnnotationConfigContextLoader.class, classes = {TestServiceConfig.class, CachingConfig.class})
public class AppUserRoleServiceImplTest {

    @Autowired
    private AppUserRoleService service;

    @Autowired
    private AppUserRoleRepository repository;

    @Test
    public void testGetByAppUser() throws Exception {
        final AppUserRoleEntity role = new AppUserRoleEntity();
        EasyMock.expect(repository.findByKeyAppUser(EasyMock.anyObject(AppUserEntity.class))).andReturn(Collections.singletonList(role)).once();
        EasyMock.replay(repository);
        final List<? extends AppUserRole> found = service.getByAppUser(new AppUserEntity());
        Assert.assertNotNull(found);
        Assert.assertEquals(1, found.size());
        Assert.assertEquals(role, found.get(0));
    }
}