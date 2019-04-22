# Axiv
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
- [x] Add new metrics
- [x] Link with static AppData class
- [x] Add button with description for chart
### Health Suggestions screen
- [x] Create listview for displaying various health suggestions
- [x] Each item opens up a dialog box for more information
- [x] Style suggestions
### Daily Targets screen
- [x] Create progress bars for each daily target
- [x] Provide ability to create new daily targets
- [x] Rename to health goals
### Track Progress screen
- [x] Line graph showing progress over a time period for a single metric 
- [x] Improve style of graph
- [x] Remove items from legend that aren't being displayed
- [x] Update graph based on spinner selection
- [x] Add functionality for switching which metric is displayed
### Set Reminders screen
- [x] Add unimplemented toast
### My Metrics screen
- [x] Meals - manual entry
- [x] Meals - search for previous meals
- [x] Meals - take picture
- [x] Meals - scan barcode
- [x] Sleep - manual entry
- [x] Steps - manual entry
- [x] Hydration - manual entry
- [x] Exercise - manual entry
- [x] Exercise - search for previous exercises
- [x] Weight - manual entry
- [x] Blood pressure - manual entry
### Competitions screen
- [x] Add featured competitions
- [x] Add private competitions
- [x] Invites
- [x] Add new users
- [x] Create new competitions
### Rewards Screen
- [x] Show current points accumulated
- [x] Show purchased rewards
- [ ] Dynamically update points
- [x] Rewards sorted into categories
### Settings screen
- [ ] Ability to log out
- [x] Add functionality to toggle public visibility of score
- [ ] Show connected devices
- [ ] Buttons for adding or selecting devices
- [x] Switch to metric units
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
- [x] Age, height, weight, gender
- [x] Personalized questions associated with each metric
### Notifications
- [ ] Reminders to hit daily targets
- [ ] Health suggestion of the day
### Data
- [x] Create singleton class to hold data across app
### Style
- [x] Pick primary colors for app
- [x] Standardize button style
- [x] Standardize padding/margins
- [x] Make titles in action bar and bottom nav bar consistent
- [x] Standardize font type and sizes
- [x] Create a logo
- [x] Use meaningful text throughout app
### Bugs
- [x] Main screens now cut off by nav bar
- [x] Compete pages back button goes back to My Health
- [x] Enter sleep end date cut off
