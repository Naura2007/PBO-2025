public class Rabbit extends Animal {
    private static final int BREEDING_AGE = 5;
    private static final int MAX_AGE = 40;
    private int age;

    public Rabbit(Field field, Location location) {
        super(field, location);
        age = 0;
    }

    @Override
    public void act(List<Animal> newRabbits) {
        incrementAge();
        if(isAlive()) {
            Location location = getField().freeAdjacentLocation(getLocation());
            if(location != null) {
                setLocation(location);
            }

            // berkembang biak jika memungkinkan
            int births = breed();
            for(int b = 0; b < births; b++) {
                newRabbits.add(new Rabbit(getField(), getLocation()));
            }
        }
    }

    private void incrementAge() {
        age++;
        if(age > MAX_AGE) {
            setDead();
        }
    }

    // logika reproduksi
    private int breed() {
        if(age >= BREEDING_AGE) {
            return Randomizer.getRandom().nextInt(3);
        } else {
            return 0;
        }
    }
}
