public class Fox extends Animal {
    private int foodLevel;

    public Fox(Field field, Location location) {
        super(field, location);
        this.foodLevel = 10;
    }

    @Override
    public void act(List<Animal> newFoxes) {
        // Fox mencari kelinci sebagai makanan
        Location newLocation = findFood();
        if(newLocation == null) {
            newLocation = getField().freeAdjacentLocation(getLocation());
        }
        if(newLocation != null) {
            setLocation(newLocation);
        } else {
            setDead();
        }

        if(isAlive()) {
            int births = Randomizer.getRandom().nextInt(2);
            for(int b = 0; b < births; b++) {
                newFoxes.add(new Fox(getField(), getLocation()));
            }
        }
    }

    private Location findFood() {
        for(Location where : getField().adjacentLocations(getLocation())) {
            Object animal = getField().getObjectAt(where);
            if(animal instanceof Rabbit) {
                Rabbit rabbit = (Rabbit) animal;
                if(rabbit.isAlive()) {
                    rabbit.setDead();
                    foodLevel = 10;
                    return where;
                }
            }
        }
        return null;
    }
}
