-- Create the database
CREATE DATABASE IF NOT EXISTS train_reservation_system;
USE train_reservation_system;

-- Table for Users
CREATE TABLE IF NOT EXISTS users (
    id VARCHAR(50) PRIMARY KEY,
    email VARCHAR(100) UNIQUE NOT NULL,
    password VARCHAR(100) NOT NULL,
    name VARCHAR(100) NOT NULL,
    mobile VARCHAR(15),
    payment_info VARCHAR(255),
    role VARCHAR(20) NOT NULL DEFAULT 'USER' -- e.g., 'USER', 'ADMIN'
);

-- Table for Trains
CREATE TABLE IF NOT EXISTS trains (
    id VARCHAR(50) PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    source VARCHAR(100) NOT NULL,
    destination VARCHAR(100) NOT NULL
);

-- Table for Seats Management
CREATE TABLE IF NOT EXISTS seats (
    train_id VARCHAR(50),
    class_type VARCHAR(50), -- Sleeper, 3A, 2A, 1A
    total_seats INT,
    available_seats INT,
    PRIMARY KEY (train_id, class_type),
    FOREIGN KEY (train_id) REFERENCES trains(id) ON DELETE CASCADE
);

-- Table for Schedule Management
CREATE TABLE IF NOT EXISTS schedules (
    id INT AUTO_INCREMENT PRIMARY KEY,
    train_id VARCHAR(50),
    departure_time VARCHAR(50),
    arrival_time VARCHAR(50),
    travel_date DATE,
    FOREIGN KEY (train_id) REFERENCES trains(id) ON DELETE CASCADE
);

-- Table for Tickets
CREATE TABLE IF NOT EXISTS tickets (
    ticket_id VARCHAR(100) PRIMARY KEY,
    user_id VARCHAR(50),
    train_id VARCHAR(50),
    quota VARCHAR(50),
    booking_date VARCHAR(50),
    travel_class VARCHAR(50),
    travel_time VARCHAR(50),
    price DOUBLE,
    tax DOUBLE,
    offers VARCHAR(255),
    status VARCHAR(20) DEFAULT 'BOOKED', -- BOOKED, CANCELLED
    FOREIGN KEY (user_id) REFERENCES users(id),
    FOREIGN KEY (train_id) REFERENCES trains(id)
);

-- Table for Payments
CREATE TABLE IF NOT EXISTS payments (
    txn_id VARCHAR(100) PRIMARY KEY,
    user_id VARCHAR(50),
    ticket_id VARCHAR(100),
    amount DOUBLE,
    method VARCHAR(50),
    details VARCHAR(255),
    FOREIGN KEY (user_id) REFERENCES users(id)
);

-- Table for Admin Notifications/Offers/Customer Service Details
CREATE TABLE IF NOT EXISTS app_details (
    detail_key VARCHAR(50) PRIMARY KEY,
    detail_value TEXT
);

-- Initial Data for Trains
INSERT IGNORE INTO trains VALUES ('1101', 'kangai', 'kovilpatti', 'chennai');
INSERT IGNORE INTO trains VALUES ('1102', 'cholai', 'Thanjavur', 'Tenkasi');
-- ... adding more from trains.txt if needed ...

-- Initial App Details
INSERT IGNORE INTO app_details VALUES ('offers', 'SAVE10: 10% off | FESTIVAL5: 5% off');
INSERT IGNORE INTO app_details VALUES ('customer_service', 'Phone: 1-800-TRAIN-TIX | Email: support@railway.com');
INSERT IGNORE INTO app_details VALUES ('privacy_policy', 'Your data is handled securely as per Railway norms.');
