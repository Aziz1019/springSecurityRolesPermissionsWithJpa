package com.example.springsecurityrolespermissionswithjpa.repo;

import com.example.springsecurityrolespermissionswithjpa.entity.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public interface AppUserRepository extends JpaRepository<AppUser, Integer> {
    Optional<AppUser> findByUsername(String username);

    @Query(value = "select app.id,app.username,app.password,r.name,array_agg(p.permission_name) from app_user app left join role r on r.id = app.role_id join role_permissions rp on r.id = rp.role_id join permission p on p.id = rp.permissions_id where app.username= ? group by app.id,r.name ", nativeQuery = true)
    List<Map<String, Object>> getAppUser(String username);
}
