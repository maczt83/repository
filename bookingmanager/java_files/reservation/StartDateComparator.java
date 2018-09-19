package progmatic.bookingmanager.reservation;

import java.util.Comparator;

public class StartDateComparator implements Comparator<ReservationInfo> {

    @Override
    public int compare(ReservationInfo r1, ReservationInfo r2) {
        return r1.getStartDate().compareTo(r2.getStartDate());
    }

}
