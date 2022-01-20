package com.ecommercep5.account_ms.controllers;

import com.ecommercep5.account_ms.models.Transaction;
import com.ecommercep5.account_ms.repositories.TransactionRepository;
import com.ecommercep5.account_ms.exceptions.TransactionNotFoundException;
import com.ecommercep5.account_ms.exceptions.InsufficientBalanceException;

import com.ecommercep5.account_ms.models.Account;
import com.ecommercep5.account_ms.repositories.AccountRepository;
import com.ecommercep5.account_ms.exceptions.AccountNotFoundException;

import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Date;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RestController
public class TransactionController {
    private final TransactionRepository transactionRepository;
    private final AccountRepository     accountRepository;

    public TransactionController(TransactionRepository transactionRepository, AccountRepository accountRepository) {
        this.transactionRepository = transactionRepository;
        this.accountRepository     = accountRepository;
    }


    @PostMapping("/transactions")
    Transaction newTransaction(@RequestBody Transaction transaction){
        Account originAccount  = this.accountRepository.findById( transaction.getUsernameOrigin() ).orElse(null);
        if(originAccount == null){
            throw new AccountNotFoundException("La cuenta con usuario " + transaction.getUsernameOrigin()
                                                + " no se encuentra registrada en la base de datos.");
        }

        Account destinyAccount = this.accountRepository.findById( transaction.getUsernameDestiny() ).orElse(null);
        if(destinyAccount == null){
            throw new AccountNotFoundException("La cuenta con usuario " + transaction.getUsernameDestiny()
                    + " no se encuentra registrada en la base de datos.");
        }

        if(originAccount.getBalance() < transaction.getValue()){
            throw new InsufficientBalanceException("La cuenta no tiene saldo suficiente para la transferencia.");
        }

        originAccount.setBalance( originAccount.getBalance() - transaction.getValue() );
        originAccount.setLastChange( new Date() );
        accountRepository.save(originAccount);

        destinyAccount.setBalance( destinyAccount.getBalance() + transaction.getValue() );
        destinyAccount.setLastChange( new Date() );
        accountRepository.save(destinyAccount);

        transaction.setDate( new Date() );
        return transactionRepository.save(transaction);
    }


    @GetMapping("/transactions/{username}")
    List<Transaction> userTransactions(@PathVariable String username){
        Account userAccount = this.accountRepository.findById(username).orElse(null);
        if(userAccount == null){
            throw new AccountNotFoundException("La cuenta con usuario " + username
                    + " no se encuentra registrada en la base de datos.");
        }

        List<Transaction> transactionsOrigin  = this.transactionRepository.findByUsernameOrigin(username);
        List<Transaction> transactionsDestiny = this.transactionRepository.findByUsernameDestiny(username);

        List<Transaction> transactions = Stream.concat(transactionsOrigin.stream(),
                                                        transactionsDestiny.stream())
                                                .collect(Collectors.toList());
        return transactions;
    }


    @PutMapping("/transactions/update")
    Transaction updateTransaction(@RequestBody Transaction transactionUpdate){
        Transaction transaction = this.transactionRepository.findById( transactionUpdate.getId() ).orElse(null);
        if(transaction == null){
            throw new TransactionNotFoundException("El código de la transacción no existe en la base de datos.");
        }
        transaction.setNote( transactionUpdate.getNote() );
        transaction.setDate( new Date() );
        return transactionRepository.save(transaction);
    }


    @DeleteMapping("/transactions/delete/{id}")
    String deleteTransaction(@PathVariable String id){
        Transaction transaction = this.transactionRepository.findById(id).orElse(null);
        if(transaction == null){
            throw new TransactionNotFoundException("El código de la transacción no existe en la base de datos.");
        }
        this.transactionRepository.deleteById(id);
        return "Eliminación exitosa";
    }
}