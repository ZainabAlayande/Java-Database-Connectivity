package repositories;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import safePay.models.Account;
import safePay.repositories.AccountRepository;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class AccountRepositoryTest {

    private AccountRepository accountRepository;
    @BeforeEach
    void setUp() {
        accountRepository=new AccountRepository();
    }

    @Test
    public void saveTest() {
        Account account = new Account("Tinu",
                new BigDecimal(20_000));
        Account savedAccount = accountRepository.save(account);
        assertNotNull(savedAccount);
    }

    @Test
    public void findByIdTest() {
        Optional<Account> foundAccount = accountRepository.findById(1);
        assertTrue(foundAccount.isPresent());
        System.out.println("FOUND ACCOUNT:: "+foundAccount.get());
    }
}