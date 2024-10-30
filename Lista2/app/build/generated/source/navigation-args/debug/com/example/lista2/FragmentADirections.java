package com.example.lista2;

import androidx.annotation.NonNull;
import androidx.navigation.ActionOnlyNavDirections;
import androidx.navigation.NavDirections;

public class FragmentADirections {
  private FragmentADirections() {
  }

  @NonNull
  public static NavDirections toFragmentB() {
    return new ActionOnlyNavDirections(R.id.to_fragmentB);
  }
}
