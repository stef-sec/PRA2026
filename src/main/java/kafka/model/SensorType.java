package kafka.model;
public class SensorType{
    public String manufacturer;
    public String name;
    public int id;
    /**
     * Zwraca producenta sensora, żeby opisać źródło pomiaru.
     */
    public String getManufacturer() { return manufacturer; }
    /**
     * Ustawia producenta sensora.
     */
    public void setManufacturer(String manufacturer) { this.manufacturer = manufacturer; }
    /**
     * Zwraca nazwę typu sensora.
     */
    public String getName() { return name; }
    /**
     * Ustawia nazwę typu sensora.
     */
    public void setName(String name) { this.name = name; }
    /**
     * Zwraca identyfikator typu sensora.
     */
    public int getId() { return id; }
    /**
     * Ustawia identyfikator typu sensora.
     */
    public void setId(int id) { this.id = id; }
}
