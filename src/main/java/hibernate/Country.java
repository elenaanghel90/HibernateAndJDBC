package hibernate;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import java.util.Objects;

@Entity
public class Country {
    @Id
    private String code; //code este primary key
    private String name;
    private String HeadOfState;
    private String Continent;
    //private String capital;

    @OneToOne
    private City capital;

    @OneToOne
    private CountryLanguage percentage;

    public Country() {
    }

    public Country(String code, String name, String headOfState, String continent) {
        this.code = code;
        this.name = name;
        HeadOfState = headOfState;
        Continent = continent;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setHeadOfState(String headOfState) {
        HeadOfState = headOfState;
    }

    public void setContinent(String continent) {
        Continent = continent;
    }

    @Override
    public String toString() {
        return "Country{" +
                "code='" + code + '\'' +
                ", name='" + name + '\'' +
                ", HeadOfState='" + HeadOfState + '\'' +
                ", Continent='" + Continent + '\'' +
                ", capital=" + capital +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Country country = (Country) o;
        return code.equals(country.code) &&
                name.equals(country.name) &&
                HeadOfState.equals(country.HeadOfState) &&
                Continent.equals(country.Continent);
    }

    @Override
    public int hashCode() {
        return Objects.hash(code, name, HeadOfState, Continent);
    }

}

