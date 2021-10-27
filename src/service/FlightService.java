package service;

import dao.FlightDao;
import dto.FloghtDto;

import java.util.List;
import java.util.stream.Collectors;

public class FlightService {

    private static final FlightService INSTANCE = new FlightService();

    private FlightService (){}

    private final FlightDao flightDao = FlightDao.getInstance();
    public List<FloghtDto> findAll (){
        return flightDao.findAll().stream()
                .map(flight -> new  FloghtDto(
                        flight.getId(),
                        """
                                %s - %s - %s
                                """.formatted(flight.getDepartureAirportCode(), flight.getArrivalAirportCode(), flight.getStatus())
                ))
                .collect(Collectors.toList());
    }

    public static FlightService getInstance() {
        return INSTANCE;
    }
}
