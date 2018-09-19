package progmatic.bookingmanager.booking;

import java.util.Comparator;

public class ResNumberComparator implements Comparator<BookingInfo>{

    @Override
    public int compare(BookingInfo b1, BookingInfo b2) {
        return (int) (b1.getNumOfRes() - b2.getNumOfRes());
    }
    
}
