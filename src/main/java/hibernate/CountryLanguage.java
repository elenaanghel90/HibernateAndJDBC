package hibernate;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.math.BigDecimal;

@Entity
public class CountryLanguage {
    @Column(name = "Percentage")
    private BigDecimal percentage;

    public CountryLanguage() {
    }

    @Override
    public String toString() {
        return "CountryLanguage{" +
                "percentage=" + percentage +
                '}';
    }

    public BigDecimal getPercentage() {
        return percentage;
    }

    public void setPercentage(BigDecimal percentage) {
        this.percentage = percentage;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CountryLanguage that = (CountryLanguage) o;

        return percentage != null ? percentage.equals(that.percentage) : that.percentage == null;
    }

    @Override
    public int hashCode() {
        return percentage != null ? percentage.hashCode() : 0;
    }
}
