.PHONY: help up down build test clean

help:
	@echo "Available commands:"
	@echo "  make help       - Show this help message"
	@echo "  make up         - Start the application and database in detached mode"
	@echo "  make down       - Stop and remove containers"
	@echo "  make build      - Build the application without starting"
	@echo "  make test       - Run tests"
	@echo "  make clean      - Clean the project and remove target directory"
	@echo "  make logs       - Show application logs"
	@echo "  make db-shell   - Access PostgreSQL shell"

up:
	@echo "Starting containers..."
	docker-compose up -d
	@echo "Application running at http://localhost:8081"
	@echo "PostgreSQL running on port 5433"

down:
	@echo "Stopping and removing containers..."
	docker-compose down
	@echo "Containers stopped and removed"

build:
	@echo "Building application..."
	docker-compose build
	@echo "Build complete"

test:
	@echo "Running tests..."
	mvn test
	@echo "Tests completed"

clean:
	@echo "Cleaning project..."
	mvn clean
	@rm -rf target/
	@echo "Project cleaned"

logs:
	docker-compose logs -f app

db-shell:
	@echo "Connecting to PostgreSQL shell..."
	docker-compose exec db psql -U progressoft -d fxdeals

