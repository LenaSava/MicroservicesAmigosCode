package com.olena.study.clients.fraud;

public record FraudCheckResponse(Boolean ifFraudster) {
    @Override
    public Boolean ifFraudster() {
        return ifFraudster;
    }
}
