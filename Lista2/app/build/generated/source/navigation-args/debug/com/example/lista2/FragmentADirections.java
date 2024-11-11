package com.example.lista2;

import androidx.annotation.NonNull;
import androidx.navigation.ActionOnlyNavDirections;
import androidx.navigation.NavDirections;

public class FragmentADirections {
  private FragmentADirections() {
  }

  @NonNull
  public static NavDirections actionFragmentAToFragmentB() {
    return new ActionOnlyNavDirections(R.id.action_fragmentA_to_fragmentB);
  }

  @NonNull
  public static NavDirections actionFragmentAToFragmentC() {
    return new ActionOnlyNavDirections(R.id.action_fragmentA_to_fragmentC);
  }
}
