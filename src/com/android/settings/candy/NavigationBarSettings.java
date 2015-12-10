package com.android.settings.candy;

import com.android.internal.logging.MetricsLogger;

import android.os.Bundle;
import android.preference.ListPreference;
import android.preference.Preference;
import android.preference.Preference.OnPreferenceChangeListener;
import android.preference.SwitchPreference;
import android.provider.Settings;

import com.android.settings.R;
import com.android.settings.candy.SystemSettingSwitchPreference;
import com.android.settings.SettingsPreferenceFragment;

public class NavigationBarSettings extends SettingsPreferenceFragment implements
        OnPreferenceChangeListener {

    private static final String KILL_APP_LONGPRESS_BACK = "kill_app_longpress_back";
    private static final String DOUBLE_TAP_SLEEP_NAVBAR = "double_tap_sleep_navbar";

    private SwitchPreference mKillAppLongPressBack;
    private SwitchPreference mDoubleTapSleepNarbar;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        addPreferencesFromResource(R.xml.candy_settings_navigation);

        // kill-app long press back
        mKillAppLongPressBack = (SwitchPreference) findPreference(KILL_APP_LONGPRESS_BACK);
        mKillAppLongPressBack.setOnPreferenceChangeListener(this);
        int killAppLongPressBack = Settings.Secure.getInt(getContentResolver(),
                KILL_APP_LONGPRESS_BACK, 0);
        mKillAppLongPressBack.setChecked(killAppLongPressBack != 0);

        // Double tap 2 sleep on navi
        mDoubleTapSleepNarbar = (SwitchPreference) findPreference(DOUBLE_TAP_SLEEP_NAVBAR);
        mDoubleTapSleepNarbar.setOnPreferenceChangeListener(this);
        int doubleTapSleepNarbar = Settings.Secure.getInt(getContentResolver(),
                DOUBLE_TAP_SLEEP_NAVBAR, 0);
        mDoubleTapSleepNarbar.setChecked(doubleTapSleepNarbar != 0);
    }

    @Override
    public boolean onPreferenceChange(Preference preference, Object objValue) {
        if (preference == mKillAppLongPressBack) {
            boolean value = (Boolean) objValue;
            Settings.Secure.putInt(getContentResolver(), KILL_APP_LONGPRESS_BACK,
                    value ? 1 : 0);
        } else if (preference == mDoubleTapSleepNarbar) {
            boolean value = (Boolean) objValue;
            Settings.Secure.putInt(getContentResolver(), DOUBLE_TAP_SLEEP_NAVBAR,
                    value ? 1 : 0);
        }
            return true;
    }

    @Override
    protected int getMetricsCategory() {
        return MetricsLogger.APPLICATION;
    }
}
