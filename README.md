# World Flags Quiz 🌎

An Android app intended for learning all the world's flags.

## Definitions

States and territories are split into following types:

**Sovereign**
* `sovereign`
* `partly sovereign` (partially recognized state)

**Non-sovereign**
* `non-sovereign`
* `self-governing`
* `partly self-governing`
* `non-self governing` (e.g. uninhabited)
* `special municipality`
* `colony`
* `breakaway state`
* `sector` (Antarctica)

States and territories can also be divided into:
* `UN member` states/countries/territories
* `Non-UN member` states/countries/territories

## Tasks & roadmap

- [X] Set up project
- [X] Add UI
- [X] Create a json file for a few countries to test with and logic for reading json
- [X] Add logic for displaying countries and guessing
- [X] Have a counter that keeps track of how many you guessed and how many is left
- [X] Flags: add flags locally to application and display, [Flagpedia](https://flagpedia.net)?
- [ ] Investigate using json file for storing all countries or move to a database (local SQLite vs. remote MySQL vs. use existing API) 
- [X] Investigate free icons (icon manager or plugin to use?), [Androidicons](https://www.androidicons.com/)?
- [ ] Add all countries
- [ ] Add tracker for correct/incorrect?
- [ ] Feature: implement localStorage when changing options and store in phone
- [ ] Add unit tests and integration tests
- [ ] Refactor existing code
- [X] Add feature for categorizing continents
- [X] Design: improve UI and create a DesignSystem for this project
- [ ] Design: create logo for app project
- [ ] Come back to African non UN territories/states

### Flags progress
- [X] Europe flags
- [X] Europe flags (non-UN)
- [X] North America flags
- [X] North America flags (non-UN)
- [X] South America flags
- [X] South America flags (non-UN)
- [ ] Africa flags
- [ ] Africa flags (non-UN)
- [ ] Asia flags
- [ ] Africa flags (non-UN)
- [ ] Oceania flags
- [ ] Oceania flags (non-UN)
- [ ] Antarctica flags (misc)

### Stretch goals
- [ ] Add map and have flags as option, or country names
- [ ] Add feature for U.S. states and regions

## Links

* Abbreviations of the world's continents: [here](https://planetarynames.wr.usgs.gov/Abbreviations) (`AF, AN, AS, EU, NA, OC, SA`)
* Android emulator bugfix: [here](https://stackoverflow.com/questions/42816127/waiting-for-target-device-to-come-online)
