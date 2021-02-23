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
                    orderOptionsTranslated.add("Implicit");
                    break;
                case ADRESA_ALFABETIC:
                    orderOptionsTranslated.add("Alfabetic");
                    break;
                case DATA_DESCRESCATOR:
                    orderOptionsTranslated.add("Cronologic");
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
            case "Implicit":
                return OrderOptionsEnum.ID;
            case "Alfabetic" :
                return OrderOptionsEnum.ADRESA_ALFABETIC;
            case "Cronologic":
                return OrderOptionsEnum.DATA_DESCRESCATOR;
            default:
                return null;
        }
    }
}
