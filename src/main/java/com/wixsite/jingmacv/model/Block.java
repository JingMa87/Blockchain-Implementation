package com.wixsite.jingmacv.model;

import java.util.Date;
import java.util.List;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * A list of transactions with accompanying hashes. If the transactions in a block are tampered with, it's hash will also change and the next block will have a different previous hash.
 * This system of linked hashes ensures the security of the blockchain.
 */
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class Block {

	private final Long index;
	private final List<Transaction> transactions;
	private final Date timestamp;
	private String previousHash;
	private long nonce = 0; // An arbitrary number to ensure the hash keeps changing.
	private String hash;
	
	public Block(long index, List<Transaction> transactions, Date timestamp) {
		this.index = index;
		this.transactions = transactions;
		this.timestamp = timestamp;
		this.previousHash = "0";
		this.hash = "0";
	}
	
	/**
	 * This function tries to find a hash with a certain prefix, determined by the difficulty of the blockchain.
	 * @param difficulty The higher the difficulty value, the more time it'll take to find a valid hash.
	 * @return This block.
	 */
	public Block mine(int difficulty) {
		String prefix = StringUtils.repeat("0", difficulty);
		while(!hash.startsWith(prefix)) {
			nonce++;
			hash = calculateHash();
		}
		return this;
	}

	public String calculateHash() {
		return DigestUtils.sha256Hex(index.hashCode() + transactions.hashCode() + timestamp.hashCode() + previousHash + nonce);
	}

}
