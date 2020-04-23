package com.nicolrom.dao;

import com.nicolrom.entities.Pipe;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PipeDao {

    List<Pipe> getAllPipes();

    Pipe getPipeByDiameter(String diameter);
}
