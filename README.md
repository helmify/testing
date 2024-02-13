## Testing Repository for helm-start

Here we maintain Github Actions Pipelines for: 
- building and deploying our own Spring Initializr instance

Also, we maintain code here for testing spring and quarkus related functionality of helm-start.
To that end, there are two apps, one built with spring boot, one built with quarkus. 
The apps try to read and write from stores connected to them via helm charts provided by helm-start.
This is supposed to serve as an End-to-End testing approach, ensuring quality of the helm charts provided by helm-start.
