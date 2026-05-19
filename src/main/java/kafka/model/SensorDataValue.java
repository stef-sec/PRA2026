package kafka.model;
public class SensorDataValue {
    public String value_type;
    public String value;
    public Object id;
    /**
     * Tworzy pustą wartość pomiaru, żeby Jackson mógł ją bezproblemowo zbudować.
     */
    public SensorDataValue() {}
    /**
     * Tworzy wartość z gotową liczbą tekstową, gdy potrzebujemy prostego obiektu pomocniczego.
     */
    public SensorDataValue(String value) { this.value = value; }
    /**
     * Zwraca typ pomiaru, np. temperatura albo wilgotność.
     */
    public String getValue_type() { return value_type; }
    /**
     * Ustawia typ pomiaru podczas deserializacji.
     */
    public void setValue_type(String value_type) { this.value_type = value_type; }
    /**
     * Zwraca samą wartość pomiaru jako tekst.
     */
    public String getValue() { return value; }
    /**
     * Ustawia wartość pomiaru.
     */
    public void setValue(String value) { this.value = value; }
    /**
     * Zwraca identyfikator wartości, jeśli występuje w odpowiedzi API.
     */
    public Object getId() { return id; }
    /**
     * Ustawia identyfikator wartości.
     */
    public void setId(Object id) { this.id = id; }
}
