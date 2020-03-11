package com.chs.androiddailytext.jetpack.navigation;

import androidx.annotation.NonNull;
import androidx.navigation.ActionOnlyNavDirections;
import androidx.navigation.NavDirections;
import com.chs.androiddailytext.R;

public class SecondFragmentDirections {
  private SecondFragmentDirections() {
  }

  @NonNull
  public static NavDirections actionSecondFragmentToThirdFragment() {
    return new ActionOnlyNavDirections(R.id.action_secondFragment_to_thirdFragment);
  }
}
