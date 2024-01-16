package com.example.demo.service;

import com.example.demo.model.TestUser;
import com.example.demo.repository.TestUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TestUserServiceImpl implements TestUserService{

    @Autowired
    private TestUserRepository testUserRepository;

    @Override
    public List<TestUser> getAllTestUsers() {
        return testUserRepository.getAllTestUsers();
    }
    @Override
    public TestUser createTestUser(TestUser testUser) {
        return testUserRepository.createTestUser(testUser);
    }

    @Override
    public TestUser updateTestUser(TestUser testUser) {
        return testUserRepository.updateTestUser(testUser);
    }

    @Override
    public TestUser deleteTestUser(TestUser testUser) {
        return testUserRepository.deleteTestUser(testUser);
    }
}
