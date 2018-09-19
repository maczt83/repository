package progmatic.bookingmanager.reservation;

import java.util.Comparator;

public class RoomTypeComparator implements Comparator<ReservationInfo>{

    @Override
    public int compare(ReservationInfo r1, ReservationInfo r2) {
        return r1.getRoomTypeName().compareTo(r2.getRoomTypeName());
    }
    
}
