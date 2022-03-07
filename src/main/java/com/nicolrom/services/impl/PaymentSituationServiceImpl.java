package com.nicolrom.services.impl;

import com.nicolrom.dao.PaymentSituationDao;
import com.nicolrom.entities.*;
import com.nicolrom.enums.PhaseEnum;
import com.nicolrom.services.PhaseService;
import com.nicolrom.services.PaymentSituationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaymentSituationServiceImpl implements PaymentSituationService {

    @Autowired
    private PaymentSituationDao paymentSituationDao;

    @Autowired
    private PhaseService phaseService;

    @Override
    public void save(PaymentSituation paymentSituation) {
        paymentSituationDao.save(paymentSituation);
    }

    @Override
    //TODO implement SM3 concrete thickness
    public void calculate(Volume volume, List<Article> articles) {
        for (Hole hole : volume.getHoles()){
            for (Article article : articles){
                PaymentSituation paymentSituation = createPaymentSituation(volume, article, hole);
                switch (article.getCode()){
                    case "SM1":
                        paymentSituation.setPrice(calculateArticleCodeSM1(paymentSituation));
                        paymentSituationDao.save(paymentSituation);
                        break;
                    case "SM2":
                        paymentSituation.setPrice(calculateArticleCodeSM2(paymentSituation));
                        paymentSituationDao.save(paymentSituation);
                        break;
                    case "SM4":
                        paymentSituation.setPrice(calculateArticleCodeSM4(paymentSituation));
                        paymentSituationDao.save(paymentSituation);
                        break;
                    case "SM5":
                        paymentSituation.setPrice(calculateArticleCodeSM5(paymentSituation));
                        paymentSituationDao.save(paymentSituation);
                        break;
                }
            }
        }
    }

    private PaymentSituation createPaymentSituation(Volume volume, Article article, Hole hole){
        PaymentSituation paymentSituation = new PaymentSituation();
        paymentSituation.setVolume(volume);
        paymentSituation.setArticle(article);
        paymentSituation.setHole(hole);

        return paymentSituation;
    }

    /* Sapaturi executate in zona verde, pamant si macadam */
    private double calculateArticleCodeSM1(PaymentSituation paymentSituation){
        return paymentSituation.getHole().getHoleVolume() * paymentSituation.getArticle().getPrice();
    }
    /* Desfacere carosabil cu pavaj din piatra cubica, pavele, dale din beton, borduri
     */
    private double calculateArticleCodeSM2(PaymentSituation paymentSituation){
        Hole hole = paymentSituation.getHole();
        Article article = paymentSituation.getArticle();
        return hole.getHoleLength() * hole.getHoleWidth() * article.getPrice();
    }

    /* Asternere strat de nisip */
    private double calculateArticleCodeSM4(PaymentSituation paymentSituation){
        Hole hole = paymentSituation.getHole();
        Article article = paymentSituation.getArticle();
        double price = 0;

        Phase phase = phaseService.getHolePhaseByPhaseType(hole, PhaseEnum.UMPLERE);
        if (phase != null){
            for (MaterialNotice materialNotice : phase.getMaterialNoticeSet()){
                if ("Nisip".equals(materialNotice.getMaterial().getName())){
                    price = materialNotice.getQuantity() * article.getPrice();
                }
            }
        }
        return price;
    }
    /* Umplerea cu pamant
     */
    private double calculateArticleCodeSM5(PaymentSituation paymentSituation){
        Hole hole = paymentSituation.getHole();
        Article article = paymentSituation.getArticle();
        double price = 0;

        Phase phase = phaseService.getHolePhaseByPhaseType(hole, PhaseEnum.UMPLERE);
        if (phase != null){
            for (MaterialNotice materialNotice : phase.getMaterialNoticeSet()){
                if ("Pamant".equals(materialNotice.getMaterial().getName())){
                    price = materialNotice.getQuantity() * article.getPrice();
                }
            }
        }
        return price;
    }
    /*
    Umplerea cu balast, piatra sparta sau macadam
     */
    private double calculateArticleCodeSM6(PaymentSituation paymentSituation){
        Hole hole = paymentSituation.getHole();
        Article article = paymentSituation.getArticle();
        double price = 0;

        Phase phase = phaseService.getHolePhaseByPhaseType(hole, PhaseEnum.UMPLERE);
        if (phase != null){
            for (MaterialNotice materialNotice : phase.getMaterialNoticeSet()){
                if ("Balast".equals(materialNotice.getMaterial().getName())
                        || "Piatra Sparta".equals(materialNotice.getMaterial().getName())){
                    price = materialNotice.getQuantity() * article.getPrice();
                }
            }
        }
        return price;
    }

    /*
    Sapatura la o adancime mai mare 1,5 m
     */
    private double calculateArticleCodeSM7(PaymentSituation paymentSituation){
        Hole hole = paymentSituation.getHole();
        Article article = paymentSituation.getArticle();
        double price = 0;

        if (hole.getHoleDepth() > 1.5){
            price = (hole.getHoleDepth() - 1.5) * hole.getHoleWidth() * hole.getHoleLength() * article.getPrice();
        }

        return price;
    }
}
