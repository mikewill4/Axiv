#Axiv
Axiv is an integrated health app that allows users to track their fitness and
diet improvements. Users can compete with their friends and earn rewards.
## Development Branches:
* mwilliams-dev
* dfox-dev
* fwiltman-dev
* dstevens-dev
## Icons:
Icons obtained from [Material Design](https://material.io/tools/icons/)
## Task list:
### Bottom navigation bar
- [x] Appropriate icons
- [x] Fragments for each screen
- [x] Adding swiping between screens
### My Health screen
- [x] Create radar chart for metrics
- [x] Create buttons (Health suggestions, Daily targets, Track progress)
- [x] Add scrolling
- [x] Add onclick to buttons -> open new activities
- [x] Remove radar chart show values
- [x] Change menu item icon to refresh symbol for refreshing chart
- [x] Fix scaling issues for radar chart on different screen sizes
### Health Suggestions screen
- [x] Create listview for displaying various health suggestions
- [x] Each item opens up a dialog box for more information
- [ ] Suggestions are styled towards improving a specific metric
### Daily Targets screen
- [x] Create progress bars for each daily target
- [ ] Provide ability to create new daily targets
### Track Progress screen
- [x] Line graph showing progress over a time period for a single metric 
- [ ] Add functionality for switching which metric is displayed
- [ ] Add functionality for switching time period displayed
### My Metrics screen
- [ ] Meals - manual entry
- [ ] Meals - search for previous meals
- [x] Meals - take picture
- [x] Meals - scan barcode
- [ ] Sleep - manual entry
- [ ] Steps - manual entry
- [x] Hydration - manual entry
- [ ] Exercise - manual entry
- [ ] Exercise - search for previous exercises
- [ ] Weight - manual entry
- [ ] Blood pressure - manual entry
### Competitions screen
- [x] Add featured competitions
- [x] Add private competitions
- [ ] Invites
- [ ] Add new users
- [ ] Create new competitions
### Rewards Screen
- [ ] Show current points accumulated
- [ ] Rewards sorted into categories
- [ ] Ability to filter by rewards you can actually afford
### Settings screen
- [ ] Add functionality to toggle public visibility of score
- [ ] Show connected devices
- [ ] Buttons for adding or selecting devices
- [ ] Switch to metric units
- [ ] Turn off notifications
### Start screen
- [x] Option to login
- [x] Option to register
- [x] Logo
### Login screen
- [x] Text entry for username and password
- [x] Forgot password button 
### Registration screen
- [x] Full name
- [x] Email and password
- [ ] Age, height, weight, gender
- [ ] Personalized fitness and diet questions
### Notifications
- [ ] Reminders to hit daily targets
- [ ] Health suggestion of the day
### Data
- [x] Create singleton class to hold data across app
### Style
- [ ] Pick primary colors for app
- [ ] Standardize button style
- [ ] Standardize font type and sizes
- [x] Create a logo
### Bugs
- [x] Main screens now cut off by nav bar
