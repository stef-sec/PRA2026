package kafka.model;
public class Sensor {
    public String pin;
    public int id;
    public SensorType sensor_type;
    /**
     * Zwraca pin sensora, czyli jego techniczny identyfikator w systemie.
     */
    public String getPin() { return pin; }
    /**
     * Ustawia pin sensora z danych wejściowych.
     */
    public void setPin(String pin) { this.pin = pin; }
    /**
     * Zwraca numer identyfikacyjny sensora.
     */
    public int getId() { return id; }
    /**
     * Ustawia numer identyfikacyjny sensora.
     */
    public void setId(int id) { this.id = id; }
    /**
     * Zwraca typ sensora, żeby wiedzieć, jaki model generował pomiar.
     */
    public SensorType getSensor_type() { return sensor_type; }
    /**
     * Ustawia typ sensora z odpowiedzi API.
     */
    public void setSensor_type(SensorType sensor_type) { this.sensor_type = sensor_type; }
}
