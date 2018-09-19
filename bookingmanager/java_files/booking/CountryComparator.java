package progmatic.bookingmanager.booking;

import java.util.Comparator;

public class CountryComparator implements Comparator<BookingInfo>{

    @Override
    public int compare(BookingInfo b1, BookingInfo b2) {
        return b1.getCountry().compareTo(b2.getCountry());
    }
    
}
