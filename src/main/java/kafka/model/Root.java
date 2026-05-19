package kafka.model;
import java.util.ArrayList;
public class Root {
    public Sensor sensor;
    public ArrayList<SensorDataValue> sensordatavalues;
    public Object sampling_rate;
    public long id;
    public String timestamp;
    public Location location;
    /**
     * Zwraca podpięty sensor, żeby inne klasy mogły odczytać metadane pomiaru.
     */
    public Sensor getSensor() { return sensor; }
    /**
     * Ustawia sensor, gdy dane są deserializowane z JSON.
     */
    public void setSensor(Sensor sensor) { this.sensor = sensor; }
    /**
     * Zwraca listę wartości pomiarowych dla jednego rekordu.
     */
    public ArrayList<SensorDataValue> getSensordatavalues() { return sensordatavalues; }
    /**
     * Ustawia listę pomiarów odczytaną z odpowiedzi API.
     */
    public void setSensordatavalues(ArrayList<SensorDataValue> sensordatavalues) { this.sensordatavalues = sensordatavalues; }
    /**
     * Zwraca informację o częstotliwości próbkowania, jeśli jest dostępna.
     */
    public Object getSampling_rate() { return sampling_rate; }
    /**
     * Ustawia częstotliwość próbkowania z danych wejściowych.
     */
    public void setSampling_rate(Object sampling_rate) { this.sampling_rate = sampling_rate; }
    /**
     * Zwraca identyfikator rekordu, który służy do jednoznacznego rozpoznania pomiaru.
     */
    public long getId() { return id; }
    /**
     * Ustawia identyfikator rekordu podczas parsowania JSON.
     */
    public void setId(long id) { this.id = id; }
    /**
     * Zwraca znacznik czasu odczytu, aby można było śledzić moment pomiaru.
     */
    public String getTimestamp() { return timestamp; }
    /**
     * Ustawia znacznik czasu rekordu.
     */
    public void setTimestamp(String timestamp) { this.timestamp = timestamp; }
    /**
     * Zwraca lokalizację sensora, aby później grupować rekordy po miejscu.
     */
    public Location getLocation() { return location; }
    /**
     * Ustawia lokalizację rekordu na podstawie danych z API.
     */
    public void setLocation(Location location) { this.location = location; }
}
