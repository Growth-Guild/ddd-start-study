package edu.study.order.domain;

public enum OrderState {
    PAYMENT_WAITING {
        @Override
        public boolean isAfterDelivered() {
            return false;
        }
    },
    PREPARING {
        @Override
        public boolean isAfterDelivered() {
            return false;
        }
    },
    SHIPPED,
    DELIVERING,
    DELIVERY_COMPLETED,
    CANCELED;

    public boolean isAfterDelivered() {
        return true;
    }
}
