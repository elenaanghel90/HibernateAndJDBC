package hibernate;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Objects;

@Entity
public class Certificat {
    @Id
    private int id;
    private String limbaj;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLimbaj() {
        return limbaj;
    }

    public void setLimbaj(String limbaj) {
        this.limbaj = limbaj;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Certificat that = (Certificat) o;
        return id == that.id &&
                limbaj.equals(that.limbaj);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, limbaj);
    }

    @Override
    public String toString() {
        return "Certificat{" +
                "ID=" + id +
                ", limbaj='" + limbaj + '\'' +
                '}';
    }
}
