package com.wixsite.jingmacv.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import lombok.EqualsAndHashCode;
import lombok.Getter;

/**
 * A network of blocks representing a public ledger, to which users can add transaction information in a secure manner.
 * The proof of work is built in using the mining principle, which forces users to find a hash below a certain difficulty.
 */
@Getter
@EqualsAndHashCode
public class Blockchain {
	
	private final List<Block> blocks = new ArrayList<>();
	private final int difficulty = 4;
	
	public Blockchain() {
		// The genesis block is the first block in the blockchain.
		Block genesisBlock = new Block(Collections.emptyList(), new Date());
		blocks.add(genesisBlock.mine(difficulty));
	}
	
	public boolean add(Block block) {
		block.setPreviousHash(getLatestBlock().getHash());
		return blocks.add(block.mine(difficulty));
	}
	
	public Block getLatestBlock() {
		return blocks.get(blocks.size() - 1);
	}
	
	public boolean isValid() {
		for (int i = blocks.size() - 1; i > 0; i--) {
			Block currentBlock = blocks.get(i);
			Block previousBlock = blocks.get(i - 1);
			if (!currentBlock.getHash().equals(currentBlock.calculateHash())) {
				return false;
			} else if (!currentBlock.getPreviousHash().equals(previousBlock.getHash())) {
				return false;
			}
		}
		return true;
	}
	
	public String toString() {
		return blocks.stream().map(Block::toString).reduce((a, b) -> a + "\n" + b).get();
	}
	
}
