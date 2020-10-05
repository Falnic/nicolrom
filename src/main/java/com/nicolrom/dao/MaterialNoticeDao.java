package com.nicolrom.dao;

import com.nicolrom.entities.MaterialNotice;

import java.util.List;

public interface MaterialNoticeDao {
    void saveMaterialNotice (MaterialNotice notice);

    void deleteMaterialNotice(MaterialNotice notice);
}
