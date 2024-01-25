package com.example.demo.repository;


import com.example.demo.model.LookupModel;
import com.example.demo.protection.CipherUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Repository
public class LookupDaoImpl implements LookupDao{
    @Autowired
    private DataSource dataSource;
    private JdbcTemplate jdbcTemplate;
    CipherUtils oCipherUtils = new CipherUtils();

    @Override
    public List<LookupModel> branchList() {
        jdbcTemplate = new JdbcTemplate(dataSource);
        List<LookupModel> oBranchList = new ArrayList<LookupModel>();
        try {
            NamedParameterJdbcTemplate npjt = new NamedParameterJdbcTemplate(jdbcTemplate);

            StringBuilder sBuilder = new StringBuilder();
            sBuilder.append(" SELECT BRANCH_ID,\n" +
                    "       BRANCH_NAME\n" +
                    "  FROM BRTA_ARCH.L_BRANCH ");

            MapSqlParameterSource paramSource = new MapSqlParameterSource();

            List<Map<String, Object>> rows = npjt.queryForList(sBuilder.toString(), paramSource);

            for (@SuppressWarnings("rawtypes")
            Map row : rows) {
                LookupModel oLookupModel = new LookupModel();
                oLookupModel.setId(String.valueOf(row.get("BRANCH_ID")));
                oLookupModel.setName(String.valueOf(row.get("BRANCH_NAME")));
                oBranchList.add(oLookupModel);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return oBranchList;
    }
}
