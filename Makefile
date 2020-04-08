help:
	@echo hello world

run: up
	. ./setenv.sh && SPRING_PROFILES_ACTIVE=dev ./gradlew build bootRun

debug: up
	SPRING_PROFILES_ACTIVE=dev ./gradlew build bootRun --debug-jvm

run-test:
	SPRING_PROFILES_ACTIVE=test ./gradlew build bootRun

debug-test:
	SPRING_PROFILES_ACTIVE=test ./gradlew build bootRun --debug-jvm

unit-test:
	./gradlew --rerun-tasks test

dbclient:
	docker-compose exec database /dbclient.sh

up:
	docker-compose up -d

down:
	docker-compose down

pull:
	docker-compose pull

ps:
	docker-compose ps

logs:
	docker-compose logs -f