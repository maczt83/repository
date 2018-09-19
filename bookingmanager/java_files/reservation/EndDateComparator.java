package progmatic.bookingmanager.reservation;

import java.util.Comparator;

public class EndDateComparator implements Comparator<ReservationInfo>{

    @Override
    public int compare(ReservationInfo r1, ReservationInfo r2) {
        return r1.getEndDate().compareTo(r2.getEndDate());
    }
    
}
