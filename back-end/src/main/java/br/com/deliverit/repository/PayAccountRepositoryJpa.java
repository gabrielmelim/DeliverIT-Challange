package br.com.deliverit.repository;

import br.com.deliverit.domain.PayAccount;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PayAccountRepositoryJpa extends JpaRepository<PayAccount, Long> {
}
