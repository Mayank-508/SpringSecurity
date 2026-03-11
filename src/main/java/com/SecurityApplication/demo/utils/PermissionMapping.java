package com.SecurityApplication.demo.utils;

import com.SecurityApplication.demo.entity.enums.Permissions;
import com.SecurityApplication.demo.entity.enums.Role;
import org.hibernate.usertype.UserVersionType;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.security.Permission;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import static com.SecurityApplication.demo.entity.enums.Permissions.*;
import static com.SecurityApplication.demo.entity.enums.Role.*;

public class PermissionMapping {

    static Map<Role, Set<Permissions>> map= Map.of(
            USER, Set.of(USER_VIEW,POST_VIEW),
            CREATOR, Set.of(USER_VIEW,POST_VIEW,USER_UPDATE, POST_UPDATE),
            ADMIN,Set.of(POST_CREATE,USER_UPDATE,USER_CREATE, POST_UPDATE,USER_VIEW,POST_DELETE)
    );

    public static Set<SimpleGrantedAuthority> getAuthoritiesForRole(Role role)
    {
        return map.get(role).stream().map(permission-> new SimpleGrantedAuthority(permission.name()))
                .collect(Collectors.toSet());
    }

}
