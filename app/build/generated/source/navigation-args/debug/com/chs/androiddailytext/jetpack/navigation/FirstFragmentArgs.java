package com.chs.androiddailytext.jetpack.navigation;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.navigation.NavArgs;
import java.lang.IllegalArgumentException;
import java.lang.Object;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.HashMap;

public class FirstFragmentArgs implements NavArgs {
  private final HashMap arguments = new HashMap();

  private FirstFragmentArgs() {
  }

  private FirstFragmentArgs(HashMap argumentsMap) {
    this.arguments.putAll(argumentsMap);
  }

  @NonNull
  @SuppressWarnings("unchecked")
  public static FirstFragmentArgs fromBundle(@NonNull Bundle bundle) {
    FirstFragmentArgs __result = new FirstFragmentArgs();
    bundle.setClassLoader(FirstFragmentArgs.class.getClassLoader());
    if (bundle.containsKey("title")) {
      String title;
      title = bundle.getString("title");
      if (title == null) {
        throw new IllegalArgumentException("Argument \"title\" is marked as non-null but was passed a null value.");
      }
      __result.arguments.put("title", title);
    } else {
      __result.arguments.put("title", "i am title");
    }
    return __result;
  }

  @SuppressWarnings("unchecked")
  @NonNull
  public String getTitle() {
    return (String) arguments.get("title");
  }

  @SuppressWarnings("unchecked")
  @NonNull
  public Bundle toBundle() {
    Bundle __result = new Bundle();
    if (arguments.containsKey("title")) {
      String title = (String) arguments.get("title");
      __result.putString("title", title);
    } else {
      __result.putString("title", "i am title");
    }
    return __result;
  }

  @Override
  public boolean equals(Object object) {
    if (this == object) {
        return true;
    }
    if (object == null || getClass() != object.getClass()) {
        return false;
    }
    FirstFragmentArgs that = (FirstFragmentArgs) object;
    if (arguments.containsKey("title") != that.arguments.containsKey("title")) {
      return false;
    }
    if (getTitle() != null ? !getTitle().equals(that.getTitle()) : that.getTitle() != null) {
      return false;
    }
    return true;
  }

  @Override
  public int hashCode() {
    int result = 1;
    result = 31 * result + (getTitle() != null ? getTitle().hashCode() : 0);
    return result;
  }

  @Override
  public String toString() {
    return "FirstFragmentArgs{"
        + "title=" + getTitle()
        + "}";
  }

  public static class Builder {
    private final HashMap arguments = new HashMap();

    public Builder(FirstFragmentArgs original) {
      this.arguments.putAll(original.arguments);
    }

    public Builder() {
    }

    @NonNull
    public FirstFragmentArgs build() {
      FirstFragmentArgs result = new FirstFragmentArgs(arguments);
      return result;
    }

    @NonNull
    public Builder setTitle(@NonNull String title) {
      if (title == null) {
        throw new IllegalArgumentException("Argument \"title\" is marked as non-null but was passed a null value.");
      }
      this.arguments.put("title", title);
      return this;
    }

    @SuppressWarnings("unchecked")
    @NonNull
    public String getTitle() {
      return (String) arguments.get("title");
    }
  }
}
