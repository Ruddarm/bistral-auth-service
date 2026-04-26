package com.bistral.app.bistral_auth_service.UnitTest.repository;


import com.bistral.app.bistral_auth_service.repository.RoleRepository;
import com.bistral.app.bistral_auth_service.repository.UserRoleMappingRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.UUID;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class UserRoleMappingCrudRepositoryUnitTest {

    static {
        System.setProperty("user.timezone", "Asia/Kolkata");
    }

    @Autowired
    private UserRoleMappingRepository userRoleMappingRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Test
    void contextLoads() {
    }

    @Test
    public void testFindByRolesOfUserByUserId() {
        userRoleMappingRepository
                .findRolesOfUserByUserId(UUID.fromString("5db33be8-04e3-4374-8ed0-d7899ec4024d"))
                .forEach((userRole) ->
                        System.err.println("user ROle is -->" + userRole)
                );
    }

    @Test
    public void testingFindPermissionBYContext() {
//        userRoleMappingRepository
//                .findPermissionByContext(UUID.fromString("5db33be8-04e3-4374-8ed0-d7899ec4024d"),
//                        UUID.fromString("5db33be8-04e3-4374-8ed0-d7899ec4024d"),
//                        UUID.fromString("62991efd-9a16-425c-83e1-b105e38ad793"),
//                        UUID.fromString("a288c767-0cf7-4b1f-a8b5-a779abd55b86")
//                )
//                .forEach(System.out::println);
        roleRepository.findRoleByIdAndBistroIdWithPermissions(UUID.fromString("a288c767-0cf7-4b1f-a8b5-a779abd55b86"),
                        UUID.fromString("5db33be8-04e3-4374-8ed0-d7899ec4024d"
                        ))
                .forEach((r) ->
                        System.out.printf("%s %s %s ", r.getRoleName(), r.getResourceName(), r.getActionName())
                );

    }
}
