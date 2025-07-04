package Domain.Product;

import java.time.LocalDate;

public interface IExpirable {
 boolean isExpired();
 void setExpirationDate(LocalDate date);
 LocalDate getExpirationDate();

}
