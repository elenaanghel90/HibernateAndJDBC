package worldDataBaseJDBC;

import java.sql.*;

public class WorldApp {
    public static String url = "jdbc:mysql://localhost:3306/world";
    public static String user = "root";
    public static String pass = "Durduib90";

    public static void main(String[] args) {

        //getAllCountriesAndLanguages(url, user, pass);
        showCapitalAndLanguages("ROM");
        //createANewCountry("FRU", "Frutia", "Asia", 123456);
        //updateHeadOfNewState("Elena Anghel");
        //deleteMyCountry("Frutia");
    }

    public static void getAllCountriesAndLanguages(String url, String user, String pass) {
        try (Connection conn = DriverManager.getConnection(url, user, pass);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM world.country")) {

            while (rs.next()) {
                String code = rs.getString("code");
                String name = rs.getString("name");
                String continent = rs.getString("continent");
                long population = rs.getLong("population");
                System.out.println(code + "," + name + "," + continent + "," + population);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void showCapitalAndLanguages(String countryCode) {
        String selectCapitalAndLanuagesSql = "SELECT *, city.name as 'City Name'" +
                " from country C left join city ON (C.Capital = City.ID) " +
                "left join countrylanguage CL ON (C.code = CL.countrycode)\n" +
                "where c.code = ?";

        try (Connection conn = DriverManager.getConnection(url, user, pass);
             PreparedStatement stmt = conn.prepareStatement(selectCapitalAndLanuagesSql)) {
            stmt.setString(1, countryCode);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    String code = rs.getString("Code");
                    String name = rs.getString("Name");
                    String headOfState = rs.getString("HeadOfState");
                    String capital = rs.getString("City Name");
                    String language = rs.getString("language");
                    System.out.println( name + "," + code + "," + capital + "," + language + "," + headOfState);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void createANewCountry(String code, String name, String continent, long population) {
        String sql = "INSERT INTO country(code, name, continent, population)" +
                "VALUES(?, ?, ?, ?)";
        try (
                Connection conn = DriverManager.getConnection(url, user, pass);
                PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, code);
            stmt.setString(2, name);
            stmt.setString(3, continent);
            stmt.setLong(4, population);
            int count = stmt.executeUpdate();
            System.out.println("Inserted rows: " + count);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void updateHeadOfNewState(String HeadOfState) {
        String sql = "UPDATE country set country.HeadOfState = ? where country.name = 'Frutia';";
        try (
                Connection conn = DriverManager.getConnection(url, user, pass);
                PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, HeadOfState);

            int count = stmt.executeUpdate();
            System.out.println("Inserted head of state: " + count);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void deleteMyCountry(String country) {
        String sql = "DELETE FROM country WHERE country.name = ?";
        try (
                Connection conn = DriverManager.getConnection(url, user, pass);
                PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, country);

            //conn.setAutoCommit(false);
            conn.rollback();
            conn.setAutoCommit(true);
            int count = stmt.executeUpdate();
            System.out.println("Country already created was deleted: " + count);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
