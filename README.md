# List of todo's

1. Due to the time, I ve skipped couple of architecture patterns. Mistakes: prefs inside view model directly, no LiveData/StateFlow for getting numbers method ect.
2. I should add the RecyclerView with all data which I stored in my database and shared prefs.
3. So I would add the RecyclerView implemetation with UseCase like "GetAllEventsInfoUseCase" which will return the list of data. Settings this data in StateFlow and subscribe inside the acitvity
4. Create separate koin modules for diffetent instances
5. Move input field validation class to some validator class

# What done

1. Saving data into DB with Room
2. Handling BOOT event via BroadcastReciver
3. Handling event Cancel btn via BroadcastReciver
4. Scheduling task via WorkManager for every 15 minutes, also added imidiate displaying of notification when event triggered
5. Set up max cancellation attempts via EditText and Button
6. Increase time interval of notification if current "cancel" btn clicked > allowed number
   
