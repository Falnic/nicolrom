package com.nicolrom.services;

import com.nicolrom.entities.Pipe;

import java.util.List;

public interface PipeService {

    List<Pipe> getAllPipes();

    Pipe getPipeByDiameter(String diameter);
}
