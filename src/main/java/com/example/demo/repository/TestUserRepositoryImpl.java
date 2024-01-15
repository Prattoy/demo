package com.example.demo.repository;

import com.example.demo.model.TestUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class TestUserRepositoryImpl implements TestUserRepository{

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public TestUser createTestUser(TestUser testUser) {
        TestUser oTestUser = new TestUser();
        try {
            SimpleJdbcCall simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate).withProcedureName("TEST_USER_CUD");
            Map<String, Object> inParamMap = new HashMap<String, Object>();

            inParamMap.put("P_ACTION_TYPE", "I");
            inParamMap.put("P_USER_ID", null);
            inParamMap.put("P_USER_NAME", testUser.getUserName());
            inParamMap.put("P_EMAIL", testUser.getEmail());
            inParamMap.put("P_PHONE_NO", testUser.getPhoneNo());
            inParamMap.put("P_STATUS", testUser.getStatus());
            inParamMap.put("P_INSERT_BY", "1");

            Map<String, Object> outParamMap = simpleJdbcCall.execute(inParamMap);
            oTestUser.setMessageCode(String.valueOf(outParamMap.get("P_USER_ID")));
            oTestUser.setMessageCode(String.valueOf(outParamMap.get("O_STATUS_CODE")));
            oTestUser.setMessage(String.valueOf(outParamMap.get("O_STATUS_MSG")));


        } catch (Exception e) {
            e.printStackTrace();
        }
        return oTestUser;
    }

    @Override
    public TestUser updateTestUser(TestUser testUser) {
        TestUser oTestUser = new TestUser();
        try {
            SimpleJdbcCall simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate).withCatalogName("BRTA_ARCH").withProcedureName("TEST_USER_CUD");
            Map<String, Object> inParamMap = new HashMap<String, Object>();

            inParamMap.put("P_ACTION_TYPE", "U");
            inParamMap.put("P_USER_ID", testUser.getUserId());
            inParamMap.put("P_USER_NAME", testUser.getUserName());
            inParamMap.put("P_EMAIL", testUser.getEmail());
            inParamMap.put("P_PHONE_NO", testUser.getPhoneNo());
            inParamMap.put("P_STATUS", testUser.getStatus());
            inParamMap.put("P_INSERT_BY", "1");

            Map<String, Object> outParamMap = simpleJdbcCall.execute(inParamMap);
            oTestUser.setMessageCode(String.valueOf(outParamMap.get("O_STATUS_CODE")));
            oTestUser.setMessage(String.valueOf(outParamMap.get("O_STATUS_MSG")));


        } catch (Exception e) {
            e.printStackTrace();
        }
        return oTestUser;
    }

    @Override
    public TestUser deleteTestUser(TestUser testUser) {
        TestUser oTestUser = new TestUser();
        try {
            SimpleJdbcCall simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate).withCatalogName("BRTA_ARCH").withProcedureName("TEST_USER_CUD");
            Map<String, Object> inParamMap = new HashMap<String, Object>();

            inParamMap.put("P_ACTION_TYPE", "D");
            inParamMap.put("P_USER_ID", testUser.getUserId());
            inParamMap.put("P_USER_NAME", testUser.getUserName());
            inParamMap.put("P_EMAIL", testUser.getEmail());
            inParamMap.put("P_PHONE_NO", testUser.getPhoneNo());
            inParamMap.put("P_STATUS", testUser.getStatus());
            inParamMap.put("P_INSERT_BY", "1");

            Map<String, Object> outParamMap = simpleJdbcCall.execute(inParamMap);
            oTestUser.setMessageCode(String.valueOf(outParamMap.get("O_STATUS_CODE")));
            oTestUser.setMessage(String.valueOf(outParamMap.get("O_STATUS_MSG")));


        } catch (Exception e) {
            e.printStackTrace();
        }
        return oTestUser;
    }
}
