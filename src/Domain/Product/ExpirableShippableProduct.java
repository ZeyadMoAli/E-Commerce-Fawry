package Domain.Product;

import Domain.Interfaces.IExpirable;
import Domain.Interfaces.IShippable;

import java.time.LocalDate;

public class ExpirableShippableProduct extends Product implements IExpirable, IShippable {
    private LocalDate expirationDate;

    public ExpirableShippableProduct(String name, double price, int quantity, double weight, LocalDate expirationDate) {
        super(name, price, quantity, weight);
        setExpirationDate(expirationDate);
    }

    @Override
    public boolean isExpired() {
        return expirationDate.isBefore(LocalDate.now());
    }

    @Override
    public void setExpirationDate(LocalDate date) {
        this.expirationDate = date;
    }

    @Override
    public LocalDate getExpirationDate() {
        return expirationDate;
    }

    @Override
    public boolean isAvailable() {
        return super.isAvailable() && !isExpired();
    }
}
