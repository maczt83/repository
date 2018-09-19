package progmatic.bookingmanager.reservation;

import java.util.Comparator;

public class EmailComparator implements Comparator<ReservationInfo>{

    @Override
    public int compare(ReservationInfo r1, ReservationInfo r2) {
        return r1.getEmailAddress().compareTo(r2.getEmailAddress());
    }
    
}
