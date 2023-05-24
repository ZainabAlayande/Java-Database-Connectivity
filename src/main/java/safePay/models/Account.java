package safePay.models;

import java.math.BigDecimal;

public class Account {
    private int id;
    private String name;
    private final BigDecimal balance;

    public Account(String name, BigDecimal balance) {
        this.name = name;
        this.balance = balance;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", balance=" + balance +
                '}';
    }
}
