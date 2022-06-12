package com.bonces.webservicesbonces.schedule.service;

import com.bonces.webservicesbonces.data.domain.model.entity.BoundData;
import com.bonces.webservicesbonces.data.domain.model.entity.InitialCostData;
import com.bonces.webservicesbonces.data.domain.model.enums.TypeInterestRate;
import com.bonces.webservicesbonces.quota.domain.model.entity.Quota;
import com.bonces.webservicesbonces.quota.domain.model.enums.TypeOfGracePeriod;
import com.bonces.webservicesbonces.results.domain.model.entity.ProfitabilityResults;
import com.bonces.webservicesbonces.results.domain.model.entity.ResultsOfCurrentPriceAndProfit;
import com.bonces.webservicesbonces.results.domain.model.entity.ResultsOfDecisionRatio;
import com.bonces.webservicesbonces.results.domain.model.entity.StructuringResults;

import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class FrenchAlgorithm {
    //Data
    public BoundData boundData;
    public InitialCostData initialCostData;

    //Results
    public ResultsOfDecisionRatio resultsOfDecisionRatio;
    public StructuringResults structuringResults;
    public ResultsOfCurrentPriceAndProfit resultsOfCurrentPriceAndProfit;
    public ProfitabilityResults profitabilityResults;
    public Set<Quota> quotas;
    private final Long gracePeriod;
    private final TypeOfGracePeriod typeOfGracePeriod;
    private final Date date;

    public FrenchAlgorithm(BoundData boundData, InitialCostData initialCostData, Date date) {
        this.boundData = boundData;
        this.initialCostData = initialCostData;
        this.gracePeriod = boundData.getGracePeriod();
        this.typeOfGracePeriod = boundData.getTypeOfGracePeriod();
        this.resultsOfDecisionRatio = new ResultsOfDecisionRatio();
        this.resultsOfCurrentPriceAndProfit = new ResultsOfCurrentPriceAndProfit();
        this.profitabilityResults = new ProfitabilityResults();
        this.date = date;
        this.quotas = new HashSet<>();
        execute();
    }

    private void execute() {
        calculatedPaymentSchedule();
    }

    private int getPeriodsPerYear() {
        return boundData.getDaysYear() / boundData.getCouponFrequency().days;
    }

    private int getNumberOfPeriods() {
        return getPeriodsPerYear() * boundData.getYears();
    }

    private double getEffectiveAnnualRate() {
        if (boundData.getTypeInterestRate() == TypeInterestRate.EFFECTIVE) {
            return boundData.getInterestRate();
        }

        int period = boundData.getDaysYear() / boundData.getCapitalization().days;
        return Math.pow(1 + boundData.getInterestRate()/period, period) - 1;
    }

    private double getEffectiveRate() {
        return Math.pow(1 + getEffectiveAnnualRate(), (double) boundData.getCouponFrequency().days/ (double) boundData.getDaysYear()) - 1;
    }

    private double getCOK() {
        return Math.pow(1 + boundData.getAnnualDiscountRate(), (double) boundData.getCouponFrequency().days / (double) boundData.getDaysYear()) - 1;
    }

    private double getInitialCostsEmitter() {
        return (initialCostData.getStructuring() +
                initialCostData.getCollocation() +
                initialCostData.getFloatation() +
                initialCostData.getCavali()) * boundData.getCommercialValue();
    }

    private double getInitialCostsBondholder() {
        return (initialCostData.getFloatation() +
                initialCostData.getCavali()) * boundData.getCommercialValue();
    }

    private double calculateQuota(double indexBound, double remainingPeriods) {
        double aux = Math.pow(1 + getEffectiveRate(), remainingPeriods);
        return -indexBound * ((getEffectiveRate() * aux)/(aux - 1));
    }

    private double calculateRateIndicatorProfitability(double tir) {
        return Math.pow(tir + 1, (double) boundData.getDaysYear() / (double) boundData.getCouponFrequency().days) - 1;
    }

    private double getFlowSummation(double[] flow, double rate, int periodsNumber) {
        double sum = 0;
        for (int i = 1; i <= periodsNumber; i++) {
            sum += Math.abs(flow[i - 1] / Math.pow(1 + rate, i));
        }
        return sum;
    }

    private double getFlowSummation(double[] flow, double periodsNumber) {
        double sum = 0;
        for (int i = 0; i < periodsNumber; i++) {
            sum += flow[i];
        }
        return sum;
    }

    private double getDuration(double[] currentFlows, double[] currentFlowsPerPeriod) {
        double numerator = getFlowSummation(currentFlowsPerPeriod, getNumberOfPeriods());
        double denominator = getFlowSummation(currentFlows, getNumberOfPeriods());
        return numerator / denominator;
    }

    private double getConvexity(double[] convexityFactor, double[] currentFlows) {
        return getFlowSummation(convexityFactor, getNumberOfPeriods())/
                (Math.pow(1 + getCOK(), 2) * getFlowSummation(currentFlows, getNumberOfPeriods())
                * Math.pow((double) boundData.getDaysYear() / (double) boundData.getCouponFrequency().days, 2));
    }

    private double getModifiedDuration(double duration) {
        return duration / (1 + getCOK());
    }

    private double getTIR(double investment, double[] flow, double top, double precision) {
        double left = 0, right = top, mit;

        while (left < right - precision) {
            mit = Math.round(((left + right) / 2.0) * 1000000.0) / 1000000.0;
            if (getFlowSummation(flow, mit, getNumberOfPeriods()) < Math.abs(investment))
                right = mit - precision;
            else
                left = mit;
        }

        return left;
    }

    private void calculatedStructuringResults() {
        structuringResults = new StructuringResults();

        structuringResults.setCouponFrequencyDays(boundData.getCouponFrequency().days);
        structuringResults.setCapitalizationDays(boundData.getCapitalization().days);
        structuringResults.setPeriodsPerYear(getPeriodsPerYear());
        structuringResults.setTotalNumberOfPeriods(getNumberOfPeriods());
        structuringResults.setEffectiveAnnualRate(getEffectiveAnnualRate());
        structuringResults.setEffectiveRate(getEffectiveRate());
        structuringResults.setCOK(getCOK());
        structuringResults.setInitialCostsEmitter(getInitialCostsEmitter());
        structuringResults.setInitialCostsBondholder(getInitialCostsBondholder());
    }

    private TypeOfGracePeriod[] initializeGracePeriod() {
        final int NUMBER_OF_PERIODS = getNumberOfPeriods();
        TypeOfGracePeriod[] gracePeriods = new TypeOfGracePeriod[NUMBER_OF_PERIODS];

        if (gracePeriod != null && gracePeriod > 0) {
            for (int i = 0; i < gracePeriod; i++) {
                gracePeriods[i] = typeOfGracePeriod;
            }
            for (int i = gracePeriod.intValue(); i < NUMBER_OF_PERIODS; i++) {
                gracePeriods[i] = TypeOfGracePeriod.S;
            }
        }
        else {
            for (int i = 0; i < NUMBER_OF_PERIODS; i++) {
                gracePeriods[i] = TypeOfGracePeriod.S;
            }
        }

        return gracePeriods;
    }

    private void calculatedPaymentSchedule() {
        calculatedStructuringResults();

        final int NUMBER_OF_PERIODS = getNumberOfPeriods();

        double initialBound = boundData.getNominalValue();
        double[] bounds = new double[NUMBER_OF_PERIODS];
        double[] indexedBond = new double[NUMBER_OF_PERIODS];
        double[] coupon = new double[NUMBER_OF_PERIODS];
        double[] quota = new double[NUMBER_OF_PERIODS];
        double[] amortization = new double[NUMBER_OF_PERIODS];
        double[] premium = new double[NUMBER_OF_PERIODS];
        double[] shield = new double[NUMBER_OF_PERIODS];
        double[] emitterStream = new double[NUMBER_OF_PERIODS];
        double[] emitterFlowWithShield = new double[NUMBER_OF_PERIODS];
        double[] boundHolderFlow = new double[NUMBER_OF_PERIODS];
        double[] currentFlow = new double[NUMBER_OF_PERIODS];
        double[] currentFlowPerPeriod = new double[NUMBER_OF_PERIODS];
        double[] convexityFactor = new double[NUMBER_OF_PERIODS];
        TypeOfGracePeriod[] gracePeriods = initializeGracePeriod();

        Date quotaDate = date;
        Calendar calendar;

        for (int i = 1; i <= NUMBER_OF_PERIODS; i++) {
            if (i == 1) bounds[i - 1] = initialBound;
            else {
                if (gracePeriods[i - 2] == TypeOfGracePeriod.T) bounds[i - 1] = indexedBond[i - 2] - coupon[i - 2];
                else bounds[i - 1] = indexedBond[i - 2] + amortization[i - 2];
            }

            indexedBond[i - 1] = bounds[i - 1];
            coupon[i - 1] = -indexedBond[i - 1] * structuringResults.getEffectiveRate();

            if (gracePeriods[i - 1] == TypeOfGracePeriod.T) quota[i - 1] = 0;
            else {
                if (gracePeriods[i - 1] == TypeOfGracePeriod.P) quota[i - 1] = coupon[i - 1];
                else quota[i - 1] = calculateQuota(indexedBond[i - 1],NUMBER_OF_PERIODS - i + 1);
            }

            if (gracePeriods[i - 1] == TypeOfGracePeriod.T || gracePeriods[i - 1] == TypeOfGracePeriod.P) amortization[i - 1] = 0;
            else amortization[i - 1] = quota[i - 1]-coupon[i - 1];

            if (i == NUMBER_OF_PERIODS) premium[i - 1] = -indexedBond[i - 1] * initialCostData.getPremium();
            else premium[i - 1] = 0;

            shield[i - 1] = -coupon[i - 1] * boundData.getIncomeTax();
            emitterStream[i - 1] = quota[i - 1] + premium[i - 1];
            emitterFlowWithShield[i - 1] = emitterStream[i - 1] + shield[i - 1];
            boundHolderFlow[i - 1] = -emitterStream[i - 1];
            currentFlow[i - 1] = boundHolderFlow[i - 1] / Math.pow(1 + getCOK(), i);
            currentFlowPerPeriod[i - 1] = (currentFlow[i - 1] * i) * (double) boundData.getCouponFrequency().days / (double) boundData.getDaysYear();
            convexityFactor[i - 1] = currentFlow[i - 1] * i * (i + 1);

            calendar = Calendar.getInstance();
            calendar.setTime(quotaDate);
            calendar.add(Calendar.DATE, boundData.getCouponFrequency().days);
            quotaDate = calendar.getTime();

            Quota newQuota = new Quota();
            newQuota.setNumberOfQuota(i);
            newQuota.setScheduledDate(quotaDate);
            newQuota.setTypeOfGracePeriod(gracePeriods[i - 1]);
            newQuota.setBond(bounds[i - 1]);
            newQuota.setIndexedBond(indexedBond[i - 1]);
            newQuota.setCoupon(coupon[i - 1]);
            newQuota.setQuota(quota[i - 1]);
            newQuota.setAmortization(amortization[i - 1]);
            newQuota.setPremium(premium[i - 1]);
            newQuota.setShield(shield[i - 1]);
            newQuota.setEmitterStream(emitterStream[i - 1]);
            newQuota.setEmitterFlowWithShield(emitterFlowWithShield[i - 1]);
            newQuota.setBoundHolderFlow(boundHolderFlow[i - 1]);
            newQuota.setCurrentFlow(currentFlow[i - 1]);
            newQuota.setCurrentFlowPerPeriod(currentFlowPerPeriod[i - 1]);
            newQuota.setConvexityFactor(convexityFactor[i - 1]);

            quotas.add(newQuota);
        }

        double boundHolderFlowInitial = structuringResults.getInitialCostsEmitter() - boundData.getCommercialValue();
        double emitterFlowWithShieldInitial = boundHolderFlowInitial;
        double emitterStreamInitial = - boundData.getCommercialValue() - structuringResults.getInitialCostsBondholder();

        //Result of current price and profit
        double currentPrice = getFlowSummation(boundHolderFlow, getCOK(), NUMBER_OF_PERIODS);
        double lostProfit =  emitterStreamInitial + getFlowSummation(boundHolderFlow, getCOK(), NUMBER_OF_PERIODS);
        resultsOfCurrentPriceAndProfit.setCurrentPrice(currentPrice);
        resultsOfCurrentPriceAndProfit.setLostProfit(lostProfit);

        //Result decision ratio
        double duration = getDuration(currentFlow, currentFlowPerPeriod);
        double convexity = getConvexity(convexityFactor, currentFlow);
        double total = duration + convexity;
        double modifiedDuration = getModifiedDuration(duration);
        resultsOfDecisionRatio.setDuration(duration);
        resultsOfDecisionRatio.setConvexity(convexity);
        resultsOfDecisionRatio.setTotal(total);
        resultsOfDecisionRatio.setModifiedDuration(modifiedDuration);

        //Profitability results
        double emitterTirTcea = getTIR(boundHolderFlowInitial, emitterStream, 5, 0.000001);
        double emitterTirTceaWithShield = getTIR(emitterFlowWithShieldInitial, emitterFlowWithShield, 5, 0.000001);
        double bondHolderTirTrea = getTIR(emitterStreamInitial, boundHolderFlow, 5, 0.000001);
        double emitterTcea = calculateRateIndicatorProfitability(emitterTirTcea);
        double emitterTceaWithShield = calculateRateIndicatorProfitability(emitterTirTceaWithShield);
        double bondholderTrea = calculateRateIndicatorProfitability(bondHolderTirTrea);
        profitabilityResults.setEmitterTirTcea(emitterTirTcea);
        profitabilityResults.setEmitterTirTceaWithShield(emitterTirTceaWithShield);
        profitabilityResults.setBondholderTirTrea(bondHolderTirTrea);
        profitabilityResults.setEmitterTcea(emitterTcea);
        profitabilityResults.setEmitterTceaWithShield(emitterTceaWithShield);
        profitabilityResults.setBondHolderTrea(bondholderTrea);
    }
}
