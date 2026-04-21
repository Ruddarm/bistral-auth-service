package com.bistral.app.bistral_auth_service.UnitTest.repository;


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

    @Test
    void contextLoads() {
    }

    @Test
    public void testFindByRolesOfUserByUserId(){
        userRoleMappingRepository
                .findRolesOfUserByUserId(UUID.fromString("5db33be8-04e3-4374-8ed0-d7899ec4024d"))
                .forEach((userRole)->
                        System.err.println("user ROle is -->"+userRole)
                );
    }
}
