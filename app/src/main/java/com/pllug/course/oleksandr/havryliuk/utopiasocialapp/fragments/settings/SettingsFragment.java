package com.pllug.course.oleksandr.havryliuk.utopiasocialapp.fragments.settings;

import android.os.Bundle;

import android.support.annotation.Nullable;
import com.pllug.course.oleksandr.havryliuk.utopiasocialapp.R;
import com.takisoft.fix.support.v7.preference.PreferenceFragmentCompat;

public class SettingsFragment extends PreferenceFragmentCompat {

    @Override
    public void onCreatePreferencesFix(@Nullable Bundle savedInstanceState, String rootKey) {
        setPreferencesFromResource(R.xml.pref, rootKey);
    }

}