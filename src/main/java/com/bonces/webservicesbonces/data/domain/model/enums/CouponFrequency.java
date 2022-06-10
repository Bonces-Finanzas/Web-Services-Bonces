package com.bonces.webservicesbonces.data.domain.model.enums;

public enum CouponFrequency {
    MONTHLY(30),
    BIMONTHLY(60),
    QUARTERLY(90),
    FOURMONTHLY(120),
    SIXMONTHLY(180),
    ANNUAL(360);

    public final int days;

    CouponFrequency(int days) {
        this.days = days;
    }
}
