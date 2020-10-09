package com.nicolrom.translators.impl;

import com.nicolrom.enums.OrderOptionsEnum;
import com.nicolrom.translators.HoleTranslator;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class HoleTranslatorImpl implements HoleTranslator {

    @Override
    public List<String> translateOrderOptions() {
        List<String> orderOptionsTranslated = new ArrayList<>();
        for (OrderOptionsEnum orderOptionEnum : OrderOptionsEnum.values()){
            switch (orderOptionEnum){
                case ID:
                    orderOptionsTranslated.add("Ordinea Adaugarii");
                    break;
                case ADRESA_ALFABETIC:
                    orderOptionsTranslated.add("Adresa alfabetic");
                    break;
                case DATA_DESCRESCATOR:
                    orderOptionsTranslated.add("Data invers cronologic");
                    break;
                default:
                    orderOptionsTranslated.add(orderOptionEnum.name());
                    break;
            }
        }

        return orderOptionsTranslated;
    }

    @Override
    public OrderOptionsEnum translateOrderOption(String orderOption) {
        switch (orderOption){
            case "Ordinea Adaugarii":
                return OrderOptionsEnum.ID;
            case "Adresa alfabetic" :
                return OrderOptionsEnum.ADRESA_ALFABETIC;
            case "Data invers cronologic":
                return OrderOptionsEnum.DATA_DESCRESCATOR;
            default:
                return null;
        }
    }
}
