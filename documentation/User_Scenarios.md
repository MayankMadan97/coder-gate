# User Scenarios

## Features

* Easily installable on a GitHub repository
* Get a code quality report by automated analysis
* Set threshold values for code quality metrics
* Alert contributer when code crosses threshold during pull request
* Ability to reject pull request if code crosses threshold values
* See how the code quality of your project varies per commit

## Scenarios
1. ### **User installs the GitHub app to their repository**
    When the user installs the app to their repository, they are prompted to authorize the app to have both read and write permissions. The read permission is required to read the contents of the repository to be scanned. The write permissions are required to install ```application-designite.yml``` which is used to configure code scanning.  

2. ### **User logs in to application's website**
    The user can log in to the website using their GitHub credentials. Once authenticated, they are sent to the dashboard. The dashboard contains important information about the user's repositories. The user can see:
    * Total number of repositories handled by the application
    * Total number of contributors
    * Health of each repository  
  
3. ### **User wants to set threshold values for a repository**
    When a user clicks on a repository, they are presented with the thresold values of various code quality metrics. The threshold form is divided into 4 parts:
    * Overall
    * Architecure Smells
    * Design Smells
    * Implementation Smells  
  
    Each metric can be assigned a threshold value by using the slider below it. The user cannot set values greater than the maximum set by the slider. A checkbox is available which allows the user to decide if a pull requests needs to be rejected if the code quality passes threshold values. Once the values are set, the user can press the ```Submit``` button to set those values.

4. ### **User wants to see insights for a repository**
    When a user clicks on a repository, they are presented with the Threshold tab and Insights tab. When the user clicks on the Insights tab, they are presented with the following charts:
    * Code Smell Densities Vs Number of Occurrences
    * Types Of Smell Vs Number of Occurrences
    * Implementation, Design, Architecture Smell Density Vs Timestamp

5. ### **Contributer raises a pull request**
    When a contributer raises a pull request, the application performs a code quality analysis and checks if it breaks the already set threshold values. If it passes the check, the application does not intervene and the final decision for the pull request getting accepted depends on the owner of the repository. It creates an issue and assigns to the contributor with the with the analysis and threshold values it broke.  
    However, the repository owner has the ability to enable a feature that will reject a pull request automatically if it breaks threshold values.

[**Go back to README.md**](../README.md)
