package com.example.demo.repository;

import com.example.demo.helper.RemoveNull;
import com.example.demo.model.TestUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class TestUserRepositoryImpl implements TestUserRepository{

    @Autowired
    private RemoveNull removeNull;
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<TestUser> getAllTestUsers(){
        NamedParameterJdbcTemplate npjt = new NamedParameterJdbcTemplate(jdbcTemplate);
        List<TestUser> list = new ArrayList<TestUser>();
        StringBuilder sBuilder = new StringBuilder();
        try {
            sBuilder.append("SELECT * FROM BRTA_ARCH.TEST_USER ORDER BY INSERT_DATE DESC");

            MapSqlParameterSource paramSource = new MapSqlParameterSource();

            List<Map<String, Object>> rows = npjt.queryForList(sBuilder.toString(), paramSource);
            for (Map row : rows) {
                TestUser testUser = new TestUser();

                String userIdString = String.valueOf(row.get("USER_ID"));
                testUser.setUserId(userIdString == "null" ? 0L : Long.parseLong(removeNull.nullRemove(userIdString)));

                testUser.setUserName(removeNull.nullRemove(String.valueOf(row.get("USER_NAME"))));
                testUser.setEmail(removeNull.nullRemove(String.valueOf(row.get("EMAIL"))));
                testUser.setPhoneNo(removeNull.nullRemove(String.valueOf(row.get("PHONE_NO"))));
                testUser.setStatus(removeNull.nullRemove(String.valueOf(row.get("STATUS"))));
                testUser.setInsertDate(removeNull.nullRemove(String.valueOf(row.get("INSERT_DATE"))));

                String insertByString = String.valueOf(row.get("INSERT_BY"));
                testUser.setInsertBy(insertByString == "null" ? 0L : Long.parseLong(removeNull.nullRemove(insertByString)));

                testUser.setUpdateDate(removeNull.nullRemove(String.valueOf(row.get("UPDATE_DATE"))));

                String updateByString = String.valueOf(row.get("UPDATE_BY"));
                testUser.setUpdateBy(updateByString == "null" ? 0L : Long.parseLong(removeNull.nullRemove(updateByString)));

                list.add(testUser);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
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
            SimpleJdbcCall simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate).withProcedureName("TEST_USER_CUD");
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
            SimpleJdbcCall simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate).withProcedureName("TEST_USER_CUD");
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
