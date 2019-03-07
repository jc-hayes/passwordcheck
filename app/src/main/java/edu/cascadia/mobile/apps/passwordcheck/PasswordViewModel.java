package edu.cascadia.mobile.apps.passwordcheck;
// Adapted from https://www.bignerdranch.com/blog/two-way-data-binding-on-android-observing-your-view-with-xml/

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;


public class PasswordViewModel extends ViewModel {
    public MutableLiveData<String> password;
    public MutableLiveData<String> passwordQuality;

    public PasswordViewModel() {
        password = new MutableLiveData<>();
        passwordQuality = new MutableLiveData<>();
    }

    public String getPassword() {
        return password.getValue();
    }

    public String getPasswordQuality() {
        if (password == null || getPassword().equals("")) {
            return "Enter a password";
        } else if (password.getValue().equals("password")) {
            return "Very bad";
        } else if (password.getValue().length() < 6) {
            return "Short";
        } else {
            return "Okay";
        }
    }

    public void setPassword(String password) {
        if(!this.password.getValue().equals(password)) {
            this.password.setValue(password);
            //notifyPropertyChanged(BR.passwordQuality);
            //notifyPropertyChanged(BR.password);
        }
    }

    public void onPasswordTextChanged(CharSequence charSequence) {
        setPassword(charSequence.toString());
        getPasswordQuality();
    }
}
