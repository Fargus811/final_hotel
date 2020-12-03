package by.sergeev.hotel.service.impl;

import by.sergeev.hotel.dao.DaoFactory;
import by.sergeev.hotel.dao.impl.BookingDaoImpl;
import org.mockito.Mockito;
import org.powermock.core.classloader.annotations.PowerMockIgnore;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.testng.PowerMockObjectFactory;
import org.testng.IObjectFactory;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.ObjectFactory;
import org.testng.annotations.Test;

@PowerMockIgnore({"com.sun.org.apache.xerces.*", "javax.xml.*", "org.xml.*", "org.w3c.*",
        "com.sun.org.apache.xalan.*", "javax.management.*"})
@PrepareForTest({BookingDaoImpl.class})
public class BookingServiceImplTest {

    BookingDaoImpl bookingDao;

    @ObjectFactory
    public IObjectFactory setObjectFactory() {
        return new PowerMockObjectFactory();
    }

    @BeforeMethod
    public void setUp() {
        bookingDao = Mockito.mock(BookingDaoImpl.class);
 //       Mockito.when(DaoFactory.daoFactory).thenReturn(bookingDao);
    }

    @Test
    public void testFindBookingById() {
    }

    @Test
    public void testFindBookingsByUserId() {
    }

    @Test
    public void testChangeBookingStatusById() {
    }

    @Test
    public void testCreateBooking() {
    }

    @Test
    public void testAddRoomToBooking() {
    }

    @Test
    public void testGetTotalCostOfBooking() {
    }

    @Test
    public void testFindAll() {
    }

    @Test
    public void testPayForBooking() {
    }

    @Test
    public void testDeleteRoomFromBooking() {
    }
}
