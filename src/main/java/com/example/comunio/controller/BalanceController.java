package com.example.comunio.controller;

import com.example.comunio.domain.calculator.BalanceCalculator;
import com.example.comunio.domain.calculator.BalanceResult;
import com.example.comunio.domain.printer.BalanceResultPrinter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/balance")
@RequiredArgsConstructor
public class BalanceController {

    private final BalanceCalculator balanceCalculator;
    private final BalanceResultPrinter balanceResultPrinter;

    @GetMapping
    public List<BalanceResult> getUserWithBalance() {
        List<BalanceResult> calculate = balanceCalculator.calculate();
        balanceResultPrinter.print(calculate);
        return calculate;
    }

}
