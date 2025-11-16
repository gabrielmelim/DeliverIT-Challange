package br.com.deliverit.repository;

import br.com.deliverit.domain.PayAccount;

import java.util.List;

public interface PaymentAccountRepository {
    public List<PayAccount> list();
    public PayAccount create(PayAccount payAccount);
}
