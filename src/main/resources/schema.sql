DROP TABLE IF EXISTS ReservationLogs;
DROP TABLE IF EXISTS RecurringRules;
DROP TABLE IF EXISTS Reservation;

CREATE TABLE IF NOT EXISTS Reservation (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    start_date DATE NOT NULL,
    end_date DATE NOT NULL
);

CREATE TABLE IF NOT EXISTS RecurringRules (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    reservation_id BIGINT NOT NULL,
    weekday VARCHAR(255) NOT NULL,  -- 또는 다른 형태로 변경 가능
    FOREIGN KEY (reservation_id) REFERENCES Reservation(id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS ReservationLogs (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    reservation_id BIGINT NOT NULL,
    performed_date DATE NOT NULL,
    notes TEXT,  -- NULL 허용
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (reservation_id) REFERENCES Reservation(id) ON DELETE CASCADE
);