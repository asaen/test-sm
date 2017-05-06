package de.smava.recrt.persistence.model;

import de.smava.recrt.model.AppUserRole;
import de.smava.recrt.model.UserRole;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "app_user_role")
public class AppUserRoleEntity implements AppUserRole {

    @EmbeddedId
    private AppUserRoleKey key;

    @Override
    public UserRole getRole() {
        return key.getRole();
    }

    @Override
    public void setRole(UserRole role) {
        if (this.key == null) {
            synchronized (AppUserRoleEntity.class) {
                if (this.key == null) {
                    this.key = new AppUserRoleKey();
                }
            }
        }
        this.key.setRole(role);
    }

    public AppUserEntity getAppUser() {
        return key.getAppUser();
    }

    public void setAppUser(AppUserEntity appUser) {
        if (this.key == null) {
            synchronized (AppUserRoleEntity.class) {
                if (this.key == null) {
                    this.key = new AppUserRoleKey();
                }
            }
        }
        this.key.setAppUser(appUser);
    }
}
