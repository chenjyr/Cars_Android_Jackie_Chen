package com.example.axiomzencars.data.api.processor;

import com.example.axiomzencars.data.car.Price;

public class StandardPriceProcessor extends ResponseProcessor {

    public StandardPriceProcessor(String content) {
        super(content);
    }

    @Override
    protected Price process() {
        return new Price(getContent());
    }
}
