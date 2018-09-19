package progmatic.bookingmanager.reservation;

import java.util.Comparator;

public class NameComparator implements Comparator<ReservationInfo>{

    @Override
    public int compare(ReservationInfo r1, ReservationInfo r2) {
        return r1.getName().compareTo(r2.getName());
    }
    
}
