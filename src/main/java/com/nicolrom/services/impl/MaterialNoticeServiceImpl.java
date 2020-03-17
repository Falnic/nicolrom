package com.nicolrom.services.impl;

import com.nicolrom.dao.MaterialNoticeDao;
import com.nicolrom.entities.MaterialNotice;
import com.nicolrom.services.MaterialNoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class MaterialNoticeServiceImpl implements MaterialNoticeService {

    @Autowired
    private MaterialNoticeDao materialNoticeDao;

    @Override
    public void saveMaterialNotice(MaterialNotice notice) {
        materialNoticeDao.saveMaterialNotice(notice);
    }

    @Override
    public void saveMaterialNotices(Set<MaterialNotice> materialNotices) {
        for (MaterialNotice materialNotice : materialNotices){
            saveMaterialNotice(materialNotice);
        }
    }
}
