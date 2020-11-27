package com.nicolrom.dao;

import com.nicolrom.entities.MaterialNotice;

public interface MaterialNoticeDao {
    void saveMaterialNotice (MaterialNotice notice);

    void updateMaterialNotice(MaterialNotice notice);

    void deleteMaterialNotice(MaterialNotice notice);
}
