# World Flags Quiz 🌎

An Android app intended for learning all the world's flags.

## Flags

For the first version of the app, flags from UN member states will be included. Source from [UN](https://www.un.org/en/about-us/member-states).
- [X] UN member state flags (193 flags, as of 2024)

## Definitions (WIP)

Continents are defined by geographical continent.

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

## Progress & roadmap

#### V1
- [X] Figma Design for the UI
- [X] Set up project
- [X] Add flags and metadata in json file (avoid relying on internet connection)
- [X] Implement UI
- [X] Option feature
- [X] Play feature
- [ ] Logo and app name

#### V2
- [ ] Improve UI Design in Figma
- [ ] Include non-UN flags
- [ ] Counter of number correct guessed vs. incorrect
- [ ] Implement localStorage for selected option
- [ ] Investigate: SQLite instead of json files?
- [ ] Include unit & integration tests
- [ ] Investigate free icons to use, [Androidicons](https://www.androidicons.com/)?

## Links

* Abbreviations of the world's continents: [here](https://planetarynames.wr.usgs.gov/Abbreviations) (`AF, AN, AS, EU, NA, OC, SA`)
* Android emulator bugfix: [here](https://stackoverflow.com/questions/42816127/waiting-for-target-device-to-come-online)
