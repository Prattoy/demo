package com.example.demo.service;


import com.example.demo.model.LookupModel;
import com.example.demo.repository.LookupDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LookupServiceImpl implements LookupService{

    @Autowired
    private LookupDao lookupDao;

    @Override
    public List<LookupModel> branchList() {
        return lookupDao.branchList();
    }
}
