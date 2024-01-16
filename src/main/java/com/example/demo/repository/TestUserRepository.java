package com.example.demo.repository;

import com.example.demo.model.TestUser;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TestUserRepository {
    List<TestUser> getAllTestUsers();
    TestUser createTestUser(TestUser testUser);
    TestUser updateTestUser(TestUser testUser);
    TestUser deleteTestUser(TestUser testUser);
}
