package safePay.repositories;

import safePay.exceptions.DatabaseConnectionFailedException;
import safePay.exceptions.InvalidStatementException;
import safePay.models.Account;

import java.math.BigDecimal;
import java.sql.*;
import java.util.Optional;

public class AccountRepository {
    private final Connection connection;
    public AccountRepository(){
        String url = "jdbc:mysql://localhost/safe_pay?createDatabaseIfNotExist=true";
        String username="root";
        String password = "abimbola";
        try {
            connection = DriverManager.getConnection(url, username, password);

        } catch (SQLException e) {
            throw new DatabaseConnectionFailedException(e.getMessage());
        }
    }

    public Account save(Account account){
        try {
//            String sql = "INSERT INTO account (`id`,`name`,`balance`) VALUES (NULL,"+"\'"+account.getName()+"\',"+"\'"+account.getBalance()+"\'"+")";
            String sql = "INSERT INTO account (`id`,`name`,`balance`) VALUES (NULL,?,?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, account.getName());
            statement.setBigDecimal(2, account.getBalance());
            statement.executeUpdate();

            return account;
        } catch (SQLException e) {
            throw new InvalidStatementException(e.getMessage());
        }finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public Optional<Account> findById(int id) {
        String sql = "SELECT * FROM account WHERE id=(?)";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) return mapRowToAccount(resultSet);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            try {

                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return Optional.empty();
    }

    private static Optional<Account> mapRowToAccount(ResultSet resultSet) throws SQLException {
        int accountId = resultSet.getInt(1);
        String name = resultSet.getString(2);
        BigDecimal balance = resultSet.getBigDecimal(3);
        Account account = new Account(name, balance);
        account.setId(accountId);
        return Optional.of(account);
    }
}
