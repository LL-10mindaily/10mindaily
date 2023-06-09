package com.ll.tenmindaily.boundedContext.investment.Stocks;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("Korea")
public class KoreaStock extends Stock {

}
