import com.ly.fn.admin.modules.order.domain.Airport;
import com.ly.fn.admin.modules.order.domain.MallOrder;
import com.ly.fn.admin.modules.order.service.AirportService;
import com.ly.fn.admin.modules.order.service.OrderService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:config/spring-context.xml"})
public class OrderTest {

    @Resource
    private OrderService orderService;
    @Resource
    private AirportService airportService;

    @Test
    public void getOrderById() {
        MallOrder mallOrder = orderService.getById(188L);
        System.out.println(mallOrder.getOrderCode());
    }

    @Test
    public void getAirport() {
        Airport airport = airportService.getById(1L);
        System.out.println(airport.getAirportName());
    }

    @Test
    public void addAirport() {
        Airport airport = new Airport();
        airport.setAirportCode("SZWJJC");
        airport.setAirportName("苏州吴江机场");
        airport.setFirstWord("S");
        airport = airportService.addAirport(airport);
        System.out.println(airport.getId());
    }

    @Test
    public void updateAirport() {
        Airport airport = airportService.getById(231L);
        airport.setFirstWord("B");
        airport.setAirportName("苏州吴江国际机场");
        airport.setAirportCode("SZWJGJJC");
        airportService.updateAirport(airport);
    }
}
