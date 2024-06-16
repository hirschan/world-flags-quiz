# World Flags Quiz 🌎

An Android app intended for learning all the world's flags.

## Flags

For the first version of the app, flags from UN member states will be included. Source from [UN](https://www.un.org/en/about-us/member-states).
- [X] UN member state flags (193 flags, as of 2024)

## Definitions

Continents are defined by geographical continent, learn more [here](https://en.wikipedia.org/wiki/Continent).

States/countries/territories are divided into:
* `UN member` states
* `Non-UN member` states

## Acknowledgements

- The flag on the app icon in this project is from [Gitlab B.V.](https://gitlab.com/gitlab-org/gitlab-svgs/-/tree/main) and under the MIT License.

## Progress & roadmap

#### V1
- [X] Figma Design for the UI
- [X] Set up project
- [X] Add flags and metadata in json file (avoid relying on internet connection)
- [X] Implement UI
- [X] Option feature
- [X] Play feature
- [X] App icon

#### V2
- [ ] Bug: exit screen when finished, no delay
- [ ] Improve UI Design in Figma
- [ ] Include non-UN flags
- [ ] Counter of number correct guessed vs. incorrect
- [ ] Implement localStorage for selected option
- [ ] Investigate: SQLite instead of json files?
- [ ] Include unit & integration tests
- [ ] Light & dark mode (stretch goal)
- [ ] Crop flags in same size?
- [ ] Investigate free icons to use, [Androidicons](https://www.androidicons.com/)?

## Links

* Abbreviations of the world's continents: [here](https://planetarynames.wr.usgs.gov/Abbreviations) (`AF, AN, AS, EU, NA, OC, SA`)
* Android Studio emulator bug fix: [here](https://stackoverflow.com/questions/42816127/waiting-for-target-device-to-come-online)
* Android Studio disable "show logcat automatically": [here](https://stackoverflow.com/questions/76118961/how-to-prevent-android-studio-to-automatically-switch-to-run-tab-when-i-start)
