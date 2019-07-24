package com.wixsite.jingmacv.model;

import lombok.Data;

/**
 * A user who can transact money.
 */
@Data
public class User {

	private final String firstName;
	private final String lastName;
	private final String userName;
	private final String email;
	
}
