# AnkiPopulator

[![CI Build](https://github.com/drag0sd0g/AnkiPopulator/workflows/CI%20Build/badge.svg)](https://github.com/drag0sd0g/AnkiPopulator/actions/workflows/ci.yml)
[![Code Coverage](https://github.com/drag0sd0g/AnkiPopulator/workflows/Code%20Coverage/badge.svg)](https://github.com/drag0sd0g/AnkiPopulator/actions/workflows/coverage.yml)
[![Dependency Check](https://github.com/drag0sd0g/AnkiPopulator/workflows/Dependency%20Check/badge.svg)](https://github.com/drag0sd0g/AnkiPopulator/actions/workflows/dependency-check.yml)
[![Java Version](https://img.shields.io/badge/Java-21-blue.svg)](https://www.oracle.com/java/technologies/downloads/#java21)
[![Gradle Version](https://img.shields.io/badge/Gradle-8.11.1-blue.svg)](https://gradle.org/)
[![License](https://img.shields.io/badge/License-MIT-green.svg)](LICENSE)

AnkiPopulator is a modern Java 21 tool for automating the population of Anki decks with Japanese vocabulary, kanji, and expressions. It supports various file formats and integrates with Anki via its HTTP API. This tool is designed to help anyone study for their JLPT N1 exam.

## Features

- 🎯 Parse and import vocabulary and kanji from multiple sources
- 📝 Add notes to Anki using customizable templates
- 🔍 Read-only commands for deck management
- 🚀 Modern CLI powered by picocli
- 🐳 Docker support for local testing
- ✅ Comprehensive unit and integration tests
- 🏗️ SOLID principles architecture
- 📦 Latest dependency versions

## Architecture

The project follows SOLID principles with clear separation of concerns:
- **Service Layer**: `AnkiService` interface for HTTP operations
- **Parser Layer**: Extensible parsers for different file formats
- **Configuration**: Centralized configuration management
- **CLI**: Command-line interface with subcommands

## Prerequisites

- **Java 21** or higher
- **Gradle 8.11.1** (included via wrapper)
- **Anki** with AnkiConnect add-on enabled (or use Docker setup)

## Getting Started

### Build

```sh
./gradlew build
```

### Run with CLI

The application provides a unified CLI with multiple subcommands:

```sh
# Show help
./gradlew run --args="--help"

# Add all Japanese notes
./gradlew run --args="add-all-jp"

# Add vocabulary notes
./gradlew run --args="add-vocabulary"

# Add kanji notes
./gradlew run --args="add-kanji"

# Execute read-only commands
./gradlew run --args="read-only"
```

### Using Docker for Local Testing

Start a containerized Anki server:

```sh
docker-compose up -d
```

This will start Anki server on port 8765.

### Legacy Main Classes

For backward compatibility, the original main classes are still available:

```sh
java -cp build/libs/AnkiPopulator-1.0-SNAPSHOT.jar com.dragos.anki.AddAllJPNotesMain
```

## Testing

### Run All Tests (Unit + Integration)

```sh
./gradlew test
```

This runs both unit and integration tests. Integration tests use testcontainers and require Docker to be available.

### Run with Coverage

```sh
./gradlew test jacocoTestReport
```

Coverage reports are generated in `build/reports/jacoco/test/html/index.html`

## Configuration

- **Deck Names**: Configured in `AnkiConfiguration.java`
- **Templates**: Edit templates in `src/main/resources/templates/`
- **Vocabulary Files**: Place files in `src/main/resources/`

## Extending

To add new parsers or support new file formats:

1. Implement the `VocabularyFileParser` interface
2. Extend `AbstractVocabularyFileParser` for common functionality
3. Create a new command class in `com.dragos.anki.commands`
4. Register the command in `AnkiPopulatorCLI`

## Development

### Check for Dependency Updates

```sh
./gradlew dependencyUpdates
```

### Build Distribution

```sh
./gradlew installDist
```

The distribution will be available in `build/install/AnkiPopulator/`

## CI/CD

The project uses GitHub Actions for:
- **CI Build**: Automated builds and tests on every push
- **Code Coverage**: Coverage reports with Jacoco
- **Dependency Check**: Weekly dependency update checks

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

## Contact

**Dragos** - [@drag0sd0g](https://github.com/drag0sd0g)

