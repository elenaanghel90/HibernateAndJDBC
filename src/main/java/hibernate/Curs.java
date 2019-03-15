package hibernate;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
public class Curs {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    //pentru a genera automat un ID in baza de date MySQL, pentru a nu mai introduce manual ID-ul
    @Column(name = "ID")
    private int id;
    @Column(name = "NUME")
    private String nume;
    @Column(name = "LIMBAJ")
    private String limbaj;
    @Column(name = "DATA_START")
    private LocalDate dataStart;
    @Column(name = "DATA_FINAL")
    private LocalDate dataFinal;

    @ManyToOne
    @JoinColumn(name = "ID_LOCATIE")
    private Locatie locatie;

    @ManyToMany(mappedBy = "cursuri", fetch = FetchType.EAGER)
    private List<Student> students;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public String getLimbaj() {
        return limbaj;
    }

    public void setLimbaj(String limbaj) {
        this.limbaj = limbaj;
    }

    public LocalDate getDataStart() {
        return dataStart;
    }

    public void setDataStart(LocalDate dataStart) {
        this.dataStart = dataStart;
    }

    public LocalDate getDataFinal() {
        return dataFinal;
    }

    public void setDataFinal(LocalDate dataFinal) {
        this.dataFinal = dataFinal;
    }

    public Locatie getLocatie() {
        return locatie;
    }

    public void setLocatie(Locatie locatie) {
        this.locatie = locatie;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }
}
