ASSIGNMENT 7 README

MODIFICATIONS TO PREVIOUS DESIGN:

For this assignment we had to modify our old View implementations (primarily guiview). Previously we put our loop to run the animation in the view itself. This resulted in our view being tightly coupled to our model, as we had to call all of our command move methods from the view. We moved our animation loop to the controller for this assignment for easier, cleaner dispatch between the model and view. Additionally, we made a method in the model to execute all of the command methods so that is no longer tied to anything other than the model. 

CONTROLLER DESIGN

Our design of the controller mostly mimics the example MVC code we went over in class. As the only real user interaction is through the respective JFrame buttons and panels, the listeners and actions should be implemented in the controller. We instantiate all of the ActionListeners in the Controller and pass them to our view. 

We also decided to somewhat use double dispatch to allow our controller to run without too much casting. The main method initially calls our controller's runView method, which dispatches to a method in the views. This view method (called dispatchRun()) will simply run the necessary methods itself in the case of SVG and Text, as they need no additional help from the controller. However, in the visual and hybrid views cases they will call the respective controller method to run loops for the animations so the model can be transformed as necessary. We cast the controller's view to the respective view in each method, as only those view types will end up calling those methods.


DISCLAIMER: 
Something is wrong with our JPanel layouts. It appears to be drawing 2 panels. This makes resizing the window go haywire. We are unsure why this is happening and we can't fix it. Thank you, and our apologies.