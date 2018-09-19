package progmatic.bookingmanager.booking;

import java.util.Comparator;

public class NameComparator implements Comparator<BookingInfo>{

    @Override
    public int compare(BookingInfo b1, BookingInfo b2) {
        return b1.getName().compareTo(b2.getName());
    }
    
}
