package com.example.demo.repository;

import com.example.demo.model.TestUser;
import org.springframework.stereotype.Repository;

@Repository
public interface TestUserRepository {
    TestUser createTestUser(TestUser testUser);
    TestUser updateTestUser(TestUser testUser);
    TestUser deleteTestUser(TestUser testUser);
}
