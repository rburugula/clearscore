# Clear Score

Libraries used
Retrofit - for API calls
lifecycle-extensions:2.2.0 - For View Model
Dagger - For dependency injection
Mocito related dependencies

- I am using MVVM architecture to develop this application.
- I have architected the code in a way that there is seperation of concerns, have created separate packages for Data,
 repository, DI etc..
- As per the UI, to show the donut I am using an external library futured.donut, as I felt this was very close
to what was expected from this demo.
- I have added unit tests to the repository layer and the viewmodel.
- As of now I have only covered a basic scenario to handle if there is an exception while performing an api call.
this can be expanded to get the actual error responses based on the actual api implementation.
- I have added an addition button on the UI to refresh the data which will perform an api call. This is only to
show the data changing when go offline/online on the device (Internet connectivity)
- I have added kotlinx-coroutines-test for support with running unit tests..

