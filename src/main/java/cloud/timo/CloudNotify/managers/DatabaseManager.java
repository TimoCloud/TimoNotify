package cloud.timo.CloudNotify.managers;

import cloud.timo.CloudNotify.CloudNotify;
import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.UUID;

public class DatabaseManager extends Thread {
    public MysqlDataSource dataSource;

    public DatabaseManager() {
        start();
        dataSource = new MysqlDataSource();
        dataSource.setUseSSL(CloudNotify.getInstance().getFileManager().getConfig().getBoolean("MySQL.useSSL"));
        dataSource.setUser(CloudNotify.getInstance().getFileManager().getConfig().getString("MySQL.user"));
        dataSource.setPassword(CloudNotify.getInstance().getFileManager().getConfig().getString("MySQL.password"));
        dataSource.setDatabaseName(CloudNotify.getInstance().getFileManager().getConfig().getString("MySQL.databaseName"));
        dataSource.setServerName(CloudNotify.getInstance().getFileManager().getConfig().getString("MySQL.host"));
        dataSource.setPort(CloudNotify.getInstance().getFileManager().getConfig().getInt("MySQL.port"));
        execute("CREATE TABLE IF NOT EXISTS cloudNotify (uuid VARCHAR(128) NOT NULL, state BOOLEAN NOT NULL, PRIMARY KEY(uuid))");
    }

    private void execute(String request) {
        try {
            Connection connection = dataSource.getConnection();
            Statement statement = connection.createStatement();
            statement.executeUpdate(request);

            statement.close();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean notNull(UUID uuid) {
        try {
            Connection connection = dataSource.getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM cloudNotify WHERE uuid='" + uuid.toString() + "'");
            while (resultSet.next()) return true;

            resultSet.close();
            statement.close();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public void setNotifyState(UUID uuid, boolean b) {
        execute("REPLACE INTO cloudNotify (uuid, state) VALUES ('" + uuid.toString() + "', " + b + ")");
    }

    public boolean getNotifyState(UUID uuid) {
        boolean state = false;

        try {
            Connection connection = dataSource.getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT state FROM cloudNotify WHERE uuid='" + uuid.toString() + "'");
            resultSet.next();
            state = resultSet.getBoolean("state");
            resultSet.close();
            statement.close();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return state;
    }

}
