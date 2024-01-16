package com.example.demo.service;

import com.example.demo.model.TestUser;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TestUserService {
    List<TestUser> getAllTestUsers();
    TestUser createTestUser(TestUser testUser);
    TestUser updateTestUser(TestUser testUser);
    TestUser deleteTestUser(TestUser testUser);
}
