package com.example.springsecurityrolespermissionswithjpa.service;

import com.example.springsecurityrolespermissionswithjpa.entity.AppUser;
import com.example.springsecurityrolespermissionswithjpa.entity.Permission;
import com.example.springsecurityrolespermissionswithjpa.entity.Role;
import com.example.springsecurityrolespermissionswithjpa.repo.AppUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.security.Permissions;
import java.util.*;


@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {
    private final AppUserRepository appUserRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<AppUser> byUsername = appUserRepository.findByUsername(username);
        List<Map<String, Object>> appUsers = appUserRepository.getAppUser(username);

        AppUser appUser = new AppUser();
        appUser.setId((Integer) appUsers.get(0).get("id"));
        appUser.setUsername((String) appUsers.get(0).get("username"));
        appUser.setPassword((String) appUsers.get(0).get("password"));
        String role_name = (String) appUsers.get(0).get("name");

        Role role = new Role();
        role.setPermissions(Set.of(
                new Permission("create"),
//                new Permission("read"),
                new Permission("update"),
                new Permission("delete")
        ));
        role.setRoleName("ROLE_Admin");
        appUser.setRole(role);

//        List<Role.Permissions> list = new ArrayList<>();
//        for (String o : (String[]) rs.getArray(5).getArray()) {
//            Role.Permissions permissions = new Role.Permissions();
//            permissions.setPer_name(o);
//            list.add(permissions);
//        }
//        role.setPermissions(list);
//        appUser.setRole(role);

        return byUsername.orElseThrow(() -> new UsernameNotFoundException("Username was not detected"));
    }
}
