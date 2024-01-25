package com.example.demo.repository;

import com.example.demo.model.LookupModel;

import java.util.List;

public interface LookupDao {
    List<LookupModel> branchList();
}
