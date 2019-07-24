package com.wixsite.jingmacv.model;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Transaction information. The amount is represented with a BigDecimal in order to scale into big values.
 */
@Data
@AllArgsConstructor
public class Transaction {

	private final User sender;
	private final User receiver;
	private BigDecimal amount;
	
}
