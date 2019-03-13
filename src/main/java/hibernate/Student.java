package hibernate;



import javax.persistence.*;
import java.util.Objects;

@Entity
public class Student {
    @Id
    private int id;
    private String nume;
    @Column(name = "Prenume")
    private String prenume;

    @OneToOne
    @JoinColumn(name = "id_certificat")
    private Certificat certificat; //e acelasi lucru cu private Certificat id_certificat

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return id == student.id &&
                nume.equals(student.nume) &&
                certificat.equals(student.certificat);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nume, certificat);
    }

    @Override
    public String toString() {
        return "Student{" +
                "Id_certificat=" + id +
                ", nume='" + nume + '\'' +
                ", certificat=" + certificat +
                '}';
    }

    public Student(int id, String nume,String prenume, Certificat certificat) {
        this.id = id;
        this.nume = nume;
        this.certificat = certificat;
        this.prenume = prenume;
    }

    public Student(){}
}
