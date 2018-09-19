package progmatic.bookingmanager.booking;

import java.util.Comparator;

public class EmailComparator implements Comparator<BookingInfo> {

    @Override
    public int compare(BookingInfo b1, BookingInfo b2) {
        return b1.getEmailAddress().compareTo(b2.getEmailAddress());
    }

}
