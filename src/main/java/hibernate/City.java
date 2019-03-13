package hibernate;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import java.util.Objects;

@Entity
public class City {
    @Id
    private String ID;
    private String Name;



    @Override
    public String toString() {
        return "City{" +
                "ID='" + ID + '\'' +
                ", Name='" + Name + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        City city = (City) o;
        return ID.equals(city.ID) &&
                Name.equals(city.Name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ID, Name);
    }
}
