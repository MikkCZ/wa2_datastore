package cz.cvut.fel.wa2.stankmic.ukol01.entities;

public class Bus extends Car {
    private static final long serialVersionUID = 1196741184072613719L;

    private int seats;

    public Bus(String name) {
        super(name);
    }

    public Bus(String name, int seats) {
        this(name);
        this.seats = seats;
    }

    public int getSeats() {
        return seats;
    }

    public void setSeats(int seats) {
        this.seats = seats;
    }

    public String toString() {
        return String.format("%s, seats %d", super.toString(), seats);
    }
}
