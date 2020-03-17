package com.nicolrom.services;

import com.nicolrom.entities.MaterialNotice;

import java.util.List;
import java.util.Set;

public interface MaterialNoticeService {
    void saveMaterialNotice (MaterialNotice notice);

    void saveMaterialNotices (Set<MaterialNotice> materialNotices);
}
