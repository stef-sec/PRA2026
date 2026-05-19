package kafka.service;

import kafka.model.Root;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.InputStream;
import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class SensorDataClientTest {

    @Mock
    DataFetcher fetcher;

    @Test
    public void loadValidJson_returnsList() throws Exception {
        InputStream in = getClass().getResourceAsStream("/test-data-valid.json");
        when(fetcher.fetch("http://dummy")).thenReturn(in);

        SensorDataClient client = new SensorDataClient(fetcher);
        List<Root> list = client.readFromUrl("http://dummy");

        assertEquals(3, list.size());
        assertNotNull(list.get(0).getSensordatavalues());
    }

    @Test
    public void loadEmptyJson_returnsEmptyList() throws Exception {
        InputStream in = getClass().getResourceAsStream("/test-data-empty.json");
        when(fetcher.fetch("http://dummy")).thenReturn(in);

        SensorDataClient client = new SensorDataClient(fetcher);
        List<Root> list = client.readFromUrl("http://dummy");

        assertTrue(list.isEmpty());
    }

    @Test
    public void loadMalformedJson_throws() throws Exception {
        InputStream in = getClass().getResourceAsStream("/test-data-malformed.json");
        when(fetcher.fetch("http://dummy")).thenReturn(in);

        SensorDataClient client = new SensorDataClient(fetcher);
        assertThrows(Exception.class, () -> client.readFromUrl("http://dummy"));
    }

    @Test
    public void fetcherThrowsIOException_throws() throws Exception {
        when(fetcher.fetch("http://dummy")).thenThrow(new IOException("network"));

        SensorDataClient client = new SensorDataClient(fetcher);
        assertThrows(IOException.class, () -> client.readFromUrl("http://dummy"));
    }
}
