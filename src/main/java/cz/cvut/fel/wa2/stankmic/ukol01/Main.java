package cz.cvut.fel.wa2.stankmic.ukol01;

import cz.cvut.fel.wa2.stankmic.ukol01.entities.*;
import cz.cvut.fel.wa2.stankmic.ukol01.stores.DiskEntityStore;
import cz.cvut.fel.wa2.stankmic.ukol01.stores.IEntityStore;

import java.io.IOException;
import java.util.Collection;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        final Scanner scanner = new Scanner(System.in);
        final IEntityStore<Car> auta = new DiskEntityStore<>(new Car(null).getStoreLocation());
        final IEntityStore<Person> lidi = new DiskEntityStore<>(new Person(null, null).getStoreLocation());
        final IEntityStore<CarOrderRelation> objednavky = new DiskEntityStore<>(new CarOrderRelation(null, null).getStoreLocation());
        while (true) {
            System.out.println("Vyberte:");
            System.out.println("1 - vytvareni:");
            System.out.println("2 - vypis:");
            System.out.println("3 - upravit:");
            System.out.println("4 - smazat:");
            final int volba = Integer.parseInt(scanner.nextLine());
            switch (volba) {
                case 1:
                    vytvareni(scanner, auta, lidi, objednavky);
                    break;
                case 2:
                    vypis(scanner, auta, lidi, objednavky);
                    break;
                case 3:
                    upravit(scanner, auta, lidi, objednavky);
                    break;
                case 4:
                    smazat(scanner, auta, lidi, objednavky);
                    break;
            }
        }
    }

    private static void vytvareni(Scanner scanner, IEntityStore<Car> auta, IEntityStore<Person> lidi, IEntityStore<CarOrderRelation> objednavky) throws IOException, ClassNotFoundException {
        System.out.println("Vyberte:");
        System.out.println("1 - auto:");
        System.out.println("2 - autobus:");
        System.out.println("3 - clovek:");
        System.out.println("4 - objednavka:");
        final int volba = Integer.parseInt(scanner.nextLine());
        switch (volba) {
            case 1:
                System.out.println("Zadejte nazev auta:");
                final String nazevAuta = scanner.nextLine();
                final Car auto = new Car(nazevAuta);
                auta.createNew(auto);
                System.out.println(auto);
                break;
            case 2:
                System.out.println("Zadejte nazev autobusu:");
                final String nazevAutobusu = scanner.nextLine();
                final Bus autobus = new Bus(nazevAutobusu);
                System.out.println("Zadejte pocet sedadel:");
                final int sedadel = Integer.parseInt(scanner.nextLine());
                autobus.setSeats(sedadel);
                auta.createNew(autobus);
                System.out.println(autobus);
                break;
            case 3:
                System.out.println("Zadejte jmeno <enter> prijmeni:");
                final String jmeno = scanner.nextLine();
                final String prijmeni = scanner.nextLine();
                final Person clovek = new Person(jmeno, prijmeni);
                lidi.createNew(clovek);
                System.out.println(clovek);
                break;
            case 4:
                System.out.println("Zadejte id auta <enter> cloveka:");
                final String idAuta = scanner.nextLine();
                final String idCloveka = scanner.nextLine();
                final CarOrderRelation objednavka = new CarOrderRelation(auta.getById(idAuta), lidi.getById(idCloveka));
                objednavky.createNew(objednavka);
                System.out.println(objednavka);
                break;
        }
    }

    private static void upravit(Scanner scanner, IEntityStore<Car> auta, IEntityStore<Person> lidi, IEntityStore<CarOrderRelation> objednavky) throws IOException, ClassNotFoundException {
        System.out.println("Vyberte:");
        System.out.println("1 - auto:");
        System.out.println("2 - autobus:");
        System.out.println("3 - clovek:");
        System.out.println("4 - objednavka:");
        final int volba = Integer.parseInt(scanner.nextLine());
        System.out.println("Zadejte ID:");
        final String id = scanner.nextLine();
        switch (volba) {
            case 1:
                final Car auto = auta.getById(id);
                System.out.println(auto);
                System.out.println("Zadejte nazev auta:");
                auto.setName(scanner.nextLine());
                auta.update(auto);
                System.out.println(auto);
                break;
            case 2:
                final Bus autobus = (Bus)auta.getById(id);
                System.out.println(autobus);
                System.out.println("Zadejte nazev autobusu:");
                autobus.setName(scanner.nextLine());
                System.out.println("Zadejte pocet sedadel:");
                autobus.setSeats(Integer.parseInt(scanner.nextLine()));
                auta.update(autobus);
                System.out.println(autobus);
                break;
            case 3:
                final Person clovek = lidi.getById(id);
                System.out.println(clovek);
                System.out.println("Zadejte jmeno:");
                clovek.setFirstName(scanner.nextLine());
                System.out.println("Zadejte prijmeni:");
                clovek.setLastName(scanner.nextLine());
                lidi.update(clovek);
                System.out.println(clovek);
                break;
            case 4:
                final CarOrderRelation objednavka = objednavky.getById(id);
                System.out.println(objednavka);
                System.out.println("Zadejte id auta:");
                final String idAuta = scanner.nextLine();
                objednavka.setOwner(auta.getById(idAuta));
                System.out.println("Zadejte id cloveka:");
                final String idCloveka = scanner.nextLine();
                objednavka.setOwned(lidi.getById(idCloveka));
                objednavky.update(objednavka);
                System.out.println(objednavka);
                break;
        }
    }

    private static void vypis(Scanner scanner, IEntityStore<Car> auta, IEntityStore<Person> lidi, IEntityStore<CarOrderRelation> objednavky) throws IOException, ClassNotFoundException {
        System.out.println("Vyberte:");
        System.out.println("1 - auto:");
        System.out.println("2 - autobus:");
        System.out.println("3 - clovek:");
        System.out.println("4 - objednavka:");
        IEntityStore store = null;
        final int volbaA = Integer.parseInt(scanner.nextLine());
        switch (volbaA) {
            case 1:
                store = auta;
                break;
            case 2:
                store = auta;
                break;
            case 3:
                store = lidi;
                break;
            case 4:
                store = objednavky;
                break;
        }
        System.out.println("Vyberte:");
        System.out.println("1 - podle ID:");
        System.out.println("2 - vsechny:");
        final int volbaB = Integer.parseInt(scanner.nextLine());
        switch (volbaB) {
            case 1:
                vypisPodleID(scanner, store);
                break;
            case 2:
                vypisVse(store);
                break;
        }
    }

    private static void vypisVse(IEntityStore store) throws IOException, ClassNotFoundException {
        Collection<IEntity> list = store.getAll();
        list.forEach(System.out::println);
    }

    private static void vypisPodleID(Scanner scanner, IEntityStore store) throws IOException, ClassNotFoundException {
        System.out.println("Zadejte ID:");
        IEntity e = store.getById(scanner.nextLine());
        System.out.println(e);
    }

    private static void smazat(Scanner scanner, IEntityStore<Car> auta, IEntityStore<Person> lidi, IEntityStore<CarOrderRelation> objednavky) throws IOException, ClassNotFoundException {
        System.out.println("Vyberte:");
        System.out.println("1 - auto:");
        System.out.println("2 - autobus:");
        System.out.println("3 - clovek:");
        System.out.println("4 - objednavka:");
        IEntityStore store = null;
        final int volbaA = Integer.parseInt(scanner.nextLine());
        switch (volbaA) {
            case 1:
                store = auta;
                break;
            case 2:
                store = auta;
                break;
            case 3:
                store = lidi;
                break;
            case 4:
                store = objednavky;
                break;
        }
        System.out.println("Zadejte ID:");
        final String id = scanner.nextLine();
        store.delete(id);
    }

    private static void test() throws IOException, ClassNotFoundException {
        final IEntityStore<Car> carStore = new DiskEntityStore<>(new Car(null).getStoreLocation());
        final Car car1 = new Car("auto1");
        final Car car2 = new Car("auto2");
        final Car bus1 = new Bus("bus1", 42);
        final Car bus2 = new Bus("bus2", 49);
        carStore.createNew(car1);
        carStore.createNew(car2);
        carStore.createNew(bus1);
        carStore.createNew(bus2);
        printAll(carStore);

        final IEntityStore<Person> personStore = new DiskEntityStore<>(new Person(null, null).getStoreLocation());
        final Person p1 = new Person("John", "Doe");
        final Person p2 = new Person("Dave", "Lister");
        personStore.createNew(p1);
        personStore.createNew(p2);
        printAll(personStore);

        final IEntityStore<Relation<Car, Person>> relationStore = new DiskEntityStore<>(new CarOrderRelation(null, null).getStoreLocation());
        final Relation<Car, Person> r1 = new CarOrderRelation<>(car2, p1);
        final Relation<Car, Person> r2 = new CarOrderRelation<>(bus1, p1);
        relationStore.createNew(r1);
        relationStore.createNew(r2);
        printAll(relationStore);
    }

    private static <T extends IEntity> void printAll(IEntityStore<T> store) throws ClassNotFoundException, IOException {
        final Collection<T> results = store.getAll();
        results.forEach(System.out::println);
    }
}
