package com.wixsite.jingmacv;

import java.math.BigDecimal;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import com.wixsite.jingmacv.model.Block;
import com.wixsite.jingmacv.model.Blockchain;
import com.wixsite.jingmacv.model.Transaction;
import com.wixsite.jingmacv.model.User;

/**
 * Main class that initiates a blockchain and demonstrates the security of the system.
 */
public class Main {

	public static void main(String[] args) throws NoSuchAlgorithmException {
		// Initiate blockchain and confirm validity.
		Blockchain blockchain = new Blockchain();
		blockchain.add(firstBlock());
		blockchain.add(secondBlock());
		System.out.println(blockchain + "\r\n");
		System.out.println("Is valid: " + blockchain.isValid());
		// By changing the transferred amount, the calculated hash of the block will be different. This will cause the blockchain to be invalid.
		Block firstBlock = blockchain.getBlocks().get(1);
		firstBlock.getTransactions().get(0).setAmount(new BigDecimal(99));
		System.out.println("Is valid: " + blockchain.isValid());
		// Even if we recalculate the hash for the block, the blockchain won't be valid cause the next block will have a different previousHash.
		firstBlock.setHash(firstBlock.calculateHash());
		System.out.println("Is valid: " + blockchain.isValid());
	}
	
	private static Block firstBlock() {
		User andrew = new User("Andrew", "Yang", "Yang2020", "andrew.yang@gmail.com");
		User bernie = new User("Bernie", "Sanders", "Sanders87", "bernie.sanders@outlook.com");
		Transaction transaction1 = new Transaction(andrew, bernie, new BigDecimal(6.20));
		Transaction transaction2 = new Transaction(bernie, andrew, new BigDecimal(13.98));
		List<Transaction> transactions = Arrays.asList(transaction1, transaction2);
		return new Block(1, transactions, new Date());
	}
	
	private static Block secondBlock() {
		User andrew = new User("Andrew", "Yang", "Yang2020", "andrew.yang@gmail.com");
		User donald = new User("Donald", "Trump", "MAGA", "donald.trump@live.com");
		Transaction transaction1 = new Transaction(andrew, donald, new BigDecimal(2));
		Transaction transaction2 = new Transaction(donald, andrew, new BigDecimal(128.35));
		List<Transaction> transactions = Arrays.asList(transaction1, transaction2);
		return new Block(2, transactions, new Date());
	}
	
}
