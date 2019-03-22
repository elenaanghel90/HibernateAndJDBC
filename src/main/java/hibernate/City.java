package hibernate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import java.util.Objects;

@Entity
public class City {
    @Id
    private String ID;
    @Column(name = "Name")
    private String name;

    @Column(name = "District")
    private String district;
    @Column(name = "Population")
    private int population;


    @Override
    public String toString() {
        return "City{" +
                "ID='" + ID + '\'' +
                ", name='" + name + '\'' +
                ", district='" + district + '\'' +
                ", population=" + population +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        City city = (City) o;

        if (population != city.population) return false;
        if (ID != null ? !ID.equals(city.ID) : city.ID != null) return false;
        if (name != null ? !name.equals(city.name) : city.name != null) return false;
        return district != null ? district.equals(city.district) : city.district == null;
    }

    @Override
    public int hashCode() {
        int result = ID != null ? ID.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (district != null ? district.hashCode() : 0);
        result = 31 * result + population;
        return result;
    }
}
