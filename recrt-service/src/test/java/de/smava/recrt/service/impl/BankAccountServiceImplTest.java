package de.smava.recrt.service.impl;

import de.smava.recrt.model.BankAccount;
import de.smava.recrt.persistence.model.AppUserEntity;
import de.smava.recrt.persistence.model.BankAccountEntity;
import de.smava.recrt.persistence.repository.AppUserRepository;
import de.smava.recrt.persistence.repository.BankAccountRepository;
import de.smava.recrt.service.BankAccountService;
import de.smava.recrt.service.config.CachingConfig;
import java.util.Collections;
import java.util.List;
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
public class BankAccountServiceImplTest {

    @Autowired
    private BankAccountService service;

    @Autowired
    private BankAccountRepository accounts;

    @Autowired
    private AppUserRepository users;

    @Before
    public void init() {
        EasyMock.reset(accounts, users);
    }

    @Test
    public void testGetByAppUser() throws Exception {
        final String username = "user";
        final AppUserEntity user = new AppUserEntity(username);
        final BankAccountEntity account = new BankAccountEntity();
        EasyMock.expect(users.findOne(EasyMock.eq(username))).andReturn(user).once();
        EasyMock.expect(accounts.findByAppUser(EasyMock.eq(user))).andReturn(Collections.singletonList(account)).once();
        EasyMock.replay(accounts, users);
        final List<? extends BankAccount> found = service.getByAppUser(username);
        Assert.assertNotNull(found);
        Assert.assertEquals(1, found.size());
        Assert.assertEquals(account, found.get(0));
    }

    @Test
    public void testCreate() throws Exception {
        final String username = "user";
        final AppUserEntity user = new AppUserEntity(username);
        final BankAccountEntity account = new BankAccountEntity();
        Assert.assertNull(service.create(account));
        account.setAppUser(user);
        EasyMock.expect(users.findOne(EasyMock.eq(username))).andReturn(user).once();
        EasyMock.expect(accounts.save(EasyMock.anyObject(BankAccountEntity.class))).andReturn(account).once();
        EasyMock.replay(accounts, users);
        final BankAccount found = service.create(account);
        Assert.assertNotNull(found);
        Assert.assertEquals(account, found);
    }
}