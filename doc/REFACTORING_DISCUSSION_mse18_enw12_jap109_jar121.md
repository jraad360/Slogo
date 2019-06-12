# NavBar
We decided to refactor the NavBar class to eliminate unnecessary ChangeListeners and break these into methods.

# CommandHistory
We decided to modify the CommandHistory to use a list of strings instead of a file. This eliminated the need for file reading and IOException catching, which streamlines our code, stabilizes it, and provides greater paths to expansion for displaying more workspaces.

# Backend: General
Rather than storing several instance variables and getter methods in various backend classes, we reorganized our code to utilize a map. This helps keep greater track of various turtle related properties.
