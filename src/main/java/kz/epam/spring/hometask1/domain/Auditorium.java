package kz.epam.spring.hometask1.domain;

import java.util.Objects;
import java.util.Set;
import java.util.TreeSet;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;
import java.util.stream.LongStream;

public class Auditorium extends DomainObject {
    private String name;
    private long numberOfSeats;
    private int rowSize;

    public Auditorium(String name, long numberOfSeats, int rowCount, Set<Long> vipSeats) {
        setId(ThreadLocalRandom.current().nextLong(0, 100));
        this.name = name;
        this.rowSize = rowCount;
        this.numberOfSeats = numberOfSeats;
    }

    public Auditorium() {
    }

    public Set<Long> getAllSeats() {
        return LongStream.range(1, numberOfSeats + 1).boxed().collect(Collectors.toSet());
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
        Set<Long> vipSeats = new TreeSet<>();

        for (int i = 0; i < rowSize; i++) {
            vipSeats.add((long) i);
        }

        return vipSeats;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        Auditorium that = (Auditorium) o;
        return numberOfSeats == that.numberOfSeats &&
                Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, numberOfSeats);
    }

    public int getRowSize() {
        return rowSize;
    }

    public void setRowSize(int rowSize) {
        this.rowSize = rowSize;
    }

    @Override
    public String toString() {
        return "Auditorium " + name + ", number Of Seats=" + numberOfSeats + ", row count=" + rowSize +
                ", vip Seats=" + getVipSeats();
    }
}