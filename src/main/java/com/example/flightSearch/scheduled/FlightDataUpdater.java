package com.example.flightSearch.scheduled;

import com.example.flightSearch.model.Flights;
import com.example.flightSearch.service.AirportService;
import com.example.flightSearch.service.FlightService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class FlightDataUpdater{

    private final FlightService flightService;
    private final AirportService airportService;

    // Her gün saat 3'te çalışacak olan bir görev
    @Scheduled(cron = "0 0 3 * * ?")
    //@Scheduled(fixedRate = 10000)
    public void updateFlightsDataFromAPI() {
        List<Flights> fetchedFlights = callThirdPartyAPI();
        saveFlightsToDatabase(fetchedFlights);
    }

    private List<Flights> callThirdPartyAPI() {
        List<Flights> mockFlights = createMockFlights();
        return mockFlights;
    }

    private void saveFlightsToDatabase(List<Flights> flights) {
        for (Flights flight : flights) {
            flightService.createFlight(flight);
        }
    }

    private List<Flights> createMockFlights() {
        List<Flights> mockFlights = new ArrayList<>();
            for (int i = 1; i <= 10; i++) {
                for(int j = 1; j<=5; j++){
                    Flights flights = new Flights(
                            Integer.toUnsignedLong(i+j),
                            LocalDate.now(),
                            LocalDate.now().plusDays(2),
                            300,
                            airportService.getByAirportId(Integer.toUnsignedLong(j)),
                            airportService.getByAirportId(Integer.toUnsignedLong(i))
                    );
                    mockFlights.add(flights);
                }
            };
        return mockFlights;
    }
}
