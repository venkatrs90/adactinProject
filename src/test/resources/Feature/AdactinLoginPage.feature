Feature: Adactin Hotel Login webpage verification

Scenario: To validate adactin hotel 
Given Browserlaunch and loginpage
When User should be in room details
When User should enter personal details
And User should cancel booking
Then Adactin hotel again loginpage