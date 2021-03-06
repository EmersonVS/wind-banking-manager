package com.wind.banking.app.models.helpers;

import java.util.ArrayList;
import java.util.List;

import com.wind.banking.app.models.entity.account.Account;
import com.wind.banking.app.models.entity.account.financial.FinancialError;

public class MoneyOperationHelper {
	
	public static List<Account> TransferMoneyBetweenAccounts(Account origin, Account destiny, Double value) throws TransferError {
		if(value <= 0) {
			throw new TransferError("Value must be positive");
		}
		List<Account> accountList = new ArrayList<Account>();
		try {
			origin.WithdrawCC(value);
			destiny.DepositCC(value);
			accountList.add(origin);
			accountList.add(destiny);
			return accountList;
		} catch (FinancialError error) {
			throw new TransferError("Not enough balance");
		}
	}
	
	public static Account TransferMoneyBetweenBalances(Account account, String typeOrigin, Double value) throws TransferError {
		if(value <= 0) {
			throw new TransferError("Value must be positive");
		}
		try {
			switch (typeOrigin) {
			case "Conta corrente":
				account.WithdrawCC(value);
				account.DepositPP(value);
				break;
			case "Conta poupança":
				account.WithdrawPP(value);
				account.DepositCC( value);
				break;
			}
			return account;
		} catch (FinancialError error) {
			throw new TransferError(error.getMessage());
		} catch(Error error) {
			System.out.println(error);
			throw error;
		}
	}
}
