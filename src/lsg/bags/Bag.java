package lsg.bags;

import lsg.armor.BlackWitchVeil;
import lsg.armor.DragonSlayerLeggings;
import lsg.bags.Collectible;
import lsg.exceptions.BagFullException;

import java.util.HashSet;

public class Bag {

    public static final String BULLET_POINT = "\u2219";

    private int capacity;
    private int weight;
    private HashSet<Collectible> items;

    public Bag() {

        items = new HashSet<Collectible>();

    }

    public Bag(int capacity) {

        this();
        this.capacity = capacity;

    }

    public int getCapacity() {
        return capacity;
    }

    public int getWeight() {
        return weight;
    }

    public void push(Collectible item) throws BagFullException {

        if(item.getWeight() <= (capacity - weight)) {

            items.add(item);

        }
        else {

            throw new BagFullException(this);

        }

    }

    public Collectible pop(Collectible item) {

         boolean removed = items.remove(item);
         if(removed) {

             weight = weight - item.getWeight();
             return item;

         }
         else {

             return null;

         }

    }

    public boolean contains(Collectible item) {
        return items.contains(item);
    }

    public Collectible[] getItems() {
        return items.toArray(new Collectible[items.size()]);
    }

    public static void transfer(Bag from, Bag into) throws BagFullException {

        if(from == null || into == null) {

            return;

        }

        for(Collectible item : from.items) {

            if(item.getWeight() <= (into.capacity - into.weight)) {

                if(from.pop(item) != null) {

                    into.push(item);

                }

            }
            else {

                throw new BagFullException(into);

            }

        }

    }

    @Override
    public String toString() {

        String bagToString = this.getClass().getSimpleName() + " [ " + items.size() + " items | " + weight + "/" + capacity + " kg ]";

        if(items.size() > 0) {

            for (Collectible item : items) {

                bagToString = bagToString + "\n" + BULLET_POINT + " " + item.toString() + "[" + item.getWeight() + " kg]";

            }

        }
        else {

            bagToString = bagToString + "\n" + BULLET_POINT + " empty";

        }

        return bagToString;

    }

}
