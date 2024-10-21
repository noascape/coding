Feature: Weather Forecast

  Scenario Outline: User requests weather for a specific location
    Given a location with latitude <latitude> and longitude <longitude>
    When the user requests the weather for the day
    Then the daily forecast should be displayed

    Examples:
      | latitude | longitude |
      | 52.52    | 13.405    |
      | 49.4885  | 9.7704    |
