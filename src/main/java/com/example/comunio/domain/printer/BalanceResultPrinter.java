package com.example.comunio.domain.printer;

import com.example.comunio.domain.calculator.BalanceResult;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class BalanceResultPrinter {

    public void print(List<BalanceResult> results) {
        results.forEach(result ->
                System.out.println(result.getUsername() + "\n"
                        + "Balance: " + result.getBalance() + "\n"
                        + "Kreditlimit: " + result.getCreditLimit() + "\n"
                        + "Value: " + result.getValue() + "\n")
        );
    }

}
