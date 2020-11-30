package de.ppi.here.socialmedia.service.accessright;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import de.ppi.here.socialmedia.service.RoleService;

@Service
public class IsAdminAccessRightStrategy implements AccessRightCheckStrategy {

    @Autowired
    private RoleService roleService;

    @Override
    public boolean checkAccessRights(final Integer channelId, final Integer userId) {
        return roleService.isAdmin(userId);
    }
}
