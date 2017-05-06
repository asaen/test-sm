package de.smava.recrt.service.impl;

import de.smava.recrt.persistence.repository.AppUserRepository;
import de.smava.recrt.persistence.repository.AppUserRoleRepository;
import de.smava.recrt.persistence.repository.BankAccountRepository;
import org.easymock.EasyMock;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TestServiceConfig {

    @Bean(name = "appUserRoleRepository")
    public AppUserRoleRepository getAppUserRoleRepository() {
        return EasyMock.mock(AppUserRoleRepository.class);
    }

    @Bean(name = "appUserRepository")
    public AppUserRepository getAppUserRepository() {
        return EasyMock.mock(AppUserRepository.class);
    }

    @Bean(name = "bankAccountRepository")
    public BankAccountRepository getBankAccountRepository() {
        return EasyMock.mock(BankAccountRepository.class);
    }

}
