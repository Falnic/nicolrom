package com.nicolrom.services.impl;

import com.nicolrom.dao.PipeDao;
import com.nicolrom.entities.Pipe;
import com.nicolrom.services.PipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PipeServiceImpl implements PipeService {

    @Autowired
    private PipeDao pipeDao;

    @Override
    public List<Pipe> getAllPipes() {
        return pipeDao.getAllPipes();
    }

    @Override
    public Pipe getPipeByDiameter(String diameter) {
        return pipeDao.getPipeByDiameter(diameter);
    }
}
