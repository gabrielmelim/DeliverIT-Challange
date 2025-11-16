package br.com.deliverit.repository;

import br.com.deliverit.domain.PayAccount;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PaymentAccountRepositoryImpl implements PaymentAccountRepository {

    private final PayAccountRepositoryJpa jpaRepository;

    public PaymentAccountRepositoryImpl(PayAccountRepositoryJpa jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    @Override
    public List<PayAccount> list() {
        return jpaRepository.findAll();
    }

    @Override
    public PayAccount create(PayAccount payAccount) {
        return jpaRepository.save(payAccount);
    }
}
