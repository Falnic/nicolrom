package com.nicolrom.services;

import com.nicolrom.entities.Article;
import com.nicolrom.entities.Volume;
import com.nicolrom.entities.PaymentSituation;

import java.util.List;

public interface PaymentSituationService {

    void save (PaymentSituation paymentSituation);

    void calculate(Volume volume, List<Article> articles);
}
