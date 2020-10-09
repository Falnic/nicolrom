package com.nicolrom.translators;

import com.nicolrom.enums.OrderOptionsEnum;

import java.util.List;

public interface HoleTranslator {
    List<String> translateOrderOptions();

    OrderOptionsEnum translateOrderOption(String orderOption);
}
