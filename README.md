# AndroidEssentials1

- Go to Firebase and in your App Console page Go to Authentication and enable Google Login.
- Follow all Steps correctly as mentioned in firebase docs for google sign-in
- Add the given dependencies and sync gradle
- Add SHA-1 fingerprint to your project in Firebase console
- Add Google Sign in button in UI and Get reference for the same (Normal Button wont Work)
- Just copy paste everything from Documentation and try to solve all errors
- Copy Paste Configure Google Sign in code to onCreate() and fix imports
- Add the signIn() method and create an integer for the Auth as RC_SIGN_IN=1
- Create GoogleApiClient object that is used in the signIn() and reference as :

//Most important part for google sign in: 

mGoogleApiClient=new GoogleApiClient.Builder(getApplicationContext())
                .enableAutoManage(this, new GoogleApiClient.OnConnectionFailedListener() {
                    @Override
                    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
                        Toast.makeText(MainActivity.this, "Connection Error", Toast.LENGTH_LONG).show();
                    }
                })
                .addApi(Auth.GOOGLE_SIGN_IN_API,gso)   
                .build();

- Now add onActivityResult Method and fix all errors
- Now add the firebaseAuthWithGoogle Method 
- Add FirebaseAuthentication object mAuth and reference as 
        mAuth=FirebaseAuth.getInstance();
- Add the TAG String to anything
- This completes Google Sign in, but to redirect to another page :

- Create the page with a logout button where redirection is done.
- Create an FirebaseAuth.AuthStateListener and reference as:

mAuthListener=new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                if(firebaseAuth.getCurrentUser()!=null)
                {
                    startActivity(new Intent(MainActivity.this,AccountActivity.class));
                }
            }
        };

- Override onStart and add mAuth.addAuthStateListener(mAuthListener);
- In the Logout Activity add FirebaseAuth object and in the onClick of button add FirebaseAuthobject.signOut();
- To redirect after signing out add AuthStateListener as above and check currentuser == null
- Override onStart similarly.
- For getting all details of a user Just create a FirebaseUser object and reference it as:  
		user=mAuth.getCurrentUser();
- All methods to fetch details for the user is now available.
