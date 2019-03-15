package hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.time.LocalDate;
import java.util.ArrayList;
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
        //updateAStudent(26, "A");
        //Locatie foundLocation = findLocation("Bucuresti");
        //insertACourse("jdbc", "sql", LocalDate.of(2019, 1, 1), LocalDate.of(2020, 1, 1), foundLocation);

        Student foundStudent = findStudent("Elena");
        Curs foundCurs = findCourse(8);
        subscribeToCourse(foundStudent, foundCurs);
    }

    private static Student findStudent(String prenume) {
        Session session = sessionFactory.openSession();

        Student student = session.createQuery("SELECT S FROM Student S Where S.prenume = :name", Student.class)
                .setParameter("name", prenume)
                .getSingleResult();

        System.out.println("The student was found: " + student);
        session.close();
        return student;

    }

    private static Curs findCourse(int id) {
        Session session = sessionFactory.openSession();

        Curs curs = session.createQuery("SELECT C FROM Curs C Where C.id = :id", Curs.class)
                .setParameter("id", id)
                .getSingleResult();

        System.out.println("The course was found: " + curs);
        session.close();
        return curs;
    }

    private static Certificat createCertificate() {
        Certificat certificat = new Certificat();
        certificat.setId(1);

        return certificat;
    }

    private static Locatie findLocation(String oras) {
        Session session = sessionFactory.openSession();
        //session.beginTransaction();

        Locatie location = session.createQuery(
                "SELECT L FROM Locatie L WHERE L.oras = :oras", Locatie.class)
                .setParameter("oras", oras)
                .getSingleResult();

        session.close();
        return location;
    }

    private static void subscribeToCourse(Student student, Curs curs) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        List<Curs> cursuri = student.getCursuri();
        if (cursuri == null) {
            cursuri = new ArrayList<>();
        }
        cursuri.add(curs);

        List<Student> students = curs.getStudents();
        if (students == null) {
            students = new ArrayList<>();
        }
        students.add(student);


        session.update(student);
        session.getTransaction().commit();
        session.close();
    }

    public static void insertACourse(String nume, String limbaj, LocalDate dataStart, LocalDate dataFinal, Locatie locatie) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        Curs newCourse = new Curs();
        newCourse.setNume(nume);
        newCourse.setLimbaj(limbaj);
        newCourse.setDataStart(dataStart);
        newCourse.setDataFinal(dataFinal);
        newCourse.setLocatie(locatie);


        session.save(newCourse);//salvam obiectul creat,trecere din transient in persistent
        session.getTransaction().commit();
        session.close();

        System.out.println("It was created a new course");
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
