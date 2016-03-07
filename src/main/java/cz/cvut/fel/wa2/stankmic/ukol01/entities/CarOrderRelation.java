package cz.cvut.fel.wa2.stankmic.ukol01.entities;

public class CarOrderRelation<U extends Car, V extends Person> extends Relation<U, V> {
    private static final long serialVersionUID = -4998959113825192953L;

    public CarOrderRelation(U owner, V owned) {
        super(owner, owned);
    }
}
