package hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.util.List;

public class TrainingApp {
    public static StandardServiceRegistry registry;
    public static MetadataSources sources;
    public static Metadata metadata;
    public static SessionFactory sessionFactory;

    public static void main(String[] args) {
        SessionFactory();
        //joinOneToOneRetrieveStudentCertificat();
        //createANewStudent(createCertificate(), "C", "Emanuel", 28);
        //deleteNewStudent(28);
        updateAStudent(26,"A");
    }

    private static Certificat createCertificate() {
        Certificat certificat = new Certificat();
        certificat.setId(1);

        return certificat;
    }



    public static void SessionFactory() {
        // Create registry
        registry = new StandardServiceRegistryBuilder().configure("training.cfg.xml").build();
        // Create MetadataSources
        sources = new MetadataSources(registry);
        // Create Metadata
        metadata = sources.getMetadataBuilder().build();
        // Create SessionFactory
        sessionFactory = metadata.getSessionFactoryBuilder().build();
    }

    public static void createANewStudent(Certificat certificat, String nume, String prenume, int id) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        Student newStudent = new Student(id, nume, prenume, certificat);
        System.out.println(newStudent);
        session.persist(newStudent);
        session.getTransaction().commit();
        session.close();

    }

    public static void updateAStudent(int id, String nume) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        Student oldStudent = session.find(Student.class, id);
        oldStudent.setNume(nume);

        session.update(oldStudent);

        session.getTransaction().commit();
        session.close();

        System.out.println("The name of the student was updated");
    }

    public static void deleteNewStudent(int id) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        Student newStudent = session.find(Student.class, id);
        session.delete(newStudent);
        session.getTransaction().commit();
        session.close();
        System.out.println("My new student was deleted");
    }

    public static void joinOneToOneRetrieveStudentCertificat() {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        String selectStudent = "SELECT S, C " +
                "FROM Student S LEFT JOIN fetch S.certificat C";
        List<Object[]> resultList = session.createQuery(selectStudent, Object[].class)
                .setMaxResults(4)
                .list();
        for (Object[] c : resultList) {
            System.out.println(c[1]);
            System.out.println(c[2]);

        }
        session.getTransaction().commit();
        //session.close();
    }
}
