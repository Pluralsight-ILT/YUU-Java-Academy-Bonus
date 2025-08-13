-- Create database
CREATE DATABASE IF NOT EXISTS book_recommendations;
USE book_recommendations;

-- Create books table
CREATE TABLE books (
    id INT PRIMARY KEY AUTO_INCREMENT,
    title VARCHAR(255) NOT NULL,
    author VARCHAR(255) NOT NULL,
    genre VARCHAR(100) NOT NULL,
    rating DECIMAL(2,1) DEFAULT 0.0,
    year_published INT,
    description TEXT,
    is_available BOOLEAN DEFAULT true
);

-- Insert sample books (mind-opening and entertaining)
INSERT INTO books (title, author, genre, rating, year_published, description, is_available) VALUES
('Breakfast of Champions', 'Kurt Vonnegut', 'Fiction', 4.3, 1973, 'A satirical novel about American society and free will', true),
('The Island of Dr. Moreau', 'H.G. Wells', 'Fiction', 4.1, 1896, 'A classic science fiction tale about the boundaries of science and ethics', true),
('A Brief History of Time', 'Stephen Hawking', 'Physics', 4.7, 1988, 'An exploration of cosmology and the nature of time and space', true),
('Cosmos', 'Carl Sagan', 'Science', 4.8, 1980, 'A journey through the universe and our place within it', true),
('The Elegant Universe', 'Brian Greene', 'Physics', 4.5, 1999, 'An introduction to superstring theory and the nature of reality', true),
('Sapiens', 'Yuval Noah Harari', 'Philosophy', 4.6, 2011, 'A brief history of humankind and how we became the dominant species', true),
('The Code Book', 'Simon Singh', 'Mathematics', 4.4, 1999, 'The science of secrecy from ancient Egypt to quantum cryptography', true),
('The Selfish Gene', 'Richard Dawkins', 'Science', 4.2, 1976, 'A revolutionary approach to understanding evolution and behavior', true),
('Flatland', 'Edwin Abbott Abbott', 'Mathematics', 4.0, 1884, 'A mathematical fantasy about dimensions and social hierarchy', true),
('Parallel Worlds', 'Michio Kaku', 'Physics', 4.3, 2004, 'A journey through creation, higher dimensions, and the future of the cosmos', true),
('The Immortal Life of Henrietta Lacks', 'Rebecca Skloot', 'Science', 4.7, 2010, 'The true story behind one of the most important medical breakthroughs', true),
('Ender\'s Game', 'Orson Scott Card', 'Fiction', 4.5, 1985, 'A thought-provoking science fiction novel about strategy, empathy, and growing up', true);

-- Verify data insertion
SELECT COUNT(*) as total_books FROM books;
SELECT genre, COUNT(*) as count FROM books GROUP BY genre;