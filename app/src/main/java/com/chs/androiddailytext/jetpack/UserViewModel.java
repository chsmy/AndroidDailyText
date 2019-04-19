package com.chs.androiddailytext.jetpack;

import android.app.Application;

import java.util.List;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

/**
 * @author chs
 * date：2019-04-19 14:24
 * des：
 */
public class UserViewModel extends AndroidViewModel {
    private LiveData<List<User>> mUsers;
    private UserRepository mRepository;

    public UserViewModel(Application application) {
        super(application);
        mRepository = new UserRepository(application);
        mUsers =  mRepository.getAllUser();
    }

    public LiveData<List<User>> getUsers(){
        return mUsers;
    }

    public void insertUser(User user){
        mRepository.insert(user);
    }

    public void deleteAll(){
        mRepository.deleteAll();
    }
    public void update(User user){
        mRepository.update(user);
    }
}
