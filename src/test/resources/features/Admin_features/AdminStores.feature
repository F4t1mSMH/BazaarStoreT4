Feature: View all stores in the system as an Admin.

  Background:
    Given user goes to homepage
    When user enters email "admin@sda.com" and password "Password.12345"
    And user clicks login button
    Then admin should be logged in successfully

  @Admin1 @smoke
  Scenario: Verify Admin can view all stores list
    When Navigate to Stores page.
    Then All existing stores are displayed correctly with Name, Description, Location, and Admins.


  @Admin2 @smoke
  Scenario Outline: Verify adding a new store with valid data
    When Navigate to Stores page.
    And Navigate to Add Store page
    And Enter valid Name, Description, Location, and Admins :
      | Field       | Value           |
      | Name        | <Name>          |
      | Description | <Description>   |
      | Location    | <Location>      |
      | Admin       | <Admin>         |
    And Click Save
    Then The store is successfully added to the list, and a confirmation message is displayed.

    Examples:
      | Name        | Description         | Location | Admin          |
      |       Extra       | Electronics store   | Riyadh   | Store Manager |


  @Admin3
  Scenario Outline: Verify error message for missing required fields
    When Navigate to Stores page.
    And Navigate to Add Store page

    And Leave one or more required fields empty:
      | Field       | Value           |
      | Name        | <Name>          |
      | Description | <Description>   |
      | Location    | <Location>      |
      | Admin       | <Admin>         |
    And Click Save
    Then Error message is shown for missing required fields; store is not added

    Examples:
      | Name        | Description         | Location | Admin          |
      | Test Store   | Electronics store  | Riyadh   |                |
      |              | Electronics store   | Riyadh   | Store Manager |
      | Test Store   |                    | Riyadh   | Store Manager |
      | Test Store   | Electronics store  |          | Store Manager |

  @Admin4 @smoke
  Scenario: Verify successful update of store details
    When Navigate to Stores page.
    And Click Edit for an existing store.
    And Enter valid Name, Location :
    And Click Save
    Then The store is successfully update to the list, and a confirmation message is displayed.

  @Admin5 @smoke
  Scenario: Verify updated data reflects in the list after saving.
    When Navigate to Stores page.
    And Check the updated store.

  @Admin6 @smoke
  Scenario: Verify error for missing/invalid inputs during edit.
    When Navigate to Stores page.
    And Click Edit for an updated store.
    And Try Leave one or more required fields empty:
      | Name       | Description       | Location | Admin |
      |            | Electronics store | Riyadh   | Store Manager |
      | Jeddah Tec store |                   | Riyadh   | Store Manager |
      |  Jeddah Tec store | Electronics store |          | Store Manager |
      | Jeddah Tec store  | Electronics store |   Riyadh |               |

  @Admin7 @smoke
  Scenario: Verify delete confirmation dialog appears and Verify canceling deletion keeps the store intact.
    When Navigate to Stores page.
    And Click Delete on a store.
    Then Confirmation dialog appears asking for confirmation.
    And Click Cancel in confirmation dialog.
    Then Confirm store remains in the list.

  @Admin8 @smoke
  Scenario: Verify delete confirmation dialog appears and store is deleted after confirming deletion.
    When Navigate to Stores page.
    And Click Delete on a store.
    Then Confirmation dialog appears asking for confirmation.
    And Click Yes, delete it on a store.
    Then Confirm deletion, and store is removed from UI  list; success message shown.












