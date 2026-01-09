package com.aespa.armageddon.core.domain.transaction.command.domain.repository;

import com.aespa.armageddon.core.domain.transaction.command.domain.aggregate.Transaction;

public interface TransactionRepository {

    Transaction save(Transaction transaction);
}
