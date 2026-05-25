-- DEMO USERS (password is "password")
INSERT INTO users (id, username, password_hash, email) VALUES
(1, 'admin', '$2a$10$URlV0sV8JgU2g5EwR6u1ju6nO77T0qBqkSVH8bH8z7nqYFZy8e5uq', 'admin@example.com')
ON DUPLICATE KEY UPDATE email=VALUES(email);

INSERT INTO users (id, username, password_hash, email) VALUES
(2, 'jay', '$2a$10$URlV0sV8JgU2g5EwR6u1ju6nO77T0qBqkSVH8bH8z7nqYFZy8e5uq', 'jay@example.com')
ON DUPLICATE KEY UPDATE email=VALUES(email);

INSERT INTO users (id, username, password_hash, email) VALUES
(3, 'aman', '$2a$10$URlV0sV8JgU2g5EwR6u1ju6nO77T0qBqkSVH8bH8z7nqYFZy8e5uq', 'aman@example.com')
ON DUPLICATE KEY UPDATE email=VALUES(email);

INSERT INTO users (id, username, password_hash, email) VALUES
(4, 'hitanshu', '$2a$10$URlV0sV8JgU2g5EwR6u1ju6nO77T0qBqkSVH8bH8z7nqYFZy8e5uq', 'hitanshu@example.com')
ON DUPLICATE KEY UPDATE email=VALUES(email);

-- ACCOUNTS
INSERT INTO account (id, account_number, type, balance, owner_id) VALUES
(100, 'CHK-001', 'CHECKING', 1500.00, 2),
(101, 'SAV-001', 'SAVINGS', 3200.00, 2),
(200, 'CHK-002', 'CHECKING', 900.00, 3),
(300, 'CHK-003', 'CHECKING', 1200.00, 4)
ON DUPLICATE KEY UPDATE balance=VALUES(balance);

-- PAYEES
INSERT INTO payee (id, name, account_number, reference, owner_id) VALUES
(1, 'Electric Co', 'ELEC-001', 'ACC-123', 2),
(2, 'Water Utility', 'WATR-001', 'ACC-456', 2)
ON DUPLICATE KEY UPDATE reference=VALUES(reference);

-- ALERT PREFS
INSERT INTO alert_preference (id, user_id, low_balance_enabled, low_balance_threshold, large_tx_enabled, large_tx_threshold, bill_reminder_enabled, channel)
VALUES (1, 2, true, 100.00, true, 500.00, true, 'EMAIL')
ON DUPLICATE KEY UPDATE channel=VALUES(channel);
