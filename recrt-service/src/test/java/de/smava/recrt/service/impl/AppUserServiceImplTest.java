package de.smava.recrt.service.impl;

import de.smava.recrt.model.AppUser;
import de.smava.recrt.persistence.model.AppUserEntity;
import de.smava.recrt.persistence.repository.AppUserRepository;
import de.smava.recrt.service.AppUserService;
import de.smava.recrt.service.config.CachingConfig;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.easymock.EasyMock;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(loader = AnnotationConfigContextLoader.class, classes = {TestServiceConfig.class, CachingConfig.class})
public class AppUserServiceImplTest {

    @Autowired
    private AppUserService service;

    @Autowired
    private AppUserRepository repository;

    @Before
    public void init() {
        EasyMock.reset(repository);
    }

    @Test
    public void testGetAll() throws Exception {
        final String userA = "userA";
        final String userB = "userB";
        final Set<String> users = new HashSet<>(Arrays.asList(userA, userB));
        EasyMock.expect(repository.findAll()).andReturn(Arrays.asList(new AppUserEntity(userA), new AppUserEntity(userB))).once();
        EasyMock.replay(repository);
        final List<? extends AppUser> found = service.getAll();
        Assert.assertNotNull(found);
        Assert.assertEquals(2, found.size());
        Assert.assertTrue(users.contains(found.get(0).getUsername()));
        Assert.assertTrue(users.contains(found.get(1).getUsername()));
    }

    @Test
    public void testGet() throws Exception {
        final String userA = "userA";
        EasyMock.expect(repository.findOne(EasyMock.eq(userA))).andReturn(new AppUserEntity(userA)).once();
        EasyMock.replay(repository);
        final AppUser found = service.get(userA);
        Assert.assertNotNull(found);
        Assert.assertEquals(userA, found.getUsername());
    }
}