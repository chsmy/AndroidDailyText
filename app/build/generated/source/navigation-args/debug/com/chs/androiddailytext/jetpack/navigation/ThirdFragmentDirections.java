package com.chs.androiddailytext.jetpack.navigation;

import androidx.annotation.NonNull;
import androidx.navigation.ActionOnlyNavDirections;
import androidx.navigation.NavDirections;
import com.chs.androiddailytext.R;

public class ThirdFragmentDirections {
  private ThirdFragmentDirections() {
  }

  @NonNull
  public static NavDirections actionThirdFragmentToFirstFragment() {
    return new ActionOnlyNavDirections(R.id.action_thirdFragment_to_firstFragment);
  }

  @NonNull
  public static NavDirections actionThirdFragmentToSecondFragment() {
    return new ActionOnlyNavDirections(R.id.action_thirdFragment_to_secondFragment);
  }
}
