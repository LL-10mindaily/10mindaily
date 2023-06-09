package com.ll.tenmindaily.boundedContext.investment.Stocks;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QAmericaStock is a Querydsl query type for AmericaStock
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QAmericaStock extends EntityPathBase<AmericaStock> {

    private static final long serialVersionUID = 50974063L;

    public static final QAmericaStock americaStock = new QAmericaStock("americaStock");

    public final QStock _super = new QStock(this);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createDate = _super.createDate;

    //inherited
    public final NumberPath<Double> currentPrice = _super.currentPrice;

    //inherited
    public final NumberPath<Double> dividendRate = _super.dividendRate;

    //inherited
    public final NumberPath<Double> dividendYield = _super.dividendYield;

    //inherited
    public final StringPath exDividendDate = _super.exDividendDate;

    //inherited
    public final NumberPath<Double> fiftyDayAverage = _super.fiftyDayAverage;

    //inherited
    public final NumberPath<Double> fiftyTwoWeekHigh = _super.fiftyTwoWeekHigh;

    //inherited
    public final NumberPath<Double> fiftyTwoWeekLow = _super.fiftyTwoWeekLow;

    //inherited
    public final NumberPath<Double> forwardPE = _super.forwardPE;

    //inherited
    public final NumberPath<Long> id = _super.id;

    //inherited
    public final NumberPath<Double> marketCap = _super.marketCap;

    //inherited
    public final NumberPath<Double> previousClose = _super.previousClose;

    //inherited
    public final NumberPath<Double> priceToSalesTrailing12Months = _super.priceToSalesTrailing12Months;

    //inherited
    public final StringPath symbol = _super.symbol;

    //inherited
    public final NumberPath<Double> targetHighPrice = _super.targetHighPrice;

    //inherited
    public final NumberPath<Double> targetLowPrice = _super.targetLowPrice;

    //inherited
    public final NumberPath<Double> targetMedianPrice = _super.targetMedianPrice;

    //inherited
    public final NumberPath<Double> twoHundredDayAverage = _super.twoHundredDayAverage;

    public QAmericaStock(String variable) {
        super(AmericaStock.class, forVariable(variable));
    }

    public QAmericaStock(Path<? extends AmericaStock> path) {
        super(path.getType(), path.getMetadata());
    }

    public QAmericaStock(PathMetadata metadata) {
        super(AmericaStock.class, metadata);
    }

}

