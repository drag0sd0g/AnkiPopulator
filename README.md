# AnkiPopulator

AnkiPopulator is a Java tool for automating the population of Anki decks with Japanese vocabulary, kanji, and expressions. It supports various file formats and integrates with Anki via its HTTP API. This tool is meant to help anyone study for their JLPT N1 exam.

## Features

- Parse and import vocabulary and kanji from multiple sources
- Add notes to Anki using customizable templates
- Read-only commands for deck management
- Easily extensible with new parsers and templates

## Folder Structure

- `src/main/java/com/dragos/anki/`: Main Java source code
- `src/main/resources/`: Vocabulary and kanji files, templates, configuration

## Prerequisites

- Java 8 or higher
- Gradle
- **Anki must be running locally with the AnkiConnect add-on enabled**

## Getting Started

### Build

```sh
./gradlew build
```

### Run

Example for running the main classes:

```sh
java -cp build/libs/AnkiPopulator.jar com.dragos.anki.AddAllJPNotesMain
```

Replace `AddAllJPNotesMain` with any of the following as needed:

- `AddKanjiNoteCommandsMain`
- `AddVocabularyNoteCommandsMain`
- `ReadOnlyCommandsMain`

## Usage

- To add all Japanese notes: `AddAllJPNotesMain`
- To add kanji notes: `AddKanjiNoteCommandsMain`
- To add vocabulary notes: `AddVocabularyNoteCommandsMain`
- For read-only commands: `ReadOnlyCommandsMain`

## Configuration

- Edit templates in `src/main/resources/templates/`
- Manage deck names in `src/main/resources/readonly/deckNames.json`
- Place vocabulary and kanji files in `src/main/resources/`

## Extending

To add new parsers or support new file formats, extend the classes in `src/main/java/com/dragos/anki/parser/`.

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

## Contact

**Dragos** - [@drag0sd0g](https://github.com/drag0sd0g)
