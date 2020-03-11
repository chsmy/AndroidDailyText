package com.chs.androiddailytext.jetpack.navigation;

import androidx.annotation.NonNull;
import androidx.navigation.ActionOnlyNavDirections;
import androidx.navigation.NavDirections;
import com.chs.androiddailytext.R;

public class FirstFragmentDirections {
  private FirstFragmentDirections() {
  }

  @NonNull
  public static NavDirections actionFirstFragmentToSecondFragment() {
    return new ActionOnlyNavDirections(R.id.action_firstFragment_to_secondFragment);
  }
}
