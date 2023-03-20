package pojos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class BookingResponsePojo {

    private Integer bookingig;
    private BookingPojo booking;

    public BookingResponsePojo() {
    }

    public BookingResponsePojo(Integer bookingig, BookingPojo booking) {
        this.bookingig = bookingig;
        this.booking = booking;
    }

    public Integer getBookingig() {
        return bookingig;
    }

    public void setBookingig(Integer bookingig) {
        this.bookingig = bookingig;
    }

    public BookingPojo getBooking() {
        return booking;
    }

    public void setBooking(BookingPojo booking) {
        this.booking = booking;
    }

    @Override
    public String toString() {
        return "BookingResponsePojo{" +
                "bookingig=" + bookingig +
                ", booking=" + booking +
                '}';
    }
}
