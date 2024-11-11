package com.example.lista2;

import androidx.annotation.NonNull;
import androidx.navigation.ActionOnlyNavDirections;
import androidx.navigation.NavDirections;

public class FragmentBDirections {
  private FragmentBDirections() {
  }

  @NonNull
  public static NavDirections actionFragmentBToFragmentC() {
    return new ActionOnlyNavDirections(R.id.action_fragmentB_to_fragmentC);
  }

  @NonNull
  public static NavDirections actionFragmentBToFragmentA() {
    return new ActionOnlyNavDirections(R.id.action_fragmentB_to_fragmentA);
  }
}
