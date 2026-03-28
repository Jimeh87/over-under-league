# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## What This Is

An NBA Over/Under League tracker — a full-stack Spring Boot + Angular app that fetches live NBA standings (via REST APIs and Selenium web scraping), compares them against CSV-defined over/under limits, and scores user picks. Deployed to DigitalOcean via CircleCI on every push to `master`.

## Commands

### Backend (Maven)
```bash
./mvnw clean install          # Full build (compiles Java + builds Angular, runs tests)
./mvnw clean install -DskipTests  # Build without tests
./mvnw spring-boot:run        # Run backend on :8080
./mvnw test                   # Run all Java tests
./mvnw test -Dtest=PaceCalculatorTest                          # Run single test class
./mvnw test -Dtest=PaceCalculatorTest#testCalculate_...        # Run single test method
./mvnw verify                 # Run unit + integration tests (Surefire + Failsafe)
```

### Frontend (Angular)
```bash
npm start       # Dev server on :4200, proxied to backend at :8080
npm run build   # Production build (output: dist/overunderleague/browser)
npm test        # Karma + Jasmine tests (Chrome)
npm run watch   # Watch mode build
```

### Docker (local)
```bash
./mvnw clean install && docker run -p 8080:8080 --rm -it $(docker build -q .)
```

## Architecture

**Data flow:** Angular → `GET /api/user-scores` → `UserStandingsController` → `UserStandingsService` combines NBA standings + CSV over/under limits + pace calculation → JSON response rendered in standings table.

### Backend packages (`src/main/java/com/overunderleague/`)
- `core/overunder` — loads per-team over/under values from `over-under-2025.csv`
- `core/standing` — NBA standings model
- `core/overunderpace` — pace calculation (how far ahead/behind each team is vs their limit)
- `core/userscore` — user picks from `user-picks-2025.csv` and scoring logic
- `integration/nbaclient`, `nbaclient2` — two REST clients for NBA official data
- `integration/nbascraper` — Selenium-based scraper (fallback standings source)
- `integration/tsnscraper` — TSN sports scraper (in progress, see untracked files)
- `controller` — REST endpoints (`/api/user-scores`, `/api/over-under-pace`)
- `configuration` — Spring config including `NbaStandingsProviderConfiguration` (selects which data source to use)
- `util/WebDriverFactory` — creates Chrome/Chromium WebDriver for scraping

### Frontend (`src/main/angular/`)
Angular 18 app bootstrapped via `main.ts`. Main component is `app/standings` which calls `UserStandingsService`. The `hot-cold-icon` component renders visual over/under pace indicators.

### Key config
- `src/main/resources/application.properties` — NBA API URLs, scraper headless mode (`app.tsnscraper.headless`), cache settings
- `proxy.conf.json` — dev proxy routes `/api/**` to `localhost:8080`

### Build integration
`frontend-maven-plugin` in `pom.xml` downloads Node v22.9.0 and runs `npm install` + `npm run build` as part of `./mvnw clean install`. Angular output lands in `target/classes/static/`, served by Spring Boot.

### Docker
The `Dockerfile` installs Chromium + ChromeDriver on Alpine JRE 21 so Selenium scraping works in the container.

## Deployment

Push to `master` → CircleCI builds + tests → pushes Docker image to Docker Hub → **manually** deploy from DigitalOcean dashboard.
