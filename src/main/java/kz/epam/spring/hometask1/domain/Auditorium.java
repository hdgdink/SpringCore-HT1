package kz.epam.spring.hometask1.domain;

import java.util.Collection;
import java.util.Collections;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;
import java.util.stream.LongStream;

public class Auditorium extends DomainObject {
    private String name;
    private long numberOfSeats;
    private int rowCount;
    private Set<Long> vipSeats = Collections.emptySet();

    public Auditorium(String name, long numberOfSeats, int rowCount, Set<Long> vipSeats) {
        setId(ThreadLocalRandom.current().nextLong(0, 100));
        this.name = name;
        this.rowCount = rowCount;
        this.numberOfSeats = numberOfSeats;
        this.vipSeats = vipSeats;
    }

    public Auditorium() {
    }

    public Set<Long> getAllSeats() {
        return LongStream.range(1, numberOfSeats + 1).boxed().collect(Collectors.toSet());
    }

    public long countVipSeats(Collection<Long> seats) {
        return seats.stream().filter(seat -> vipSeats.contains(seat)).count();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getNumberOfSeats() {
        return numberOfSeats;
    }

    public void setNumberOfSeats(long numberOfSeats) {
        this.numberOfSeats = numberOfSeats;
    }

    public Set<Long> getVipSeats() {
        return vipSeats;
    }

    public void setVipSeats(Set<Long> vipSeats) {
        this.vipSeats = vipSeats;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        Auditorium that = (Auditorium) o;
        return numberOfSeats == that.numberOfSeats &&
                Objects.equals(name, that.name) &&
                Objects.equals(vipSeats, that.vipSeats);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, numberOfSeats, vipSeats);
    }

    public int getRowCount() {
        return rowCount;
    }

    public void setRowCount(int rowCount) {
        this.rowCount = rowCount;
    }

    @Override
    public String toString() {
        return "Auditorium " + name + ", number Of Seats=" + numberOfSeats + ", row count=" + rowCount +
                ", vip Seats=" + vipSeats;
    }
}