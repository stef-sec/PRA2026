package kafka.model;
public class Location{
    public String latitude;
    public String altitude;
    public int indoor;
    public int id;
    public int exact_location;
    public String country;
    public String longitude;
    /**
     * Zwraca szerokość geograficzną, żeby można było opisać miejsce pomiaru.
     */
    public String getLatitude() { return latitude; }
    /**
     * Ustawia szerokość geograficzną z danych wejściowych.
     */
    public void setLatitude(String latitude) { this.latitude = latitude; }
    /**
     * Zwraca wysokość nad poziomem morza, jeśli API ją podało.
     */
    public String getAltitude() { return altitude; }
    /**
     * Ustawia wysokość lokalizacji.
     */
    public void setAltitude(String altitude) { this.altitude = altitude; }
    /**
     * Zwraca informację, czy sensor działa wewnątrz budynku.
     */
    public int getIndoor() { return indoor; }
    /**
     * Ustawia flagę indoor.
     */
    public void setIndoor(int indoor) { this.indoor = indoor; }
    /**
     * Zwraca identyfikator lokalizacji.
     */
    public int getId() { return id; }
    /**
     * Ustawia identyfikator lokalizacji.
     */
    public void setId(int id) { this.id = id; }
    /**
     * Zwraca flagę dokładnej lokalizacji.
     */
    public int getExact_location() { return exact_location; }
    /**
     * Ustawia flagę dokładnej lokalizacji.
     */
    public void setExact_location(int exact_location) { this.exact_location = exact_location; }
    /**
     * Zwraca nazwę kraju, żeby dało się filtrować rekordy geograficznie.
     */
    public String getCountry() { return country; }
    /**
     * Ustawia nazwę kraju.
     */
    public void setCountry(String country) { this.country = country; }
    /**
     * Zwraca długość geograficzną, żeby pełny adres miejsca był kompletny.
     */
    public String getLongitude() { return longitude; }
    /**
     * Ustawia długość geograficzną z JSON-a.
     */
    public void setLongitude(String longitude) { this.longitude = longitude; }
}
