package com.now.fitness.nowfitnessui.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.now.fitness.nowfitnessui.Model.Database;
import com.now.fitness.nowfitnessui.Object.UserProfile;
import com.now.fitness.nowfitnessui.R;

import java.util.List;
import java.util.regex.Pattern;

/**
 * This class is the User Profile Activity in which user will input their detials to be able to use the application
 * @author Maycor Gerilla, Romeric Heredia
 **/
public class UserProfileActivity extends AppCompatActivity {

    static private final String TAG = "NOWFitness-UI";
    private EditText mFnameTextView;
    private EditText mLnameTextView;
    private EditText mHeightTextView;
    private EditText mWeightTextView;
    private RadioGroup mRadioGroup;
    private String firstname;
    private String lastname;
    private String gender;
    private String userId;
    private Database mDb;
    private String stringdouble = "";
    private TextView textView;

    int duration = Toast.LENGTH_LONG;

    /**
     * Creates the view for UserProfileActivity
     * @param savedInstanceState Bundle
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);

        //create and open database
        mDb = new Database(this);
        mDb.open();

        //populate fields if user data exists
        //get values from MainActivity
        firstname = getIntent().getStringExtra("firstname");
        lastname = getIntent().getStringExtra("lastname");
        userId = getIntent().getStringExtra("userId");

        //get value of editText and RadioGroup
        mFnameTextView = (EditText) findViewById(R.id.editText_FirstName);
        mLnameTextView = (EditText) findViewById(R.id.editText_LastName);
        mHeightTextView = (EditText) findViewById(R.id.editText_Height);
        mWeightTextView = (EditText) findViewById(R.id.editText_Weight);
        mRadioGroup = (RadioGroup) findViewById(R.id.radioGroupGender);

        if(firstname != null||lastname != null){
            try {
                List<UserProfile> usr = Database.mUserDAL.findById(Integer.parseInt(userId));
                UserProfile user = usr.get(0);

                mFnameTextView.setText(user.getFirstname());
                mLnameTextView.setText(user.getLastname());

                stringdouble = Double.toString(user.getHeight());
                Log.i(TAG,"USER PROFILE ACTIVITY: height = "+stringdouble);
                mHeightTextView.setText(stringdouble);
                stringdouble = Double.toString(user.getWeight());
                Log.i(TAG,"USER PROFILE ACTIVITY: weight = "+stringdouble);
                mWeightTextView.setText(stringdouble);

                gender = user.getGender();


               switch(gender){
                    case "Female": mRadioGroup.check(R.id.radioButtonFemale);
                        break;
                    case "Male": mRadioGroup.check(R.id.radioButtonMale);
                        break;
                    case "Other": mRadioGroup.check(R.id.radioButtonOther);
                        break;
                }
            }catch (Exception ex){
                Toast.makeText(this, R.string.prompt_error, duration).show();
                Log.e(TAG, "error: "+ex.getMessage());
            }
        }
    }

    /**
     *  Retrieves the user input from the View
     *  @param view View
     */

    public void onClick_UserProfileDone(View view) {
        int flag = 0;
        String regexStr = "^[1-9]\\d*(\\.\\d+)?$";
        //get value of editText
        mFnameTextView = (EditText) findViewById(R.id.editText_FirstName);
        mLnameTextView = (EditText) findViewById(R.id.editText_LastName);
        mHeightTextView = (EditText) findViewById(R.id.editText_Height);
        mWeightTextView = (EditText) findViewById(R.id.editText_Weight);
        mRadioGroup = (RadioGroup) findViewById(R.id.radioGroupGender);

        //perform validation for required fields firstname and lastname
        if( mFnameTextView.getText().toString().length() == 0 ) {
            mFnameTextView.setError("First name is required!");
            flag++;
        }else if(!Pattern.matches("[a-zA-Z]+", mFnameTextView.getText().toString())) {
            mFnameTextView.setError("Incorrect input!");
            flag++;
        }
        if( mLnameTextView.getText().toString().length() == 0 ) {
            mLnameTextView.setError("Last name is required!");
            flag++;
        }else if(!Pattern.matches("[a-zA-Z]+", mLnameTextView.getText().toString())) {
            mLnameTextView.setError("Incorrect input!");
            flag++;
        }

        textView = (TextView) findViewById(R.id.textView_Gender);
        if(mRadioGroup.getCheckedRadioButtonId()<0){
            textView.setError("Please a gender");
            flag++;
        }

        //perform validation for height and weight input
        try {
            double h = Double.parseDouble(mHeightTextView.getText().toString());
        } catch (NumberFormatException e) {
           flag++;
            mHeightTextView.setError("Incorrect input!");
        }

        try {
            double w = Double.parseDouble(mWeightTextView.getText().toString());
        } catch (NumberFormatException e) {
            flag++;
            mWeightTextView.setError("Incorrect input!");
        }


        //Passed the validation
        if(flag == 0) {
        //store data in entity
        UserProfile user = new UserProfile();
        user.setFirstname(mFnameTextView.getText().toString());
        user.setLastname(mLnameTextView.getText().toString());
        user.setWeight(Double.parseDouble(mWeightTextView.getText().toString()));
        user.setHeight(Double.parseDouble(mHeightTextView.getText().toString()));
        user.setGender(gender);


        try{
            //call DAO object
            //check if user already exists or not
           if(userId!=null){
                boolean isUpdated = Database.mUserDAL.updateUser(user);

                if(isUpdated){
                    Toast.makeText(this, R.string.prompt_success_update, duration).show();
                }

            }else{
               boolean isInserted = Database.mUserDAL.insertUser(user);
               if(isInserted){
                   Toast.makeText(this, R.string.prompt_success_insert, duration).show();
               }
               List<UserProfile> usr = Database.mUserDAL.findByName(user.getFirstname());
               UserProfile usrObj = usr.get(0);
                firstname = usrObj.getFirstname();
                lastname = usrObj.getLastname();
                userId = String.valueOf(usrObj.getUserId());
            }
        }catch (Exception ex){
            Toast.makeText(this, R.string.prompt_error, duration).show();
        }

        Intent intent = new Intent(UserProfileActivity.this, MainActivity.class);
        intent.putExtra("firstname", firstname);
        intent.putExtra("lastname", lastname);
        intent.putExtra("userId", userId);


            startActivity(intent);
            finish();
            mDb.close();
        }
    }

    /**
     * Tags the radio button checked
     * @param view View
     */
    public void onRadioButtonClicked(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch(view.getId()) {
            case R.id.radioButtonFemale:
                if (checked)
                    gender = "Female";
                    break;
            case R.id.radioButtonMale:
                if (checked)
                    gender = "Male";
                    break;
            case R.id.radioButtonOther:
                if (checked)
                    gender = "Other";
                    break;
        }
    }
}
