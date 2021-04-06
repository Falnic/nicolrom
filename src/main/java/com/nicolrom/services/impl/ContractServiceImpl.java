package com.nicolrom.services.impl;

import com.nicolrom.dao.ContractDao;
import com.nicolrom.entities.Contract;
import com.nicolrom.services.ContractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContractServiceImpl implements ContractService {


    @Autowired
    private ContractDao contractDao;

    @Override
    public List<Contract> getAllContracts() {
        return contractDao.getAllContracts();
    }
}
