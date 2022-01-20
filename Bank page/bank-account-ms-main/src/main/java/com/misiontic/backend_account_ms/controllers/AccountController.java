package com.misiontic.backend_account_ms.controllers;

import com.misiontic.backend_account_ms.exceptions.TransactionNotFoundException;
import com.misiontic.backend_account_ms.models.Account;
import com.misiontic.backend_account_ms.repositories.AccountRepository;
import com.misiontic.backend_account_ms.exceptions.AccountNotFoundException;
import org.springframework.web.bind.annotation.*;

@RestController
public class AccountController {
    private final AccountRepository accountRepository;

    public AccountController(AccountRepository accountRepository){
        this.accountRepository = accountRepository;
    }


    @GetMapping("/")
    String startMessage(){
        return "Welcome to AccountMS services";
    }


    @GetMapping("/accounts/{username}")
    Account getAccount(@PathVariable String username){
        return this.accountRepository.findById(username).
                orElseThrow(() -> new AccountNotFoundException("No se han encontrado " +
                        "cuentas asociadas al usuario " + username + " enviado."));
    }


    @PostMapping("/accounts")
    Account createAccount(@RequestBody Account account){
        return accountRepository.save(account);
    }

    
    @DeleteMapping("/accounts/delete/{username}")
    String deleteAccount(@PathVariable String username){
        Account account = accountRepository.findById(username).orElse(null);
        if(account == null) {
            throw new AccountNotFoundException("La cuenta a eliminar no se pudo encontrar.");
        }
        this.accountRepository.deleteById(username);
        return "Eliminacion completa";
    }
}