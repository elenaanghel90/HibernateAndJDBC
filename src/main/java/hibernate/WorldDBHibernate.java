package hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import java.util.List;

public class WorldDBHibernate {
    public static StandardServiceRegistry registry;
    public static MetadataSources sources;
    public static Metadata metadata;
    public static SessionFactory sessionFactory;

    public static void main(String[] args) {
        SessionFactory();
        //getCountryByCode("ROM");
        //listCountries();
        //createCountry();
        //updatePresidentToMyCountry("ANG");
        //deleteMyCountry("ANG");
        //getCountriesAndCapitals();
        getCountriesWithLetterR("R%");
    }

    public static void getAllCountriesAAndTheirLanguage() {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        List<Object[]> resultList = session.createQuery( "SELECT * FROM country C\n" +
                "INNER JOIN countrylanguage CL\n" +
                "ON C.Name = CL.Language; ")
               //.setMaxResults(20)
                .list();

        for(Object[] c : resultList){
            System.out.println(c[0]);
            System.out.println(c[1]);
        }

    }

    public static void SessionFactory() {
        registry = new StandardServiceRegistryBuilder().configure().build();
        sources = new MetadataSources(registry);
        metadata = sources.getMetadataBuilder().build();
        sessionFactory = metadata.getSessionFactoryBuilder().build();
    }

    public static void getCountryByCode(String code) {
        Session session = sessionFactory.openSession();
        Transaction transaction = transaction = session.beginTransaction();

        Country country = session.find(Country.class, code);
        session.close();
        //transaction.commit();
        System.out.println(country.toString());
    }

    public static void listCountries() {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        List<Country> countries = session.createQuery(
                "SELECT c FROM Country c", Country.class)
                .setMaxResults(10) //doar primele 10 tari
                .list();
        for (Country c : countries) {
            System.out.println(c);
        }
        session.getTransaction().commit();
    }

    public static void createCountry() {
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        Country myCountry = new Country("ANG", "Anghel", "Elena President", "Europe");
        System.out.println(myCountry);
        session.persist(myCountry); //persist adauga un obict nou
        session.getTransaction().commit();
        session.close();
    }

    public static void updatePresidentToMyCountry(String code) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        Country country = session.find(Country.class, code);
        country.setHeadOfState("Emanuel");
        session.persist(country);
        session.getTransaction().commit();
        session.close();
        System.out.println(country.toString());

    }

    public static void deleteMyCountry(String code) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        Country country = session.find(Country.class, code);
        session.delete(country);
        session.getTransaction().commit();
        session.close();
        System.out.println("My country was deleted");
    }

    //join cu relatie One to One
    public static void getCountriesAndCapitals() {
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        List<Object[]> resultList = session.createQuery("SELECT C, City FROM Country C LEFT JOIN C.capital City", Object[].class)
                .setMaxResults(20)
                .list();
        for (Object[] c : resultList) {
            System.out.println(c[0]);
            System.out.println(c[1]);
        }
        session.getTransaction().commit();
        session.close();
    }

    //parameter binding
    public static void getCountriesWithLetterR(String name) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        List<Country> countries = session.createQuery(
                "SELECT C FROM Country C WHERE C.name like :name", Country.class)
                .setParameter("name", name)
                .getResultList();

        for (Country c : countries) {
            System.out.println(c);
        }

        session.getTransaction().commit();
        session.close();

    }
}
