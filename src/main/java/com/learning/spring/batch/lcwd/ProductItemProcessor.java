package com.learning.spring.batch.lcwd;

import java.math.BigDecimal;
import java.math.RoundingMode;

import org.springframework.batch.item.ItemProcessor;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ProductItemProcessor implements ItemProcessor<Product, Product> {

	@Override
	public Product process(Product item) throws Exception {
		log.info("process() method invoked for item - " + item.getProductId());
		double price = Double.parseDouble(item.getPrice());
		double discount = Double.parseDouble(item.getDiscount());
		double discountedPrice = price - (price * (discount / 100));
		 // Round discountedPrice to 2 decimal places
        BigDecimal discountedPriceBD = BigDecimal.valueOf(discountedPrice)
                .setScale(2, RoundingMode.HALF_UP);

        item.setDiscountedPrice(""+discountedPriceBD.doubleValue());
        log.info("ItemProcessor, process() - price : {}, discount: {}, discountedPrice: {}", price, discount,discountedPrice);
		return item;
	}

}
