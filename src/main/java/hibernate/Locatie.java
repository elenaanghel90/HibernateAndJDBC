package hibernate;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
public class Locatie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)//pentru a genera automat un ID in baza de date MySQL, pentru a nu mai introduce manual ID-ul
    private int id;
    @Column(name = "ORAS")
    private String oras;
    @Column(name = "STRADA")
    private String strada;
    @Column(name = "NUMAR_SALA")
    private int numarSala;

    @OneToMany(mappedBy = "locatie")
    private List<Curs> cursuri;

    public Locatie() {
           }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getOras() {
        return oras;
    }

    public void setOras(String oras) {
        this.oras = oras;
    }

    public String getStrada() {
        return strada;
    }

    public void setStrada(String strada) {
        this.strada = strada;
    }

    public int getNumarSala() {
        return numarSala;
    }

    public void setNumarSala(int numarSala) {
        this.numarSala = numarSala;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Locatie locatie = (Locatie) o;
        return id == locatie.id &&
                numarSala == locatie.numarSala &&
                Objects.equals(oras, locatie.oras) &&
                Objects.equals(strada, locatie.strada);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, oras, strada, numarSala);
    }
}
