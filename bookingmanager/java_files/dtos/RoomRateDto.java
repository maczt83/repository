package progmatic.bookingmanager.dtos;

import java.util.Date;
import progmatic.bookingmanager.databaseEntity.RoomRate;

public class RoomRateDto {
    private long id;
    private Date startDate;
    private Date endDate;
    private int rate;

    public RoomRateDto(RoomRate roomRate) {
        this.id = roomRate.getId();
        this.endDate = roomRate.getEndDate();
        this.startDate = roomRate.getStartDate();
        this.rate = roomRate.getRate();
    }

    public RoomRateDto() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }
}
