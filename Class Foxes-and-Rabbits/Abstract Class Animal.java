public abstract class Animal {
    private boolean alive;
    private Location location;
    private Field field;

    public Animal(Field field, Location location) {
        this.alive = true;
        this.field = field;
        setLocation(location);
    }

    public boolean isAlive() {
        return alive;
    }

    public void setDead() {
        alive = false;
        if(location != null) {
            field.clear(location);
            location = null;
            field = null;
        }
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location newLocation) {
        if(location != null) {
            field.clear(location);
        }
        location = newLocation;
        field.place(this, newLocation);
    }

    public Field getField() {
        return field;
    }

    // Semua hewan wajib memiliki tindakan pada setiap langkah simulasi
    public abstract void act(List<Animal> newAnimals);
}
