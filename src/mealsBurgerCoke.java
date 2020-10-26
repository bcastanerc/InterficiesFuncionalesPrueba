import java.util.ArrayList;
import java.util.List;

// CONSTRUCCIÓ D'OBJECTES
class mealsBurgerCoke {
    Meal prepareVegMeal(){
        Meal m = new Meal();
        m.addItem(new VegBurger());
        m.addItem(new Pepsi());
        return m;
    }
}

interface Pack {
    String pack();
}

interface Item {
    String getName();
    double getPrice();
    Pack packing();
}

class Meal {
    private List<Item> items = new ArrayList<>();

    void addItem(Item i){
        items.add(i);
    }

    double getCost(){
        /*double total = 0;
        for(Item i : items){
            total += i.getPrice();
        }
        return total;*/
        return items.stream().map(Item::getPrice).mapToDouble(Double::doubleValue).sum();
    }

    @Override
    public String toString(){
        String s = "";
        for(Item i : items){
            s += String.format("Item: %s, Preu: %f, Packing: %s \n", i.getName(), i.getPrice(), i.packing());
        }

        s += String.format("Total cost: %f", this.getCost());

        return s;
    }
}

class Wrapper implements Pack{
    @Override
    public String pack() {
        return "Wrapper";
    }
}

class Bottle implements Pack {
    @Override
    public String pack() {
        return "Bottle";
    }
}

abstract class Burger implements Item {
    @Override
    public Pack packing() {
        return new Wrapper();
    }
}

class VegBurger extends Burger {
    @Override
    public String getName() {
        return "Hamburguesa vegana";
    }

    @Override
    public double getPrice() {
        return 4.50;
    }
}

abstract class Drink implements Item {
    @Override
    public Pack packing() {
        return new Bottle();
    }
}

class Pepsi extends Drink {
    @Override
    public String getName() {
        return "Pepsi";
    }

    @Override
    public double getPrice() {
        return 1.20;
    }
}

class Coke extends Drink {
    @Override
    public String getName() {
        return "Coke";
    }

    @Override
    public double getPrice() {
        return 1.60;
    }
}